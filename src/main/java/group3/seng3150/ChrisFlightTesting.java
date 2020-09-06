package group3.seng3150;

import group3.seng3150.flightLogic.FlightPlanSearch;
import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class ChrisFlightTesting {
    public static void main(String[] args){
        System.out.println("hello");
//        EntityManager em;
        FlightHolder flights = new FlightHolder();

//        flights.setFlights(em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber='AF5028' AND f.ticketCode='B' AND " +
//                "f.classCode='BUS' AND f.departureDate<'2015-09-24 09:50:00'", Flight.class).getResultList());
        Flight flight1 = new Flight();
        flight1.setFlightNumber("A1");
        flight1.setAirlineCode("ABC");
        flight1.setDepartureDate(Timestamp.valueOf("2020-10-10 8:00:00"));
        flight1.setDepartureCode("ADL");
        flight1.setDestination("AMS");
        flight1.setArrivalDate(Timestamp.valueOf("2020-10-10 10:00:00"));
        flight1.setDuration(2);
        Availability availability1 = new Availability();
        availability1.setFlightNumber("A1");
        availability1.setAirlineCode("ABC");
        availability1.setDepartureDate(Timestamp.valueOf("2020-10-10 8:00:00"));
        availability1.setTicketCode("Standard");
        availability1.setClassCode("ECO");
//        availability1.setNumberAvailableSeatsLeg1("10");

        Flight flight2 = new Flight();
        flight2.setFlightNumber("A2");
        flight2.setAirlineCode("ABC");
        flight2.setDepartureDate(Timestamp.valueOf("2020-10-10 11:00:00"));
        flight2.setDepartureCode("AMS");
        flight2.setDestination("ATL");
        flight2.setArrivalDate(Timestamp.valueOf("2020-10-10 14:00:00"));
        flight2.setDuration(3);
        Availability availability2 = new Availability();
        availability2.setFlightNumber("A2");
        availability2.setAirlineCode("ABC");
        availability2.setDepartureDate(Timestamp.valueOf("2020-10-10 11:00:00"));
        availability2.setTicketCode("Standard");
        availability2.setClassCode("ECO");
//        availability2.setNumberAvailableSeatsLeg1("10");

//        FlightPlanSearch searcher = new FlightPlanSearch();
        List<Flight> flightsList = new LinkedList<>();
        flightsList.add(flight1);
        flightsList.add(flight2);
        List<Availability> availabilityList = new LinkedList<>();
        availabilityList.add(availability1);
        availabilityList.add(availability2);




//        List<FlightPlan> flightPlans = searcher.createFlightPlans(flightsList,"ADL", "ATL", "2020-10-10 6:00:00",availabilityList);

//        List<FlightPlan> departureFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, true);

//        for(int i=0; i<flights.getFlights().size(); i++){
//            System.out.println(flights.getFlights().get(i).getDepartureDate());
//        }
//
//        flights.sortFlights("departureTimeAscending");
//        for(int i=0; i<flights.getFlights().size(); i++){
//            System.out.println(flights.getFlights().get(i).getDepartureDate());
//        }
//
//        flights.sortFlights("departureTimeDescending");
//        for(int i=0; i<flights.getFlights().size(); i++){
//            System.out.println(flights.getFlights().get(i).getDepartureDate());
//        }


    System.out.println("Stop");




    }

}
