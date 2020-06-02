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
            String standard = "default";
            String userTypeWords = "Personal";
            if(user.getUserType()==2) {
                userTypeWords = "Family";
            } else if(user.getUserType()==1){
                userTypeWords = "Business";
            }
            ModelAndView view = new ModelAndView("home");


            view.addObject("user", user);
            view.addObject("userID", user.getUserID());
            view.addObject("firstName", user.getFirstName());
            view.addObject("lastName", user.getLastName());
            view.addObject("email", user.getEmail());
            view.addObject("userType", userTypeWords);
            view.addObject("dateOfBirth", user.getDateOfBirth());
            view.addObject("citizenship", user.getCitizenship());
            view.addObject("gender", user.getGender());
            view.addObject("address", standard);
            view.addObject("emergencyContact", standard);
            view.addObject("familyMembers", standard);
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
    String citizenship = "'Default'";
    String tempEmail = "'" + email + "'";
        //currently the usertype is throwing an error, stored in SQL as int, in model as String, need to change one or the other
        //This if statement is for if it is changed to an int.
    int userNum = 0;
    if(userType.equals("Personal")){
        userNum = 0;
    }else if(userType.equals("Business")){
        userNum = 1;
    }else{
        userNum = 2;
    }
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
                newUser.setCitizenship(citizenship);

                //ANGUS CHANGED STUFF
                //newUser.setUserType(0);
                newUser.setUserType(userNum); //This is for if when it is fixed it has an int value input
                em.merge(newUser);
                em.getTransaction().commit();
                view = new ModelAndView("login");
                message += firstName;

            } else {
                message = "Registration unsuccessful. An Account using " + email + " already exists, please use a unique email address or login to the existing account.";
                view = new ModelAndView("register");

            }
        }
        catch(Exception e)
        {
            message = "Registration unsuccessful. Error Thrown";
            e.printStackTrace();
        }
        view.addObject("message", message);
        return view;
    }




}
