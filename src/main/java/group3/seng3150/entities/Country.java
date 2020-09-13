//Class: Country
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is representing Countries


package group3.seng3150.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Country")
public class Country {

    @Id
    @Column(name = "countryCode3")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String countryCode3;

    @Column(name = "countryName")
    @Basic(optional = false)
    private String countryName;


    public Country() {
    }

    public String getCountryCode3() {
        return countryCode3;
    }

    public void setCountryCode3(String countryCode3) {
        this.countryCode3 = countryCode3;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(countryCode3, country.countryCode3) &&
                Objects.equals(countryName, country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode3, countryName);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryCode3='" + countryCode3 + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}

