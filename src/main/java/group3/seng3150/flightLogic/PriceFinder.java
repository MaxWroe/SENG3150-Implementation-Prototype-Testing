package group3.seng3150.flightLogic;

import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Price;

import javax.persistence.EntityManager;
import java.util.List;

public class PriceFinder {

    private EntityManager em;

    public PriceFinder(EntityManager em) {
        this.em = em;
    }


    public List<Price> getPrice(int leg, Availability av){


        String ac = av.getAirlineCode();
        String fn = av.getFlightNumber();
        String cc = av.getClassCode();
        String tc = av.getTicketCode();
        String dd = av.getDepartureDate().toString();


        List<Price> prices = em.createQuery(
                "SELECT p FROM Price p WHERE p.airlineCode = '"+ac+"' " +
                        "AND p.flightNumber = '"+fn+"' " +
                        "AND p.classCode = '"+cc+"' " +
                        "AND p.ticketCode = '"+tc+"' " +
                        "AND p.startDate <'"+dd+"' AND p.endDate>'"+dd+"'", Price.class).getResultList();
        return prices;

    }
}
