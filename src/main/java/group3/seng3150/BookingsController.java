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
import java.util.Random;

@Controller
public class BookingsController {

    private EntityManager em;
    @Autowired
    public BookingsController(EntityManager em){this.em =em;}

    //get method show bookings
    @GetMapping("/flightBooking")
    public ModelAndView showBooking(HttpSession session){
        ModelAndView view = new ModelAndView("flightBooking");
        return view;
    }

    //get method manage bookings
    @PostMapping("/manageBooking/cancel")
    public ModelAndView manageBookingCancelling(HttpSession session,
                                                @RequestParam("userID") String userID,
                                                @RequestParam("bookingNumber") int bookingNumber) {
        ModelAndView view = new ModelAndView("manageBooking");
        //String UserID = session.getAttribute(userId);
        String message = new String();
        try{
            List<Booking> booking = em.createQuery("SELECT b FROM Booking b WHERE b.userID=" + userID).getResultList();
            em.getTransaction().begin();
            em.remove(booking.get(bookingNumber));
            em.getTransaction().commit();

            booking.remove(bookingNumber);

            view.addObject("booking", booking);
        }
        catch(Exception e)
        {
            message = "No bookings found for this user.";
            view.addObject("message", message);
            e.printStackTrace();
        }

        return view;
    }

    //get method manage bookings
    @GetMapping("/manageBooking")
    public ModelAndView manageBooking(HttpSession session) {
        ModelAndView view = new ModelAndView("manageBooking");
        String UserID = (String)session.getAttribute("userId");
        String message = new String();
        try{
            List<Booking> booking = em.createQuery("SELECT b FROM Booking b WHERE b.userID=" + UserID).getResultList();
            view.addObject("booking", booking);

            //checks if booking is empty
            if(booking.isEmpty()){
                message = "No bookings found for this user.";
                view.addObject("message", message);
            }
        }
        catch(Exception e)
        {
            //doesn't seem to be going here
            message = "No bookings found for this user.";
            view.addObject("message", message);
            e.printStackTrace();
        }

        return view;
    }

    @PostMapping("/bookFlight")
    public ModelAndView bookFlight(HttpSession session,
                                   @RequestParam("dateOfBirth") Date dateOfBirth,
                                   @RequestParam("firstName") String firstName,
                                   @RequestParam("userID") String userID){
        ModelAndView view = new ModelAndView("manageBooking");
        return view;
    }

