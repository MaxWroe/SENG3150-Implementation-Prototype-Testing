package group3.seng3150;



import group3.seng3150.entities.*;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class RecommendationGenerator {

    private EntityManager em;
    private int dayRange = 7;
    public RecommendationGenerator(EntityManager em) {
        this.em =em;
    }

    public List<FlightPlan> getRecommendations(UserAccount user){
        List<FlightPlan> fp = new LinkedList<>();
        String cc; //class code
        if(user.getUserType()==2){

            //do business stuff
        }

        //do personal stuff
        List<Booking> userBookings = getBookings(user);

        //set the airport
        String ap; //airport
        if(user.getClosestAirport()!=null){
            ap = user.getClosestAirport();
        } else if(userBookings.get(userBookings.size())!=null){
            ap = userBookings.get(userBookings.size()).getDeparture();
        } else ap = null;

        if(userBookings.get(userBookings.size())!=null){
            cc= userBookings.get(userBookings.size()).getClassCode();
        } else cc = "ECO";


        List<HolidayPackages> hp =  em.createQuery("SELECT h FROM HolidayPackages h").getResultList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, dayRange);
        String newDate = sdf.format(c.getTime());

        for (int i = 0; i < hp.size(); i++) {
            //departure, arrival, departuredate, cc
            Flight flight = null; //getFlight()
            //fp.add(searched flightplan);
        }

        return null;
    }

    public List<Booking> getBookings(UserAccount user){
        List<Booking> bookings = em.createQuery("SELECT b FROM Booking b WHERE b.userID=" + user.getUserID()).getResultList();
        return bookings;
    }
}
