package group3.seng3150;

import group3.seng3150.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
@RequestMapping("login")
public class UserLogin {

    private EntityManager em;

    @Autowired
    public UserLogin(EntityManager em){this.em =em;}

    @GetMapping
    @RequestMapping("login")
    public ModelAndView login(
            @RequestParam(name="userName", defaultValue="") String userName,
            @RequestParam(name="password", defaultValue="") String password
    )
    {
       ModelAndView view = new ModelAndView("home");

        return view;
    }



}
