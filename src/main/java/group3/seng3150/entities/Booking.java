//Class: Airport
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is representing flight bookings in the database

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

    @Column(name = "returnTrip")
    @Basic(optional = false)
    private int returnTrip;

    @Column(name = "OverallPrice")
    @Basic(optional = false)
    private Double overallPrice;

    //If there was more time we would put booked flights into their own table to keep booking from being this big.
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
    @Column(name = "Departure")
    @Basic(optional = false)
    private String departure;
    @Column(name = "Destination")
    @Basic(optional = false)
    private String destination;
    @Column(name = "ArrivalTime")
    @Basic(optional = false)
    private Timestamp arrivalTime;
    @Column(name = "Price")
    @Basic(optional = false)
    private Double price;

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
    @Column(name = "Departure2")
    private String departure2;
    @Column(name = "Destination2")
    private String destination2;
    @Column(name = "ArrivalTime2")
    private Timestamp arrivalTime2;
    @Column(name = "Price2")
    private Double price2;

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
    @Column(name = "Departure3")
    private String departure3;
    @Column(name = "Destination3")
    private String destination3;
    @Column(name = "ArrivalTime3")
    private Timestamp arrivalTime3;
    @Column(name = "Price3")
    private Double price3;

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
    @Column(name = "Departure4")
    private String departure4;
    @Column(name = "Destination4")
    private String destination4;
    @Column(name = "ArrivalTime4")
    private Timestamp arrivalTime4;
    @Column(name = "Price4")
    private Double price4;




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

    public int getReturnTrip() {
        return returnTrip;
    }

    public void setReturnTrip(int returnTrip) {
        this.returnTrip = returnTrip;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOverallPrice() {
        return overallPrice;
    }

    public void setOverallPrice(Double overallPrice) {
        this.overallPrice = overallPrice;
    }

    public Double getPrice2() {
        return price2;
    }

    public void setPrice2(Double price2) {
        this.price2 = price2;
    }

    public Double getPrice3() {
        return price3;
    }

    public void setPrice3(Double price3) {
        this.price3 = price3;
    }

    public Double getPrice4() {
        return price4;
    }

    public void setPrice4(Double price4) {
        this.price4 = price4;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDeparture2() {
        return departure2;
    }

    public void setDeparture2(String departure2) {
        this.departure2 = departure2;
    }

    public String getDestination2() {
        return destination2;
    }

    public void setDestination2(String destination2) {
        this.destination2 = destination2;
    }

    public Timestamp getArrivalTime2() {
        return arrivalTime2;
    }

    public void setArrivalTime2(Timestamp arrivalTime2) {
        this.arrivalTime2 = arrivalTime2;
    }

    public String getDeparture3() {
        return departure3;
    }

    public void setDeparture3(String departure3) {
        this.departure3 = departure3;
    }

    public String getDestination3() {
        return destination3;
    }

    public void setDestination3(String destination3) {
        this.destination3 = destination3;
    }

    public Timestamp getArrivalTime3() {
        return arrivalTime3;
    }

    public void setArrivalTime3(Timestamp arrivalTime3) {
        this.arrivalTime3 = arrivalTime3;
    }

    public String getDeparture4() {
        return departure4;
    }

    public void setDeparture4(String departure4) {
        this.departure4 = departure4;
    }

    public String getDestination4() {
        return destination4;
    }

    public void setDestination4(String destination4) {
        this.destination4 = destination4;
    }

    public Timestamp getArrivalTime4() {
        return arrivalTime4;
    }

    public void setArrivalTime4(Timestamp arrivalTime4) {
        this.arrivalTime4 = arrivalTime4;
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
