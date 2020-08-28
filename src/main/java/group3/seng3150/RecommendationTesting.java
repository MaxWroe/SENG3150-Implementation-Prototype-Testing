package group3.seng3150;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;

@Controller
public class RecommendationTesting {

    private EntityManager em;
    @Autowired
    public RecommendationTesting(EntityManager em){this.em =em;}
    @GetMapping("/RecTesting")
    public ModelAndView Index() {

        RecommendationGenerator rg = new RecommendationGenerator(this.em);
        //rg.generatePersonal("LAX");
        ModelAndView view = new ModelAndView("recTesting");
        return view;
    }

}

