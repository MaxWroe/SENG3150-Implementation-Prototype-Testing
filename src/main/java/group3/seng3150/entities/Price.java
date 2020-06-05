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
    private String airlineCode;

    @Column(name = "FlightNumber")
    @Basic(optional = false)
    private String flightNumber;

    @Column(name = "ClassCode")
    @Basic(optional = false)
    private String classCode;

    @Column(name = "TicketCode")
    @Basic(optional = false)
    private String ticketCode;

    @Column(name = "StartDate")
    @Basic(optional = false)
    private Timestamp startDate;

    @Column(name = "EndDate")
    @Basic(optional = false)
    private Timestamp endDate;

    @Column(name = "Price")
    @Basic(optional = false)
    private int price;

    @Column(name = "PriceLeg1")
    private String priceLeg1;

    @Column(name = "PriceLeg2")
    private String priceLeg2;



    public Price() {
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceLeg1() {
        return priceLeg1;
    }

    public void setPriceLeg1(String priceLeg1) {
        this.priceLeg1 = priceLeg1;
    }

    public String getPriceLeg2() {
        return priceLeg2;
    }

    public void setPriceLeg2(String priceLeg2) {
        this.priceLeg2 = priceLeg2;
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

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
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

