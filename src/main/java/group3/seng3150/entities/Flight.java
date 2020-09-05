//Class: Airport
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is representing flight routes

package group3.seng3150.entities;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@IdClass(FlightID.class)
@Table(name = "Flights")
public class Flight implements Cloneable{

    @Id
    @Column(name = "FlightNumber", table = "Flights")
    @Basic(optional = false)
    private String flightNumber;

    @Id
    @Column(name = "AirlineCode", table = "Flights")
    @Basic(optional = false)
    private String airlineCode;

    @Id
    @Column(name = "DepartureTime", table = "Flights")
    @Basic(optional = false)
    private Timestamp departureDate;

    @Id
    @Column(name = "DepartureCode", table = "Flights")
    @Basic(optional = false)
    private String departureCode; //airport code for departure


    //Stopover data can be null
    @Column(name = "StopOverCode", table = "Flights")
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

    @Column(name = "Duration", table = "Flights")
    @Basic(optional = false)
    private Integer duration;

    @Column(name = "DurationSecondLeg", table = "Flights")
    private Integer durationSecondLeg;


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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDurationSecondLeg() {
        return durationSecondLeg;
    }

    public void setDurationSecondLeg(Integer durationSecondLeg) {
        this.durationSecondLeg = durationSecondLeg;
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
                ", airlineCode='" + airlineCode + '\'' +
                ", departureDate=" + departureDate +
                ", departureCode='" + departureCode + '\'' +
                ", stopOverCode='" + stopOverCode + '\'' +
                ", arrivalStopOverTime=" + arrivalStopOverTime +
                ", departureTimeStopOver=" + departureTimeStopOver +
                ", destination='" + destination + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", duration=" + duration +
                '}';
    }

    @Override
    public Flight clone(){

        return null;
    }


}
