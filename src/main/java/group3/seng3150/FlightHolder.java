package group3.seng3150;

import group3.seng3150.flightLogic.FlightsSort;
import group3.seng3150.entities.Availability;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/*
Author: Chris Mather
Description: This class is a bean that stores lists of flightsPlans and availabilites and can call methods to manipulate them
 */


public class FlightHolder {
    private FlightsSort sorter;
    private List<FlightPlan> flightPlans;
    private List<Availability> availabilities;

    public FlightHolder(){
        sorter = new FlightsSort();
        flightPlans = new ArrayList<>();
    }

    //sorts the list of flights by the sorting method
    public List<FlightPlan> sortFlightPlans(String sortMethod){
        flightPlans = sorter.sortFlightPlan(flightPlans, sortMethod);
        return flightPlans;
    }

    public List<FlightPlan> getFlightPlans() {
        setFlightPlanPositions();
        return flightPlans;
    }

    public void setFlightPlans(List<FlightPlan> flightPlans) {
        this.flightPlans = flightPlans;
        setFlightPlanPositions();
    }

    public FlightsSort getSorter() {
        return sorter;
    }

    public void setSorter(FlightsSort sorter) {
        this.sorter = sorter;
        setFlightPlanPositions();
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    //
    public void setFlightPlanPositions()
    {
        for (int i=0; i<flightPlans.size(); i++){
            if(flightPlans.get(i)!=null) {
                flightPlans.get(i).setPosition(i);
            }
        }
    }

    //gives flight plans the entity manager and sets prices for those flight plans based on their availabilities
    public void setAllPrices(EntityManager em){
        for(int i=0; i<flightPlans.size(); i++){
            if(flightPlans.get(i)!=null) {
                flightPlans.get(i).setPrices(em);
            }
        }
    }
}
