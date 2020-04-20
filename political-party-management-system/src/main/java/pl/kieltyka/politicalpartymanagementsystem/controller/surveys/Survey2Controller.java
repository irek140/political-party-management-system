package pl.kieltyka.politicalpartymanagementsystem.controller.surveys;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import javafx.scene.control.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.model.Vote;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey2;
import pl.kieltyka.politicalpartymanagementsystem.repository.surveys.Survey2Repository;
import pl.kieltyka.politicalpartymanagementsystem.repository.UserRepository;
import pl.kieltyka.politicalpartymanagementsystem.repository.VoteRepository;
import pl.kieltyka.politicalpartymanagementsystem.services.EmailService;

import javax.persistence.Table;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class Survey2Controller {

    @Autowired
    Survey2Repository survey2Repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    private EmailService emailService;


    //Metody używane do pracy z ankietą z dwiema odpowiedziami
    @PreAuthorize("hasAuthority('ROLE_MEMBER') or hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/survey2/create", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createSurvey2(@RequestBody Survey2 survey2) {
        System.out.println("Create new survey2...");
        LocalDate date = LocalDate.now();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Ankieta - region: '" + survey2.getRegion() + "', okręg: '" + survey2.getConstituency() + "'");
        List<User> usersList = userRepository.findAll();
        usersList.remove(0);
        usersList.remove(0);
        List<User> addressees = new ArrayList<User>();
        List<Vote> votes = new ArrayList<Vote>();
        String region = survey2.getRegion();
        String constituency = survey2.getConstituency();
        if ((survey2.getConstituency() != "") && (survey2.getRegion() == "")) {
            System.out.println("survey2.getConstituency() != null && survey2.getRegion() == ''");
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
            survey2Repository.save(
                    new Survey2(survey2.getQuestion(), survey2.getAnswer1(), survey2.getAnswer2(), survey2.getValue1(), survey2.getValue2(), survey2.getRegion(), survey2.getConstituency(), (User) userDetails, votes, addressees, date, survey2.getEnddate()));

        } else if ((survey2.getRegion() != "") && (survey2.getConstituency() == "")) {
            System.out.println("survey2.getRegion() != null && survey2.getConstituency() == ''");
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
            survey2Repository.save(
                    new Survey2(survey2.getQuestion(), survey2.getAnswer1(), survey2.getAnswer2(), survey2.getValue1(), survey2.getValue2(), survey2.getRegion(), survey2.getConstituency(), (User) userDetails, votes, addressees, date, survey2.getEnddate()));

        } else if ((survey2.getRegion() != "") && (survey2.getConstituency() != "")) {
            System.out.println("survey2.getRegion() != null && survey2.getConstituency() != null");
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
            survey2Repository.save(
                    new Survey2(survey2.getQuestion(), survey2.getAnswer1(), survey2.getAnswer2(), survey2.getValue1(), survey2.getValue2(), survey2.getRegion(), survey2.getConstituency(), (User) userDetails, votes, addressees, date, survey2.getEnddate()));

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
            survey2Repository.save(
                    new Survey2(survey2.getQuestion(), survey2.getAnswer1(), survey2.getAnswer2(), survey2.getValue1(), survey2.getValue2(), survey2.getRegion(), survey2.getConstituency(), (User) userDetails, votes, usersList, date, survey2.getEnddate()));
        }
            System.out.println();
            return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_MEMBER') or hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/survey2/create-for-members", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createSurvey2ForMembers(@RequestBody Survey2 survey2) {
        System.out.println("Create new survey2...");
        LocalDate date = LocalDate.now();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Ankieta - region: '" + survey2.getRegion() + "', okręg: '" + survey2.getConstituency() + "'");
        List<User> usersList = userRepository.findAll();
        usersList.remove(0);
        usersList.remove(0);
        List<User> addressees = new ArrayList<User>();
        List<Vote> votes = new ArrayList<Vote>();
        String region = survey2.getRegion();
        String constituency = survey2.getConstituency();
        if ((survey2.getConstituency() != "") && (survey2.getRegion() == "")) {
            System.out.println("survey2.getConstituency() != null && survey2.getRegion() == ''");
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
            survey2Repository.save(
                    new Survey2(survey2.getQuestion(), survey2.getAnswer1(), survey2.getAnswer2(), survey2.getValue1(), survey2.getValue2(), survey2.getRegion(), survey2.getConstituency(), (User) userDetails, votes, addressees, date, survey2.getEnddate()));

        } else if ((survey2.getRegion() != "") && (survey2.getConstituency() == "")) {
            System.out.println("survey2.getRegion() != null && survey2.getConstituency() == ''");
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
            survey2Repository.save(
                    new Survey2(survey2.getQuestion(), survey2.getAnswer1(), survey2.getAnswer2(), survey2.getValue1(), survey2.getValue2(), survey2.getRegion(), survey2.getConstituency(), (User) userDetails, votes, addressees, date, survey2.getEnddate()));

        } else if ((survey2.getRegion() != "") && (survey2.getConstituency() != "")) {
            System.out.println("survey2.getRegion() != null && survey2.getConstituency() != null");
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
            survey2Repository.save(
                    new Survey2(survey2.getQuestion(), survey2.getAnswer1(), survey2.getAnswer2(), survey2.getValue1(), survey2.getValue2(), survey2.getRegion(), survey2.getConstituency(), (User) userDetails, votes, addressees, date, survey2.getEnddate()));

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
            survey2Repository.save(
                    new Survey2(survey2.getQuestion(), survey2.getAnswer1(), survey2.getAnswer2(), survey2.getValue1(), survey2.getValue2(), survey2.getRegion(), survey2.getConstituency(), (User) userDetails, votes, usersList, date, survey2.getEnddate()));
        }
        System.out.println();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value="/survey2/list-to-vote", consumes = {"application/json"}, produces = {"application/json"})
    public List<Survey2> getSurveys2ForMeToVote() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Get all syrveys2 from user: " + userDetails.getUsername() + "...");
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<Survey2> allSurveys;
        List<Survey2> surveys = new ArrayList<>();
        allSurveys = survey2Repository.findAll();
        for(Survey2 s : allSurveys) {
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
    @GetMapping(value="/survey2/list-with-results", consumes = {"application/json"}, produces = {"application/json"})
    public List<Survey2> getSurveys2ForMeWithResults() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Get all syrveys2 from user: " + userDetails.getUsername() + "...");
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<Survey2> allSurveys;
        List<Survey2> surveys = new ArrayList<>();
        allSurveys = survey2Repository.findAll();
        for(Survey2 s : allSurveys) {
            if(s.getAddressees().contains(user)) {
                    surveys.add(s);
            }
        }
        List<Survey2> surveysByOwner = survey2Repository.findByOwner(user);
        surveys.addAll(surveysByOwner);
        return surveys;
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/survey2/vote1/{id}")
    public ResponseEntity<Survey2> vote1(@PathVariable("id") long id) {
        System.out.println("Zagłosowano!");
        System.out.println(id);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Survey2> survey = survey2Repository.findById(id);
        if (survey != null) {
            Survey2 _survey = survey.get();
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

            return new ResponseEntity<>(survey2Repository.save(_survey), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/survey2/vote2/{id}")
    public ResponseEntity<Survey2> vote2(@PathVariable("id") long id) {
        System.out.println("Zagłosowano!");
        System.out.println(id);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Survey2> survey = survey2Repository.findById(id);
        if (survey != null) {
            Survey2 _survey = survey.get();
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

            return new ResponseEntity<>(survey2Repository.save(_survey), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
    @DeleteMapping("/survey2/delete/{id}")
    public ResponseEntity<String> deleteSurvey2(@PathVariable("id") long id) {
        System.out.println("Delete Survey2 with ID = " + id + "...");
        survey2Repository.deleteById(id);
        return new ResponseEntity<>("Survey2 has been deleted!", HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @GetMapping(value="/survey2/generate-raport/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public Survey2 generateSurveys2Raport(@PathVariable("id") long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Survey2> optionalSurvey = survey2Repository.findById(id);
        Survey2 survey = new Survey2();

        if(optionalSurvey.isPresent()) {
            survey = optionalSurvey.get();
            double count = survey.getValue1() + survey.getValue2();
            String voted1, voted2;
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
            String percentages1 = ((survey.getValue1()/count) * 100) + "";
            String percentages2 = ((survey.getValue2()/count) * 100) + "";
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



//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
//            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
//            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
//    @GetMapping(value="/survey2/generate-raport/{id}", consumes = {"application/json"}, produces = {"application/json"})
//    public Survey2 generateSurveys2Raport(@PathVariable("id") long id) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userRepository.findByUsername(userDetails.getUsername());
//        Optional<Survey2> optionalSurvey = survey2Repository.findById(id);
//        Survey2 survey = new Survey2();
//
//        if(optionalSurvey.isPresent()) {
//            survey = optionalSurvey.get();
//            double count = survey.getValue1() + survey.getValue2();
//            String voted1, voted2;
//            if(survey.getValue1() > 0) {
//                voted1 = survey.getValue1() + "";
//            } else {
//                voted1 = "0";
//            }
//            if(survey.getValue2() > 0) {
//                voted2 = survey.getValue2() + "";
//            } else {
//                voted2 = "0";
//            }
//            String percentages1 = ((survey.getValue1()/count) * 100) + "";
//            String percentages2 = ((survey.getValue2()/count) * 100) + "";
//            try {
//                String nameOfRaport = "raport-" + survey.getId() + "-" + survey.getOwner().getName() + "-" + survey.getOwner().getSurname() + ".pdf";
//                String path = "C:\\Users\\Irek\\Desktop\\" + nameOfRaport;
//                Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
//                BaseFont font = BaseFont.createFont("E:\\Praca-Inzynierska\\political-party-management-system\\src\\main\\resources\\font\\Aller.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//                Font polFont = new Font(font, 20, Font.BOLD);
//                BaseFont font2 = BaseFont.createFont("E:\\Praca-Inzynierska\\political-party-management-system\\src\\main\\resources\\font\\Asap.otf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//                Font polFontParagraph = new Font(font2, 14);
//                Document document = new Document();
//                PdfWriter.getInstance(document, new FileOutputStream(path));
//                document.open();
//                Paragraph preface = new Paragraph("Raport z przeprowadzonego głosowania", polFont);
//                preface.setAlignment(Element.ALIGN_CENTER);
//                document.add(preface);
//                document.add(new Paragraph(30f, " "));
//
//                //Image image1 = Image.getInstance("E:\\Praca-Inzynierska\\political-party-management-system\\src\\main\\resources\\godlo-polski.jpg");
//
//                Paragraph content = new Paragraph();
//                content.add(new Paragraph(
//                        "Autor Pytania: " + user.getName() + " " + user.getSurname(), polFontParagraph ));
//                content.add(new Paragraph(
//                        "Data utworzenia pytania: " + survey.getCreated(), polFontParagraph ));
//                content.add(new Paragraph(30f, " "));
//                document.add(content);
//
//                Paragraph question = new Paragraph();
//                question.add(new Paragraph(
//                        "Treść pytania: " + survey.getQuestion(), polFontParagraph ));
//                question.add(new Paragraph(20f, " "));
//                document.add(question);
//
//                PdfPTable table = new PdfPTable(3);
//                PdfPCell c1 = new PdfPCell(new Phrase("Odpowiedź", polFontParagraph));
//                c1.setMinimumHeight(25f);
//                c1.setPaddingBottom(3f);
//                table.addCell(c1);
//                c1 = new PdfPCell(new Phrase("Ilość głosów", polFontParagraph));
//                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                c1.setMinimumHeight(25f);
//                c1.setPaddingBottom(3f);
//                table.addCell(c1);
//                c1 = new PdfPCell(new Phrase("Procent", polFontParagraph));
//                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                c1.setMinimumHeight(25f);
//                c1.setPaddingBottom(3f);
//                table.addCell(c1);
//                table.setHeaderRows(1);
//
//
//                c1 = new PdfPCell(new Phrase(survey.getAnswer1(), polFontParagraph));
//                c1.setMinimumHeight(25f);
//                c1.setPaddingBottom(3f);
//                table.addCell(c1);
//                c1 = new PdfPCell(new Phrase(voted1, polFontParagraph));
//                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                c1.setMinimumHeight(25f);
//                c1.setPaddingBottom(3f);
//                table.addCell(c1);
//                c1 = new PdfPCell(new Phrase(percentages1, polFontParagraph));
//                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                c1.setMinimumHeight(25f);
//                c1.setPaddingBottom(3f);
//                table.addCell(c1);
//
//
//                c1 = new PdfPCell(new Phrase(survey.getAnswer2(), polFontParagraph));
//                c1.setMinimumHeight(25f);
//                c1.setPaddingBottom(3f);
//                table.addCell(c1);
//                c1 = new PdfPCell(new Phrase(voted2, polFontParagraph));
//                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                c1.setMinimumHeight(25f);
//                c1.setPaddingBottom(3f);
//                table.addCell(c1);
//                c1 = new PdfPCell(new Phrase(percentages2, polFontParagraph));
//                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                c1.setMinimumHeight(25f);
//                c1.setPaddingBottom(3f);
//                table.addCell(c1);
//
//                table.setPaddingTop(4f);
//                float [] pointColumnWidths = {220F, 70F, 70F};
//                table.setWidths(pointColumnWidths);
//                document.add(table);
//
//                document.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return survey;
//    }

}