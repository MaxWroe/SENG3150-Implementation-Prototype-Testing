package group3.seng3150;
import group3.seng3150.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;


public class travelAgentController {

    private EntityManager em;
    @Autowired
    public travelAgentController(EntityManager em){this.em =em;}

    @GetMapping("TravelAgent/travelAgentPage")
    public ModelAndView Index() {
        ModelAndView view = new ModelAndView("TravelAgent/travelAgentPage");



        return view;
    }

}
