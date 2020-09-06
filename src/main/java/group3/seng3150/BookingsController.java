package group3.seng3150;

import group3.seng3150.entities.Booking;
import group3.seng3150.createBooking;
import group3.seng3150.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
                                                @RequestParam("bookingID") String bookingID) {
        ModelAndView view = new ModelAndView("home");
        //String UserID = session.getAttribute(userId);
        String message = new String();
        try{
            List<Booking> booking = em.createQuery("SELECT b FROM Booking b WHERE b.userID=" + userID).getResultList();

            em.getTransaction().begin();
            em.remove(booking.get(Integer.parseInt(bookingID)));
            em.getTransaction().commit();

            booking.remove(bookingID);

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
        ModelAndView view = new ModelAndView("Users/manageBooking");
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
                                   HttpServletRequest request,
                                   Authentication auth,
                                   @RequestParam("flightPlanPosition") int position,
                                   @RequestParam("adultsBooking") String adultsBookingS,
                                   //@RequestParam(name="departure") String positionDepartureS,
                                   @RequestParam(name="return") String positionReturnS,
                                   @RequestParam(name="trip") String trip,
                                   //@RequestParam(name="returnAdultsBooking") int returnAdultsBooking,
                                   //@RequestParam(name="returnChildrenBooking") int returnChildrenBooking,
                                   @RequestParam(name="returnClassBooking") String returnClassBooking,
                                   @RequestParam("childrenBooking") String childrenBooking
    ){
        ModelAndView view = new ModelAndView("Users/manageBooking");
        createBooking bookingMaker = new createBooking(em);
        bookingMaker.makeBooking(session, request, auth, position, adultsBookingS, childrenBooking, positionReturnS, trip, returnClassBooking);
        /*
        List<FlightPlan> searchResults = (List<FlightPlan>) session.getAttribute("departureFlights");
        FlightPlan departure = searchResults.get(position);
        LinkedList<Booking> departureBooking = new LinkedList<Booking>();
        int adultsBooking = Integer.parseInt(adultsBookingS);
        int childrenBookingS = Integer.parseInt(childrenBooking);
        String tempName = new String();
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + auth.getName()).getSingleResult();
        int currentPosition = 1;

        for(int i=1; i<=adultsBooking;i++) {
            Booking tempBooking = new Booking();
            tempBooking.setUserID(auth.getName());
            if(i==1){
                tempBooking.setFirstName(user.getFirstName());
                tempBooking.setLastName(user.getLastName());
                tempBooking.setDateOfBirth(user.getDateOfBirth());
            }else{
                tempName = "adultFirstName" + Integer.toString(i);
                tempBooking.setFirstName(request.getParameter(tempName));
                tempName = "adultLastName" + Integer.toString(i);
                tempBooking.setLastName(request.getParameter(tempName));
                tempName = "adultDOB" + Integer.toString(i);
                String temp = request.getParameter(tempName);
                Date tempDate1 = Date.valueOf(temp);
                tempBooking.setDateOfBirth(tempDate1);
                currentPosition = i;
            }
            departureBooking.add(tempBooking);
        }

        for(int j=1; j<=childrenBookingS;j++) {
            Booking tempBooking = new Booking();

            tempName = "childFirstName" + Integer.toString(j);
            tempBooking.setFirstName(request.getParameter(tempName));
            tempName = "childLastName" + Integer.toString(j);
            tempBooking.setLastName(request.getParameter(tempName));
            tempName = "childDOB" + Integer.toString(j);
            String temp = request.getParameter(tempName);
            Date tempDate1 = Date.valueOf(temp);
            tempBooking.setDateOfBirth(tempDate1);
            currentPosition = j;
            departureBooking.add(tempBooking);
        }
        for(int i =0; i<departureBooking.size();i++) {
            em.getTransaction().begin();
            em.merge(departureBooking.get(i));
            em.getTransaction().commit();
        }
        /*
        for(int i =0; i<returnBooking.size();i++) {
            em.getTransaction().begin();
            em.merge(returnBooking.get(i));
            em.getTransaction().commit();
        }
*/
        /*
        int adultsBooking = Integer.parseInt(adultsBookingS);
        int childrenBookingS = Integer.parseInt(childrenBooking);
        String UserID = (String)session.getAttribute("userId");
        String firstName = (String)session.getAttribute("firstName");
        String lastName = (String)session.getAttribute("lastName");
        Date dob = (Date)session.getAttribute("dateOfBirth");
        List<Booking> departureBooking = (List<Booking>)session.getAttribute("departureBookings");
        <Booking> returnBooking = (List<Booking>)session.getAttribute("returnBookings");
        //request.getParameter("");
        ModelAndView view = new ModelAndView("home");
        String tempName = new String();
        int currentPosition = 1;
        for(int i=1; i<=adultsBooking;i++) {
            departureBooking.get(i-1).setUserID(UserID);
            returnBooking.get(i-1).setUserID(UserID);
            if(i==1){
                departureBooking.get(i-1).setFirstName(firstName);
                returnBooking.get(i-1).setFirstName(firstName);
                departureBooking.get(i-1).setLastName(lastName);
                returnBooking.get(i-1).setLastName(lastName);
                departureBooking.get(i-1).setDateOfBirth(dob);
                returnBooking.get(i-1).setDateOfBirth(dob);
            }else{
                tempName = "adultFirstName" + Integer.toString(i);
                departureBooking.get(i-1).setFirstName(request.getParameter(tempName));
                returnBooking.get(i-1).setFirstName(request.getParameter(tempName));
                tempName = "adultLastName" + Integer.toString(i);
                departureBooking.get(i-1).setLastName(request.getParameter(tempName));
                returnBooking.get(i-1).setLastName(request.getParameter(tempName));
                tempName = "adultDOB" + Integer.toString(i);
                String temp = request.getParameter(tempName);
                Date tempDate1 = Date.valueOf(temp);
                departureBooking.get(i-1).setDateOfBirth(tempDate1);
                returnBooking.get(i-1).setDateOfBirth(tempDate1);
                currentPosition = i;
            }
        }

        for(int j=1; j<=childrenBookingS;j++) {
            departureBooking.get(currentPosition).setUserID(UserID);
            returnBooking.get(currentPosition).setUserID(UserID);
            tempName = "childFirstName" + Integer.toString(j);
            departureBooking.get(currentPosition).setFirstName(request.getParameter(tempName));
            returnBooking.get(currentPosition).setFirstName(request.getParameter(tempName));
            tempName = "childLastName" + Integer.toString(j);
            departureBooking.get(currentPosition).setLastName(request.getParameter(tempName));
            returnBooking.get(currentPosition).setLastName(request.getParameter(tempName));
            tempName = "childDOB" + Integer.toString(j);
            String temp = request.getParameter(tempName);
            Date tempDate1 = Date.valueOf(temp);
            departureBooking.get(currentPosition).setDateOfBirth(tempDate1);
            returnBooking.get(currentPosition).setDateOfBirth(tempDate1);
            currentPosition++;
        }
        for(int i =0; i<departureBooking.size();i++) {
            em.getTransaction().begin();
            em.merge(departureBooking.get(i));
            em.getTransaction().commit();
        }
        for(int i =0; i<returnBooking.size();i++) {
            em.getTransaction().begin();
            em.merge(returnBooking.get(i));
            em.getTransaction().commit();
        }
         */
        return view;
    }

    @PostMapping("/flightBookingReturn")
    public ModelAndView displayBooking(HttpSession session,
                                       @RequestParam(name="departure", defaultValue="") String positionDepartureS,
                                       @RequestParam(name="return", defaultValue="") String positionReturnS,
                                       @RequestParam(name="trip", defaultValue="") String trip,
                                       @RequestParam(name="returnAdultsBooking", defaultValue="") int returnAdultsBooking,
                                       @RequestParam(name="returnChildrenBooking", defaultValue="") int returnChildrenBooking,
                                       @RequestParam(name="returnClassBooking", defaultValue="") String returnClassBooking
    ){
        int positionDeparture = Integer.parseInt(positionDepartureS);
        int positionReturn = Integer.parseInt(positionReturnS);
        //create a List of new bookings with the flight details selected, and add it to the session for the actual booking page to receive payment
        //and persist it
        ModelAndView view = new ModelAndView("bookingPage");

        List<Booking> bookingsDeparture = new LinkedList<Booking>();
        List<Booking> bookingsReturn = new LinkedList<Booking>();

        Boolean returnTrip = false;
        if(trip.equals("return")){
            returnTrip=true;
        }

        FlightHolder searchDeparture = (FlightHolder) session.getAttribute("departureFlights");
        searchDeparture.setFlightPlanPositions();
        FlightPlan flightPlan = searchDeparture.getFlightPlansDeparting().get(positionDeparture);
        flightPlan.getDepartureDate();

        for (int i = 0; i < returnAdultsBooking + returnChildrenBooking; i++) {
            Booking newBooking = new Booking();
            newBooking.setGroupSize(returnAdultsBooking + returnChildrenBooking);
            newBooking.setReturnTrip(1);
            //Works based on a flightPlan having no more than 4 flights, as per assumptions for bookings
            for (int j = 0; j < flightPlan.getFlights().size(); j++) {
                if (j == 0) {
                    //set destination, departure, arrivalTime
                    newBooking.setAirlineCode(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setBookingDate(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setFlightNumber(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination(flightPlan.getFlights().get(j).getDestination());
                    newBooking.setDeparture(flightPlan.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode("D");
                    newBooking.setClassCode(returnClassBooking);
                } else if (j == 1) {
                    newBooking.setAirlineCode2(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime2(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber2(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination2(flightPlan.getFlights().get(j).getDestination());
                    newBooking.setDeparture2(flightPlan.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime2(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode2("D");
                    newBooking.setClassCode2(returnClassBooking);
                } else if (j == 2) {
                    newBooking.setAirlineCode3(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime3(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber3(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination3(flightPlan.getFlights().get(j).getDestination());
                    newBooking.setDeparture3(flightPlan.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime3(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode3("D");
                    newBooking.setClassCode3(returnClassBooking);
                } else if (j == 3) {
                    newBooking.setAirlineCode4(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime4(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber4(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination4(flightPlan.getFlights().get(j).getDestination());
                    newBooking.setDeparture4(flightPlan.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime4(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode4("D");
                    newBooking.setClassCode4(returnClassBooking);
                }
            }
            bookingsDeparture.add(newBooking);
        }


        FlightHolder searchReturn = (FlightHolder) session.getAttribute("returnFlights");
        searchReturn.setFlightPlanPositions();
        FlightPlan flightPlanR = searchReturn.getFlightPlansReturning().get(positionReturn);
        flightPlan.getDepartureDate();

        for (int i = 0; i < returnAdultsBooking + returnChildrenBooking; i++) {
            Booking newBooking = new Booking();
            newBooking.setGroupSize(returnAdultsBooking + returnChildrenBooking);
            newBooking.setReturnTrip(1);
            //Works based on a flightPlan having no more than 4 flights, as per assumptions for bookings
            for (int j = 0; j < flightPlanR.getFlights().size(); j++) {
                if (j == 0) {
                    newBooking.setAirlineCode(flightPlanR.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime(flightPlanR.getFlights().get(j).getDepartureDate());
                    newBooking.setBookingDate(flightPlanR.getFlights().get(j).getArrivalDate());
                    newBooking.setFlightNumber(flightPlanR.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination(flightPlanR.getFlights().get(j).getDestination());
                    newBooking.setDeparture(flightPlanR.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime(flightPlanR.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode("D");
                    newBooking.setClassCode(returnClassBooking);
                } else if (j == 1) {
                    newBooking.setAirlineCode2(flightPlanR.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime2(flightPlanR.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber2(flightPlanR.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination2(flightPlanR.getFlights().get(j).getDestination());
                    newBooking.setDeparture2(flightPlanR.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime2(flightPlanR.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode2("D");
                    newBooking.setClassCode2(returnClassBooking);
                } else if (j == 2) {
                    newBooking.setAirlineCode3(flightPlanR.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime3(flightPlanR.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber3(flightPlanR.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination3(flightPlanR.getFlights().get(j).getDestination());
                    newBooking.setDeparture3(flightPlanR.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime3(flightPlanR.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode3("D");
                    newBooking.setClassCode3(returnClassBooking);
                } else if (j == 3) {
                    newBooking.setAirlineCode4(flightPlanR.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime4(flightPlanR.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber4(flightPlanR.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination4(flightPlanR.getFlights().get(j).getDestination());
                    newBooking.setDeparture4(flightPlanR.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime4(flightPlanR.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode4("D");
                    newBooking.setClassCode4(returnClassBooking);
                }
            }
            bookingsReturn.add(newBooking);
        }
        //*****************SUBJECT TO CHANGE**********************************
        view.addObject("departureFlight", flightPlan.getFlights().get(0));
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
        view.addObject("departureClass", returnClassBooking);
        view.addObject("returnClass", returnClassBooking);
        view.addObject("adultsBooked", returnAdultsBooking);
        view.addObject("childrenBooked", returnChildrenBooking);
        //*****************SUBJECT TO CHANGE**********************************
        view.addObject("returnFlight", flightPlanR.getFlights().get(0));
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
        LinkedList<Booking> bookingsDeparture = new LinkedList<Booking>();

        FlightHolder searchDeparture = (FlightHolder) session.getAttribute("departureFlights");
        searchDeparture.setFlightPlanPositions();
        FlightPlan flightPlan = searchDeparture.getFlightPlansDeparting().get(positionDeparture);
        flightPlan.getDepartureDate();

        for (int i = 0; i < onewayAdultsBooking + onewayChildrenBooking; i++) {
            Booking newBooking = new Booking();
            newBooking.setGroupSize(onewayAdultsBooking + onewayChildrenBooking);
            newBooking.setReturnTrip(0);
            //Works based on a flightPlan having no more than 4 flights, as per assumptions for bookings
            for (int j = 0; j < flightPlan.getFlights().size(); j++) {
                if (j == 0) {
                    newBooking.setAirlineCode(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setBookingDate(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setFlightNumber(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination(flightPlan.getFlights().get(j).getDestination());
                    newBooking.setDeparture(flightPlan.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode("D");
                    newBooking.setClassCode(onewayClassBooking);
                } else if (j == 1) {
                    newBooking.setAirlineCode2(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime2(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber2(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination3(flightPlan.getFlights().get(j).getDestination());
                    newBooking.setDeparture3(flightPlan.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime3(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode2("D");
                    newBooking.setClassCode2(onewayClassBooking);
                } else if (j == 2) {
                    newBooking.setAirlineCode3(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime3(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber3(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination3(flightPlan.getFlights().get(j).getDestination());
                    newBooking.setDeparture3(flightPlan.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime3(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode3("D");
                    newBooking.setClassCode3(onewayClassBooking);
                } else if (j == 3) {
                    newBooking.setAirlineCode4(flightPlan.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime4(flightPlan.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber4(flightPlan.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination4(flightPlan.getFlights().get(j).getDestination());
                    newBooking.setDeparture4(flightPlan.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime4(flightPlan.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode4("D");
                    newBooking.setClassCode4(onewayClassBooking);
                }
            }
            bookingsDeparture.add(newBooking);
        }

        //departureBookings is a List<Booking> that holds the bookings created with the selected flight plan detail put in, but without the user data yet
        session.setAttribute("departureBookings", bookingsDeparture);
        view.addObject("trip", trip);view.addObject("departureBookings", bookingsDeparture);
        view.addObject("departurePrice", flightPlan.getPrice());
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
