package group3.seng3150.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class AvailabilityID implements Serializable {

    private String flightNumber;
    private String airlineCode;
    private Timestamp departureDate;
    private String departureCode;

    public AvailabilityID() {
    }

    public AvailabilityID(String flightNumber, String airlineCode, Timestamp departureDate, String departureCode) {
        this.flightNumber = flightNumber;
        this.airlineCode = airlineCode;
        this.departureDate = departureDate;
        this.departureCode = departureCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilityID that = (AvailabilityID) o;
        return Objects.equals(flightNumber, that.flightNumber) &&
                Objects.equals(airlineCode, that.airlineCode) &&
                Objects.equals(departureDate, that.departureDate) &&
                Objects.equals(departureCode, that.departureCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, airlineCode, departureDate, departureCode);
    }
}