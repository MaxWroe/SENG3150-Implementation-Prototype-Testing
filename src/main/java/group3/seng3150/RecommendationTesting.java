package group3.seng3150;

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
        RecommendationGenerator rg = new RecommendationGenerator(this.em);
        rg.getRecommendations(user.get(0));
        //rg.generatePersonal("LAX");
        ModelAndView view = new ModelAndView("recTesting");
        return view;
    }

}

