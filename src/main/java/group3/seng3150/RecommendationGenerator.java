package group3.seng3150;

import group3.seng3150.entities.Booking;
import group3.seng3150.entities.UserAccount;

import javax.persistence.EntityManager;
import java.util.List;

public class RecommendationGenerator {
    private EntityManager em;

    public RecommendationGenerator(EntityManager em){this.em =em;}


    public List<FlightPlan> getRecommendations(UserAccount user){

        if(user.getUserType()==0){
            return getPersonal(user);
        } else if(user.getUserType()==1){

            return getFamily(user);
        } else if(user.getUserType()==2){

            return getBusiness(user);
        }

        return null; //just a default
    }


    public List<FlightPlan> getPersonal(UserAccount user){
        String airport;
        if(hasBooked(user).size()>=1){

            airport = hasBooked(user).get(0).getDeparture();
            return generatePersonal(airport);

        } else if (user.getClosestAirport()!=null){

            airport = user.getClosestAirport();
            return generatePersonal(airport);

        } else return getGeneral();

    }

    public List<FlightPlan> getFamily(UserAccount user){
        String airport;
        if(hasBooked(user).size()>=1){

            airport = hasBooked(user).get(0).getDeparture();
            return generateFamily(airport);

        } else if (user.getClosestAirport()!=null){

            airport = user.getClosestAirport();
            return generateFamily(airport);

        } else return getGeneral();

    }

    public List<FlightPlan> getBusiness(UserAccount user){
        String airport;
        if(hasBooked(user).size()>=1){

            airport = hasBooked(user).get(0).getDeparture();
            return generateBusiness(airport);

        } else if (user.getClosestAirport()!=null){

            airport = user.getClosestAirport();
            return generateBusiness(airport);

        } else return getGeneral();
    }

    public List<FlightPlan> generateFamily(String airport){

        //this will have to use flight search
        return null;
    }

    public List<FlightPlan> generatePersonal(String airport){

        //this will have to use flight search
        return null;
    }

    public List<FlightPlan> generateBusiness(String airport){

        //this will have to use flight search
        return null;
    }

    public List<FlightPlan> getGeneral(){

        return null;
    }

    public List<Booking> hasBooked(UserAccount user){

        List<Booking> booking = em.createQuery("SELECT b FROM Booking b WHERE b.userID=" + user.getUserID()).getResultList();

        return booking;
    }

}