    @PostMapping("/flightBookingReturn")
    public ModelAndView displayBooking(HttpSession session,
                                       @RequestParam(name="flightPlan") String positionDepartureS,
                                       @RequestParam(name="return") String positionReturnS,
                                       @RequestParam(name="trip") String trip,
                                       @RequestParam(name="onewayAdultsBooking") int onewayAdultsBooking,
                                       @RequestParam(name="onewayChildrenBooking") int onewayChildrenBooking,
                                       @RequestParam(name="onewayClassBooking") String onewayClassBooking,
                                       @RequestParam(name="returnAdultsBooking") int returnAdultsBooking,
                                       @RequestParam(name="returnChildrenBooking") int returnChildrenBooking,
                                       @RequestParam(name="returnClassBooking") String returnClassBooking
    ){
        int positionDeparture = Integer.parseInt(positionDepartureS);
        int positionReturn = Integer.parseInt(positionReturnS);
        //create a List of new bookings with the flight details selected, and add it to the session for the actual booking page to receive payment
        //and persist it
        ModelAndView view = new ModelAndView("flightBooking");

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

        Boolean returnTrip = false;
        if(trip.equals("return")){
            returnTrip=true;
        }

        FlightHolder searchDeparture = (FlightHolder) session.getAttribute("departureFlights");
        searchDeparture.setFlightPlanPositions();
        FlightPlan flightPlan = searchDeparture.getFlightPlans().get(positionDeparture);
        flightPlan.getDepartureDate();

        for (int i = 0; i < onewayAdultsBooking + onewayChildrenBooking; i++) {
            Booking newBooking = new Booking();
            newBooking.setGroupSize(onewayAdultsBooking + onewayChildrenBooking);
            //Works based on a flightPlan having no more than 4 flights, as per assumptions for bookings
            for (int j = 0; j < flightPlan.getFlights().size(); j++) {
                if (j == 0) {
                    newBooking.setAirlineCode(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setBookingDate(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setFlightNumber(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode(generateTicketNumber());
                    newBooking.setClassCode(onewayClassBooking);
                } else if (j == 1) {
                    newBooking.setAirlineCode2(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime2(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber2(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode2(generateTicketNumber());
                    newBooking.setClassCode2(onewayClassBooking);
                } else if (j == 2) {
                    newBooking.setAirlineCode3(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime3(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber3(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode3(generateTicketNumber());
                    newBooking.setClassCode3(onewayClassBooking);
                } else if (j == 3) {
                    newBooking.setAirlineCode4(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime4(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber4(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode4(generateTicketNumber());
                    newBooking.setClassCode4(onewayClassBooking);
                }
            }
            bookingsDeparture.add(newBooking);
        }


        FlightHolder searchReturn = (FlightHolder) session.getAttribute("returnFlights");
        searchReturn.setFlightPlanPositions();
        FlightPlan flightPlanR = searchReturn.getFlightPlans().get(positionDeparture);
        flightPlan.getDepartureDate();

        for (int i = 0; i < onewayAdultsBooking + onewayChildrenBooking; i++) {
            Booking newBooking = new Booking();
            newBooking.setGroupSize(onewayAdultsBooking + onewayChildrenBooking);
            //Works based on a flightPlan having no more than 4 flights, as per assumptions for bookings
            for (int j = 0; j < flightPlanR.getFlights().size(); j++) {
                if (j == 0) {
                    newBooking.setAirlineCode(flightPlanR.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime(flightPlanR.getFlights().get(j).getDepartureDate());
                    newBooking.setBookingDate(flightPlanR.getFlights().get(j).getArrivalDate());
                    newBooking.setFlightNumber(flightPlanR.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode(generateTicketNumber());
                    newBooking.setClassCode(returnClassBooking);
                } else if (j == 1) {
                    newBooking.setAirlineCode2(flightPlanR.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime2(flightPlanR.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber2(flightPlanR.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode2(generateTicketNumber());
                    newBooking.setClassCode2(returnClassBooking);
                } else if (j == 2) {
                    newBooking.setAirlineCode3(flightPlanR.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime3(flightPlanR.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber3(flightPlanR.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode3(generateTicketNumber());
                    newBooking.setClassCode3(returnClassBooking);
                } else if (j == 3) {
                    newBooking.setAirlineCode4(flightPlanR.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime4(flightPlanR.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber4(flightPlanR.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode4(generateTicketNumber());
                    newBooking.setClassCode4(returnClassBooking);
                }
            }
            bookingsReturn.add(newBooking);
        }
        //*****************SUBJECT TO CHANGE**********************************
        view.addObject("departureFlight", flightPlanR.getFlights().get(0));
        //*****************SUBJECT TO CHANGE**********************************


        //departureBookings is a List<Booking> that holds the bookings created with the selected flight plan detail put in, but without the user data yet
        session.setAttribute("departureBookings", bookingsDeparture);
        //returnBookings is a List<Booking> that holds the bookings created with the selected flight plan detail put in, but without the user data yet if the search had a return booking included
        session.setAttribute("returnBookings", bookingsReturn);
        //This is a boolean that indicates whether there is a return trip or not. True for yes, false for no.
        session.setAttribute("returnTrip", returnTrip);
        view.addObject("trip", trip);
        view.addObject("departureBookings", bookingsDeparture);
        view.addObject("returnBookings", bookingsReturn);
        view.addObject("returnTrip", returnTrip);
        view.addObject("departurePrice", flightPlan.getPrice());
        view.addObject("returnPrice", flightPlanR.getPrice());
        view.addObject("departureClass", onewayClassBooking);
        view.addObject("returnClass", returnClassBooking);
        view.addObject("adultsBooked", onewayAdultsBooking);
        view.addObject("childrenBooked", onewayChildrenBooking);
        //*****************SUBJECT TO CHANGE**********************************
        view.addObject("returnFlight", flightPlan.getFlights().get(0));
        //*****************SUBJECT TO CHANGE**********************************
        return view;
    }

    @PostMapping("/flightBookingOneway")
    public ModelAndView displayBookingOneway(HttpSession session,
                                       @RequestParam(name="flightPlan") String positionDepartureS,
                                       @RequestParam(name="onewayAdultsBooking") int onewayAdultsBooking,
                                       @RequestParam(name="onewayChildrenBooking") int onewayChildrenBooking,
                                       @RequestParam(name="trip") String trip,
                                       @RequestParam(name="onewayClassBooking") String onewayClassBooking
    ){
        int positionDeparture = Integer.parseInt(positionDepartureS);

        //create a List of new bookings with the flight details selected, and add it to the session for the actual booking page to receive payment
        //and persist it
        ModelAndView view = new ModelAndView("flightBooking");
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

        FlightHolder searchDeparture = (FlightHolder) session.getAttribute("departureFlights");
        searchDeparture.setFlightPlanPositions();
        FlightPlan flightPlan = searchDeparture.getFlightPlans().get(positionDeparture);
        flightPlan.getDepartureDate();

        for (int i = 0; i < onewayAdultsBooking + onewayChildrenBooking; i++) {
            Booking newBooking = new Booking();
            newBooking.setGroupSize(onewayAdultsBooking + onewayChildrenBooking);
            //Works based on a flightPlan having no more than 4 flights, as per assumptions for bookings
            for (int j = 0; j < flightPlan.getFlights().size(); j++) {
                if (j == 0) {
                    newBooking.setAirlineCode(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setBookingDate(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setFlightNumber(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode(generateTicketNumber());
                    newBooking.setClassCode(onewayClassBooking);
                } else if (j == 1) {
                    newBooking.setAirlineCode2(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime2(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber2(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode2(generateTicketNumber());
                    newBooking.setClassCode2(onewayClassBooking);
                } else if (j == 2) {
                    newBooking.setAirlineCode3(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime3(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber3(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode3(generateTicketNumber());
                    newBooking.setClassCode3(onewayClassBooking);
                } else if (j == 3) {
                    newBooking.setAirlineCode4(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime4(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber4(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setTicketCode4(generateTicketNumber());
                    newBooking.setClassCode4(onewayClassBooking);
                }
            }
            bookingsDeparture.add(newBooking);
        }

        //departureBookings is a List<Booking> that holds the bookings created with the selected flight plan detail put in, but without the user data yet
        session.setAttribute("departureBookings", bookingsDeparture);
        view.addObject("trip", trip);
        view.addObject("departureBookings", bookingsDeparture);
        view.addObject("departurePrice", flightPlan.getPrice());
        view.addObject("departureClass", onewayClassBooking);
        view.addObject("adultsBooked", onewayAdultsBooking);
        view.addObject("childrenBooked", onewayChildrenBooking);
        return view;
    }

    public String generateTicketNumber(){
        Random rand = new Random();
        int ticketNumberLength = 20;
        String generatedFrom = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        generatedFrom += generatedFrom.toLowerCase();
        generatedFrom += "0123456789";
        char[] alphanumSymbols = generatedFrom.toCharArray();
        char[] ticketChars = new char[ticketNumberLength];

        for(int i=0; i<ticketNumberLength; i++){
            ticketChars[i] = alphanumSymbols[rand.nextInt(generatedFrom.length())];
        }
        return ticketChars.toString();
    }

}
