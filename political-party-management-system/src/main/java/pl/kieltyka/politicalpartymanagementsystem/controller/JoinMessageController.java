package pl.kieltyka.politicalpartymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.kieltyka.politicalpartymanagementsystem.model.Authority;
import pl.kieltyka.politicalpartymanagementsystem.model.JoinMessage;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.repository.AuthorityRepository;
import pl.kieltyka.politicalpartymanagementsystem.repository.JoinMessageRepository;
import pl.kieltyka.politicalpartymanagementsystem.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class JoinMessageController {

    @Autowired
    JoinMessageRepository joinMessageRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthorityRepository authRepo;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @GetMapping(value="/join/give-requests")
    public List<JoinMessage> getAllRequests() {
        System.out.println("Get all Requests...");
        List<JoinMessage> allRequests;
        List<JoinMessage> requests = new ArrayList<>();
        allRequests = joinMessageRepository.findAll();
        for(JoinMessage request : allRequests) {
            if(request.isMember() == false) {
                requests.add(request);
            }
        }
        return requests;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping(value = "/join", consumes = {"application/json"}, produces = {"application/json"})
    public JoinMessage createRequest(@RequestBody JoinMessage joinMessage) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JoinMessage _joinMessage = new JoinMessage(joinMessage.getContent(), (User) userDetails);

            if(!((User) userDetails).isSendRequest()) {
                System.out.println("Ten User nie jest jeszcze członkiem partii");
                if(joinMessage instanceof JoinMessage) {
                    System.out.println("Join to the party...");
                    _joinMessage = joinMessageRepository.save(new JoinMessage(joinMessage.getContent(), (User)userDetails));

                    Optional<User> userData = userRepo.findById(((User) userDetails).getId());
                    if(userData.isPresent()) {
                        User _user = userData.get();
                        _user.setSendRequest(true);
                        userRepo.save(_user);
                    }
                    return _joinMessage;
                }
            }
        return new JoinMessage("Ten user jest już członkiem", (User) userDetails);
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/join/has")
    public JoinMessage hasJoinMessage() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<JoinMessage> _joinMessage = joinMessageRepository.findByAuthor((User) userDetails);
        if(_joinMessage.isPresent()) {
            return _joinMessage.get();
        }
        return null;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/join/user")
    public User getJoinMessageInfo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) userDetails;
    }

    @PreAuthorize("hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @PutMapping("/join/member/{id}")
    public ResponseEntity<User> becomeMember(@PathVariable("id") long id, @RequestBody JoinMessage joinMessage) {
        System.out.println("User " + joinMessage.getAuthor().getUsername() + " is member!");
        JoinMessage joinM = joinMessage;
        User u = userRepo.findByUsername(joinMessage.getAuthor().getUsername());
        if (u != null) {
            User _user = u;

            System.out.println(joinMessage.getContent());
            System.out.println(joinMessage.getAuthor());
            System.out.println(joinMessage.isMember());

            joinM.setMember(true);
            //Dodanie roli członka partii do użytkownika
            List<Authority> authorities = new ArrayList<>();
            authorities.add(authRepo.findByAuthority("ROLE_USER"));
            authorities.add(authRepo.findByAuthority("ROLE_MEMBER"));
            _user.setMember(true);
            _user.setAuthorities(authorities);

            joinMessageRepository.save(joinM);

            return new ResponseEntity<>(userRepo.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @DeleteMapping("/join/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        System.out.println("Delete JoinMessage with ID = " + id + "...");
        JoinMessage joinMessage = joinMessageRepository.findAllById(id);
        Optional<User> userData = userRepo.findById(joinMessage.getAuthor().getId());
        if(userData.isPresent()) {
            User _user = userData.get();
            _user.setSendRequest(false);
            userRepo.save(_user);
        }
        joinMessageRepository.deleteById(id);
        return new ResponseEntity<>("JoinMessage has been deleted!", HttpStatus.OK);
    }
}
