package fi.backendkurssi.fishapp.domain;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String species;
    private double length;
    private double weight;
    private Date date;
    private String location;

    public Fish() {
    }

    public Fish(Long id, String species, double length, double weight, Date date, String location) {
        super();
        this.id = id;
        this.species = species;
        this.length = length;
        this.weight = weight;
        this.date = date;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
