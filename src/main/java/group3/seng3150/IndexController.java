package group3.seng3150;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public ModelAndView Index() {

        ModelAndView view = new ModelAndView("index");
        return view;
    }
}
