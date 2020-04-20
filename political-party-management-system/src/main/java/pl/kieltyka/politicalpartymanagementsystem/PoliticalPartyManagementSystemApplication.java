package pl.kieltyka.politicalpartymanagementsystem;

import com.sun.istack.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.kieltyka.politicalpartymanagementsystem.model.*;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey2;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey3;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey4;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey5;
import pl.kieltyka.politicalpartymanagementsystem.repository.*;
import pl.kieltyka.politicalpartymanagementsystem.repository.surveys.Survey2Repository;
import pl.kieltyka.politicalpartymanagementsystem.repository.surveys.Survey3Repository;
import pl.kieltyka.politicalpartymanagementsystem.repository.surveys.Survey4Repository;
import pl.kieltyka.politicalpartymanagementsystem.repository.surveys.Survey5Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@SpringBootApplication
public class PoliticalPartyManagementSystemApplication {

    private Logger log = Logger.getLogger(PoliticalPartyManagementSystemApplication.class);

    @Autowired
    private AuthorityRepository authRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPassEncoder;

    @Autowired
    private ArticleRepository articleRepo;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ConstituencyRepository constituencyRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private JoinMessageRepository joinMessageRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private Survey2Repository survey2Repository;

    @Autowired
    private Survey3Repository survey3Repository;

    @Autowired
    private Survey4Repository survey4Repository;

    public static void main(String[] args) {
        SpringApplication.run(PoliticalPartyManagementSystemApplication.class, args);
    }

