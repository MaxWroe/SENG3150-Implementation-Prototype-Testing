package group3.seng3150;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @GetMapping("/home")
    public ModelAndView Index() {
        ModelAndView view = new ModelAndView("home");
        return view;
    }

    @GetMapping("/search")
    public ModelAndView displaySearch() {
        ModelAndView view = new ModelAndView("search");
        return view;
    }

    @GetMapping("/recommendations")
    public ModelAndView displayRecomendations() {
        ModelAndView view = new ModelAndView("travelRecommendations");
        return view;
    }

    @GetMapping("/bookingtemp")
    public ModelAndView displayBooking() {
        ModelAndView view = new ModelAndView("flightBooking");
        return view;
    }
}
