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
                case "departureTimeAscending":
                    if (left.get(leftIndex).getDepartureDate().before(right.get(rightIndex).getDepartureDate())) {
                        merged.add(left.get(leftIndex++));
                    } else {
                        merged.add(right.get(rightIndex++));
                    }
                    break;

                case "departureTimeDescending":
                    if (left.get(leftIndex).getDepartureDate().after(right.get(rightIndex).getDepartureDate())) {
                        merged.add(left.get(leftIndex++));
                    } else {
                        merged.add(right.get(rightIndex++));
                    }
                    break;

                case "priceAscending":
                    if (left.get(leftIndex).getPrice()<(right.get(rightIndex).getPrice())) {
                        merged.add(left.get(leftIndex++));
                    } else {
                        merged.add(right.get(rightIndex++));
                    }
                    break;

                case "priceDescending":
                    if (left.get(leftIndex).getPrice()>(right.get(rightIndex).getPrice())) {
                        merged.add(left.get(leftIndex++));
                    } else {
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