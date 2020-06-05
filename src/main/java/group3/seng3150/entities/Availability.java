package group3.seng3150.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Availability")
public class Availability {

    @Id
    @Column(name = "FlightNumber", table = "Availability")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String flightNumber;

    @Column(name = "AirlineCode", table = "Availability")
    @Basic(optional = false)
    private String airlineCode;

    @Column(name = "DepartureTime", table = "Availability")
    @Basic(optional = false)
    private Timestamp departureDate;

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

    //Constructor
    public Availability() {
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
        return "Availability{" +
                "flightNumber='" + flightNumber + '\'' +
                ", airlineCode='" + airlineCode + '\'' +
                ", departureDate=" + departureDate +
                ", ticketCode='" + ticketCode + '\'' +
                ", classCode='" + classCode + '\'' +
                ", numberAvailableSeatsLeg1='" + numberAvailableSeatsLeg1 + '\'' +
                ", numberAvailableSeatsLeg2='" + numberAvailableSeatsLeg2 + '\'' +
                '}';
    }
}
