package group3.seng3150;

import group3.seng3150.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

@Controller
public class AccountController {

    private EntityManager em;
    @Autowired
    public AccountController(EntityManager em){this.em =em;}

    //get method AccountDetails
    @GetMapping("/accountDetails")
    public ModelAndView displayAccountDetails() {
        ModelAndView view = new ModelAndView("accountDetails");
        return view;
    }


}
