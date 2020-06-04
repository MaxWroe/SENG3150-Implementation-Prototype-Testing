package group3.seng3150.entities;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

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
    private Timestamp bookingDate;

    @Column(name = "GroupSize")
    @Basic(optional = false)
    private int groupSize;



    //flight 1 - data is all foreign keys
    @Column(name = "AirlineCode")
    @Basic(optional = false)
    private String airlineCode;
    @Column(name = "FlightNumber")
    @Basic(optional = false)
    private String flightNumber;
    @Column(name = "DepartureTime")
    @Basic(optional = false)
    private Timestamp departureTime;
    @Column(name = "ClassCode")
    @Basic(optional = false)
    private String classCode;
    @Column(name = "TicketCode")
    @Basic(optional = false)
    private String ticketCode;

    //flight 2
    @Column(name = "AirlineCode2")
    private String airlineCode2;
    @Column(name = "FlightNumber2")
    private String flightNumber2;
    @Column(name = "DepartureTime2")
    private Timestamp departureTime2;
    @Column(name = "ClassCode2")
    private String classCode2;
    @Column(name = "TicketCode2")
    private String ticketCode2;

    //flight 3
    @Column(name = "AirlineCode3")
    private String airlineCode3;
    @Column(name = "FlightNumber3")
    private String flightNumber3;
    @Column(name = "DepartureTime3")
    private Timestamp departureTime3;
    @Column(name = "ClassCode3")
    private String classCode3;
    @Column(name = "TicketCode3")
    private String ticketCode3;

    //flight 4
    @Column(name = "AirlineCode4")
    private String airlineCode4;
    @Column(name = "FlightNumber4")
    private String flightNumber4;
    @Column(name = "DepartureTime4")
    private Timestamp departureTime4;
    @Column(name = "ClassCode4")
    private String classCode4;
    @Column(name = "TicketCode4")
    private String ticketCode4;




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

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getAirlineCode2() {
        return airlineCode2;
    }

    public void setAirlineCode2(String airlineCode2) {
        this.airlineCode2 = airlineCode2;
    }

    public String getFlightNumber2() {
        return flightNumber2;
    }

    public void setFlightNumber2(String flightNumber2) {
        this.flightNumber2 = flightNumber2;
    }

    public Timestamp getDepartureTime2() {
        return departureTime2;
    }

    public void setDepartureTime2(Timestamp departureTime2) {
        this.departureTime2 = departureTime2;
    }

    public String getClassCode2() {
        return classCode2;
    }

    public void setClassCode2(String classCode2) {
        this.classCode2 = classCode2;
    }

    public String getTicketCode2() {
        return ticketCode2;
    }

    public void setTicketCode2(String ticketCode2) {
        this.ticketCode2 = ticketCode2;
    }

    public String getAirlineCode3() {
        return airlineCode3;
    }

    public void setAirlineCode3(String airlineCode3) {
        this.airlineCode3 = airlineCode3;
    }

    public String getFlightNumber3() {
        return flightNumber3;
    }

    public void setFlightNumber3(String flightNumber3) {
        this.flightNumber3 = flightNumber3;
    }

    public Timestamp getDepartureTime3() {
        return departureTime3;
    }

    public void setDepartureTime3(Timestamp departureTime3) {
        this.departureTime3 = departureTime3;
    }

    public String getClassCode3() {
        return classCode3;
    }

    public void setClassCode3(String classCode3) {
        this.classCode3 = classCode3;
    }

    public String getTicketCode3() {
        return ticketCode3;
    }

    public void setTicketCode3(String ticketCode3) {
        this.ticketCode3 = ticketCode3;
    }

    public String getAirlineCode4() {
        return airlineCode4;
    }

    public void setAirlineCode4(String airlineCode4) {
        this.airlineCode4 = airlineCode4;
    }

    public String getFlightNumber4() {
        return flightNumber4;
    }

    public void setFlightNumber4(String flightNumber4) {
        this.flightNumber4 = flightNumber4;
    }

    public Timestamp getDepartureTime4() {
        return departureTime4;
    }

    public void setDepartureTime4(Timestamp departureTime4) {
        this.departureTime4 = departureTime4;
    }

    public String getClassCode4() {
        return classCode4;
    }

    public void setClassCode4(String classCode4) {
        this.classCode4 = classCode4;
    }

    public String getTicketCode4() {
        return ticketCode4;
    }

    public void setTicketCode4(String ticketCode4) {
        this.ticketCode4 = ticketCode4;
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
                ", airlineCode='" + airlineCode + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureTime=" + departureTime +
                ", classCode='" + classCode + '\'' +
                ", ticketCode='" + ticketCode + '\'' +
                ", airlineCode2='" + airlineCode2 + '\'' +
                ", flightNumber2='" + flightNumber2 + '\'' +
                ", departureTime2=" + departureTime2 +
                ", classCode2='" + classCode2 + '\'' +
                ", ticketCode2='" + ticketCode2 + '\'' +
                ", airlineCode3='" + airlineCode3 + '\'' +
                ", flightNumber3='" + flightNumber3 + '\'' +
                ", departureTime3=" + departureTime3 +
                ", classCode3='" + classCode3 + '\'' +
                ", ticketCode3='" + ticketCode3 + '\'' +
                ", airlineCode4='" + airlineCode4 + '\'' +
                ", flightNumber4='" + flightNumber4 + '\'' +
                ", departureTime4=" + departureTime4 +
                ", classCode4='" + classCode4 + '\'' +
                ", ticketCode4='" + ticketCode4 + '\'' +
                '}';
    }
}
