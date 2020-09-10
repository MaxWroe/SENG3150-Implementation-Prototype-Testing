package group3.seng3150;

import group3.seng3150.entities.*;
import group3.seng3150.recommendationLogic.RecommendationGenerator;
import group3.seng3150.recommendationLogic.RecommendationPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;
import group3.seng3150.entities.Country;

@Controller
public class DefaultController {

    private EntityManager em;
    @Autowired
    public DefaultController(EntityManager em){this.em =em;}

    @GetMapping("/home")
    public ModelAndView Index() {
        ModelAndView view = new ModelAndView("/home");
        return view;
    }

//    @GetMapping("/search")
//    public ModelAndView displaySearch() {
//       ModelAndView view = new ModelAndView("home");
//        return view;
//    }

    @GetMapping("/travelRecommendations")
    public ModelAndView displayRecomendations(Authentication auth) {
        ModelAndView view = new ModelAndView("travelRecommendations");
        RecommendationGenerator genPackages = new RecommendationGenerator(em);
        String emailSearch = "'" + auth.getName() + "'";
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
        view.addObject("recommendationPackages",genPackages.getRecommendations(user));
        return view;
    }


    @GetMapping("/accessDenied")
    public ModelAndView displayAccessDenied() {
        ModelAndView view = new ModelAndView("accessDenied");
        return view;
    }


    @GetMapping("/customerSupport")
    public ModelAndView displayCustomerSupport() {
        ModelAndView view = new ModelAndView("Users/customerSupport");
        return view;
    }

    // testing and dont want to register
    @PostMapping("/login")
    public ModelAndView login(@RequestParam(name="email") String email,
                              HttpSession session) {
        ModelAndView view = new ModelAndView("home");
        session.setAttribute("email", email);
        //session.setAttribute("userID", email);
        return view;
    }



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

/*
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
    */

/*
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
*/
    //wishlist Get
    @GetMapping("/wishList")
    public ModelAndView displayWishList(Authentication auth) {
        ModelAndView view = new ModelAndView("Users/wishList");
        String emailSearch = "'" + auth.getName() + "'";
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
        List<WishListEntry> wishList = (List<WishListEntry>) em.createQuery("SELECT w FROM WishListEntry w WHERE w.userID=" + user.getUserID()).getResultList();
        List<Country> countries = (List<Country>) em.createQuery("SELECT c FROM Country c").getResultList();
        view.addObject("countries", countries);
        view.addObject("wishList", wishList);
        return view;
    }

    //wishlist Post
    @PostMapping("/wishList")
    public ModelAndView updateWishList(Authentication auth,
                                       @RequestParam(name="countryCode", defaultValue = "AUS") String countryCode) {
        ModelAndView view = new ModelAndView("Users/wishList");
        String emailSearch = "'" + auth.getName() + "'";
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
        List<WishListEntry> wishList = (List<WishListEntry>) em.createQuery("SELECT w FROM WishListEntry w WHERE w.userID=" + user.getUserID()).getResultList();
        List<Country> countries = (List<Country>) em.createQuery("SELECT c FROM Country c").getResultList();
        Country addedCountry = Country em.createQuery("SELECT c FROM Country c WHERE c.countryCode3=" + countryCode).getSingleResult();
        WishListEntry newWishlist = new WishListEntry();
        newWishlist.setCountryCode3(countryCode);
        newWishlist.setCountryName(addedCountry.getCountryName());
        newWishlist.setUserID(user.getUserID());
        wishList.add(newWishlist);
        em.getTransaction().begin();
        em.merge(newWishlist);
        em.getTransaction().commit();
        view.addObject("countries", countries);
        view.addObject("wishList", wishList);
        return view;
    }

/*
    @GetMapping("/travelAgentPage")
    public ModelAndView displayHolidayPackage() {
        ModelAndView view = new ModelAndView("TravelAgent/travelAgentPage");
        return view;
    }
*/
}

