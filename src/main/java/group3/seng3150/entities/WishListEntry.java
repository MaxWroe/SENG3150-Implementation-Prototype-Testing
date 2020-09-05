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
@Table(name = "Enquiry")
public class WishListEntry {

    @Id
    @Column(name = "WishlistEntryID")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer WishlistEntryID;

    @Column(name = "UserID")
    @Basic(optional = false)
    private Integer userID;

    @Column(name = "countryCode3")
    @Basic(optional = false)
    private String countryCode3;

    @Column(name = "countryName")
    private String countryName;


    public WishListEntry() {
    }

    public Integer getWishlistEntryID() {
        return WishlistEntryID;
    }

    public void setWishlistEntryID(Integer wishlistEntryID) {
        WishlistEntryID = wishlistEntryID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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
        WishListEntry that = (WishListEntry) o;
        return Objects.equals(WishlistEntryID, that.WishlistEntryID) &&
                Objects.equals(userID, that.userID) &&
                Objects.equals(countryCode3, that.countryCode3) &&
                Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(WishlistEntryID, userID, countryCode3, countryName);
    }

    @Override
    public String toString() {
        return "WishListEntry{" +
                "WishlistEntryID=" + WishlistEntryID +
                ", userID=" + userID +
                ", countryCode3='" + countryCode3 + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}

