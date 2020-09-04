//Class: Airport
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is representing flight the data for an airport

package group3.seng3150.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Destinations")
public class Airport {

    @Id
    @Column(name = "DestinationCode", table = "Destinations")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String destinationCode;

    @Column(name = "CountryCode3", table = "Destinations")
    private String country;

    @Column(name = "ShutdownStartDate", table = "Destinations")
    private Timestamp shutdownStartDate;

    @Column(name = "ShutdownEndDate", table = "Destinations")
    private Timestamp shutdownEndDate;

    //Constructor
    public Airport() {
    }

    //Getters and Setters
    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getShutdownStartDate() {
        return shutdownStartDate;
    }

    public void setShutdownStartDate(Timestamp shutdownStartDate) {
        this.shutdownStartDate = shutdownStartDate;
    }

    public Timestamp getShutdownEndDate() {
        return shutdownEndDate;
    }

    public void setShutdownEndDate(Timestamp shutdownEndDate) {
        this.shutdownEndDate = shutdownEndDate;
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
                "destinationCode='" + destinationCode + '\'' +
                ", country='" + country + '\'' +
                ", shutdownStartDate='" + shutdownStartDate + '\'' +
                ", shutdownEndDate='" + shutdownEndDate + '\'' +
                '}';
    }
}
