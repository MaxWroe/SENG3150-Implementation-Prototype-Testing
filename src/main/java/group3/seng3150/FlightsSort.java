package group3.seng3150;

import group3.seng3150.entities.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightsSort {

    public FlightsSort(){}

    //departure time ascending
    //departure time descending
    //price ascending
    //price descending

    public List<Flight> sort(List<Flight> list, String sortMethod){
        if(list.size()<2){
            return list;
        }
        int middle = list.size()/2;
        return merge(
                sort(list.subList(0, middle), sortMethod),
                sort(list.subList(middle, list.size()), sortMethod),
                sortMethod);
    }

    public List<Flight> merge(List<Flight> left, List<Flight> right, String sortMethod){
        int leftIndex = 0;
        int rightIndex = 0;

        List<Flight> merged = new ArrayList<Flight>();

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

//                case "capacityascending":
//                    if (left.get(leftIndex).getNumberAvailableSeatsLeg1()<(right.get(rightIndex).getNumberAvailableSeatsLeg1())) {
//                        merged.add(left.get(leftIndex++));
//                    } else {
//                        merged.add(right.get(rightIndex++));
//                    }
//                    break;
//
//                case "capacitydescending":
//                    if (left.get(leftIndex).getNumberAvailableSeatsLeg1()>(right.get(rightIndex).getNumberAvailableSeatsLeg1())) {
//                        merged.add(left.get(leftIndex++));
//                    } else {
//                        merged.add(right.get(rightIndex++));
//                    }
//                    break;

                case "stopoversascending":
                    if (left.get(leftIndex).getDepartureDate().before(right.get(rightIndex).getDepartureDate())) {
                        if (left.get(leftIndex).getStopOverCode() != "") {
                            merged.add(left.get(leftIndex++));
                        } else if (right.get(rightIndex).getStopOverCode() != "") {
                            merged.add(right.get(rightIndex++));

                        } else {
                            merged.add(left.get(leftIndex++));
                        }
                    }
                    else {
                        if(right.get(rightIndex).getStopOverCode() != ""){
                            merged.add(right.get(rightIndex++));
                        }
                        else if(left.get(leftIndex).getStopOverCode() != "") {
                            merged.add(left.get(leftIndex++));
                        } else {
                            merged.add(right.get(rightIndex++));
                        }
                    }

                    break;

                case "stopoversdescending":
                    if (left.get(leftIndex).getDepartureDate().before(right.get(rightIndex).getDepartureDate()))
                        {
                            if(right.get(rightIndex).getStopOverCode() != ""){
                                merged.add(right.get(rightIndex++));
                            }
                            else if(left.get(leftIndex).getStopOverCode() != "") {
                                merged.add(left.get(leftIndex++));
                            } else {
                                merged.add(right.get(rightIndex++));
                            }
                    }
                    else {
                        if (left.get(leftIndex).getStopOverCode() != "") {
                            merged.add(left.get(leftIndex++));
                        } else if (right.get(rightIndex).getStopOverCode() != "") {
                            merged.add(right.get(rightIndex++));

                        } else {
                            merged.add(left.get(leftIndex++));
                        }
                    }
                    break;
            }
        }
        merged.addAll(left.subList(leftIndex, left.size()));
        merged.addAll(right.subList(rightIndex, right.size()));
        return merged;
    }
}