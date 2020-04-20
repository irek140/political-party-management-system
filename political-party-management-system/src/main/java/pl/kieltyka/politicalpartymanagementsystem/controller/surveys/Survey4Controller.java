package pl.kieltyka.politicalpartymanagementsystem.controller.surveys;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.model.Vote;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey4;
import pl.kieltyka.politicalpartymanagementsystem.repository.UserRepository;
import pl.kieltyka.politicalpartymanagementsystem.repository.VoteRepository;
import pl.kieltyka.politicalpartymanagementsystem.repository.surveys.Survey4Repository;
import pl.kieltyka.politicalpartymanagementsystem.services.EmailService;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class Survey4Controller {

    @Autowired
    Survey4Repository survey4Repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    private EmailService emailService;


    @PreAuthorize("hasAuthority('ROLE_MEMBER') or hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/survey4/create", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createSurvey4(@RequestBody Survey4 survey4) {
        System.out.println("Create new survey3...");
        LocalDate date = LocalDate.now();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Ankieta - region: '" + survey4.getRegion() + "', okręg: '" + survey4.getConstituency() + "'");
        List<User> usersList = userRepository.findAll();
        usersList.remove(0);
        usersList.remove(0);
        List<User> addressees = new ArrayList<User>();
        List<Vote> votes = new ArrayList<Vote>();
        String region = survey4.getRegion();
        String constituency = survey4.getConstituency();
        if ((survey4.getConstituency() != "") && (survey4.getRegion() == "")) {
            System.out.println("survey4.getConstituency() != null && survey4.getRegion() == ''");
            for (User user : usersList) {
                System.out.println("jestem w pętli for");
                System.out.println(user.getUsername() + ", region: '" + user.getRegion() + "', okręg: '" + user.getConstituency() + "'");
                if(Objects.equals(user.getConstituency(), constituency)) {
                    System.out.println("user o id: " + user.getId());
                    if(user.getEmail() != null) {
                        emailService.sendSurveyNotification(user);
                    }
                    addressees.add(user);
                    Vote vote = new Vote(user.getId());
                    votes.add(vote);
                    System.out.println("O " + user.getUsername());
                }
            }
            survey4Repository.save(
                    new Survey4(survey4.getQuestion(), survey4.getAnswer1(), survey4.getAnswer2(), survey4.getAnswer3(), survey4.getAnswer4(), survey4.getValue1(), survey4.getValue2(), survey4.getValue3(), survey4.getValue4(), survey4.getRegion(), survey4.getConstituency(), (User) userDetails, votes, addressees, date, survey4.getEnddate()));

        } else if ((survey4.getRegion() != "") && ((survey4.getConstituency() == ""))) {
            System.out.println("survey4.getRegion() != null && survey4.getConstituency() == ''");
            for (User user : usersList) {
                System.out.println("jestem w pętli for");
                System.out.println(user.getUsername() + ", region: '" + user.getRegion() + "', okręg: '" + user.getConstituency() + "'");
                if (Objects.equals(user.getRegion(), region)) {
                    System.out.println("user o id: " + user.getId());
                    if(user.getEmail() != null) {
                        emailService.sendSurveyNotification(user);
                    }
                    addressees.add(user);
                    Vote vote = new Vote(user.getId());
                    votes.add(vote);
                    System.out.println("R " + user.getUsername());
                }
            }
            survey4Repository.save(
                    new Survey4(survey4.getQuestion(), survey4.getAnswer1(), survey4.getAnswer2(), survey4.getAnswer3(), survey4.getAnswer4(), survey4.getValue1(), survey4.getValue2(), survey4.getValue3(), survey4.getValue4(), survey4.getRegion(), survey4.getConstituency(), (User) userDetails, votes, addressees, date, survey4.getEnddate()));

        } else if ((survey4.getRegion() != "") && (survey4.getConstituency() != "")) {
            System.out.println("survey4.getRegion() != null && survey4.getConstituency() != null");
            for (User user : usersList) {
                System.out.println("jestem w pętli for");
                System.out.println(user.getUsername() + ", region: '" + user.getRegion() + "', okręg: '" + user.getConstituency() + "'");
                if ((user.getConstituency().equals(constituency)) && (user.getRegion().equals(region))) {
                    System.out.println("user o id: " + user.getId());
                    if(user.getEmail() != null) {
                        emailService.sendSurveyNotification(user);
                    }
                    addressees.add(user);
                    Vote vote = new Vote(user.getId());
                    votes.add(vote);
                    System.out.println("O&R " + user.getUsername());
                }
            }
            survey4Repository.save(
                    new Survey4(survey4.getQuestion(), survey4.getAnswer1(), survey4.getAnswer2(), survey4.getAnswer3(), survey4.getAnswer4(), survey4.getValue1(), survey4.getValue2(), survey4.getValue3(), survey4.getValue4(), survey4.getRegion(), survey4.getConstituency(), (User) userDetails, votes, addressees, date, survey4.getEnddate()));

        } else {
            System.out.println("else");
            for (User user : usersList) {
                System.out.println("else - user o id: " + user.getId());
                if(user.getEmail() != null) {
                    emailService.sendSurveyNotification(user);
                }
                Vote vote = new Vote(user.getId());
                votes.add(vote);
            }
            survey4Repository.save(
                    new Survey4(survey4.getQuestion(), survey4.getAnswer1(), survey4.getAnswer2(), survey4.getAnswer3(), survey4.getAnswer4(), survey4.getValue1(), survey4.getValue2(), survey4.getValue3(), survey4.getValue4(), survey4.getRegion(), survey4.getConstituency(), (User) userDetails, votes, usersList, date, survey4.getEnddate()));
        }
        System.out.println();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_MEMBER') or hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/survey4/create-for-members", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createSurvey4ForMembers(@RequestBody Survey4 survey4) {
        System.out.println("Create new survey3...");
        LocalDate date = LocalDate.now();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Ankieta - region: '" + survey4.getRegion() + "', okręg: '" + survey4.getConstituency() + "'");
        List<User> usersList = userRepository.findAll();
        usersList.remove(0);
        usersList.remove(0);
        List<User> addressees = new ArrayList<User>();
        List<Vote> votes = new ArrayList<Vote>();
        String region = survey4.getRegion();
        String constituency = survey4.getConstituency();
        if ((survey4.getConstituency() != "") && (survey4.getRegion() == "")) {
            System.out.println("survey4.getConstituency() != null && survey4.getRegion() == ''");
            for (User user : usersList) {
                if(user.isMember()) {
                    System.out.println("jestem w pętli for");
                    System.out.println(user.getUsername() + ", region: '" + user.getRegion() + "', okręg: '" + user.getConstituency() + "'");
                    if(Objects.equals(user.getConstituency(), constituency)) {
                        System.out.println("user o id: " + user.getId());
                        if(user.getEmail() != null) {
                            emailService.sendSurveyNotification(user);
                        }
                        addressees.add(user);
                        Vote vote = new Vote(user.getId());
                        votes.add(vote);
                        System.out.println("O " + user.getUsername());
                    }
                }
            }
            survey4Repository.save(
                    new Survey4(survey4.getQuestion(), survey4.getAnswer1(), survey4.getAnswer2(), survey4.getAnswer3(), survey4.getAnswer4(), survey4.getValue1(), survey4.getValue2(), survey4.getValue3(), survey4.getValue4(), survey4.getRegion(), survey4.getConstituency(), (User) userDetails, votes, addressees, date, survey4.getEnddate()));

        } else if ((survey4.getRegion() != "") && ((survey4.getConstituency() == ""))) {
            System.out.println("survey4.getRegion() != null && survey4.getConstituency() == ''");
            for (User user : usersList) {
                if(user.isMember()) {
                    System.out.println("jestem w pętli for");
                    System.out.println(user.getUsername() + ", region: '" + user.getRegion() + "', okręg: '" + user.getConstituency() + "'");
                    if (Objects.equals(user.getRegion(), region)) {
                        System.out.println("user o id: " + user.getId());
                        if(user.getEmail() != null) {
                            emailService.sendSurveyNotification(user);
                        }
                        addressees.add(user);
                        Vote vote = new Vote(user.getId());
                        votes.add(vote);
                        System.out.println("R " + user.getUsername());
                    }
                }
            }
            survey4Repository.save(
                    new Survey4(survey4.getQuestion(), survey4.getAnswer1(), survey4.getAnswer2(), survey4.getAnswer3(), survey4.getAnswer4(), survey4.getValue1(), survey4.getValue2(), survey4.getValue3(), survey4.getValue4(), survey4.getRegion(), survey4.getConstituency(), (User) userDetails, votes, addressees, date, survey4.getEnddate()));

        } else if ((survey4.getRegion() != "") && (survey4.getConstituency() != "")) {
            System.out.println("survey4.getRegion() != null && survey4.getConstituency() != null");
            for (User user : usersList) {
                if(user.isMember()) {
                    System.out.println("jestem w pętli for");
                    System.out.println(user.getUsername() + ", region: '" + user.getRegion() + "', okręg: '" + user.getConstituency() + "'");
                    if ((user.getConstituency().equals(constituency)) && (user.getRegion().equals(region))) {
                        System.out.println("user o id: " + user.getId());
                        if(user.getEmail() != null) {
                            emailService.sendSurveyNotification(user);
                        }
                        addressees.add(user);
                        Vote vote = new Vote(user.getId());
                        votes.add(vote);
                        System.out.println("O&R " + user.getUsername());
                    }
                }
            }
            survey4Repository.save(
                    new Survey4(survey4.getQuestion(), survey4.getAnswer1(), survey4.getAnswer2(), survey4.getAnswer3(), survey4.getAnswer4(), survey4.getValue1(), survey4.getValue2(), survey4.getValue3(), survey4.getValue4(), survey4.getRegion(), survey4.getConstituency(), (User) userDetails, votes, addressees, date, survey4.getEnddate()));

        } else {
            System.out.println("else");
            for (User user : usersList) {
                if(user.isMember()) {
                    System.out.println("else - user o id: " + user.getId());
                    if(user.getEmail() != null) {
                        emailService.sendSurveyNotification(user);
                    }
                    Vote vote = new Vote(user.getId());
                    votes.add(vote);
                }
            }
            survey4Repository.save(
                    new Survey4(survey4.getQuestion(), survey4.getAnswer1(), survey4.getAnswer2(), survey4.getAnswer3(), survey4.getAnswer4(), survey4.getValue1(), survey4.getValue2(), survey4.getValue3(), survey4.getValue4(), survey4.getRegion(), survey4.getConstituency(), (User) userDetails, votes, usersList, date, survey4.getEnddate()));
        }
        System.out.println();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value="/survey4/list-to-vote", consumes = {"application/json"}, produces = {"application/json"})
    public List<Survey4> getSurveys4ForMeToVote() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Get all syrveys4 from user: " + userDetails.getUsername() + "...");
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<Survey4> allSurveys;
        List<Survey4> surveys = new ArrayList<>();
        allSurveys = survey4Repository.findAll();
        for(Survey4 s : allSurveys) {
            if(s.getAddressees().contains(user)) {
                List<Vote> voteList = s.getVotes();
                if(!voteList.isEmpty()) {
                    for(Vote vote : voteList) {
                        if(vote.getUserid() == user.getId()) {
                            if(!vote.isVoted()) {
                                surveys.add(s);
                            }
                        }
                    }
                }
            }
        }
        return surveys;
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value="/survey4/list-with-results", consumes = {"application/json"}, produces = {"application/json"})
    public List<Survey4> getSurveys4ForMeWithResults() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Get all syrveys4 from user: " + userDetails.getUsername() + "...");
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<Survey4> allSurveys;
        List<Survey4> surveys = new ArrayList<>();
        allSurveys = survey4Repository.findAll();
        for(Survey4 s : allSurveys) {
            if(s.getAddressees().contains(user)) {
                surveys.add(s);
            }
        }
        return surveys;
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/survey4/vote1/{id}")
    public ResponseEntity<Survey4> vote1(@PathVariable("id") long id) {
        System.out.println("Zagłosowano!");
        System.out.println(id);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Survey4> survey = survey4Repository.findById(id);
        if (survey != null) {
            Survey4 _survey = survey.get();
            List<Vote> voteList = _survey.getVotes();
            for(Vote v : voteList) {
                if(v.getUserid() == user.getId()) {
                    if(!v.isVoted()) {
                        v.setVoted(true);
                        _survey.setValue1(_survey.getValue1() + 1);
                    }
                }
            }
            _survey.setVotes(voteList);

            return new ResponseEntity<>(survey4Repository.save(_survey), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/survey4/vote2/{id}")
    public ResponseEntity<Survey4> vote2(@PathVariable("id") long id) {
        System.out.println("Zagłosowano!");
        System.out.println(id);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Survey4> survey = survey4Repository.findById(id);
        if (survey != null) {
            Survey4 _survey = survey.get();
            List<Vote> voteList = _survey.getVotes();
            for(Vote v : voteList) {
                if(v.getUserid() == user.getId()) {
                    if(!v.isVoted()) {
                        v.setVoted(true);
                        _survey.setValue2(_survey.getValue2() + 1);
                    }
                }
            }
            _survey.setVotes(voteList);

            return new ResponseEntity<>(survey4Repository.save(_survey), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/survey4/vote3/{id}")
    public ResponseEntity<Survey4> vote3(@PathVariable("id") long id) {
        System.out.println("Zagłosowano!");
        System.out.println(id);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Survey4> survey = survey4Repository.findById(id);
        if (survey != null) {
            Survey4 _survey = survey.get();
            List<Vote> voteList = _survey.getVotes();
            for(Vote v : voteList) {
                if(v.getUserid() == user.getId()) {
                    if(!v.isVoted()) {
                        v.setVoted(true);
                        _survey.setValue3(_survey.getValue3() + 1);
                    }
                }
            }
            _survey.setVotes(voteList);

            return new ResponseEntity<>(survey4Repository.save(_survey), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/survey4/vote4/{id}")
    public ResponseEntity<Survey4> vote4(@PathVariable("id") long id) {
        System.out.println("Zagłosowano!");
        System.out.println(id);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Survey4> survey = survey4Repository.findById(id);
        if (survey != null) {
            Survey4 _survey = survey.get();
            List<Vote> voteList = _survey.getVotes();
            for(Vote v : voteList) {
                if(v.getUserid() == user.getId()) {
                    if(!v.isVoted()) {
                        v.setVoted(true);
                        _survey.setValue4(_survey.getValue4() + 1);
                    }
                }
            }
            _survey.setVotes(voteList);

            return new ResponseEntity<>(survey4Repository.save(_survey), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
    @DeleteMapping("/survey4/delete/{id}")
    public ResponseEntity<String> deleteSurvey4(@PathVariable("id") long id) {
        System.out.println("Delete Survey4 with ID = " + id + "...");
        survey4Repository.deleteById(id);
        return new ResponseEntity<>("Survey4 has been deleted!", HttpStatus.OK);
    }




    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @GetMapping(value="/survey4/generate-raport/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public Survey4 generateSurveys4Raport(@PathVariable("id") long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Survey4> optionalSurvey = survey4Repository.findById(id);
        Survey4 survey = new Survey4();

        if(optionalSurvey.isPresent()) {
            survey = optionalSurvey.get();
            double count = survey.getValue1() + survey.getValue2() + survey.getValue3() + survey.getValue4();
            String voted1, voted2, voted3, voted4;
            if(survey.getValue1() > 0) {
                voted1 = survey.getValue1() + "";
            } else {
                voted1 = "0";
            }
            if(survey.getValue2() > 0) {
                voted2 = survey.getValue2() + "";
            } else {
                voted2 = "0";
            }
            if(survey.getValue3() > 0) {
                voted3 = survey.getValue3() + "";
            } else {
                voted3 = "0";
            }
            if(survey.getValue4() > 0) {
                voted4 = survey.getValue4() + "";
            } else {
                voted4 = "0";
            }
            String percentages1 = ((survey.getValue1()/count) * 100) + "";
            String percentages2 = ((survey.getValue2()/count) * 100) + "";
            String percentages3 = ((survey.getValue3()/count) * 100) + "";
            String percentages4 = ((survey.getValue4()/count) * 100) + "";
            try {
                String nameOfRaport = "raport-" + survey.getId() + "-" + survey.getOwner().getName() + "-" + survey.getOwner().getSurname() + ".pdf";
                String path = "C:\\Users\\Irek\\Desktop\\" + nameOfRaport;
                Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
                BaseFont font = BaseFont.createFont("E:\\Praca-Inzynierska\\political-party-management-system\\src\\main\\resources\\font\\Aller.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font polFont = new Font(font, 20, Font.BOLD);
                BaseFont font2 = BaseFont.createFont("E:\\Praca-Inzynierska\\political-party-management-system\\src\\main\\resources\\font\\Asap.otf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font polFontParagraph = new Font(font2, 14);
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(path));
                document.open();
                Paragraph preface = new Paragraph("Raport z przeprowadzonego głosowania", polFont);
                preface.setAlignment(Element.ALIGN_CENTER);
                document.add(preface);
                document.add(new Paragraph(30f, " "));

                //Image image1 = Image.getInstance("E:\\Praca-Inzynierska\\political-party-management-system\\src\\main\\resources\\godlo-polski.jpg");

                Paragraph content = new Paragraph();
                content.add(new Paragraph(
                        "Autor Pytania: " + user.getName() + " " + user.getSurname(), polFontParagraph ));
                content.add(new Paragraph(
                        "Data utworzenia pytania: " + survey.getCreated(), polFontParagraph ));
                content.add(new Paragraph(30f, " "));
                document.add(content);

                Paragraph question = new Paragraph();
                question.add(new Paragraph(
                        "Treść pytania: " + survey.getQuestion(), polFontParagraph ));
                question.add(new Paragraph(20f, " "));
                document.add(question);

                PdfPTable table = new PdfPTable(3);
                PdfPCell c1 = new PdfPCell(new Phrase("Odpowiedź", polFontParagraph));
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase("Ilość głosów", polFontParagraph));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase("Procent", polFontParagraph));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);
                table.setHeaderRows(1);


                c1 = new PdfPCell(new Phrase(survey.getAnswer1(), polFontParagraph));
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(voted1, polFontParagraph));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(percentages1, polFontParagraph));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);


                c1 = new PdfPCell(new Phrase(survey.getAnswer2(), polFontParagraph));
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(voted2, polFontParagraph));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(percentages2, polFontParagraph));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);


                c1 = new PdfPCell(new Phrase(survey.getAnswer3(), polFontParagraph));
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(voted3, polFontParagraph));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(percentages3, polFontParagraph));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);


                c1 = new PdfPCell(new Phrase(survey.getAnswer4(), polFontParagraph));
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(voted4, polFontParagraph));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);
                c1 = new PdfPCell(new Phrase(percentages4, polFontParagraph));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setMinimumHeight(25f);
                c1.setPaddingBottom(3f);
                table.addCell(c1);

                table.setPaddingTop(4f);
                float [] pointColumnWidths = {220F, 70F, 70F};
                table.setWidths(pointColumnWidths);
                document.add(table);

                document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return survey;
    }


}
