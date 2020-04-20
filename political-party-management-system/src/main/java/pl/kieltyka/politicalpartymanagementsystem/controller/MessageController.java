package pl.kieltyka.politicalpartymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.kieltyka.politicalpartymanagementsystem.model.Message;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.repository.MessageRepository;
import pl.kieltyka.politicalpartymanagementsystem.repository.UserRepository;
import pl.kieltyka.politicalpartymanagementsystem.services.EmailService;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping(value = "/messages/create", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createMessage(@RequestBody Message message) {
        System.out.println("Create new message...");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> Addressees = message.getAddressees();
        for(User user : Addressees) {
            Message _message = messageRepository.save(
                    new Message(message.getTitle(), message.getContent(), (User)userDetails, user, message.getAddressees()));
            if(user.getEmail() != null) {
                emailService.sendMessageNotification(user);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/messages/delete/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id") long id) {
        System.out.println("Delete Message with ID = " + id + "...");
        messageRepository.deleteById(id);
        return new ResponseEntity<>("Message has been deleted!", HttpStatus.OK);
    }

//    @GetMapping("/messages/{id}")
//    public List<Message> getMessages(@PathVariable("id") long id) {
//        System.out.println("Get all messages from user with id = " + id + "...");
//        List<Message> messages;
//        Optional<User> user = userRepository.findById(id);
//        messages = messageRepository.findByAddressees(user);
//        return messages;
//    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value="/messages", consumes = {"application/json"}, produces = {"application/json"})
    public List<Message> getMyMessages() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Get all messages from user: " + userDetails.getUsername() + "...");
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<Message> messages;
        messages = messageRepository.findByRecipient(user);
        Collections.reverse(messages);
        return messages;
    }

}
