package pl.kieltyka.politicalpartymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "join_messages")
public class JoinMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition="TEXT")
    private String content;

    //@JsonBackReference
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column
    private boolean isMember = false;


    public JoinMessage() {
    }

    public JoinMessage(String content, User author) {
        this.content = content;
        this.author = author;
    }

    public JoinMessage(String content, User author, boolean isMember) {
        this.content = content;
        this.author = author;
        this.isMember = isMember;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

}