    @PostConstruct
    public void initializeDb() {

        //Dodanie początkowych regionów
        regionRepository.save(new Region("Podkarpackie"));
            constituencyRepository.save(new Constituency("Rzeszów", "Podkarpackie"));
            constituencyRepository.save(new Constituency("Strzyżów", "Podkarpackie"));
            constituencyRepository.save(new Constituency("Przemyśl", "Podkarpackie"));
        regionRepository.save(new Region("Małopolskie"));
            constituencyRepository.save(new Constituency("Kraków", "Małopolskie"));
            constituencyRepository.save(new Constituency("Tarnów", "Małopolskie"));
        regionRepository.save(new Region("Świętokrzyskie"));
            constituencyRepository.save(new Constituency("Kielce", "Świętokrzyskie"));
        regionRepository.save(new Region("Lubelskie"));
            constituencyRepository.save(new Constituency("Lublin", "Lubelskie"));
        regionRepository.save(new Region("Mazowieckie"));
            constituencyRepository.save(new Constituency("Warszawa", "Mazowieckie"));
            constituencyRepository.save(new Constituency("Radom", "Mazowieckie"));
        regionRepository.save(new Region("Podlaskie"));
        regionRepository.save(new Region("Warmińsko-Mazurskie"));
        regionRepository.save(new Region("Pomorskie"));
        regionRepository.save(new Region("Zachoddniopomorskie"));
        regionRepository.save(new Region("Kujawsko-Pomorskie"));
        regionRepository.save(new Region("Łódzkie"));
        regionRepository.save(new Region("Wielkopolskie"));
        regionRepository.save(new Region("Lubuskie"));
        regionRepository.save(new Region("Dolnośląskie"));
        regionRepository.save(new Region("Śląskie"));
            constituencyRepository.save(new Constituency("Katowice", "Śląskie"));
            constituencyRepository.save(new Constituency("Gliwice", "Śląskie"));
            constituencyRepository.save(new Constituency("Chorzów", "Śląskie"));
            constituencyRepository.save(new Constituency("Zabrze", "Śląskie"));
        regionRepository.save(new Region("Opolskie"));

        //Dodanie użytkowników o różnych uprawnieniach
        Authority adminAuth = new Authority("ROLE_ADMIN");
        Authority userAuth = new Authority ("ROLE_USER");
        Authority memberAuth = new Authority ("ROLE_MEMBER");
        Authority partyPresidentAuth = new Authority ("ROLE_PARTY_PRESIDENT");
        Authority partyVicePresidentAuth = new Authority ("ROLE_PARTY_VICE_PRESIDENT");
        Authority partySecretaryAuth = new Authority ("ROLE_PARTY_SECRETARY");
        Authority regionPresidentAuth = new Authority ("ROLE_REGION_PRESIDENT");
        Authority regionVicePresidentAuth = new Authority ("ROLE_REGION_VICE_PRESIDENT");
        Authority regionSecretaryAuth = new Authority ("ROLE_REGION_SECRETARY");
        Authority constituencyPresidentAuth = new Authority ("ROLE_CONSTITUENCY_PRESIDENT");
        Authority constituencyVicePresidentAuth = new Authority ("ROLE_CONSTITUENCY_VICE_PRESIDENT");
        Authority constituencySecretaryAuth = new Authority ("ROLE_CONSTITUENCY_SECRETARY");


        log.info("Authority " + authRepo.save(adminAuth).getAuthority() + " added.");
        log.info("Authority " + authRepo.save(userAuth).getAuthority() + " added.");
        log.info("Authority " + authRepo.save(memberAuth).getAuthority() + " added.");
        log.info("Authority " + authRepo.save(partyPresidentAuth).getAuthority() + " added.");
        log.info("Authority " + authRepo.save(partyVicePresidentAuth).getAuthority() + " added.");
        log.info("Authority " + authRepo.save(partySecretaryAuth).getAuthority() + " added.");
        log.info("Authority " + authRepo.save(regionPresidentAuth).getAuthority() + " added.");
        log.info("Authority " + authRepo.save(regionVicePresidentAuth).getAuthority() + " added.");
        log.info("Authority " + authRepo.save(regionSecretaryAuth).getAuthority() + " added.");
        log.info("Authority " + authRepo.save(constituencyPresidentAuth).getAuthority() + " added.");
        log.info("Authority " + authRepo.save(constituencyVicePresidentAuth).getAuthority() + " added.");
        log.info("Authority " + authRepo.save(constituencySecretaryAuth).getAuthority() + " added.");


        List<Authority> authori = new ArrayList<>();
        authori.add(authRepo.findByAuthority("ROLE_USER"));

        User usernull = new User("usernull", bCryptPassEncoder.encode("usernull"), authori);
        usernull.setEnabled(true);
        usernull.setName("");
        usernull.setSurname("");
        log.info("User " + userRepo.save(usernull).getUsername() + " added.");

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authRepo.findByAuthority("ROLE_USER"));
        authorities.add(authRepo.findByAuthority("ROLE_ADMIN"));

        User admin = new User("admin", bCryptPassEncoder.encode("admin123"), authorities);
        admin.setEnabled(true);
        admin.setName("Administrator");
        admin.setSurname("Systemu");
        log.info("User " + userRepo.save(admin).getUsername() + " added.");

        List<Authority> presidentAuthorities = new ArrayList<>();
        presidentAuthorities.add(authRepo.findByAuthority("ROLE_USER"));
        presidentAuthorities.add(authRepo.findByAuthority("ROLE_MEMBER"));
        presidentAuthorities.add(authRepo.findByAuthority("ROLE_PARTY_PRESIDENT"));
        User presidentUser = new User("user", bCryptPassEncoder.encode("user123"), "Jan", "Kowalski", true, presidentAuthorities);
        presidentUser.setEnabled(true);
        presidentUser.setPartyPresident(true);
        presidentUser.setRegion("Podkarpackie");
        presidentUser.setConstituency("Rzeszów");
        log.info("User " + userRepo.save(presidentUser).getUsername() + " added.");


