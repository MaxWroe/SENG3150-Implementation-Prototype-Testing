package group3.seng3150.entities;

import javax.persistence.*;

@Entity
@Table(name = "Destinations")
public class Airport {

    @Id
    @Column(name = "DestinationCode", table = "Destinations")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;

    @Column(name = "CountryCode3", table = "Destinations")
    private String country;


    //still to be considered
//    private String state;
//    private String suburb;
//    private String address;
//    private int postcode;
//    private String disabilitySupport;
//    private String languages;


    //Constructor
    public Airport() {
    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //Override Methods


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
