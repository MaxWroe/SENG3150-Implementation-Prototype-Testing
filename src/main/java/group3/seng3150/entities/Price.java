package group3.seng3150.entities;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Price")
public class Price {

    @Id
    @Column(name = "AirlineCode")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String airlineCode;

    @Column(name = "FlightNumber")
    @Basic(optional = false)
    private String flightNumber;

    @Column(name = "ClassCode")
    @Basic(optional = false)
    private Timestamp classCode;

    @Column(name = "TicketCode")
    @Basic(optional = false)
    private String ticketCode;

    @Column(name = "StartDate")
    @Basic(optional = false)
    private Date startDate;


    public Price() {
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

    public Timestamp getClassCode() {
        return classCode;
    }

    public void setClassCode(Timestamp classCode) {
        this.classCode = classCode;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Price{" +
                "airlineCode='" + airlineCode + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", classCode=" + classCode +
                ", ticketCode='" + ticketCode + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}

