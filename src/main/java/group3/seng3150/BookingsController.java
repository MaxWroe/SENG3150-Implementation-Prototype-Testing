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
                                                Authentication auth,
                                                @RequestParam("userID") String userID,
                                                @RequestParam("bookingID") String bookingID) {
        ModelAndView view = new ModelAndView("/Users/manageBooking");
        String message = new String();
        String userEmail = "'" +auth.getName()+"'";
        try{
            UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + userEmail).getSingleResult();
            List<Booking> booking = em.createQuery("SELECT b FROM Booking b WHERE b.userID=" + user.getUserID()).getResultList();
            for(int i=0;i<booking.size();i++) {
                if(booking.get(i).getBookingID().equalsIgnoreCase(bookingID)) {
                    em.getTransaction().begin();
                    em.remove(booking.get(i));
                    em.getTransaction().commit();
                    booking.remove(i);
                }
            }

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
    public ModelAndView manageBooking(HttpSession session,
                                      Authentication auth) {
        ModelAndView view = new ModelAndView("Users/manageBooking");
        String userEmail = "'" +auth.getName()+"'";
        String message = new String();
        try{
            UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + userEmail).getSingleResult();
            List<Booking> booking = em.createQuery("SELECT b FROM Booking b WHERE b.userID=" + user.getUserID()).getResultList();
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

        ModelAndView view = new ModelAndView("/Users/manageBooking");
        createBooking bookingMaker = new createBooking(em);
        bookingMaker.makeBooking(session, request, auth, (String) session.getAttribute("adultsBooked"), (String) session.getAttribute("childrenBooked"), (String) session.getAttribute("trip"));

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
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=\'" + auth.getName() +"\'").getSingleResult();
        view.addObject("fName", user.getFirstName());
        view.addObject("lName", user.getLastName());
        view.addObject("dob", user.getDateOfBirth());
        view.addObject("trip", trip);
        view.addObject("adultsBooked", adultsBookingS);
        view.addObject("childrenBooked", childrenBooking);
        view.addObject("departureFlightPlan", searchResults.get(positionDeparture - 1));
        if(trip.equals("return")) {
            view.addObject("returnFlightPlan", searchResults.get(positionReturn - 1));
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