        List<Authority> vicePresidentAuthorities = new ArrayList<>();
        vicePresidentAuthorities.add(authRepo.findByAuthority("ROLE_USER"));
        vicePresidentAuthorities.add(authRepo.findByAuthority("ROLE_MEMBER"));
        vicePresidentAuthorities.add(authRepo.findByAuthority("ROLE_PARTY_VICE_PRESIDENT"));
        User vicePresidentUser2 = new User("user2", bCryptPassEncoder.encode("user123"),"Piotr", "Nowak", true, vicePresidentAuthorities);
        vicePresidentUser2.setEnabled(true);
        vicePresidentUser2.setPartyVicePresident(true);
        vicePresidentUser2.setRegion("Małopolskie");
        vicePresidentUser2.setConstituency("Kraków");
        log.info("User " + userRepo.save(vicePresidentUser2).getUsername() + " added.");


        List<Authority> secretaryAuthorities = new ArrayList<>();
        secretaryAuthorities.add(authRepo.findByAuthority("ROLE_USER"));
        secretaryAuthorities.add(authRepo.findByAuthority("ROLE_MEMBER"));
        secretaryAuthorities.add(authRepo.findByAuthority("ROLE_PARTY_SECRETARY"));
        User secretaryUser3 = new User("user3", bCryptPassEncoder.encode("user123"), "Mariusz", "Puchacki", true, secretaryAuthorities);
        secretaryUser3.setEnabled(true);
        secretaryUser3.setPartySecretary(true);
        secretaryUser3.setRegion("Mazowieckie");
        secretaryUser3.setConstituency("Warszawa");
        log.info("User " + userRepo.save(secretaryUser3).getUsername() + " added.");

        List<Authority> regionPresidentAuthorities = new ArrayList<>();
        regionPresidentAuthorities.add(authRepo.findByAuthority("ROLE_USER"));
        regionPresidentAuthorities.add(authRepo.findByAuthority("ROLE_MEMBER"));
        regionPresidentAuthorities.add(authRepo.findByAuthority("ROLE_REGION_PRESIDENT"));
        User regionPresidentUser4 = new User("user4", bCryptPassEncoder.encode("user123"), "Adam", "Walczyk", true, regionPresidentAuthorities);
        regionPresidentUser4.setEnabled(true);
        regionPresidentUser4.setRegionPresident(true);
        regionPresidentUser4.setRegion("Podkarpackie");
        regionPresidentUser4.setConstituency("Krosno");
        log.info("User " + userRepo.save(regionPresidentUser4).getUsername() + " added.");

        List<Authority> regionVicePresidentAuthorities = new ArrayList<>();
        regionVicePresidentAuthorities.add(authRepo.findByAuthority("ROLE_USER"));
        regionVicePresidentAuthorities.add(authRepo.findByAuthority("ROLE_MEMBER"));
        regionVicePresidentAuthorities.add(authRepo.findByAuthority("ROLE_REGION_VICE_PRESIDENT"));
        User regionVicePresidentUser5 = new User("user5", bCryptPassEncoder.encode("user123"), "Adam", "Milosz", true, regionVicePresidentAuthorities);
        regionVicePresidentUser5.setEnabled(true);
        regionVicePresidentUser5.setRegionVicePresident(true);
        regionVicePresidentUser5.setRegion("Podkarpackie");
        regionVicePresidentUser5.setConstituency("Przemyśl");
        log.info("User " + userRepo.save(regionVicePresidentUser5).getUsername() + " added.");

        List<Authority> regionSecretaryAuthorities = new ArrayList<>();
        regionSecretaryAuthorities.add(authRepo.findByAuthority("ROLE_USER"));
        regionSecretaryAuthorities.add(authRepo.findByAuthority("ROLE_MEMBER"));
        regionSecretaryAuthorities.add(authRepo.findByAuthority("ROLE_REGION_SECRETARY"));
        User normalUser6 = new User("user6", bCryptPassEncoder.encode("user123"), "Norbert", "Jankowski", true, regionSecretaryAuthorities);
        normalUser6.setEnabled(true);
        normalUser6.setRegionSecretary(true);
        normalUser6.setRegion("Podkarpackie");
        normalUser6.setConstituency("Strzyżów");
        log.info("User " + userRepo.save(normalUser6).getUsername() + " added.");

