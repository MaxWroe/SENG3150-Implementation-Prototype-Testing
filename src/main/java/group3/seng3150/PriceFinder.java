package group3.seng3150;

import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Price;

import javax.persistence.EntityManager;
import java.util.List;

public class PriceFinder {

    private EntityManager em;

    public PriceFinder(EntityManager em) {
        this.em = em;
    }


    public int getPrice(int leg, Availability av){


        String ac = av.getAirlineCode();
        String fn = av.getFlightNumber();
        String cc = av.getClassCode();
        String tc = av.getTicketCode();
        String sd = av.getDepartureDate().toString();


        List<Price> prices = em.createQuery(
                "SELECT p FROM Price p WHERE p.airlineCode = '"+ac+"' " +
                        "AND p.flightNumber = '"+fn+"' " +
                        "AND p.classCode = '"+cc+"' " +
                        "AND p.ticketCode = '"+tc+"' " +
                        "AND p.startDate = '"+sd+"'", Price.class).getResultList();
        Price price = prices.get(0);

        int money = 0;

        switch(leg){
            case 0:
                money = price.getPrice();
                break;

            case 1:
                money = price.getPriceLeg1();
                break;

            case 2:
                money = price.getPriceLeg2();
                break;


        }

        return money;
    }
}
