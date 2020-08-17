
package group3.seng3150.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Reccomendation")
public class Reccomendation {

    @Id
    @Column(name = "ReccomendationID")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String reccomendationID;

    //Flight
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
    private int price;

    public Reccomendation() {
    }

    public String getReccomendationID() {
        return reccomendationID;
    }

    public void setReccomendationID(String reccomendationID) {
        this.reccomendationID = reccomendationID;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        return super.toString();
    }
}
