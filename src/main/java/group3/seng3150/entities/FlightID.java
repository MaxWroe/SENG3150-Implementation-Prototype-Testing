package group3.seng3150.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class FlightID implements Serializable {

        private String flightNumber;
        private String airlineCode;
        private Timestamp departureDate;

    public FlightID() {
    }

    public FlightID(String flightNumber, String airlineCode, Timestamp departureDate, String departureCode) {
        this.flightNumber = flightNumber;
        this.airlineCode = airlineCode;
        this.departureDate = departureDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightID flightID = (FlightID) o;
        return Objects.equals(flightNumber, flightID.flightNumber) &&
                Objects.equals(airlineCode, flightID.airlineCode) &&
                Objects.equals(departureDate, flightID.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, airlineCode, departureDate);
    }
}

