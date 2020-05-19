package group3.seng3150;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class TestController {
    @GetMapping
    public ModelAndView Index() {
        ModelAndView view = new ModelAndView("home");
        return view;
    }
}