        List<Authority> constituencyPresidentAuthorities = new ArrayList<>();
        constituencyPresidentAuthorities.add(authRepo.findByAuthority("ROLE_USER"));
        constituencyPresidentAuthorities.add(authRepo.findByAuthority("ROLE_MEMBER"));
        constituencyPresidentAuthorities.add(authRepo.findByAuthority("ROLE_CONSTITUENCY_PRESIDENT"));
        User constituencyPresidentUser7 = new User("user7", bCryptPassEncoder.encode("user123"),"Kamil", "Nowakowski", true, constituencyPresidentAuthorities);
        constituencyPresidentUser7.setEnabled(true);
        constituencyPresidentUser7.setConstituencyPresident(true);
        constituencyPresidentUser7.setRegion("Podkarpackie");
        constituencyPresidentUser7.setConstituency("Strzyżów");
        log.info("User " + userRepo.save(constituencyPresidentUser7).getUsername() + " added.");

        List<Authority> constituencyVicePresidentAuthorities = new ArrayList<>();
        constituencyVicePresidentAuthorities.add(authRepo.findByAuthority("ROLE_USER"));
        constituencyVicePresidentAuthorities.add(authRepo.findByAuthority("ROLE_MEMBER"));
        constituencyVicePresidentAuthorities.add(authRepo.findByAuthority("ROLE_CONSTITUENCY_VICE_PRESIDENT"));
        User constituencyVicePresidentUser8 = new User("user8", bCryptPassEncoder.encode("user123"),"Zbigniew", "Kot", true, constituencyVicePresidentAuthorities);
        constituencyVicePresidentUser8.setEnabled(true);
        constituencyVicePresidentUser8.setConstituencyVicePresident(true);
        constituencyVicePresidentUser8.setRegion("Podkarpackie");
        constituencyVicePresidentUser8.setConstituency("Strzyżów");
        log.info("User " + userRepo.save(constituencyVicePresidentUser8).getUsername() + " added.");

        List<Authority> constituencySecretaryAuthorities = new ArrayList<>();
        constituencySecretaryAuthorities.add(authRepo.findByAuthority("ROLE_USER"));
        constituencySecretaryAuthorities.add(authRepo.findByAuthority("ROLE_MEMBER"));
        constituencySecretaryAuthorities.add(authRepo.findByAuthority("ROLE_CONSTITUENCY_SECRETARY"));
        User constituencySecretaryUser9 = new User("user9", bCryptPassEncoder.encode("user123"), "Robert", "Kowal", true, constituencySecretaryAuthorities);
        constituencySecretaryUser9.setEnabled(true);
        constituencySecretaryUser9.setConstituencySecretary(true);
        constituencySecretaryUser9.setRegion("Podkarpackie");
        constituencySecretaryUser9.setConstituency("Strzyżów");
        log.info("User " + userRepo.save(constituencySecretaryUser9).getUsername() + " added.");

        List<Authority> memberAuthorities = new ArrayList<>();
        memberAuthorities.add(authRepo.findByAuthority("ROLE_USER"));
        memberAuthorities.add(authRepo.findByAuthority("ROLE_MEMBER"));
        User memberUser10 = new User("user10", bCryptPassEncoder.encode("user123"), "Marek", "Pol", true, memberAuthorities);
        memberUser10.setEnabled(true);
        memberUser10.setRegion("Podkarpackie");
        memberUser10.setConstituency("Strzyżów");
        log.info("User " + userRepo.save(memberUser10).getUsername() + " added.");

        User memberUser11 = new User("user11", bCryptPassEncoder.encode("user123"), "Tomasz", "Lodkowski", true, memberAuthorities);
        memberUser11.setEnabled(true);
        log.info("User " + userRepo.save(memberUser11).getUsername() + " added.");


        List<Authority> userAuthorities = new ArrayList<>();
        userAuthorities.add(authRepo.findByAuthority("ROLE_USER"));
        User normalUser12 = new User("user12", bCryptPassEncoder.encode("user123"), userAuthorities, "Mateusz", "Borowski");
        normalUser12.setEnabled(true);
        normalUser12.setRegion("Podkarpackie");
        normalUser12.setConstituency("Strzyżów");
        log.info("User " + userRepo.save(normalUser12).getUsername() + " added.");

