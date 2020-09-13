package group3.seng3150;
import group3.seng3150.entities.UserAccount;
import group3.seng3150.entities.WishListEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import group3.seng3150.entities.Airline;
import group3.seng3150.entities.Airport;

@Controller
public class FlightPubStaffController {

    private EntityManager em;
    @Autowired
    public FlightPubStaffController(EntityManager em){this.em =em;}

    @GetMapping("/manageAirline")
    public ModelAndView manageAirline() {
        ModelAndView view = new ModelAndView("FlightPub/manageAirline");

        List<Airline> airlines = (List<Airline>) em.createQuery("SELECT a FROM Airline a").getResultList();
        view.addObject("airlines", airlines);
        return view;
    }

    @PostMapping("/manageAirline/unsponsor")
    public ModelAndView unsponsorAirline(@RequestParam("airlineName1") String airlineName,
                                         @RequestParam("airlineSponsored1") int airlineSponsored) {
        ModelAndView view = new ModelAndView("FlightPub/manageAirline");
        List<Airline> airlines = (List<Airline>) em.createQuery("SELECT a FROM Airline a").getResultList();

        for(int i=0;i<airlines.size();i++) {
            if(airlines.get(i).getAirlineName().equalsIgnoreCase(airlineName)){
                airlines.get(i).setSponsored(0);
            }
            em.getTransaction().begin();
            em.merge(airlines.get(i));
            em.getTransaction().commit();
        }

        view.addObject("airlines", airlines);
        return view;
    }

    @PostMapping("/manageAirline/sponsor")
    public ModelAndView sponsorAirline(@RequestParam("airlineName") String airlineName,
                                         @RequestParam("airlineSponsored") int airlineSponsored) {
        ModelAndView view = new ModelAndView("FlightPub/manageAirline");
        List<Airline> airlines = (List<Airline>) em.createQuery("SELECT a FROM Airline a").getResultList();

        for(int i=0;i<airlines.size();i++) {
            if(airlines.get(i).getAirlineName().equalsIgnoreCase(airlineName)){
                airlines.get(i).setSponsored(1);
            }
            em.getTransaction().begin();
            em.merge(airlines.get(i));
            em.getTransaction().commit();
        }

        view.addObject("airlines", airlines);
        return view;
    }

    @GetMapping("/manageAirport")
    public ModelAndView manageAirport() {
        ModelAndView view = new ModelAndView("FlightPub/manageAirport");

        List<Airline> airports = (List<Airline>) em.createQuery("SELECT a FROM Airport a").getResultList();
        view.addObject("airports", airports);
        return view;
    }

    @PostMapping("/manageAirport/restrict")
    public ModelAndView restrictAirports(@RequestParam("airlineName2") String airlineName2,
                                         @RequestParam("shutdownStartDate") Date shutdownStartDate,
                                         @RequestParam("shutdownEndDate") Date shutdownEndDate) {
        ModelAndView view = new ModelAndView("FlightPub/manageAirport");
        List<Airport> airports = (List<Airport>) em.createQuery("SELECT a FROM Airport a").getResultList();

        for(int i=0;i<airports.size();i++) {
            if(airports.get(i).getDestinationCode().equalsIgnoreCase(airlineName2)){
                airports.get(i).setShutdownStartDate(shutdownStartDate);
                airports.get(i).setShutdownEndDate(shutdownEndDate);
            }
            em.getTransaction().begin();
            em.merge(airports.get(i));
            em.getTransaction().commit();
        }

        view.addObject("airports", airports);
        return view;
    }

    @GetMapping("/manageUsers")
    public ModelAndView manageUsers() {
        ModelAndView view = new ModelAndView("FlightPub/manageUsers");
        List<UserAccount> users = (List<UserAccount>) em.createQuery("SELECT u FROM UserAccount u").getResultList();
        view.addObject("users", users);
        return view;
    }



    @GetMapping("/addUsers")
    public ModelAndView addUsers() {
        ModelAndView view = new ModelAndView("FlightPub/addUsers");
        List<UserAccount> users = (List<UserAccount>) em.createQuery("SELECT u FROM UserAccount u").getResultList();
        view.addObject("users", users);
        return view;
    }

    @PostMapping("/addUsers/add")
    public ModelAndView storeAddUsers(@RequestParam(name = "firstName", defaultValue = "") String firstName,
                                      @RequestParam(name="lastName", defaultValue="") String lastName,
                                      @RequestParam(name="gender", defaultValue="") String gender,
                                      @RequestParam(name="password", defaultValue="") String password,
                                      @RequestParam(name="email", defaultValue="") String email,
                                      @RequestParam(name="phone", defaultValue="0") int phone,
                                      @RequestParam(name="dateOfBirth") Date dateOfBirth,
                                      @RequestParam(name="userType", defaultValue = "") String userType,
                                      @RequestParam(name ="userRole", defaultValue ="") String userRole ){
        ModelAndView view = new ModelAndView("FlightPub/addUsers");
        List<UserAccount> users = (List<UserAccount>) em.createQuery("SELECT u FROM UserAccount u").getResultList();
        String message = "Registration successful! ";
        String citizenship = "'Default'";
        String tempEmail = "'" + email + "'";
        //currently the usertype is throwing an error, stored in SQL as int, in model as String, need to change one or the other
        //This if statement is for if it is changed to an int.
        int userNum = 0;
        if(userType.equals("Personal")){
            userNum = 0;
        }else if(userType.equals("Business")){
            userNum = 1;
        }else{
            userNum = 2;
        }
        //int phoneNum = Integer.getInteger(phone);
        try{
            List<UserAccount> user = em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + tempEmail).getResultList();
            if(user.isEmpty()){
                em.getTransaction().begin();
                UserAccount newUser = new UserAccount();
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setPhone(phone);
                newUser.setDateOfBirth(dateOfBirth);
                newUser.setPassword(password);
                newUser.setCitizenship(citizenship);
                newUser.setUserType(userNum);
                newUser.setROLEDID(userRole);
                em.merge(newUser);
                em.getTransaction().commit();
                view = new ModelAndView("/FlightPub/manageUsers");
                message += firstName;

            } else {
                message = "Registration unsuccessful. An Account using " + email + " already exists, please use a unique email address or login to the existing account.";
                view = new ModelAndView("/FlightPub/manageUsers");
            }
        }
        catch(Exception e)
        {
            message = "Registration unsuccessful. Error Thrown";
            e.printStackTrace();
        }
        view.addObject("message", message);
        view.addObject("users", users);
        return view;
    }

}
