package pl.kieltyka.politicalpartymanagementsystem.model.surveys;

import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.model.Vote;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "survey4")
public class Survey4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(columnDefinition="TEXT")
    private String question;
    @Column(columnDefinition="TEXT")
    private String answer1;
    @Column(columnDefinition="TEXT")
    private String answer2;
    @Column(columnDefinition="TEXT")
    private String answer3;
    @Column(columnDefinition="TEXT")
    private String answer4;
    @Column
    private int value1;
    @Column
    private int value2;
    @Column
    private int value3;
    @Column
    private int value4;
    @Column
    private String region;
    @Column
    private String constituency;
    @Column
    private User owner;
    @ManyToMany(cascade=CascadeType.ALL)
    private List<Vote> votes;
    @ManyToMany
    private List<User> addressees;
    @Column
    private LocalDate created;
    @Column
    private LocalDate enddate;


    public Survey4(String question, String answer1, String answer2, String answer3, String answer4, int value1, int value2, int value3, int value4, String region, String constituency, User owner, List<Vote> votes, List<User> addressees) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.region = region;
        this.constituency = constituency;
        this.owner = owner;
        this.votes = votes;
        this.addressees = addressees;
    }

    public Survey4(String question, String answer1, String answer2, String answer3, String answer4, int value1, int value2, int value3, int value4, String region, String constituency, User owner, List<Vote> votes, List<User> addressees, LocalDate created) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.region = region;
        this.constituency = constituency;
        this.owner = owner;
        this.votes = votes;
        this.addressees = addressees;
        this.created = created;
    }

    public Survey4(String question, String answer1, String answer2, String answer3, String answer4, int value1, int value2, int value3, int value4, String region, String constituency, User owner, List<Vote> votes, List<User> addressees, LocalDate created, LocalDate enddate) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.region = region;
        this.constituency = constituency;
        this.owner = owner;
        this.votes = votes;
        this.addressees = addressees;
        this.created = created;
        this.enddate = enddate;
    }

    public Survey4() {
    }

    public Survey4(String question, String answer1, String answer2, String answer3, String answer4, int value1, int value2, int value3, int value4, User owner, List<User> addressees, LocalDate created, LocalDate enddate) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.owner = owner;
        this.addressees = addressees;
        this.created = created;
        this.enddate = enddate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public int getValue3() {
        return value3;
    }

    public void setValue3(int value3) {
        this.value3 = value3;
    }

    public int getValue4() {
        return value4;
    }

    public void setValue4(int value4) {
        this.value4 = value4;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<User> getAddressees() {
        return addressees;
    }

    public void setAddressees(List<User> addressees) {
        this.addressees = addressees;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }
}
