package group3.seng3150;
import group3.seng3150.WishListLogic.CountryStat;
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
import group3.seng3150.WishListLogic.WishListStatistics;

public class travelAgentController {

    private EntityManager em;
    @Autowired
    public travelAgentController(EntityManager em){this.em =em;}

    @GetMapping("travelAgentPage")
    public ModelAndView travelAgentPage() {
        ModelAndView view = new ModelAndView("TravelAgent/travelAgentPage");
        WishListStatistics createWishlist = new WishListStatistics(em);
        List<CountryStat> wishlistStats = createWishlist.getStats();
        view.addObject("countryStat", wishlistStats);
        return view;
    }

    @PostMapping("travelAgentPage/create")
    public ModelAndView travelAgentCreation(@RequestParam (name="comment", defaultValue = "")String description,
                                            @RequestParam (name="destination", defaultValue = "")String destination,
                                            @RequestParam (name="userType", defaultValue = "")String userType,
                                            @RequestParam (name="countryCode", defaultValue = "")String countryCode
    ){
        ModelAndView view = new ModelAndView("TravelAgent/travelAgentPage");
        //0 for sponsored personal, 1 for "hot location",  3 for business, 4 for self generated
        int type = 4;
        if (userType=="Sponsored"){
            type = 0;
        }else if(userType=="Hot location"){
            type = 1;
        }else if(userType=="Business"){
            type = 2;
        }else{
            type = 3;
        }

        HolidayPackages newPackage = new HolidayPackages();
        newPackage.setCountryCode(countryCode);
        newPackage.setDestination(destination);
        newPackage.setDescription(description);
        newPackage.setType(type);

        //newPackage.setCountryName(); Need to be able to retrieve the correct Country Name with Country code to set it


        return view;
    }

}
