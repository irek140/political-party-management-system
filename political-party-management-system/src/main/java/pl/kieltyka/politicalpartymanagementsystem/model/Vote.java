package pl.kieltyka.politicalpartymanagementsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long userid;
    @Column
    private boolean voted = false;


    public Vote(long user_id, boolean voted) {
        this.userid = user_id;
        this.voted = voted;
    }

    public Vote(long user_id) {
        this.userid = user_id;
    }

    public Vote() {
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long user_id) {
        this.userid = user_id;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
}
