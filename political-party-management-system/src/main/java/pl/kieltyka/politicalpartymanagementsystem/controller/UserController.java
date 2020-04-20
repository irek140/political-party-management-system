package pl.kieltyka.politicalpartymanagementsystem.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import pl.kieltyka.politicalpartymanagementsystem.model.*;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.*;
import pl.kieltyka.politicalpartymanagementsystem.model.validators.UserValidationErrors;
import pl.kieltyka.politicalpartymanagementsystem.model.validators.UserValidator;
import pl.kieltyka.politicalpartymanagementsystem.repository.*;
import pl.kieltyka.politicalpartymanagementsystem.repository.surveys.*;
import pl.kieltyka.politicalpartymanagementsystem.security.AuthenticationRequest;
import pl.kieltyka.politicalpartymanagementsystem.security.AuthenticationResponse;
import pl.kieltyka.politicalpartymanagementsystem.security.JWTUtil;
import pl.kieltyka.politicalpartymanagementsystem.services.EmailService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    private Logger log = Logger.getLogger(UserValidator.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthorityRepository authRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPassEncoder;

    @Autowired
    private UserValidator userValidate;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private JoinMessageRepository joinMessageRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private Survey2Repository survey2Repository;

    @Autowired
    private Survey3Repository survey3Repository;

    @Autowired
    private Survey4Repository survey4Repository;

    @Autowired
    private Survey5Repository survey5Repository;

    @Autowired
    private Survey6Repository survey6Repository;

    @Autowired
    private Survey7Repository survey7Repository;


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        //Optional<User> user = userRepo.findById(1L);
        if(id == 1) {
            return new ResponseEntity<>("User has not been deleted!", HttpStatus.OK);
        }
        Optional<User> u = userRepo.findById(id);
        User user = u.get();
        User usernull = userRepo.findByUsername("usernull");


        //Zabezpieczenie przed błędem, gdy użytkownik zostaje usunięty z bazy
        List<Message> addressesList = messageRepository.findByAddressees(user);
        for(Message m : addressesList) {
            List<User> newList = m.getAddressees();
            newList.remove(user);
            m.setAddressees(newList);
            messageRepository.save(m);
        }
        List<Message> recipientList = messageRepository.findByRecipient(user);
        for(Message m : recipientList) {
            m.setRecipient(usernull);
            messageRepository.save(m);
        }
        List<Message> principalList = messageRepository.findByPrincipal(user);
        for(Message m : principalList) {
            m.setPrincipal(usernull);
            messageRepository.save(m);
        }

        List<Task> addressesTaskList = taskRepository.findByAddressees(user);
        for(Task t : addressesTaskList) {
            List<User> newList = t.getAddressees();
            newList.remove(user);
            t.setAddressees(newList);
            taskRepository.save(t);
        }
        List<Task> recipientTaskList = taskRepository.findByRecipient(user);
        for(Task t : recipientTaskList) {
            t.setRecipient(usernull);
            taskRepository.save(t);
        }
        List<Task> principalTaskList = taskRepository.findByPrincipal(user);
        for(Task t : principalTaskList) {
            t.setPrincipal(usernull);
            taskRepository.save(t);
        }

        List<Article> authorList = articleRepository.findByAuthor(user);
        for(Article a : authorList) {
            a.setAuthor(usernull);
            articleRepository.save(a);
        }

        Optional<JoinMessage> jM = joinMessageRepository.findByAuthor(user);
        if(jM.isPresent()) {
            JoinMessage joinMessage = jM.get();
            joinMessageRepository.delete(joinMessage);
            //joinMessage.setAuthor(usernull);
            //joinMessageRepository.save(joinMessage);
        }


        List<Vote> userid = voteRepository.findByUserid(user.getId());
        for(Vote v : userid) {
            v.setUserid(0);
            voteRepository.save(v);
        }

        List<Survey2> addressesSurvey2List = survey2Repository.findByAddressees(user);
        for(Survey2 s : addressesSurvey2List) {
            List<User> newList = s.getAddressees();
            newList.remove(user);
            s.setAddressees(newList);
            survey2Repository.save(s);
        }
        List<Survey2> ownerSurvey2List = survey2Repository.findByOwner(user);
        for(Survey2 s : ownerSurvey2List) {
            s.setOwner(usernull);
            survey2Repository.save(s);
        }

        List<Survey3> addressesSurvey3List = survey3Repository.findByAddressees(user);
        for(Survey3 s : addressesSurvey3List) {
            List<User> newList = s.getAddressees();
            newList.remove(user);
            s.setAddressees(newList);
            survey3Repository.save(s);
        }
        List<Survey3> ownerSurvey3List = survey3Repository.findByOwner(user);
        for(Survey3 s : ownerSurvey3List) {
            s.setOwner(usernull);
            survey3Repository.save(s);
        }

        List<Survey4> addressesSurvey4List = survey4Repository.findByAddressees(user);
        for(Survey4 s : addressesSurvey4List) {
            List<User> newList = s.getAddressees();
            newList.remove(user);
            s.setAddressees(newList);
            survey4Repository.save(s);
        }
        List<Survey4> ownerSurvey4List = survey4Repository.findByOwner(user);
        for(Survey4 s : ownerSurvey4List) {
            s.setOwner(usernull);
            survey4Repository.save(s);
        }

        List<Survey5> addressesSurvey5List = survey5Repository.findByAddressees(user);
        for(Survey5 s : addressesSurvey5List) {
            List<User> newList = s.getAddressees();
            newList.remove(user);
            s.setAddressees(newList);
            survey5Repository.save(s);
        }
        List<Survey5> ownerSurvey5List = survey5Repository.findByOwner(user);
        for(Survey5 s : ownerSurvey5List) {
            s.setOwner(usernull);
            survey5Repository.save(s);
        }

        List<Survey6> addressesSurvey6List = survey6Repository.findByAddressees(user);
        for(Survey6 s : addressesSurvey6List) {
            List<User> newList = s.getAddressees();
            newList.remove(user);
            s.setAddressees(newList);
            survey6Repository.save(s);
        }
        List<Survey6> ownerSurvey6List = survey6Repository.findByOwner(user);
        for(Survey6 s : ownerSurvey6List) {
            s.setOwner(usernull);
            survey6Repository.save(s);
        }

        List<Survey7> addressesSurvey7List = survey7Repository.findByAddressees(user);
        for(Survey7 s : addressesSurvey7List) {
            List<User> newList = s.getAddressees();
            newList.remove(user);
            s.setAddressees(newList);
            survey7Repository.save(s);
        }
        List<Survey7> ownerSurvey7List = survey7Repository.findByOwner(user);
        for(Survey7 s : ownerSurvey7List) {
            s.setOwner(usernull);
            survey7Repository.save(s);
        }



        System.out.println("Delete User with ID = " + id);
        userRepo.deleteById(id);
        return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
    }


    /*
     * Metoda do rejestracji użytkownika. Waliduje jego pola (klasa UserValidator), dodaje role "ROLE_USER", koduje hasło i domyślnie
     * ustawia stan konta jako "nieaktywne".
     * @param user Obiekt użytkownika przechwycony jako JSON i przekonwertowany ja obiekt
     * @param errors Rejestr błędów automatycznie inicjalizowany przez Spring'a
     * @return UserValidationErrors Zwraca obiekt zawierający Mapę -> (Kod błędu, Wiadomość błędu). Gdy błędy walidacji nie występują zwraca
     * pustą mapę
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user, BindingResult errors) {
        userValidate.validate(user, errors);

        if (errors.hasErrors()) {
            Map<String, String> validErrors = new HashMap<>(errors.getAllErrors().size());
            for (ObjectError error : errors.getAllErrors()) {
                validErrors.put(error.getCode(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(new UserValidationErrors(validErrors));
        }

        Authority userRole = authRepo.findByAuthority("ROLE_USER");

        // usuniecie bledu podczas nieprzekazania w zadaniu listy authorities
        // lub przekazania listy z jakas rola (wtedy dostawalismy blad 500 z serwera)
        Collection<Authority> roles = new HashSet<Authority>();
        roles.add(userRole);
        user.setAuthorities(roles);

        user.setPassword(bCryptPassEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setRegion(user.getRegion());
        User savedUser = userRepo.save(user);
        log.info("User registered.");

        emailService.sendActivationEmailToUser(savedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /*
     * Metoda służąca do logowania. Sprawdza login i hasło za pomocą customowego Authentication Managera,
     * nastepnie dodaje je do kontextu security Spring'a i generuje token dla użytkownika.
     * @param request Opakowane dane do logowania (username/password)
     * @return ResponseEntity<?> Zwraca token opakowany w AuthenticationResponse i dołącza do niego status Http
     */
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        Authentication auth = null;

        try {
            auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (AuthenticationException except) {
            log.error("Error during user authentication");
            return ResponseEntity.badRequest().body(except.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(auth);

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(JWTUtil.generateToken(userDetails)));
    }


    /*
     * Metoda odświeżająca token (przedłużenie czasu wygaśnięcia tokenu)
     * @param request Zapytanie z tokenem w headerze
     * @return Zwraca token opakowany w AuthenticationResponse i dołącza do niego status Http
     */
    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String token = JWTUtil.trimToken(request.getHeader(JWTUtil.HEADER));

        String newToken = null;
        if (JWTUtil.canTokenBeRefreshed(token)) {
            newToken = JWTUtil.refrehToken(token);
            log.info("Token refreshed.");
        } else {
            log.error("Token cannot be refreshed.");
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(new AuthenticationResponse(newToken));
    }



    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println("Get all Users...");
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        return users;
    }

    @GetMapping("/users/me")
    public User getMe() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User)userDetails;
        Optional<User> u = userRepo.findById(user.getId());
        if(u.isPresent()) {
            return user;
        } else {
            User u2 = new User();
            u2.setName("");
            u2.setSurname("");
            return u2;
        }
    }

    @PreAuthorize("hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')")
    @GetMapping("/users/for-president")
    public List<User> getUsersForPresident() {
        System.out.println("Get users for president/secretary...");
        List<User> allUsers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(allUsers::add);
        for(User user : allUsers) {
            if(user.isPartyPresident()) continue;
            if(user.getUsername().equals("admin")) continue;
            if(user.isMember()) users.add(user);
        }
        return users;
    }

    @PreAuthorize("hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')")
    @GetMapping("/users/for-region-president")
    public List<User> getUsersForRegionPresident() {
        System.out.println("Get users for region president/region secretary...");
        List<User> allUsers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(allUsers::add);
        for(User user : allUsers) {
            if(user.isPartyPresident()) continue;
            if(user.isPartyVicePresident()) continue;
            if(user.isPartySecretary()) continue;
            if(user.isRegionPresident()) continue;
            if(user.getUsername().equals("admin")) continue;
            if(user.isMember()) users.add(user);
        }
        return users;
    }

    @PreAuthorize("hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @GetMapping("/users/for-constituency-president")
    public List<User> getUsersForConstituencyPresident() {
        System.out.println("Get users for constituency president/constituency secretary...");
        List<User> allUsers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(allUsers::add);
        for(User user : allUsers) {
            if(user.isPartyPresident()) continue;
            if(user.isPartyVicePresident()) continue;
            if(user.isPartySecretary()) continue;
            if(user.isRegionPresident()) continue;
            if(user.isRegionVicePresident()) continue;
            if(user.isRegionSecretary()) continue;
            if(user.isConstituencyPresident()) continue;
            if(user.getUsername().equals("admin")) continue;
            if(user.isMember()) users.add(user);
        }
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable("id") long id) {
        System.out.println("User with ID = " + id);
        Optional<User> users = userRepo.findById(id);
        return users;
    }

    @GetMapping(value = "/users/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        System.out.println("User with username = " + username);
        User user = userRepo.findByUsername(username);
        return user;
    }

    @GetMapping(value = "/users/user-name/{username}")
    public User getUserByUsernameWithoutAdmin(@PathVariable String username) {
        System.out.println("User with username = " + username);
        User user = userRepo.findByUsername(username);
        if((user.getId() == 1) || (user.getId() == 2)){
            return null;
        } else {
            return user;
        }
    }

    @GetMapping(value = "/users/name/{name}")
    public List<User> getUserByName(@PathVariable String name) {
        System.out.println("User with name = " + name);
        List<User> users = userRepo.findByName(name);
        return users;
    }

    @GetMapping(value = "/users/surname/{surname}")
    public List<User> getUserBySurname(@PathVariable String surname) {
        System.out.println("User with surname = " + surname);
        List<User> users = userRepo.findBySurname(surname);
        return users;
    }

    @GetMapping(value = "/users/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        System.out.println("User with email = " + email);
        User user = userRepo.findByEmail(email);
        return user;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/users/region/{region}")
    public List<User> getUserByRegion(@PathVariable String region) {
        System.out.println("Users with region = " + region);
        List<User> users = userRepo.findByRegion(region);
        return users;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Update User with id = " + id + "...");
        Optional<User> userData = userRepo.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setEnabled(user.isEnabled());
            return new ResponseEntity<>(userRepo.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_PARTY_PRESIDENT')")
    @PutMapping("/users/update/{id}")
    public ResponseEntity<User> updateUserProperties(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Update User with ID = " + id + "...");
        Optional<User> userData = userRepo.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setEnabled(user.isEnabled());
            _user.setUsername(user.getUsername());
            _user.setEmail(user.getEmail());
            _user.setName(user.getName());
            _user.setSurname(user.getSurname());
            _user.setPartyPresident(user.isPartyPresident());
            _user.setNationalBoard(user.isNationalBoard());
            _user.setNationalCouncil(user.isNationalCouncil());
            _user.setPartyCourt(user.isPartyCourt());
            _user.setRegion(user.getRegion());
            _user.setConstituency(user.getConstituency());
            if(_user.isPartyPresident()) {
                Authority presidentRole = authRepo.findByAuthority("ROLE_PARTY_PRESIDENT");
                Authority memberRole = authRepo.findByAuthority("ROLE_MEMBER");
                Authority userRole = authRepo.findByAuthority("ROLE_USER");
                Collection<Authority> roles = new HashSet<Authority>();
                roles.add(userRole);
                roles.add(memberRole);
                roles.add(presidentRole);
                _user.setAuthorities(roles);
            }
            return new ResponseEntity<>(userRepo.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/users/ban/{id}")
    public ResponseEntity<User> banUser(@PathVariable("id") long id) {
        System.out.println("Ban User with ID = " + id);
        Optional<User> userData = userRepo.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setBanned(!_user.isBanned());
            return new ResponseEntity<>(userRepo.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_PARTY_PRESIDENT')")
    @PutMapping("/users/party-vice-president/{id}")
    public ResponseEntity<User> choosePartyVicePresident(@PathVariable("id") long id) {
        System.out.println("Update User with ID = " + id + "...");
        Optional<User> userData = userRepo.findById(id);
        Authority vicePresidentRole = authRepo.findByAuthority("ROLE_PARTY_VICE_PRESIDENT");
        Authority userRole = authRepo.findByAuthority("ROLE_USER");
        Authority memberRole = authRepo.findByAuthority("ROLE_MEMBER");
        Collection<Authority> roles = new HashSet<Authority>();
        roles.add(userRole);
        roles.add(memberRole);
        roles.add(vicePresidentRole);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setPartyVicePresident(true);
            _user.setAuthorities(roles);
            return new ResponseEntity<>(userRepo.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_PARTY_PRESIDENT')")
    @PutMapping("/users/party-secretary/{id}")
    public ResponseEntity<User> choosePartySecretary(@PathVariable("id") long id) {
        System.out.println("Update User with ID = " + id + "...");
        Optional<User> userData = userRepo.findById(id);
        Authority secretaryRole = authRepo.findByAuthority("ROLE_PARTY_SECRETARY");
        Authority userRole = authRepo.findByAuthority("ROLE_USER");
        Authority memberRole = authRepo.findByAuthority("ROLE_MEMBER");
        Collection<Authority> roles = new HashSet<Authority>();
        roles.add(userRole);
        roles.add(memberRole);
        roles.add(secretaryRole);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setPartySecretary(true);
            _user.setAuthorities(roles);
            return new ResponseEntity<>(userRepo.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY') or hasAuthority('ROLE_REGION_SECRETARY') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @PutMapping("/users/update-from-president/{id}")
    public ResponseEntity<User> updateUserFromPresident(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Update User with ID = " + id + "...");
        Optional<User> userData = userRepo.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setBanned(user.isBanned());
            _user.setUsername(user.getUsername());
            _user.setEmail(user.getEmail());
            _user.setName(user.getName());
            _user.setSurname(user.getSurname());
            _user.setRegion(user.getRegion());
            _user.setConstituency(user.getConstituency());

            _user.setNationalBoard(user.isNationalBoard());
            _user.setNationalCouncil(user.isNationalCouncil());
            _user.setPartyCourt(user.isPartyCourt());

            _user.setPartySecretary(user.isPartySecretary());
            _user.setPartyVicePresident(user.isPartyVicePresident());
            _user.setRegionPresident(user.isRegionPresident());

            Collection<Authority> roles = new HashSet<Authority>();
            Authority memberRole = authRepo.findByAuthority("ROLE_MEMBER");
            Authority userRole = authRepo.findByAuthority("ROLE_USER");
            roles.add(userRole);
            roles.add(memberRole);
            if(_user.isPartyVicePresident()) {
                Authority vicePresidentRole = authRepo.findByAuthority("ROLE_PARTY_VICE_PRESIDENT");
                roles.add(vicePresidentRole);
            }
            if(_user.isPartySecretary()) {
                Authority secretaryRole = authRepo.findByAuthority("ROLE_PARTY_SECRETARY");
                roles.add(secretaryRole);
            }
            if(_user.isRegionPresident()) {
                Authority regionPresidentRole = authRepo.findByAuthority("ROLE_REGION_PRESIDENT");
                roles.add(regionPresidentRole);
            }

            _user.setAuthorities(roles);
            return new ResponseEntity<>(userRepo.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')")
    @PutMapping("/users/update-from-region-president/{id}")
    public ResponseEntity<User> updateUserFromRegionPresident(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Update User with ID = " + id + "...");
        Optional<User> userData = userRepo.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setBanned(user.isBanned());
            _user.setUsername(user.getUsername());
            _user.setEmail(user.getEmail());
            _user.setName(user.getName());
            _user.setSurname(user.getSurname());
            _user.setRegion(user.getRegion());
            _user.setConstituency(user.getConstituency());

            _user.setRegionSecretary(user.isRegionSecretary());
            _user.setRegionVicePresident(user.isRegionVicePresident());
            _user.setConstituencyPresident(user.isConstituencyPresident());

            Collection<Authority> roles = new HashSet<Authority>();
            Authority memberRole = authRepo.findByAuthority("ROLE_MEMBER");
            Authority userRole = authRepo.findByAuthority("ROLE_USER");
            roles.add(userRole);
            roles.add(memberRole);
            if(_user.isRegionVicePresident()) {
                Authority regionVicePresidentRole = authRepo.findByAuthority("ROLE_REGION_VICE_PRESIDENT");
                roles.add(regionVicePresidentRole);
            }
            if(_user.isRegionSecretary()) {
                Authority regionSecretaryRole = authRepo.findByAuthority("ROLE_REGION_SECRETARY");
                roles.add(regionSecretaryRole);
            }
            if(_user.isConstituencyPresident()) {
                Authority constituencyPresidentRole = authRepo.findByAuthority("ROLE_CONSTITUENCY_PRESIDENT");
                roles.add(constituencyPresidentRole);
            }

            _user.setAuthorities(roles);
            return new ResponseEntity<>(userRepo.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @PutMapping("/users/update-from-constituency-president/{id}")
    public ResponseEntity<User> updateUserFromConstituencyPresident(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Update User with ID = " + id + "...");
        Optional<User> userData = userRepo.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setBanned(user.isBanned());
            _user.setUsername(user.getUsername());
            _user.setEmail(user.getEmail());
            _user.setName(user.getName());
            _user.setSurname(user.getSurname());
            _user.setRegion(user.getRegion());
            _user.setConstituency(user.getConstituency());

            _user.setConstituencySecretary(user.isConstituencySecretary());
            _user.setConstituencyVicePresident(user.isConstituencyVicePresident());

            Collection<Authority> roles = new HashSet<Authority>();
            Authority memberRole = authRepo.findByAuthority("ROLE_MEMBER");
            Authority userRole = authRepo.findByAuthority("ROLE_USER");
            roles.add(userRole);
            roles.add(memberRole);
            if(_user.isConstituencyVicePresident()) {
                Authority constituencyVicePresidentRole = authRepo.findByAuthority("ROLE_CONSTITUENCY_VICE_PRESIDENT");
                roles.add(constituencyVicePresidentRole);
            }
            if(_user.isConstituencySecretary()) {
                Authority constituencySecretaryRole = authRepo.findByAuthority("ROLE_CONSTITUENCY_SECRETARY");
                roles.add(constituencySecretaryRole);
            }

            _user.setAuthorities(roles);
            return new ResponseEntity<>(userRepo.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
