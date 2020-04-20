package pl.kieltyka.politicalpartymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.kieltyka.politicalpartymanagementsystem.model.Authority;
import pl.kieltyka.politicalpartymanagementsystem.model.Message;
import pl.kieltyka.politicalpartymanagementsystem.model.Task;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.repository.TaskRepository;
import pl.kieltyka.politicalpartymanagementsystem.repository.UserRepository;
import pl.kieltyka.politicalpartymanagementsystem.services.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;


    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @PostMapping(value = "/tasks/create", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        System.out.println("Create new task...");
        System.out.println("Data rozpoczęcia: " + task.getStartdate() + "; data zakończenia: " + task.getEnddate());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> Addressees = task.getAddressees();
        for(User user : Addressees) {
            Task _task = taskRepository.save(
                    new Task(task.getTaskTitle(), task.getTaskContent(), (User)userDetails, user, task.getAddressees(), task.getStartdate(), task.getEnddate(), task.getReport()));
            if(user.getEmail() != null) {
                emailService.sendTaskNotification(user);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @DeleteMapping("/tasks/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") long id) {
        System.out.println("Delete Task with ID = " + id + "...");
        taskRepository.deleteById(id);
        return new ResponseEntity<>("Task has been deleted!", HttpStatus.OK);
    }

//    @GetMapping("/tasks/{id}")
//    public List<Task> getTask(@PathVariable("id") long id) {
//        System.out.println("Get all tasks from users with id = " + id + "...");
//        List<Task> tasks;
//        Optional<User> user = userRepository.findById(id);
//        tasks = taskRepository.findByAddressees(user);
//        return tasks;
//    }

    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
    @GetMapping("/tasks")
    public List<Task> getMyTasks() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Get all tasks from user: " + userDetails.getUsername() + "...");
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<Task> allTasks, tasksToDone = new ArrayList<>();
        allTasks = taskRepository.findByRecipient(user);
        for(Task task : allTasks) {
            if(!task.isRemoved()) tasksToDone.add(task);
        }
        return tasksToDone;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @GetMapping("/tasks/sent")
    public List<Task> getMySentTasks() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Get my sent tasks");
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<Task> mySentTasks;
        mySentTasks = taskRepository.findByPrincipal(user);
        System.out.println(mySentTasks.toString());
        return mySentTasks;
    }

    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
    @PutMapping("/tasks/remove/{id}")
    public ResponseEntity<Task> removeTask(@PathVariable("id") long id) {
        System.out.println("Task is removed!");
        Optional<Task> t = taskRepository.findById(id);
        if (t != null) {
            Task _task = t.get();
            System.out.println(_task.getTaskTitle());
            System.out.println(_task.getTaskContent());
            System.out.println(_task.getPrincipal());
            System.out.println(_task.getRecipient());
            System.out.println(_task.getAddressees());
            _task.setRemoved(true);
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
    @PutMapping("/tasks/accept/{id}")
    public ResponseEntity<Task> acceptTask(@PathVariable("id") long id) {
        System.out.println("Task is in progress!");
        Optional<Task> t = taskRepository.findById(id);
        if (t != null) {
            Task _task = t.get();
            System.out.println(_task.getTaskTitle());
            System.out.println(_task.getTaskContent());
            System.out.println(_task.getPrincipal());
            System.out.println(_task.getRecipient());
            System.out.println(_task.getAddressees());
            _task.setInProgress(true);
            if(_task.getPrincipal().getEmail() != null) {
                emailService.sendAcceptTaskNotification(_task);
            }
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
    @PutMapping("/tasks/done/{id}")
    public ResponseEntity<Task> finishTask(@PathVariable("id") long id) {
        System.out.println("Task is done!");
        Optional<Task> t = taskRepository.findById(id);
        if (t != null) {
            Task _task = t.get();
            System.out.println(_task.getTaskTitle());
            System.out.println(_task.getTaskContent());
            System.out.println(_task.getPrincipal());
            System.out.println(_task.getRecipient());
            System.out.println(_task.getAddressees());
            _task.setDone(true);
            if(_task.getPrincipal().getEmail() != null) {
                emailService.sendFinishTaskNotification(_task);
            }
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @PutMapping("/tasks/correct/{id}")
    public ResponseEntity<Task> correctTask(@PathVariable("id") long id) {
        System.out.println("Correct task!");
        Optional<Task> t = taskRepository.findById(id);
        if (t != null) {
            Task _task = t.get();
            System.out.println(_task.getTaskTitle());
            System.out.println(_task.getTaskContent());
            System.out.println(_task.getPrincipal());
            System.out.println(_task.getRecipient());
            System.out.println(_task.getAddressees());
            _task.setDone(false);
            _task.setInProgress(false);
            _task.setRemoved(false);
            if(_task.getRecipient().getEmail() != null) {
                emailService.sendCorrectTaskNotification(_task);
            }
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
    @PutMapping("/tasks/remove-in-owner/{id}")
    public ResponseEntity<Task> removeInOwner(@PathVariable("id") long id) {
        System.out.println("Task is removed!");
        Optional<Task> t = taskRepository.findById(id);
        if (t != null) {
            Task _task = t.get();
            System.out.println(_task.getTaskTitle());
            System.out.println(_task.getTaskContent());
            System.out.println(_task.getPrincipal());
            System.out.println(_task.getRecipient());
            System.out.println(_task.getAddressees());
            _task.setRemovedInOwner(true);
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/tasks/report")
    public ResponseEntity<Task> createReport(@RequestBody Task task) {
        System.out.println("Task with report!");
        Optional<Task> t = taskRepository.findById(task.getId());
        if (t != null) {
            Task _task = t.get();
            System.out.println(_task.getTaskTitle());
            System.out.println(_task.getTaskContent());
            System.out.println(_task.getPrincipal());
            System.out.println(_task.getRecipient());
            System.out.println(_task.getAddressees());
            System.out.println(_task.getReport());
            _task.setReport(task.getReport());
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