        User normalUser13 = new User("user13", bCryptPassEncoder.encode("user123"), userAuthorities, "Wojciech", "Bartman");
        normalUser13.setEnabled(true);
        normalUser13.setRegion("Małopolskie");
        normalUser13.setConstituency("Kraków");
        log.info("User " + userRepo.save(normalUser13).getUsername() + " added.");

        User normalUser14 = new User("user14", bCryptPassEncoder.encode("user123"), userAuthorities, "Marcin", "Witos");
        normalUser14.setEnabled(true);
        normalUser14.setRegion("Małopolskie");
        normalUser14.setConstituency("Kraków");
        log.info("User " + userRepo.save(normalUser14).getUsername() + " added.");

        User normalUser15 = new User("user15", bCryptPassEncoder.encode("user123"), userAuthorities, "Konrad", "Wela");
        normalUser15.setEnabled(true);
        normalUser15.setRegion("Mazowieckie");
        normalUser15.setConstituency("Warszawa");
        log.info("User " + userRepo.save(normalUser15).getUsername() + " added.");

        User normalUser16 = new User("user16", bCryptPassEncoder.encode("user123"), userAuthorities, "Krystian", "Szylar");
        normalUser16.setEnabled(true);
        normalUser16.setRegion("Małopolskie");
        log.info("User " + userRepo.save(normalUser16).getUsername() + " added.");

        User normalUser17 = new User("user17", bCryptPassEncoder.encode("user123"), userAuthorities, "Karolina", "Zawada");
        normalUser17.setEnabled(true);
        normalUser17.setRegion("Lubelskie");
        log.info("User " + userRepo.save(normalUser17).getUsername() + " added.");

        User normalUser18 = new User("user18", bCryptPassEncoder.encode("user123"), userAuthorities, "Agata", "Wojtkowiak");
        normalUser18.setEnabled(true);
        normalUser18.setRegion("Śląskie");
        log.info("User " + userRepo.save(normalUser18).getUsername() + " added.");

        User normalUser19 = new User("user19", bCryptPassEncoder.encode("user123"), userAuthorities, "Amelia", "Dudek");
        normalUser19.setEnabled(true);
        normalUser19.setRegion("Małopolskie");
        log.info("User " + userRepo.save(normalUser19).getUsername() + " added.");

        User normalUser20 = new User("user20", bCryptPassEncoder.encode("user123"), userAuthorities, "Grzegorz", "Kowalik");
        normalUser20.setEnabled(true);
        normalUser20.setRegion("Podkarpackie");
        log.info("User " + userRepo.save(normalUser20).getUsername() + " added.");



