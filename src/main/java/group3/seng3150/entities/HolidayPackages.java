//Class: Airport
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is representing user enquiries for customer support


package group3.seng3150.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "HolidayPackages")
public class HolidayPackages {

    @Id
    @Column(name = "PackageID")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String packageID;

    @Column(name = "Description")
    @Basic(optional = false)
    private String description;

    @Column(name = "Destination")
    @Basic(optional = false)
    private String destination;

    @Column(name = "countryCode3")
    @Basic(optional = false)
    private String countryCode;

    @Column(name = "countryName")
    @Basic(optional = false)
    private String countryName;

    //0 for sponsored personal, 1 for "hot location",  3 business personal , 4 for self generated
    @Column(name = "type")
    @Basic(optional = false)
    private int type;


    //change airline code to flight number can be null
    //add int sponsored, hot location, or general
    public HolidayPackages() {
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolidayPackages that = (HolidayPackages) o;
        return type == that.type &&
                Objects.equals(packageID, that.packageID) &&
                Objects.equals(description, that.description) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageID, description, destination, countryCode, countryName, type);
    }

    @Override
    public String toString() {
        return "HolidayPackages{" +
                "packageID='" + packageID + '\'' +
                ", description='" + description + '\'' +
                ", destination='" + destination + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", type=" + type +
                '}';
    }
}

