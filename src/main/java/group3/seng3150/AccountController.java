package group3.seng3150;

import group3.seng3150.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
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
    public ModelAndView displayAccountDetails(HttpSession session) {
        ModelAndView view = new ModelAndView("accountDetails");
        return view;
    }


    //POST method AccountDetails
    @PostMapping("/accountDetails/edit")
    public ModelAndView editAccountDetails(@RequestParam("userID") String userEmail,
                                           @RequestParam("firstName") String firstName,
                                           @RequestParam(name="lastName", defaultValue="") String lastName,
                                           @RequestParam(name="gender", defaultValue="") String gender,
                                           @RequestParam(name="password", defaultValue="") String password,
                                           @RequestParam(name="email", defaultValue="") String email,
                                           @RequestParam(name="phone", defaultValue="") String phone,
                                           @RequestParam(name="dateOfBirth") Date dateOfBirth,
                                           @RequestParam(name="citizenship") String citizenship,
                                           @RequestParam(name="familyMembers") String familyMembers, //Family members not currently in the DB, for when it is
                                           @RequestParam(name="emergencyContacts") String emergencyContacts, //Emergency Contacts members not currently in the DB, for when it is
                                           @RequestParam(name="address") String address, //address not currently in the DB, for when it is
                                           @RequestParam(name="userType") String userType,
                                           HttpSession session) {
        String emailSearch = "'" + userEmail + "'";
        String standard = "default";
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.UserID=" + emailSearch).getSingleResult();

        int userTypeNum = 0;
        if(userType.equals("Personal")) {
            userTypeNum = 0;
        } else if(userType.equals("Business")){
            userTypeNum = 1;
        } else {
            userTypeNum = 2;
        }

        int userGender = 0;
        if(gender.equals("Male")) {
            userGender = 0;
        } else{
            userGender = 1;
        }

        int phoneNumber = Integer.parseInt(phone);



        //Update the information in the database
        em.getTransaction().begin(); //Currently throwing an error when committing the transaction
        //user.setUserType(userTypeNum); //Currently broken, stored as int in DB and the USerAccount set tries to set a String, uncomment when fixed
        user.setCitizenship(citizenship);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        //user.setGender(userGender); //Currently broken, stored as int in DB and the UserAccount set tries to set a String, uncomment when fixed
        user.setPassword(password);
        user.setDateOfBirth(dateOfBirth);
        user.setEmail(email);
        user.setPhone(phoneNumber);
        user.setFamilyMembers(familyMembers);
        user.setFamilyMembers(emergencyContacts);
        user.setAddress(address);
        em.persist(user);
        em.getTransaction().commit();

        //Send the new information back to the view
        ModelAndView view = new ModelAndView("accountDetails");
        view.addObject("firstName", user.getFirstName());
        view.addObject("lastName", user.getLastName());
        view.addObject("email", user.getEmail());
        view.addObject("userType", user.getUserType());
        view.addObject("dateOfBirth", user.getDateOfBirth());
        view.addObject("citizenship", user.getCitizenship());
        view.addObject("gender", user.getGender());
        view.addObject("address", standard);
        view.addObject("emergencyContact", standard);
        view.addObject("familyMembers", standard);
        view.addObject("address", user.getAddress());
        view.addObject("emergencyContact", user.getEmergencyContact());
        view.addObject("familyMembers", user.getFamilyMembers());
        view.addObject("phone", user.getPhone());


        // Put new info in the session
        //*************************************************************************
        session.setAttribute("firstName", user.getFirstName());
        session.setAttribute("lastName", user.getLastName());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("userType", user.getUserType());
        session.setAttribute("dateOfBirth", user.getDateOfBirth());
        session.setAttribute("citizenship", user.getCitizenship());
        session.setAttribute("gender", user.getGender());
        session.setAttribute("address", standard);
        session.setAttribute("emergencyContact", standard);
        session.setAttribute("familyMembers", standard);
        session.setAttribute("address", user.getAddress());
        session.setAttribute("emergencyContact", user.getAddress());
        session.setAttribute("familyMembers", user.getAddress());
        //session.setAttribute("phone", user.getPhone());
        //*************************************************************************

        return view;
    }

}