        //Dodanie przykładowych artykułów
        Article article1 = new Article("Start Systemu", "<i>Witamy w Systemie zarządzania partią.</i><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><div style=\"text-align: left;\"><i>&nbsp;Zapraszamy do założenia konta w systemie oraz licznych odwiedzin strony. Dzięki temu będą Państwo na bieżąco z informacjami dotyczącymi organizacji oraz treściami publikowanymi przez jej członków.&nbsp;</i></div></blockquote><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"><i style=\"text-align: right; background-color: transparent; font-size: 1rem;\">Życzymy udanego korzystania z serwisu.</i></blockquote></blockquote></blockquote></blockquote></blockquote></blockquote></blockquote></blockquote></blockquote></blockquote></blockquote>", true, admin);
        Article article2 = new Article("Konwent Partii w Rzeszowie", "<div style=\"text-align: justify;\"><span style=\"background-color: transparent; font-size: 1rem;\">Trwają rozmowy na temat organizacji </span><u style=\"background-color: transparent; font-size: 1rem;\">w <b>Rzeszowie</b> krajowego konwentu Partii</u><span style=\"background-color: transparent; font-size: 1rem;\">. Już teraz zapraszamy do wzięcia w nim udziału wszystkich członków oraz sympatyków naszej organizacji. Na konwencie będą obecni wszyscy liderzy, którzy niebawem rozpoczną przygotowania do kampanii parlamentarnej. O szczegółach poinformujemy wkrótce.&nbsp;</span></div><div style=\"text-align: justify;\"><span style=\"background-color: transparent; font-size: 1rem;\"><br></span></div><div align=\"center\"><img src=\"http://orka.sejm.gov.pl/media.nsf/photos/MDUA-A3ZHZL/%24File/KB__0049.view.jpg\" width=\"400px\"><br></div><div style=\"text-align: right;\" align=\"center\"><i>fot. Krzysztof Białoskórski&nbsp; &nbsp;</i></div>", true, presidentUser);
        Article article3 = new Article("Spotkanie organizacyjne struktur Regionu Mazowieckiego", "W najbliższy piątek odbędzie się spotkanie dla członków oraz osób, które chcą zasilić szeregi Partii w województwie Mazowieckim. Tematem przewodnim będzie tworzenie struktur w regionie. Spotkanie rozpocznie wykład dr Piotra Nowaka, który przybliży program Partii w zakresie ekonomi oraz współpracy międzynarodowej.", false, secretaryUser3);


        articleRepo.save(article1);
        articleRepo.save(article2);
        articleRepo.save(article3);


        //Dodanie wiadomości do uzytkowników
        List<User> allUsers = userRepo.findAll();
        allUsers.remove(admin);
        for(User user : allUsers) {
            Message messageFromAdminToAll = new Message("Wiadomość do wszystkich użytkowników systemu", "Dzień dobry. Witamy w Systemie Zarządzania Partią. Życzymy miłego korzystania z systemu oraz udanej współpracy w społeczności.", admin, user, allUsers);
            messageRepository.save(messageFromAdminToAll);
        }

        List<User> messagesForAdmin = new ArrayList<>();
        messagesForAdmin.add(admin);
        Message messageForAdmin = new Message("Prośba o szkolenie", "Dzień dobry. Zwracam sięz uprzejmą prośbę o instruktaż dotyczący obsługi systemu.", presidentUser, admin, messagesForAdmin);
        messageRepository.save(messageForAdmin);


        List<User> messagesForPresident = userRepo.findByName("user");
        Message messageFromAdminToPresident = new Message("Powitanie", "Dzień dobry Panie Prezesie. Jakie ma Pan wrażenia po skorzystaniu z systemu?", admin, messagesForPresident);
        messageRepository.save(messageFromAdminToPresident);

        List<User> messagesForPodkarpackie = userRepo.findByRegion("Podkarpackie");
        messagesForPodkarpackie.remove(presidentUser);
        for(User user : messagesForPodkarpackie) {
            Message messageFromAdminToPodkarpackie = new Message("Wiadomość do mieszkańców Województwa Podkarpackiego", "W najbliższych dniach pojawią się szczegółowe informacje na temat planowanego konwentu człnków oraz sympatyków Partii; Prosimy o cierpliwość.", presidentUser, user, messagesForPodkarpackie);
            messageRepository.save(messageFromAdminToPodkarpackie);
        }

        List<User> messagesForStrzyzow = userRepo.findByConstituency("Strzyżów");
        for(User user : messagesForStrzyzow) {
            Message messageToStrzyzow = new Message("Wiadomość do mieszkańców okręgu Strzyżów", "Proszę o jak najliczniejsze przybycie delegacji na zbliżający się konwent.", presidentUser, user, messagesForStrzyzow);
            messageRepository.save(messageToStrzyzow);
        }



