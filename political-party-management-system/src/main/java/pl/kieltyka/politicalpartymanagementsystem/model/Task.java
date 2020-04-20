package pl.kieltyka.politicalpartymanagementsystem.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String taskTitle;
    @Column(name = "content", columnDefinition="TEXT")
    private String taskContent;
    @Column
    private boolean inProgress = false;
    @Column
    private boolean done = false;
    @Column
    private boolean removed = false;
    @Column
    private boolean removedInOwner = false;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User principal;

    @OneToOne
    private User recipient;

    @ManyToMany
    private List<User> addressees;

    @Column
    private LocalDate startdate;
    @Column
    private LocalDate enddate;
    @Column(name = "report", columnDefinition="TEXT")
    private String report;


    public Task() {
    }

    public Task(String taskTitle, String taskContent, User principal, List<User> addressees) {
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.principal = principal;
        this.addressees = addressees;
    }

    public Task(String taskTitle, String taskContent, User principal, User recipient, List<User> addressees) {
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.principal = principal;
        this.recipient = recipient;
        this.addressees = addressees;
    }

    public Task(String taskTitle, String taskContent, boolean inProgress, boolean done, boolean removed, boolean removedInOwner, User principal, User recipient, List<User> addressees, LocalDate startdate, LocalDate enddate) {
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.inProgress = inProgress;
        this.done = done;
        this.removed = removed;
        this.removedInOwner = removedInOwner;
        this.principal = principal;
        this.recipient = recipient;
        this.addressees = addressees;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Task(String taskTitle, String taskContent, User principal, User recipient, List<User> addressees, LocalDate startdate, LocalDate enddate) {
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.principal = principal;
        this.recipient = recipient;
        this.addressees = addressees;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Task(String taskTitle, String taskContent, boolean inProgress, boolean done, boolean removed, boolean removedInOwner, User principal, User recipient, List<User> addressees, LocalDate startdate, LocalDate enddate, String report) {
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.inProgress = inProgress;
        this.done = done;
        this.removed = removed;
        this.removedInOwner = removedInOwner;
        this.principal = principal;
        this.recipient = recipient;
        this.addressees = addressees;
        this.startdate = startdate;
        this.enddate = enddate;
        this.report = report;
    }

    public Task(String taskTitle, String taskContent, User principal, User recipient, List<User> addressees, LocalDate startdate, LocalDate enddate, String report) {
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.principal = principal;
        this.recipient = recipient;
        this.addressees = addressees;
        this.startdate = startdate;
        this.enddate = enddate;
        this.report = report;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
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

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public boolean isRemovedInOwner() {
        return removedInOwner;
    }

    public void setRemovedInOwner(boolean removedInOwner) {
        this.removedInOwner = removedInOwner;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
