package group3.seng3150.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class PriceID implements Serializable {

    private String flightNumber;
    private String airlineCode;
    private Timestamp departureDate;
    private String departureCode;

    public PriceID() {
    }

    public PriceID(String flightNumber, String airlineCode, Timestamp departureDate, String departureCode) {
        this.flightNumber = flightNumber;
        this.airlineCode = airlineCode;
        this.departureDate = departureDate;
        this.departureCode = departureCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceID priceID = (PriceID) o;
        return Objects.equals(flightNumber, priceID.flightNumber) &&
                Objects.equals(airlineCode, priceID.airlineCode) &&
                Objects.equals(departureDate, priceID.departureDate) &&
                Objects.equals(departureCode, priceID.departureCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, airlineCode, departureDate, departureCode);
    }
}