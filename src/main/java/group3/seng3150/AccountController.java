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
    @GetMapping("/accountDetails") //Ask about the jsp this comes from and why it is a POST form that maps to the Get mapping
    public ModelAndView displayAccountDetails(@RequestParam("userID") String userEmail) {
        String emailSearch = "'" + userEmail + "'";
        String standard = "default";
        String userTypeWords = "Personal";
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
        if(user.getUserType().equals("2")) {
            userTypeWords = "Family";
        } else if(user.getUserType().equals("1")){
            userTypeWords = "Business";
        }
        ModelAndView view = new ModelAndView("accountDetails");
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
        //view.addObject("address", user.getAddress()); Address has yet to be added to the DB, when it is, this will handle it.
        //view.addObject("emergencyContact", user.getEmergencyContact()); Emergency Contact has yet to be added to the DB, when it is, this will handle it.
        //view.addObject("familyMembers", user.getFamilyMembers()); Family Members has yet to be added to the DB, when it is, this will handle it.
        view.addObject("phone", user.getPhone());
        return view;
    }



    /*
    //POST method AccountDetails
    @PostMapping("/accountDetails")
    public ModelAndView displayAccountDetails(@RequestParam("userID") String userEmail) {
        String emailSearch = "'" + userEmail + "'";
        String standard = "default";
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
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
        //view.addObject("address", user.getAddress()); Address has yet to be added to the DB, when it is, this will handle it.
        //view.addObject("emergencyContact", user.getEmergencyContact()); Emergency Contact has yet to be added to the DB, when it is, this will handle it.
        //view.addObject("familyMembers", user.getFamilyMembers()); Family Members has yet to be added to the DB, when it is, this will handle it.
        view.addObject("phone", user.getPhone());
        return view;
    }
    */


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
                                           //@RequestParam(name="familyMembers") String familyMembers, //Family members not currently in the DB, for when it is
                                           //@RequestParam(name="emergencyContacts") String emergencyContacts, //Emergency Contacts members not currently in the DB, for when it is
                                           //@RequestParam(name="address") String address, //address not currently in the DB, for when it is
                                           @RequestParam(name="userType") String userType) {
        String emailSearch = "'" + userEmail + "'";
        String standard = "default";
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();

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
        //user.setFamilyMembers(familyMembers); Family members not currently in the DB, for when it is
        //user.setFamilyMembers(emergencyContacts); Emergency Contacts members not currently in the DB, for when it is
        //user.setAddress(address); address not currently in the DB, for when it is
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
        //view.addObject("address", user.getAddress()); Address has yet to be added to the DB, when it is, this will handle it.
        //view.addObject("emergencyContact", user.getEmergencyContact()); Emergency Contact has yet to be added to the DB, when it is, this will handle it.
        //view.addObject("familyMembers", user.getFamilyMembers()); Family Members has yet to be added to the DB, when it is, this will handle it.
        view.addObject("phone", user.getPhone());
        return view;
    }

}
