package group3.seng3150;
import group3.seng3150.entities.*;
import group3.seng3150.recommendationLogic.RecommendationGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;
import group3.seng3150.entities.Review;

@Controller
public class DefaultController {

    private EntityManager em;
    @Autowired
    public DefaultController(EntityManager em){this.em =em;}

    public EntityManager getEm() {
        return em;
    }

    @GetMapping("/")
    public ModelAndView basic() {
        ModelAndView view = new ModelAndView("/home");
        return view;
    }

    @GetMapping("/home")
    public ModelAndView Index() {
        ModelAndView view = new ModelAndView("/home");
        return view;
    }

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

    // login page
    @PostMapping("/login")
    public ModelAndView login(@RequestParam(name="email") String email,
                              HttpSession session) {
        ModelAndView view = new ModelAndView("home");
        session.setAttribute("email", email);
        return view;
    }

    @GetMapping("/logout")
    public ModelAndView executeLogout() {
        ModelAndView view = new ModelAndView("Users/logout");
        return view;
    }

    @GetMapping("/faqs")
    public ModelAndView displayFaqs() {
        ModelAndView view = new ModelAndView("General/faqs");
        return view;
    }

    @GetMapping("/reviews")
    public ModelAndView displayReview(Authentication auth) {
        ModelAndView view = new ModelAndView("General/reviews");
        List<Review> review = (List<Review>) em.createQuery("SELECT r FROM Review r").getResultList();

        String emailSearch = "'" + SecurityContextHolder.getContext().getAuthentication().getName() + "'";
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
        view.addObject("review", review);
        return view;
    }

    @GetMapping("/accessDenied")
    public ModelAndView accessDenied() {
        ModelAndView view = new ModelAndView("/accessDenied");
        return view;
    }


}

