package group3.seng3150.flightLogic;

import group3.seng3150.FlightPlan;

import java.util.ArrayList;
import java.util.List;

public class FlightsSort {

    public FlightsSort(){}

    //departure time ascending
    //departure time descending
    //price ascending
    //price descending

    public List<FlightPlan> sortFlightPlan(List<FlightPlan> list, String sortMethod){
        if(list.size()<2){
            return list;
        }
        int middle = list.size()/2;
        return mergeFlightPlan(
                sortFlightPlan(list.subList(0, middle), sortMethod),
                sortFlightPlan(list.subList(middle, list.size()), sortMethod),
                sortMethod);
    }

    public List<FlightPlan> mergeFlightPlan(List<FlightPlan> left, List<FlightPlan> right, String sortMethod){
        int leftIndex = 0;
        int rightIndex = 0;

        List<FlightPlan> merged = new ArrayList<FlightPlan>();

        while (leftIndex<left.size() && rightIndex<right.size()){
            switch (sortMethod) {
                case "timeascending":
                    if (left.get(leftIndex).getDepartureDate().before(right.get(rightIndex).getDepartureDate())) {
                        merged.add(left.get(leftIndex++));
                    } else {
                        merged.add(right.get(rightIndex++));
                    }
                    break;

                case "timedescending":
                    if (left.get(leftIndex).getDepartureDate().after(right.get(rightIndex).getDepartureDate())) {
                        merged.add(left.get(leftIndex++));
                    } else {
                        merged.add(right.get(rightIndex++));
                    }
                    break;

                case "priceascending":
                    if (left.get(leftIndex).getPrice()<(right.get(rightIndex).getPrice())) {
                        merged.add(left.get(leftIndex++));
                    } else {
                        merged.add(right.get(rightIndex++));
                    }
                    break;

                case "pricedescending":
                    if (left.get(leftIndex).getPrice()>(right.get(rightIndex).getPrice())) {
                        merged.add(left.get(leftIndex++));
                    } else {
                        merged.add(right.get(rightIndex++));
                    }
                    break;

                case "capacityascending":
                    if (left.get(leftIndex).getNumberAvailableSeats()<(right.get(rightIndex).getNumberAvailableSeats())) {
                        merged.add(left.get(leftIndex++));
                    } else {
                        merged.add(right.get(rightIndex++));
                    }
                    break;

                case "capacitydescending":
                    if (left.get(leftIndex).getNumberAvailableSeats()>(right.get(rightIndex).getNumberAvailableSeats())) {
                        merged.add(left.get(leftIndex++));
                    } else {
                        merged.add(right.get(rightIndex++));
                    }
                    break;

                case "stopoversascending":
                    if (left.get(leftIndex).getNumberStopOvers()<(right.get(rightIndex).getNumberStopOvers())) {
                        merged.add(left.get(leftIndex++));
                    }
                    else {
                        merged.add(right.get(rightIndex++));
                    }

                    break;

                case "stopoversdescending":
                    if (left.get(leftIndex).getNumberStopOvers()>(right.get(rightIndex).getNumberStopOvers()))
                    {
                        merged.add(left.get(leftIndex++));
                    }
                    else {
                        merged.add(right.get(rightIndex++));
                    }
                    break;
            }
        }
        merged.addAll(left.subList(leftIndex, left.size()));
        merged.addAll(right.subList(rightIndex, right.size()));
        return merged;
    }

}