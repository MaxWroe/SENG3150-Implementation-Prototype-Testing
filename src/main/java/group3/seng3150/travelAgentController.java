package group3.seng3150;
import group3.seng3150.entities.Booking;
import group3.seng3150.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;
import group3.seng3150.entities.HolidayPackages;

public class travelAgentController {

    private EntityManager em;
    @Autowired
    public travelAgentController(EntityManager em){this.em =em;}

    @GetMapping("TravelAgent/travelAgentPage")
    public ModelAndView travelAgentPage() {
        ModelAndView view = new ModelAndView("TravelAgent/travelAgentPage");



        return view;
    }

    @PostMapping("TravelAgent/travelAgentPage/create")
    public ModelAndView travelAgentCreation(@RequestParam (name="comment", defaultValue = "")String description,
                                            @RequestParam (name="destination", defaultValue = "")String destination,
                                            @RequestParam (name="userType", defaultValue = "")String userType,
                                            @RequestParam (name="countryCode", defaultValue = "")String countryCode
    ){
        ModelAndView view = new ModelAndView("TravelAgent/travelAgentPage");

        HolidayPackages newPackage = new HolidayPackages();
        newPackage.setCountryCode(countryCode);
        newPackage.setDestination(destination);
        newPackage.setDescription(description);
        int userNum = 0;
        if(userType=="Personal") {
            userNum = 0;
        }else if(userType=="Business") {
            userNum = 1;
        }else {
            userNum = 2;
        }
        newPackage.setType(userNum);

        //newPackage.setCountryName(); Need to be able to retrieve the correct Country Name with Country code to set it


        return view;
    }

}
