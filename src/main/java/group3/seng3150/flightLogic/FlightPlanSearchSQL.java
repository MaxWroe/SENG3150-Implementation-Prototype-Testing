package group3.seng3150.flightLogic;

import group3.seng3150.entities.*;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class FlightPlanSearchSQL {
    public FlightPlanSearchSQL(){}

    public List<Flight> retrieveFlights(Timestamp startTime, Timestamp endTime, EntityManager em){
        List<Flight> flights = new LinkedList<>();
        Query queryFlights = em.createQuery("SELECT f FROM Flight f WHERE f.departureDate >= :startTime " +
                " AND f.departureDate <= :endTime");
        queryFlights.setParameter("startTime", startTime);
        queryFlights.setParameter("endTime", endTime);
        flights = queryFlights.getResultList();

        return flights;
    }

    public List<Availability> retrieveAvailabilities(Timestamp startTime, Timestamp endTime, int numberOfPeople, String classCode, EntityManager em){
        List<Availability> availabilities = new LinkedList<>();
        Query queryAvailabilities = em.createQuery("SELECT a FROM Availability a WHERE a.departureDate >= :startTime" +
                " AND a.departureDate <= :endTime" +
                " AND a.numberAvailableSeatsLeg1 >= :numberOfPeople"+
                " AND a.classCode = :classCode" +
                " AND (a.numberAvailableSeatsLeg2 >= :numberOfPeople" +
                " OR a.numberAvailableSeatsLeg2 IS NULL)");
        queryAvailabilities.setParameter("startTime", startTime);
        queryAvailabilities.setParameter("endTime", endTime);
        queryAvailabilities.setParameter("numberOfPeople", numberOfPeople);
        queryAvailabilities.setParameter("classCode", classCode);
        availabilities = queryAvailabilities.getResultList();
        return availabilities;
    }

    public Price retrievePrice(Availability av, EntityManager em) {
        String airlineCode = av.getAirlineCode();
        String flightNumber = av.getFlightNumber();
        String classCode = av.getClassCode();
        String ticketCode = av.getTicketCode();
        Timestamp departureDate = av.getDepartureDate();
        List<Price> prices = new LinkedList<>();

        Query queryPrices = em.createQuery("SELECT p FROM Price p WHERE p.airlineCode = :airlineCode" +
                " AND p.flightNumber = :flightNumber" +
                " AND p.classCode = :classCode" +
                " AND p.ticketCode = :ticketCode" +
                " AND p.startDate < :departureDate" +
                " AND p.endDate > :departureDate");
        queryPrices.setParameter("airlineCode", airlineCode);
        queryPrices.setParameter("flightNumber", flightNumber);
        queryPrices.setParameter("classCode", classCode);
        queryPrices.setParameter("ticketCode", ticketCode);
        queryPrices.setParameter("departureDate", departureDate);
        prices = queryPrices.getResultList();
        if(prices.size()>0 && prices.get(0) != null){
            return prices.get(0);
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
