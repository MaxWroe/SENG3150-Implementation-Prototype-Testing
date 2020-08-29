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
    private List<FlightPlan> flightPlansDeparting;
    private List<FlightPlan> flightPlansReturning;
    private List<Availability> availabilities;

    public FlightHolder(){
        sorter = new FlightsSort();
        flightPlansDeparting = new ArrayList<>();
        flightPlansReturning = new ArrayList<>();
    }

    //sorts the list of flights by the sorting method
    public List<FlightPlan> sortFlightPlansDeparting(String sortMethod){
        flightPlansDeparting = sorter.sortFlightPlan(flightPlansDeparting, sortMethod);
        return flightPlansDeparting;
    }

    public List<FlightPlan> sortFlightPlansReturning(String sortMethod){
        flightPlansReturning = sorter.sortFlightPlan(flightPlansReturning, sortMethod);
        return flightPlansReturning;
    }

    public List<FlightPlan> getFlightPlansDeparting() {
//        setFlightPlanPositions();
        return flightPlansDeparting;
    }

    public void setFlightPlansDeparting(List<FlightPlan> flightPlansDeparting) {
        this.flightPlansDeparting = flightPlansDeparting;
//        setFlightPlanPositions();
    }

    public List<FlightPlan> getFlightPlansReturning() {
        return flightPlansReturning;
    }

    public void setFlightPlansReturning(List<FlightPlan> flightPlansReturning) {
        this.flightPlansReturning = flightPlansReturning;
    }

    public FlightsSort getSorter() {
        return sorter;
    }

    public void setSorter(FlightsSort sorter) {
        this.sorter = sorter;
//        setFlightPlanPositions();
    }

    public void setFlightPlanPositions()
    {
        for (int i=0; i<flightPlansDeparting.size(); i++){
            if(flightPlansDeparting.get(i)!=null) {
                flightPlansDeparting.get(i).setPosition(i);
            }
        }
        for (int i=0; i<flightPlansReturning.size(); i++){
            if(flightPlansReturning.get(i)!=null) {
                flightPlansReturning.get(i).setPosition(i);
            }
        }
    }

    //gives flight plans the entity manager and sets prices for those flight plans based on their availabilities
    public void setAllPrices(EntityManager em){
        for(int i=0; i<flightPlansDeparting.size(); i++){
            if(flightPlansDeparting.get(i)!=null) {
                flightPlansDeparting.get(i).setPrices(em);
            }
        }
        for(int i=0; i<flightPlansReturning.size(); i++){
            if(flightPlansReturning.get(i)!=null) {
                flightPlansReturning.get(i).setPrices(em);
            }
        }
    }

    public int getFlightPlansDepartingSize(){
        return flightPlansDeparting.size();
    }
}
