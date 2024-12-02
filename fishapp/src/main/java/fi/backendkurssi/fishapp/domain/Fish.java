package fi.backendkurssi.fishapp.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fishid;
    
    private String species;
    private double length;
    private double weight;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    
    private String location;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;


    
    public Fish(Long fishid, String species, double length, double weight, Date date, String location, User user) {
        super();
        this.fishid = fishid;
        this.species = species;
        this.length = length;
        this.weight = weight;
        this.date = date;
        this.location = location;
        this.user = user;
    }

    public Fish() {
    }

    public Long getFishid() {
        return fishid;
    }

    public void setFishid(Long fishid) {
        this.fishid = fishid;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
    