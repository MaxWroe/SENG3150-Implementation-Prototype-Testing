package group3.seng3150;

import group3.seng3150.entities.Booking;
import group3.seng3150.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
public class BookingsController {

    private EntityManager em;
    @Autowired
    public BookingsController(EntityManager em){this.em =em;}

    //get method show bookings
    @GetMapping("/flightBooking")
    public ModelAndView showBooking(){
        ModelAndView view = new ModelAndView("flightBooking");
        return view;
    }

    //get method manage bookings
    @GetMapping("/manageBooking")
    public ModelAndView manageBooking(HttpSession session) {
        ModelAndView view = new ModelAndView("manageBooking");
        return view;
    }

    @PostMapping("/bookingtemp")
    public ModelAndView displayBooking(HttpSession session,
                                       @RequestParam(name="departure") int positionDeparture,
                                       @RequestParam(name="return") int positionReturn,
                                       @RequestParam(name="trip") String trip,
                                       @RequestParam(name="onewayAdultsBooking") int onewayAdultsBooking,
                                       @RequestParam(name="onewayChildrenBooking") int onewayChildrenBooking,
                                       @RequestParam(name="onewayClassBooking") String onewayClassBooking,
                                       @RequestParam(name="returnAdultsBooking") int returnAdultsBooking,
                                       @RequestParam(name="returnChildrenBooking") int returnChildrenBooking,
                                       @RequestParam(name="returnClassBooking") String returnClassBooking
    ){

        //create a List of new bookings with the flight details selected, and add it to the session for the actual booking page to receive payment
        //and persist it
        List<Booking> bookingsDeparture = new List<Booking>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Booking> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Booking booking) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Booking> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Booking> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public Booking get(int index) {
                return null;
            }

            @Override
            public Booking set(int index, Booking element) {
                return null;
            }

            @Override
            public void add(int index, Booking element) {

            }

            @Override
            public Booking remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Booking> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Booking> listIterator(int index) {
                return null;
            }

            @Override
            public List<Booking> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        List<Booking> bookingsReturn = new List<Booking>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Booking> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Booking booking) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Booking> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Booking> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public Booking get(int index) {
                return null;
            }

            @Override
            public Booking set(int index, Booking element) {
                return null;
            }

            @Override
            public void add(int index, Booking element) {

            }

            @Override
            public Booking remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Booking> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Booking> listIterator(int index) {
                return null;
            }

            @Override
            public List<Booking> subList(int fromIndex, int toIndex) {
                return null;
            }
        };


        ModelAndView view = new ModelAndView("flightBooking");
        FlightPlan departure;
        FlightPlan returning;
        Boolean returnTrip = false;
        if(trip.equals("return")){
            returnTrip=true;
        }

        FlightHolder searchDeparture = (FlightHolder) session.getAttribute("departureFlights");
        searchDeparture.setFlightPlanPositions();
        FlightPlan flightPlan = searchDeparture.getFlightPlans().get(positionDeparture);
        flightPlan.getDepartureDate();
        //If it is a group booking
        if(onewayAdultsBooking+onewayChildrenBooking>1) {
            for (int i = 0; i < onewayAdultsBooking + onewayChildrenBooking; i++) {
                Booking newBooking = new Booking();
                newBooking.setGroupSize(onewayAdultsBooking + onewayChildrenBooking);
                for (int j = 0; j < flightPlan.getFlights().size(); j++) {
                    //flightPlan.getFlights().get(j)
                    if (j == 0) {
                        newBooking.setAirlineCode(flightPlan.getFlights().get(j).getAirlineCode());
                        newBooking.setDepartureTime(flightPlan.getFlights().get(j).getDepartureDate());
                        newBooking.setBookingDate(flightPlan.getFlights().get(j).getArrivalDate());
                    } else if (j == 1) {
                        newBooking.setAirlineCode2(flightPlan.getFlights().get(j).getAirlineCode());
                        newBooking.setDepartureTime2(flightPlan.getFlights().get(j).getDepartureDate());
                    } else if (j == 2) {
                        newBooking.setAirlineCode3(flightPlan.getFlights().get(j).getAirlineCode());
                        newBooking.setDepartureTime3(flightPlan.getFlights().get(j).getDepartureDate());
                    } else if (j == 3) {
                        newBooking.setAirlineCode4(flightPlan.getFlights().get(j).getAirlineCode());
                        newBooking.setDepartureTime4(flightPlan.getFlights().get(j).getDepartureDate());
                    }

                    //newBooking.setBookingDate(testDate);
                    newBooking.setClassCode("");
                    newBooking.setClassCode2("");
                    newBooking.setClassCode3("");
                    newBooking.setClassCode4("");
                    //newBooking.setDepartureTime(testDate);
                    //newBooking.setDepartureTime2(testDate);
                    //newBooking.setDepartureTime3(testDate);
                    //newBooking.setDepartureTime4(testDate);


                }
            }
        //if it is an individual booking
        }else{

        }
        //return flight if one exists goes under here
        if(returnTrip) {
            FlightHolder searchReturn = (FlightHolder) session.getAttribute("returnFlights");
            searchReturn.setFlightPlanPositions();
            //flightPlan.get(positionReturn).getDepartureDate();
        }

        return view;
    }

}
