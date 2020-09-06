package group3.seng3150.recommendationLogic;



import group3.seng3150.FlightPlan;
import group3.seng3150.entities.*;
import group3.seng3150.flightLogic.FlightPlanSearch;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class RecommendationGenerator {

    private EntityManager em;
    private int dayRange = 7;
    @Autowired
    public RecommendationGenerator(EntityManager em) {
        this.em =em;
    }

    public List<RecommendationPackage> getRecommendations(UserAccount user){
        FlightPlanSearch fps = new FlightPlanSearch(em);
        List<RecommendationPackage> rp = new LinkedList<>();
        String cc; //class code
        int numPeople; //number of people
        if(user.getUserType()==2){

            //do business stuff
        }

        //do personal stuff
        List<Booking> userBookings = getBookings(user);

        //set the airport
        String ap; //airport
        if(user.getClosestAirport()!=null){
            ap = user.getClosestAirport();
        } else if(userBookings.size()!=0){
            ap = userBookings.get(userBookings.size()).getDeparture();
        } else ap = null;

        if(userBookings.size()!=0){
            cc= userBookings.get(userBookings.size()).getClassCode();
            numPeople = userBookings.get(userBookings.size()).getGroupSize();
        } else cc = "ECO"; numPeople = 1;


        List<HolidayPackages> hp =  em.createQuery("SELECT h FROM HolidayPackages h").getResultList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, dayRange);
        String departureDate = sdf.format(c.getTime());


        for (int i = 0; i < hp.size(); i++) {
            FlightPlan flight;
            RecommendationPackage rec;
            if(ap!=null){
                flight = fps.getSingleFlightPlan(ap, hp.get(i).getDestination(), departureDate, cc, dayRange, numPeople, em);
                rec = new RecommendationPackage(hp.get(i), flight);

            } else rec = new RecommendationPackage(hp.get(i), null);
            rp.add(rec);
        }
        System.out.println("");
        return rp;
    }

    public List<Booking> getBookings(UserAccount user){
        List<Booking> bookings = em.createQuery("SELECT b FROM Booking b WHERE b.userID=" + user.getUserID()).getResultList();
        return bookings;
    }
}
