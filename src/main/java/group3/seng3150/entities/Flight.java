package group3.seng3150.entities;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "Availability")
@SecondaryTables({
        @SecondaryTable(name="Flights",
                pkJoinColumns=@PrimaryKeyJoinColumn(name="FlightNumber")),
        @SecondaryTable(name="Price",
                pkJoinColumns=@PrimaryKeyJoinColumn(name="FlightNumber"))
})
public class Flight {

    @Id
    @Column(name = "FlightNumber")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String flightNumber;

    @Column(name = "AirlineCode", table = "Availability")
    @Basic(optional = false)
    private String airlineCode;

    @Column(name = "DepartureTime", table = "Availability")
    @Basic(optional = false)
    private Timestamp departureDate;

    @Column(name = "DepartureCode", table = "Flights")
    @Basic(optional = false)
    private String departureCode; //airport code for departure


    //Stopover data can be null
    @Column(name = "StopOverCode", table = "Flights")
    @Basic(optional = false)
    private String stopOverCode; //airport code for stopover

    @Column(name = "ArrivalTimeStopOver", table = "Flights")
    private Timestamp arrivalStopOverTime;

    @Column(name = "DepartureTimeStopOver", table = "Flights")
    private Timestamp departureTimeStopOver;

    @Column(name = "DestinationCode", table = "Flights")
    @Basic(optional = false)
    private String destination;

    @Column(name = "ArrivalTime", table = "Flights")
    @Basic(optional = false)
    private Timestamp arrivalDate;

    @Column(name = "TicketCode", table = "Availability")
    @Basic(optional = false)
    private String ticketCode;

    @Column(name = "ClassCode", table = "Availability")
    @Basic(optional = false)
    private String classCode;

    @Column(name = "NumberAvailableSeatsLeg1", table = "Availability")
    @Basic(optional = false)
    private String numberAvailableSeatsLeg1;

    @Column(name = "NumberAvailableSeatsLeg2", table = "Availability")
    private String numberAvailableSeatsLeg2;

    @Column(name = "Duration", table = "Flights")
    @Basic(optional = false)
    private int duration;

    @Column(name = "Price", table = "Price")
    @Basic(optional = false)
    private double price;

    //Still to consider
    //disability, amentities, seating map, languages, foodselection


    //Constructor
    public Flight() {
    }

    //Getters and Setters


    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getNumberAvailableSeatsLeg1() {
        return numberAvailableSeatsLeg1;
    }

    public void setNumberAvailableSeatsLeg1(String numberAvailableSeatsLeg1) {
        this.numberAvailableSeatsLeg1 = numberAvailableSeatsLeg1;
    }

    public String getNumberAvailableSeatsLeg2() {
        return numberAvailableSeatsLeg2;
    }

    public void setNumberAvailableSeatsLeg2(String numberAvailableSeatsLeg2) {
        this.numberAvailableSeatsLeg2 = numberAvailableSeatsLeg2;
    }

    public String getDepartureCode() {
        return departureCode;
    }

    public void setDepartureCode(String departureCode) {
        this.departureCode = departureCode;
    }

    public String getStopOverCode() {
        return stopOverCode;
    }

    public void setStopOverCode(String stopOverCode) {
        this.stopOverCode = stopOverCode;
    }

    public Timestamp getArrivalStopOverTime() {
        return arrivalStopOverTime;
    }

    public void setArrivalStopOverTime(Timestamp arrivalStopOverTime) {
        this.arrivalStopOverTime = arrivalStopOverTime;
    }

    public Timestamp getDepartureTimeStopOver() {
        return departureTimeStopOver;
    }

    public void setDepartureTimeStopOver(Timestamp departureTimeStopOver) {
        this.departureTimeStopOver = departureTimeStopOver;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    //Override methods
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
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", ticketCode='" + ticketCode + '\'' +
                ", classCode='" + classCode + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
