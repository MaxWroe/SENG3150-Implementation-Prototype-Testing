package group3.seng3150.entities;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Booking")
public class Booking {


    @Id
    @Column(name = "BookingID")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String bookingID;

    @Column(name = "UserID")
    @Basic(optional = false)
    private String userID;

    @Column(name = "BookingType")
    @Basic(optional = false)
    private int bookingType;

    @Column(name = "FirstName")
    @Basic(optional = false)
    private String firstName;

    @Column(name = "MiddleNames")
    private String middleNames;

    @Column(name = "LastName")
    @Basic(optional = false)
    private String lastName;

    @Column(name = "DateOfBirth")
    @Basic(optional = false)
    private Date dateOfBirth;


    @Column(name = "BookingDate")
    @Basic(optional = false)
    private Date bookingDate;

    @Column(name = "GroupSize")
    @Basic(optional = false)
    private int groupSize;




    public Booking() {
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getUserID() {
        return userID;
    }


    public int getBookingType() {
        return bookingType;
    }

    public void setBookingType(int bookingType) {
        this.bookingType = bookingType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(String middleNames) {
        this.middleNames = middleNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

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
        return "Booking{" +
                "bookingID='" + bookingID + '\'' +
                ", userID='" + userID + '\'' +
                ", bookingType=" + bookingType +
                ", firstName='" + firstName + '\'' +
                ", middleNames='" + middleNames + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bookingDate=" + bookingDate +
                ", groupSize=" + groupSize +
                '}';
    }
}
