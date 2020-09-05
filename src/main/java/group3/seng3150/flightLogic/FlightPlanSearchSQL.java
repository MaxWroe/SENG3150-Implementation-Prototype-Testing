package group3.seng3150.flightLogic;

import group3.seng3150.entities.*;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class FlightPlanSearchSQL {
    public FlightPlanSearchSQL(){}

    public List<Flight> retrieveFlights(Timestamp StartTime, Timestamp endTime, EntityManager em){
        List<Flight> flights = new LinkedList<>();
        flights = em.createQuery( "SELECT f FROM Flight f WHERE f.departureDate>='" + StartTime + "'" +
                " AND f.departureDate<='" + endTime + "'", Flight.class).getResultList();
        return flights;
    }

    public List<Availability> retrieveAvailabilities(Timestamp StartTime, Timestamp endTime, int numberOfPeople, String classCode, EntityManager em){
        List<Availability> availabilities = new LinkedList<>();
        System.out.println("availabilities query: " + "SELECT a FROM Availability a WHERE a.departureDate>='" + StartTime + "'" +
                " AND a.departureDate<='" + endTime + "'" +
                " AND a.numberAvailableSeatsLeg1>=" + numberOfPeople +
                " AND a.classCode='" + classCode + "'" +
                " AND (a.numberAvailableSeatsLeg2>=" + numberOfPeople + " OR a.numberAvailableSeatsLeg2='null')");
        availabilities = em.createQuery("SELECT a FROM Availability a WHERE a.departureDate>='" + StartTime + "'" +
                " AND a.departureDate<='" + endTime + "'" +
                " AND a.numberAvailableSeatsLeg1>=" + numberOfPeople +
                " AND a.classCode='" + classCode + "'" +
                " AND (a.numberAvailableSeatsLeg2>=" + numberOfPeople + " OR a.numberAvailableSeatsLeg2=null)", Availability.class).getResultList();

        return availabilities;
//        "a.flightNumber IN " + flightNumbers + AND a.departureDate>='" + StartTime + "'" +
    }

    public Price retrievePrice(Availability av, EntityManager em) {
        String ac = av.getAirlineCode();
        String fn = av.getFlightNumber();
        String cc = av.getClassCode();
        String tc = av.getTicketCode();
        String dd = av.getDepartureDate().toString();

        List<Price> price = em.createQuery("SELECT p FROM Price p WHERE p.airlineCode='" + ac + "' " +
                        "AND p.flightNumber='" + fn + "' " +
                        "AND p.classCode='" + cc + "' " +
                        "AND p.ticketCode='" + tc + "' " +
                        "AND p.startDate<'" + dd + "' AND p.endDate>'" + dd + "'", Price.class).getResultList();
        if(price.size()>0 && price.get(0) != null){
            return price.get(0);
        }
        return null;
    }

    public List<Airport> retrieveAirports(EntityManager em){
        return em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
    }

    public List<Airline> retrieveAirlines(EntityManager em){
        return em.createQuery("SELECT a FROM Airline a", Airline.class).getResultList();
    }

    public Airline retrieveAirline(Flight flight, EntityManager em){
        String airlineCode = flight.getAirlineCode();
        List<Airline> airline = em.createQuery("SELECT a FROM Airline a WHERE a.airlineCode='" + airlineCode + "'", Airline.class).getResultList();
        if(airline.size()>0 && airline.get(0) != null){
            return airline.get(0);
        }
        return null;
    }


}
