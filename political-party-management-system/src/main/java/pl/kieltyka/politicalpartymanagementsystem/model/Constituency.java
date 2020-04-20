package pl.kieltyka.politicalpartymanagementsystem.model;

import org.springframework.beans.factory.annotation.Autowired;
import pl.kieltyka.politicalpartymanagementsystem.repository.RegionRepository;

import javax.persistence.*;

@Entity
@Table(name = "constituencies")
public class Constituency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "region", nullable = false)
//    private Region region;

    @Column
    private String parent;

    public Constituency() {
    }

    public Constituency(String name) {
        this.name = name;
    }

    public Constituency(String name, Region region) {
        this.name = name;
      //  this.region = region;
    }

    public Constituency(String name, Region region, String parent) {
        this.name = name;
     //   this.region = region;
        this.parent = parent;
    }

    public Constituency(String name, String parent) {
        this.name = name;
        this.parent = parent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Region getRegion() {
//        return region;
//    }
//
//    public void setRegion(Region region) {
//        this.region = region;
//    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
