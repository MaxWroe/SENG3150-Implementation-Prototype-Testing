package group3.seng3150;

import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FlightHolder {
    private FlightsSort sorter;
    private List<FlightPlan> flightPlans;
    private List<Availability> availabilities;

    public FlightHolder(){
        sorter = new FlightsSort();
        flightPlans = new ArrayList<>();
    }


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

    public void setFlightPlanPositions()
    {
        for (int i=0; i<flightPlans.size(); i++){
            flightPlans.get(i).setPosition(i);
        }
    }

    public void setAllPrices(EntityManager em){
        for(int i=0; i<flightPlans.size(); i++){
            flightPlans.get(i).setPrices(em);
        }
    }
}
