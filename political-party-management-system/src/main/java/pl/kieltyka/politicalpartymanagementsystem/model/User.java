package pl.kieltyka.politicalpartymanagementsystem.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user_data")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = -3924170999808980476L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name="username", unique=true)
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private boolean enabled;
    @Column
    private String  name;
    @Column
    private String  surname;

    @Column
    private boolean sendRequest = false;

    @Column
    private boolean member = false;

    @Column
    private boolean partyPresident; //prezes partii
    @Column
    private boolean partyVicePresident; //wiceprezes partii
    @Column
    private boolean partySecretary; //sekretarz partii
    @Column
    private String region = "-";
    @Column
    private boolean regionPresident; //prezes regionu
    @Column
    private boolean regionVicePresident;
    @Column
    private boolean regionSecretary;
    @Column
    private String constituency = "-"; //okręg wyborczy
    @Column
    private boolean constituencyPresident; //prezes okręgu
    @Column
    private boolean constituencyVicePresident;
    @Column
    private boolean constituencySecretary;

    @Column
    private boolean nationalBoard; //zarząd krajowy
    @Column
    private boolean nationalCouncil; //rada krajowa
    @Column
    private boolean partyCourt; //sąd partyjny

    @Column
    private boolean banned = false; //czy jest zbanowany

    @ManyToMany(fetch=FetchType.EAGER)
    private Collection<Authority> authorities;

    @OneToOne(cascade=CascadeType.ALL)
    private ActivationCode activationCode;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", orphanRemoval = false)
    private Collection<Article> userArticles;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "principal", orphanRemoval = false)
    private List<Task> userTasks;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "principal", orphanRemoval = false)
    private List<Message> userMessages;


    public User() {
        this.authorities = Collections.emptyList();
    }

    public User(String username, String password, Collection<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User(String username, String password, Collection<Authority> authorities, String name, String surname) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.name = name;
        this.surname = surname;
    }

    public User(String username, String password, String email, boolean enabled, String name, String surname, boolean partyPresident, boolean partyVicePresident, boolean partySecretary, String region, boolean regionPresident, boolean regionVicePresident, boolean regionSecretary, String constituency, boolean constituencyPresident, boolean constituencyVicePresident, boolean constituencySecretary, boolean nationalBoard, boolean nationalCouncil, boolean partyCourt, Collection<Authority> authorities, ActivationCode activationCode, Collection<Article> userArticles, List<Task> userTasks, List<Message> userMessages) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.name = name;
        this.surname = surname;
        this.partyPresident = partyPresident;
        this.partyVicePresident = partyVicePresident;
        this.partySecretary = partySecretary;
        this.region = region;
        this.regionPresident = regionPresident;
        this.regionVicePresident = regionVicePresident;
        this.regionSecretary = regionSecretary;
        this.constituency = constituency;
        this.constituencyPresident = constituencyPresident;
        this.constituencyVicePresident = constituencyVicePresident;
        this.constituencySecretary = constituencySecretary;
        this.nationalBoard = nationalBoard;
        this.nationalCouncil = nationalCouncil;
        this.partyCourt = partyCourt;
        this.authorities = authorities;
        this.activationCode = activationCode;
        this.userArticles = userArticles;
        this.userTasks = userTasks;
        this.userMessages = userMessages;
    }

    public User(String username, String password, String name, String surname, boolean member, Collection<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.member = member;
        this.authorities = authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public void removeActivationCode() {
        this.activationCode.setUser(null);
        this.activationCode = null;
    }

    public void setActivationCode(ActivationCode activationCode) {
        this.activationCode = activationCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


//    public void setUserArticles(List<Article> userArticles) {
//        this.userArticles = userArticles;
//    }

    public boolean isPartyPresident() {
        return partyPresident;
    }

    public void setPartyPresident(boolean partyPresident) {
        this.partyPresident = partyPresident;
    }

    public boolean isPartyVicePresident() {
        return partyVicePresident;
    }

    public void setPartyVicePresident(boolean partyVicePresident) {
        this.partyVicePresident = partyVicePresident;
    }

    public boolean isPartySecretary() {
        return partySecretary;
    }

    public void setPartySecretary(boolean partySecretary) {
        this.partySecretary = partySecretary;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isRegionPresident() {
        return regionPresident;
    }

    public void setRegionPresident(boolean regionPresident) {
        this.regionPresident = regionPresident;
    }

    public boolean isRegionVicePresident() {
        return regionVicePresident;
    }

    public void setRegionVicePresident(boolean regionVicePresident) {
        this.regionVicePresident = regionVicePresident;
    }

    public boolean isRegionSecretary() {
        return regionSecretary;
    }

    public void setRegionSecretary(boolean regionSecretary) {
        this.regionSecretary = regionSecretary;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public boolean isConstituencyPresident() {
        return constituencyPresident;
    }

    public void setConstituencyPresident(boolean constituencyPresident) {
        this.constituencyPresident = constituencyPresident;
    }

    public boolean isConstituencyVicePresident() {
        return constituencyVicePresident;
    }

    public void setConstituencyVicePresident(boolean constituencyVicePresident) {
        this.constituencyVicePresident = constituencyVicePresident;
    }

    public boolean isConstituencySecretary() {
        return constituencySecretary;
    }

    public void setConstituencySecretary(boolean constituencySecretary) {
        this.constituencySecretary = constituencySecretary;
    }

    public boolean isNationalBoard() {
        return nationalBoard;
    }

    public void setNationalBoard(boolean nationalBoard) {
        this.nationalBoard = nationalBoard;
    }

    public boolean isNationalCouncil() {
        return nationalCouncil;
    }

    public void setNationalCouncil(boolean nationalCouncil) {
        this.nationalCouncil = nationalCouncil;
    }

    public boolean isPartyCourt() {
        return partyCourt;
    }

    public void setPartyCourt(boolean partyCourt) {
        this.partyCourt = partyCourt;
    }

    public ActivationCode getActivationCode() {
        return activationCode;
    }

    public Collection<Article> getUserArticles() {
        return userArticles;
    }

    public void setUserArticles(Collection<Article> userArticles) {
        this.userArticles = userArticles;
    }

    public List<Task> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<Task> userTasks) {
        this.userTasks = userTasks;
    }

    public List<Message> getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(List<Message> userMessages) {
        this.userMessages = userMessages;
    }

    public boolean isSendRequest() {
        return sendRequest;
    }

    public void setSendRequest(boolean sendRequest) {
        this.sendRequest = sendRequest;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }


}
