package pl.kieltyka.politicalpartymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.kieltyka.politicalpartymanagementsystem.model.Constituency;
import pl.kieltyka.politicalpartymanagementsystem.model.Region;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.repository.ConstituencyRepository;
import pl.kieltyka.politicalpartymanagementsystem.repository.RegionRepository;
import pl.kieltyka.politicalpartymanagementsystem.repository.UserRepository;

import java.util.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConstituencyRepository constituencyRepository;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/region/create", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createRegion(@RequestBody String regionName) {
        System.out.println("Create new region...");
        regionRepository.save(new Region(regionName));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/region/delete/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable("id") long id) {
        System.out.println("Delete region with ID = " + id + "...");
        Optional<Region> regionData = regionRepository.findById(id);
        Region r = regionData.get();
        List<User> userList = userRepository.findAll();
        for(User user : userList) {
            if(user.getRegion().equals(r.getName())) {
                user.setRegion("-");
                user.setConstituency("-");
                userRepository.save(user);
            }
        }

        regionRepository.deleteById(id);
        return new ResponseEntity<>("Region has been deleted!", HttpStatus.OK);
    }

    @PutMapping("/region/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable("id") long id, @RequestBody Region region) {
        System.out.println("Update region with id = " + id + "...");
        Optional<Region> regionData = regionRepository.findById(id);
        if (regionData.isPresent()) {
            List<User> userList = userRepository.findAll();
            for(User user : userList) {
                System.out.println("Region usera = " + user.getRegion());
                System.out.println("Nazwa regionu do zaktualizowania= " + regionData.get().getName());
                System.out.println("Nowa nazwa regionu = " + region.getName());
                if(user.getRegion().equals(regionData.get().getName())) {
                    user.setRegion(region.getName());
                    userRepository.save(user);
                }
            }

            List<Constituency> constituencies = constituencyRepository.findByParent(regionData.get().getName());
            for(Constituency c : constituencies) {
                c.setParent(region.getName());
                constituencyRepository.save(c);
            }

            Region _region = regionData.get();
            _region.setName(region.getName());
            return new ResponseEntity<>(regionRepository.save(_region), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/region/list")
    public List<Region> getRegions() {
        List<Region> allRegions = new ArrayList<>();
        regionRepository.findAll().forEach(allRegions::add);
        return allRegions;
    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PostMapping(value = "/constituency/create/{name}", consumes = {"application/json"}, produces = {"application/json"})
//    public ResponseEntity<?> createConstituency(@RequestBody Region region, @PathVariable("name") String constituencyName) {
//        System.out.println("Create new constituency...");
//
//        Region reg = regionRepository.findByName(region.getName());
//        List<String> constituenciesList = reg.getConstituencies();
//        constituenciesList.add(constituencyName);
//        reg.setConstituencies(constituenciesList);
//        regionRepository.save(reg);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