        //Dodanie próśb o dołączenie do organizacji
        JoinMessage j1 = new JoinMessage("Dzień dobry. Uprzejmie proszę o wcielenie mnie w szeregi organizacji. Pozdrawiam", normalUser13);
        joinMessageRepository.save(j1);
        JoinMessage j2 = new JoinMessage("Witam serdecznie. Nazywam się Marcin Witos. Od pewnego czasu wnikliwie przyglądam się działalności Waszej Partii. Proszę o wcielenie mnie do struktur, abym także mógł zostać działaczem. Liczę na pozytywne rozpatrzenie mojej prośby.", normalUser14);
        joinMessageRepository.save(j2);
        JoinMessage j3 = new JoinMessage("Proszę o dołączenie do organizacji. Bardzo podoba mi się profil jej działalności i chciałbym również móc w niej pracować.", normalUser15);
        joinMessageRepository.save(j3);


        //Dodanie przykładowych zadań
        List<User> tasksFromPresidentToVicePresident = new ArrayList<>();
        tasksFromPresidentToVicePresident.add(vicePresidentUser2);
        LocalDate startDate = LocalDate.of(2020,1,29);
        LocalDate endDate = LocalDate.of(2020,3,1);
        Task taskFromPresidentToVicePresident = new Task("Propozycje dotyczące wyboru prezesów regionalnych", "Dzień dobry. Proszę o przygotowanie listy nominowanych przez Pana członków na <u>stanowiska prezesów regionów</u>.", presidentUser, vicePresidentUser2, tasksFromPresidentToVicePresident, startDate, endDate,"");
        taskRepository.save(taskFromPresidentToVicePresident);

        LocalDate startDate2 = LocalDate.of(2020,2,29);
        LocalDate endDate2 = LocalDate.of(2020,3,15);
        Task taskFromPresidentToVicePresident2 = new Task("Organizacja Sali na konwent Partii w Rzeszowie", "Odnaleźć salę, w której w pierwszym kwartale bierzącego roku będzie możliwe zorganizowanie konwentu Partii na około 100 osób.", presidentUser, vicePresidentUser2, tasksFromPresidentToVicePresident, startDate2, endDate2,"");
        taskFromPresidentToVicePresident2.setInProgress(true);
        taskRepository.save(taskFromPresidentToVicePresident2);

        LocalDate startDate3 = LocalDate.of(2020,1,3);
        LocalDate endDate3 = LocalDate.of(2020,1,14);
        Task taskFromPresidentToVicePresident3 = new Task("Firma produkująca ulotki", "Trzeba zorientować się na temat dostępnych na rynku firm, które mogłby podjąć sięzaprojektowania i wykonania ulotek zawierających program naszego ugrupowania.", presidentUser, vicePresidentUser2, tasksFromPresidentToVicePresident, startDate3, endDate3,"Ulotki moze wykonać firma XY Leaflet. Firma ma ugruntowaną pozycję na rynku i gwarantuje dobrą cenę oraz jakość swoich produktów.");
        taskFromPresidentToVicePresident3.setInProgress(true);
        taskFromPresidentToVicePresident3.setDone(true);
        taskRepository.save(taskFromPresidentToVicePresident3);



        //Dodanie przykładowego sondażu
        List<User> surveyForPresident = new ArrayList<>();
        surveyForPresident.add(presidentUser);
        Survey2 s1 = new Survey2("Czy W kraju powinno obowiazywac wiecaj prawa sprzyjajacego wolnemu rynkowi?", "Tak", "Nie", 11, 2, presidentUser, surveyForPresident, startDate, endDate);
        survey2Repository.save(s1);

        Survey3 s2 = new Survey3("Ktory z rodzajow postulatow proponowanych przez Partię jest Ci najblizszy?", "Wprowadzenie reform wolnorynkowych", "Reforma sluzby zdrowia", "Zmiany w prawie karnym", 9, 2, 4, presidentUser, surveyForPresident, startDate, endDate2);
        survey3Repository.save(s2);

        Survey4 s3 = new Survey4("Ktory z krajow jest dla Ciebie najatrakcyjniejszy pod katem emigracji zarobkowej?", "Wielka Brytania", "Francja", "Niemcy", "Hiszpania", 7, 0, 3, 5, presidentUser, surveyForPresident, startDate2, endDate3);
        survey4Repository.save(s3);


    }

}
