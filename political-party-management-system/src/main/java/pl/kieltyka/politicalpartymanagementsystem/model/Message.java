package pl.kieltyka.politicalpartymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column(columnDefinition="TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User principal;

    @OneToOne
    private User recipient;

    //@OneToMany(orphanRemoval = false)
    @ManyToMany
    private List<User> addressees;


    public Message() {
    }

    public Message(String title, String content, User principal, List<User> addressees) {
        this.title = title;
        this.content = content;
        this.principal = principal;
        this.addressees = addressees;
    }

    public Message(String title, String content, List<User> addressees) {
        this.title = title;
        this.content = content;
        this.addressees = addressees;
    }

    public Message(String title, String content, User principal, User recipient, List<User> addressees) {
        this.title = title;
        this.content = content;
        this.principal = principal;
        this.recipient = recipient;
        this.addressees = addressees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getPrincipal() {
        return principal;
    }

    public void setPrincipal(User principal) {
        this.principal = principal;
    }

    public List<User> getAddressees() {
        return addressees;
    }

    public void setAddressees(List<User> addressees) {
        this.addressees = addressees;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
