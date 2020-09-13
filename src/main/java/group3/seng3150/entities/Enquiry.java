//Class: Enquiry
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is representing user enquiries for customer support


package group3.seng3150.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Enquiry")
public class Enquiry {

    @Id
    @Column(name = "EnquiryID")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String enquiryID;

    @Column(name = "UserID")
    @Basic(optional = false)
    private String userID;

    @Column(name = "EnquiryDate")
    @Basic(optional = false)
    private Date enquiryDate;

    @Column(name = "Description")
    @Basic(optional = false)
    private String description;

    @Column(name = "Title")
    @Basic(optional = false)
    private String title;

    @Column(name = "EnquiryResponse")
    private String enquiryResponse;

    @Column(name = "BookingID")
    @Basic(optional = true)
    private String bookingID;

    public Enquiry() {
    }

    public String getEnquiryID() {
        return enquiryID;
    }

    public void setEnquiryID(String enquiryID) {
        this.enquiryID = enquiryID;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(Date enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public String getEnquiryResponse() {
        return enquiryResponse;
    }

    public void setEnquiryResponse(String enquiryResponse) {
        this.enquiryResponse = enquiryResponse;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Enquiry{" +
                "enquiryID='" + enquiryID + '\'' +
                ", userID='" + userID + '\'' +
                ", enquiryDate=" + enquiryDate +
                ", description='" + description + '\'' +
                ", enquiryResponse='" + enquiryResponse + '\'' +
                '}';
    }
}

