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
                                   Authentication auth
    ){

        ModelAndView view = new ModelAndView("Users/manageBooking");
        createBooking bookingMaker = new createBooking(em);
        bookingMaker.makeBooking(session, request, auth, (String) session.getAttribute("adultsBooked"), (String) session.getAttribute("childrenBooked"), (String) session.getAttribute("trip"));
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

    @PostMapping("/bookingPage")
    public ModelAndView displayBooking(HttpSession session,
                                       HttpServletRequest request,
                                       Authentication auth,
                                       @RequestParam(name="flightPlanPosition", defaultValue="1") String positionDepartureS,
                                       @RequestParam(name="returnFlightPlanPosition", defaultValue="1") String positionReturnS,
                                       @RequestParam(name="tripType", defaultValue="") String trip,
                                       @RequestParam(name="adultsBooking", defaultValue="0") String adultsBookingS,
                                       @RequestParam(name="childrenBooking", defaultValue="0") String childrenBooking,
                                       @RequestParam(name="classBooking", defaultValue="A") String returnClassBooking
    ){
        ModelAndView view = new ModelAndView("bookingPage");
        createBooking bookingMaker = new createBooking(em);
        bookingMaker.startBooking(trip, session, positionDepartureS, adultsBookingS, childrenBooking, positionReturnS, returnClassBooking);
        List<FlightPlan> searchResults = (List<FlightPlan>) session.getAttribute("departureFlights");
        int positionReturn = Integer.parseInt(positionReturnS);
        int positionDeparture = Integer.parseInt(positionDepartureS);
        view.addObject("trip", trip);
        view.addObject("adultsBooked", adultsBookingS);
        view.addObject("childrenBooked", childrenBooking);
        view.addObject("departureFLightPlan", searchResults.get(positionDeparture - 1));
        if(trip=="return") {
            view.addObject("returnFLightPlan", searchResults.get(positionReturn - 1));
        }
        session.setAttribute("childrenBooked", childrenBooking);
        session.setAttribute("adultsBooked", adultsBookingS);
        session.setAttribute("trip", trip);

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
