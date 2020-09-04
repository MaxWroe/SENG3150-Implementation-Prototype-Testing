//Class: Airport
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is representing flight the data for an airport

package group3.seng3150.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Airlines")
public class Airline {

    @Id
    @Column(name = "AirlineCode", table = "Airlines")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String airlineCode;

    @Column(name = "AirlineName", table = "Airlines")
    @Basic(optional = false)
    private String airlineName;

    @Column(name = "CountryCode3", table = "Airlines")
    @Basic(optional = false)
    private String countryCode3;


    //0 for not sponsored, 1 for sponsored
    @Column(name = "Sponsored", table = "Airlines")
    private Integer sponsored;

    //Constructor
    public Airline() {
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getCountryCode3() {
        return countryCode3;
    }

    public void setCountryCode3(String countryCode3) {
        this.countryCode3 = countryCode3;
    }

    public int getSponsored() {
        return sponsored;
    }

    public void setSponsored(int sponsored) {
        this.sponsored = sponsored;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return sponsored == airline.sponsored &&
                Objects.equals(airlineCode, airline.airlineCode) &&
                Objects.equals(airlineName, airline.airlineName) &&
                Objects.equals(countryCode3, airline.countryCode3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airlineCode, airlineName, countryCode3, sponsored);
    }

    @Override
    public String toString() {
        return "Airline{" +
                "airlineCode='" + airlineCode + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", countryCode3='" + countryCode3 + '\'' +
                ", sponsored=" + sponsored +
                '}';
    }
}
