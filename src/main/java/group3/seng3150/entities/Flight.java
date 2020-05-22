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

    //This field does not exist as information in the database (Status)
    //private String Status;

    @Column(name = "DepartureTime", table = "Availability")
    @Basic(optional = false)
    private Timestamp departureDate;


    //this could have issues with stopovers
    @Column(name = "ArrivalTime", table = "Flights")
    @Basic(optional = false)
    private Timestamp arrivalDate;

    //Fields for stopovers needed

    @Column(name = "TicketCode", table = "Availability")
    @Basic(optional = false)
    private String ticketCode;

    @Column(name = "ClassCode", table = "Availability")
    @Basic(optional = false)
    private String classCode;

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
