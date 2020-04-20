package pl.kieltyka.politicalpartymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "region", orphanRemoval = false)
//    private Collection<Constituency> constituencies;

    public Region(String name, Collection<Constituency> constituencies) {
        this.name = name;
      //  this.constituencies = constituencies;
    }

    public Region(String name) {
        this.name = name;
    }

    public Region() {
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

//    public Collection<Constituency> getConstituencies() {
//        return constituencies;
//    }
//
//    public void setConstituencies(Collection<Constituency> constituencies) {
//        this.constituencies = constituencies;
//    }
}
