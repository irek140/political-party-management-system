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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ConstituencyController {

    @Autowired
    ConstituencyRepository constituencyRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    UserRepository userRepository;

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PostMapping(value = "/constituency/create/{name}", consumes = {"application/json"}, produces = {"application/json"})
//    public ResponseEntity<?> createConstituency(@RequestBody Region region, @PathVariable("name") String constituencyName) {
//        System.out.println("Create new constituency...");
//        constituencyRepository.save(new Constituency(constituencyName, region, region.getName()));
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/constituency/create/{name}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createConstituency(@RequestBody Region region, @PathVariable("name") String constituencyName) {
        System.out.println("Create new constituency...");
        constituencyRepository.save(new Constituency(constituencyName, region.getName()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value="/constituency/list/by-region/{region-name}")
    public List<Constituency> getConstituencyByRegion(@PathVariable("region-name") String regionName) {
        System.out.println("Constituencies with region = " + regionName);
        List<Constituency> constituencies;
        constituencies = constituencyRepository.findByParent(regionName);
        return constituencies;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/constituency/delete/{id}")
    public ResponseEntity<String> deleteConstituency(@PathVariable("id") long id) {
        Optional<Constituency> constituencyData = constituencyRepository.findById(id);
        Constituency c = constituencyData.get();
        List<User> userList = userRepository.findAll();
        for(User user : userList) {
            if(user.getConstituency().equals(c.getName())) {
                user.setConstituency("-");
                userRepository.save(user);
            }
        }
        constituencyRepository.deleteById(id);
        return new ResponseEntity<>("Constituency has been deleted!", HttpStatus.OK);
    }

    @PutMapping("/constituency/update/{id}")
    public ResponseEntity<Constituency> updateConstituency(@PathVariable("id") long id, @RequestBody Constituency constituency) {
        System.out.println("Update Constituency with id = " + id + "...");
        System.out.println(constituency.getName() + constituency.getParent());
        Optional<Constituency> constituencyData = constituencyRepository.findById(id);
        if (constituencyData.isPresent()) {
            List<User> userList = userRepository.findAll();
            for(User user : userList) {
                System.out.println("Okręg usera = " + user.getConstituency());
                System.out.println("Nazwa okręgu do zaktualizowania= " + constituencyData.get().getName());
                System.out.println("Nowa nazwa okręgu = " + constituency.getName());
                if(user.getConstituency().equals(constituencyData.get().getName())) {
                    user.setConstituency(constituency.getName());
                    userRepository.save(user);
                }
            }

            if(constituencyData.get().getParent() != constituency.getParent()) {
                for(User user : userList) {
                    System.out.println("Region usera = " + user.getRegion());
                    System.out.println("Nazwa regionu do zaktualizowania= " + constituencyData.get().getParent());
                    System.out.println("Nowa nazwa regionu = " + constituency.getParent());

                        user.setRegion(constituency.getParent());
                        userRepository.save(user);
                }
            }

            Constituency _constituency= constituencyData.get();
            _constituency.setName(constituency.getName());
            _constituency.setParent(constituency.getParent());

            return new ResponseEntity<>(constituencyRepository.save(_constituency), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value="/constituency/list")
    public List<Constituency> getAllConstituencies() {
        List<Constituency> allConstituencies = new ArrayList<>();
        constituencyRepository.findAll().forEach(allConstituencies::add);
        return allConstituencies;
    }
}
