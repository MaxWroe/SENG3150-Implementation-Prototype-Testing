package group3.seng3150;

import group3.seng3150.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("register")
public class UserRegister {

    private EntityManager em;

    @Autowired
    public UserRegister(EntityManager em){this.em =em;}

    @GetMapping
    @RequestMapping("register")
    public ModelAndView register(
            @RequestParam(name="userName", defaultValue="") String userName,
            @RequestParam(name="password", defaultValue="") String password,
            @RequestParam(name="email", defaultValue="") String email,
            @RequestParam(name="dateOfBirth") Date dateOfBirth,
            @RequestParam(name="userType") String userType
    )
    {
        ModelAndView view = new ModelAndView("login");
        int phone = 6362741; //Need to add a phone number from jsp form, or take out of needed from UserAccount

        List<UserAccount> user = em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + email).getResultList();
        if(user.isEmpty()){
            em.getTransaction().begin();
            UserAccount newUser = new UserAccount(userName, email, phone, dateOfBirth, password);
            em.persist(newUser);
            em.getTransaction().commit();
            return view;
        } else {
            //view.setViewName("invalid");
            return view;
        }

    }



}
