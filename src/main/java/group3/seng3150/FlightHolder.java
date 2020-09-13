package group3.seng3150;

import group3.seng3150.flightLogic.FlightsSort;
import group3.seng3150.entities.Availability;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/*
Author: Chris Mather
Description: This class is a bean that stores lists of flightsPlans so they can be passed between pages and can call methods to manipulate the flight plans
*/


public class FlightHolder {
    private FlightsSort sorter;
    private List<FlightPlan> flightPlansDeparting;
    private List<FlightPlan> flightPlansReturning;

    public FlightHolder(){
        sorter = new FlightsSort();
        flightPlansDeparting = new ArrayList<>();
        flightPlansReturning = new ArrayList<>();
    }

    //sorts the departing list of flights by the sorting method
    public List<FlightPlan> sortFlightPlansDeparting(String sortMethod){
        flightPlansDeparting = sorter.sortFlightPlan(flightPlansDeparting, sortMethod);
        return flightPlansDeparting;
    }

    //sorts the returning list of flights by the sorting method
    public List<FlightPlan> sortFlightPlansReturning(String sortMethod){
        flightPlansReturning = sorter.sortFlightPlan(flightPlansReturning, sortMethod);
        return flightPlansReturning;
    }

    public List<FlightPlan> getFlightPlansDeparting() {
        return flightPlansDeparting;
    }

    public void setFlightPlansDeparting(List<FlightPlan> flightPlansDeparting) {
        this.flightPlansDeparting = flightPlansDeparting;
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
    }

    public int getFlightPlansDepartingSize(){
        return flightPlansDeparting.size();
    }

    public int getFlightPlansReturningSize(){
        return flightPlansReturning.size();
    }
}
