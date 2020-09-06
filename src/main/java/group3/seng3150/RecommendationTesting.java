package group3.seng3150;

import group3.seng3150.WishListLogic.WishListStatistics;
import group3.seng3150.entities.Availability;
import group3.seng3150.entities.UserAccount;
import group3.seng3150.recommendationLogic.RecommendationGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
public class RecommendationTesting {

    private EntityManager em;
    @Autowired
    public RecommendationTesting(EntityManager em){this.em =em;}
    @GetMapping("/RecTesting")
    public ModelAndView Index() {
        List<UserAccount> user = em.createQuery("SELECT u FROM UserAccount u").getResultList();
        List<Availability> testing = em.createQuery("SELECT a FROM Availability a WHERE a.departureDate>='2020-12-13 00:00:01.0' AND a.departureDate<='2020-12-13 23:59:59.0' AND a.numberAvailableSeatsLeg1>=1 AND a.classCode='ECO'").getResultList();
        //RecommendationGenerator rg = new RecommendationGenerator(this.em);
        //rg.getRecommendations(user.get(2));


        WishListStatistics wls = new WishListStatistics(em);
        List temp = wls.getStats();
        //rg.generatePersonal("LAX");
        ModelAndView view = new ModelAndView("recTesting");
        return view;
    }

}

