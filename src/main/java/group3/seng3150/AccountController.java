package group3.seng3150;

import group3.seng3150.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
public class AccountController {

    private EntityManager em;
    @Autowired
    public AccountController(EntityManager em){this.em =em;}


    //get method AccountDetails
    @GetMapping("/accountDetails")
    public ModelAndView displayAccountDetails(HttpSession session,
                                              Authentication auth,
                                              HttpServletRequest request) {
        ModelAndView view = new ModelAndView("Users/accountDetails");
        String emailSearch = "'" + auth.getName() + "'";

        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();


        String gender = "";
        if (user.getGender()==0){
            gender = "Male";
        }else if (user.getGender()==1){
            gender = "Female";
        }else {
            gender = "Other";
        }
        //Send the new information back to the view
        view.addObject("firstName", user.getFirstName());
        view.addObject("lastName", user.getLastName());
        view.addObject("email", user.getEmail());
        view.addObject("userType", user.getUserType());
        view.addObject("dateOfBirth", user.getDateOfBirth());
        view.addObject("citizenship", user.getCitizenship());
        view.addObject("gender", gender);
        view.addObject("address", user.getAddress());
        view.addObject("emergencyContact", user.getEmergencyContact());
        view.addObject("familyMembers", user.getFamilyMembers());
        view.addObject("address", user.getAddress());
        view.addObject("emergencyContact", user.getEmergencyContact());
        view.addObject("familyMembers", user.getFamilyMembers());
        view.addObject("phone", user.getPhone());

        return view;
    }

    //get method AccountDetails
    @PostMapping("/accountDetails")
    public ModelAndView displayPostAccountDetails(HttpSession session,
                                              Authentication auth,
                                              HttpServletRequest request,
                                              @RequestParam(name="update", defaultValue = "") String email) {
        ModelAndView view = new ModelAndView("Users/accountDetails");
        String emailSearch = "'" + auth.getName() + "'";
        if(request.isUserInRole("ADMIN")) {
            emailSearch = "'" + email + "'";
        }
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();


        String gender = "";
        if (user.getGender()==0){
            gender = "Male";
        }else if (user.getGender()==1){
            gender = "Female";
        }else {
            gender = "Other";
        }
        //Send the new information back to the view
        view.addObject("firstName", user.getFirstName());
        view.addObject("lastName", user.getLastName());
        view.addObject("email", user.getEmail());
        view.addObject("userType", user.getUserType());
        view.addObject("dateOfBirth", user.getDateOfBirth());
        view.addObject("citizenship", user.getCitizenship());
        view.addObject("gender", gender);
        view.addObject("address", user.getAddress());
        view.addObject("emergencyContact", user.getEmergencyContact());
        view.addObject("familyMembers", user.getFamilyMembers());
        view.addObject("address", user.getAddress());
        view.addObject("emergencyContact", user.getEmergencyContact());
        view.addObject("familyMembers", user.getFamilyMembers());
        view.addObject("phone", user.getPhone());

        return view;
    }

    //POST method AccountDetails
    @PostMapping("/accountDetails/edit")
    public ModelAndView editAccountDetails(HttpSession session,
                                           Authentication auth,
                                           @RequestParam(name = "userType", defaultValue = "") String userType,
                                           @RequestParam(name = "gender", defaultValue = "Male") String userGender,
                                           @RequestParam(name = "citizenship", defaultValue = "Australian") String citizenship,
                                           @RequestParam(name = "firstName", defaultValue = "") String firstName,
                                           @RequestParam(name = "lastName", defaultValue = "") String lastName,
                                           @RequestParam(name = "dateOfBirth", defaultValue = "01/01/1990") Date dateOfBirth,
                                           @RequestParam(name = "email", defaultValue = "") String email,
                                           @RequestParam(name = "phone", defaultValue = "0") int phoneNumber,
                                           @RequestParam(name = "familyMembers", defaultValue = " ") String familyMembers,
                                           @RequestParam(name = "address", defaultValue = " ") String address,
                                           @RequestParam(name = "airports", defaultValue = "SYD") String preferredAirport,
                                           @RequestParam(name = "emergencyContacts", defaultValue = " ") String emergencyContacts,
                                           @RequestParam(name = "password", defaultValue = " ") String password,
                                           HttpServletRequest request){

        ModelAndView view = new ModelAndView("Users/accountDetails");
        //Retrieve the user's information
        String emailSearch = "'" + auth.getName() + "'";
        if(request.isUserInRole("ADMIN")) {
            emailSearch = "'" + email + "'";
        }
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();

        int userTypeNum = 0;
        if(userType.equals("Personal")) {
            userTypeNum = 0;
        } else if(userType.equals("Business")){
            userTypeNum = 1;
        } else {
            userTypeNum = 2;
        }
        int intUserGender = 0;
        if(userGender.equals("Male")) {
            intUserGender = 0;
        } else{
            intUserGender = 1;
        }

        //int phNum = Integer.getInteger(phoneNumber);

        //Update the information in the database
        em.getTransaction().begin();
        user.setUserType(userTypeNum);
        user.setClosestAirport(preferredAirport);
        user.setCitizenship(citizenship);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(intUserGender);
        user.setPassword(password);
        user.setDateOfBirth(dateOfBirth);
        user.setEmail(email);
        user.setPhone(phoneNumber);
        user.setFamilyMembers(familyMembers);
        user.setEmergencyContact(emergencyContacts);
        user.setAddress(address);
        em.merge(user);
        em.getTransaction().commit();

        //Send the new information back to the view

        view.addObject("firstName", user.getFirstName());
        view.addObject("lastName", user.getLastName());
        view.addObject("email", user.getEmail());
        view.addObject("userType", userType);
        view.addObject("dateOfBirth", user.getDateOfBirth());
        view.addObject("citizenship", user.getCitizenship());
        view.addObject("gender", userGender);
        view.addObject("address", user.getAddress());
        view.addObject("emergencyContact", user.getEmergencyContact());
        view.addObject("familyMembers", user.getFamilyMembers());
        view.addObject("address", user.getAddress());
        view.addObject("emergencyContact", user.getEmergencyContact());
        view.addObject("familyMembers", user.getFamilyMembers());
        view.addObject("phone", user.getPhone());


        System.out.println("emailSearch for: " + emailSearch + " failed");


        return view;
    }

}
