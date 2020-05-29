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
public class AuthenticationController {

    private EntityManager em;
    @Autowired
    public AuthenticationController(EntityManager em){this.em =em;}

    //get method login
    @GetMapping("/login")
    public ModelAndView displayLogin() {
        ModelAndView view = new ModelAndView("login");
        return view;
    }

    //get after successful register
    public ModelAndView displayLogin(@RequestParam("message") String message) {
        ModelAndView view = new ModelAndView("login");
        view.addObject("message", message);
        return view;
    }


    //post method login
   @PostMapping("/login")
    public ModelAndView executeLogin(@RequestParam(name="email") String email,
                                     @RequestParam(name="password") String password
   ){
        String message = "";


        try{
            String newEmail = "'" + email + "'";
            String newPassword = "'" + password + "'";
            UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + newEmail + " AND u.password=" + newPassword).getSingleResult();

            ModelAndView view = new ModelAndView("home");


            view.addObject("user", user);
            view.addObject("email", user.getFirstName());
            return view;
        }
        catch(Exception e)
        {
            message = "Email address " + email + " or Password do not match any records, please check your details and try again, or create an account by registering.";
            ModelAndView view = new ModelAndView("login");
            view.addObject("message", message);
            e.printStackTrace();
            return view;
            //
        }

    }

    //get method register
    @GetMapping("/register")
    public ModelAndView displayRegister() {
        ModelAndView view = new ModelAndView("register");
        return view;
    }

    //post method register
    @PostMapping("/register")
    public ModelAndView executeRegister(@RequestParam("firstName") String firstName,
                                        @RequestParam(name="lastName", defaultValue="") String lastName,
                                        @RequestParam(name="gender", defaultValue="") String gender,
                                        @RequestParam(name="password", defaultValue="") String password,
                                        @RequestParam(name="email", defaultValue="") String email,
                                        @RequestParam(name="phone", defaultValue="") int phone,
                                        @RequestParam(name="dateOfBirth") Date dateOfBirth,
                                        @RequestParam(name="userType") String userType
    ){
        ModelAndView view = new ModelAndView("register");
        String message = "Registration successful! ";

    String tempEmail = "'" + email + "'";
        try{
            List<UserAccount> user = em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + tempEmail).getResultList();
            if(user.isEmpty()){
                em.getTransaction().begin();
                UserAccount newUser = new UserAccount();
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setPhone(phone);
                newUser.setDateOfBirth(dateOfBirth);
                newUser.setPassword(password);
                em.merge(newUser);
                em.getTransaction().commit();
                view = new ModelAndView("login");
                message += firstName;
                view.addObject("message", message);
            } else {
                message = "Registration unsuccessful. An Account using " + email + " already exists, please use a unique email address or login to the existing account.";
                view = new ModelAndView("register");
                view.addObject("message", message);
            }
        }
        catch(Exception e)
        {
            message = "Registration unsuccessful. Error Thrown";
            e.printStackTrace();
        }

        return view;
    }




}
