package group3.seng3150;

import group3.seng3150.entities.Airline;
import group3.seng3150.entities.Airport;
import group3.seng3150.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DefaultController {

    private EntityManager em;
    @Autowired
    public DefaultController(EntityManager em){this.em =em;}

    @GetMapping("/home")
    public ModelAndView Index() {
        ModelAndView view = new ModelAndView("home");
        return view;
    }

//    @GetMapping("/search")
//    public ModelAndView displaySearch() {
//       ModelAndView view = new ModelAndView("home");
//        return view;
//    }

    @GetMapping("/travelRecommendations")
    public ModelAndView displayRecomendations() {
        ModelAndView view = new ModelAndView("travelRecommendations");
        return view;
    }

    @GetMapping("/accessDenied")
    public ModelAndView displayAccessDenied() {
        ModelAndView view = new ModelAndView("accessDenied");
        return view;
    }


    /*
    @GetMapping("/bookingtemp")
    public ModelAndView displayBooking() {
        ModelAndView view = new ModelAndView("flightBooking");
        return view;
    }
    */


/*
    @GetMapping("/accountDetails")
    public ModelAndView displayAccountDetails() {
        ModelAndView view = new ModelAndView("accountDetails");
        return view;
    }*/



    @GetMapping("/customerSupport")
    public ModelAndView displayCustomerSupport() {
        ModelAndView view = new ModelAndView("Users/customerSupport");
        return view;
    }
    /*
    @GetMapping("/manageBooking")
    public ModelAndView displayManageBooking() {
        ModelAndView view = new ModelAndView("manageBooking");
        return view;
    }
 */

    /*
    // testing and dont want to register
    @PostMapping("/login")
    public ModelAndView login(@RequestParam(name="email") String email) {
        ModelAndView view = new ModelAndView("home");
        view.addObject("email", email);
        view.addObject("userID", email);
        return view;
    }*/



    @GetMapping("/logout")
    public ModelAndView executeLogout() {
        ModelAndView view = new ModelAndView("Users/logout");
        return view;
    }

    @GetMapping("/submitReview")
    public ModelAndView displaySubmitReview() {
        ModelAndView view = new ModelAndView("Users/submitReview");
        return view;
    }

    @GetMapping("/faqs")
    public ModelAndView displayFaqs() {
        ModelAndView view = new ModelAndView("General/faqs");
        return view;
    }

    @GetMapping("/reviews")
    public ModelAndView displayReview() {
        ModelAndView view = new ModelAndView("General/reviews");
        return view;
    }


    //airline test
    @GetMapping("/manageAirline")
    public ModelAndView displayAirlines() {
        ModelAndView view = new ModelAndView("Admin/manageAirline");
       // String UserID = (String)session.getAttribute("userId");
        String message = new String();
        try{
            List<Airline> airline = em.createQuery("SELECT airlineName FROM Airline").getResultList();
            view.addObject("airline", airline);

            //checks if booking is empty
            if(airline.isEmpty()){
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

    //airport test
    @GetMapping("/manageAirport")
    public ModelAndView displayAirport() {
        ModelAndView view = new ModelAndView("Admin/manageAirport");
        // String UserID = (String)session.getAttribute("userId");
        String message = new String();
        try{
            List<Airport> airport = em.createQuery("SELECT destinationCode FROM Airport").getResultList();
            view.addObject("airport", airport);

            //checks if booking is empty
            if(airport.isEmpty()){
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

    //wishlist test
    @GetMapping("/wishList")
    public ModelAndView displayWishList() {
        ModelAndView view = new ModelAndView("Users/wishList");
        return view;
    }
}
