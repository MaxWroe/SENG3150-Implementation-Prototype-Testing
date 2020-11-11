package group3.seng3150;

import group3.seng3150.entities.*;
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
import java.util.Calendar;
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

    @GetMapping("/submitReview")
    public ModelAndView displaySubmitReview() {
        ModelAndView view = new ModelAndView("Users/submitReview");
        return view;
    }

    @PostMapping("/submitReview")
    public ModelAndView storeReview(@RequestParam(name="rating", defaultValue = "3") int rating,
                                    @RequestParam(name="reviewType", defaultValue = "") String reviewType,
                                    @RequestParam(name="comment", defaultValue = "") String comment,
                                    @RequestParam(name="name", defaultValue = "") String name,
                                    Authentication auth
    ) {
        ModelAndView view = new ModelAndView("General/reviews");
        Review newReview = new Review();

        String emailSearch = "'" + auth.getName() + "'";
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
        newReview.setUserID(user.getUserID());
        newReview.setComment(comment);
        newReview.setRating(rating);
        newReview.setName(name);
        int type = 2;
        if(reviewType.equalsIgnoreCase("flight")){
            type = 0;
        }else if(reviewType.equalsIgnoreCase("airport")){
            type = 1;
        }else{
            type = 3;
        }
        newReview.setReviewType(type);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        newReview.setReviewDate(date);
        em.getTransaction().begin();
        em.merge(newReview);
        em.getTransaction().commit();

        return view;
    }


    @GetMapping("/customerSupport")
    public ModelAndView displayCustomerSupport(Authentication auth) {
        ModelAndView view = new ModelAndView("Users/customerSupport");
        String emailSearch = "'" + auth.getName() + "'";
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
        List<Enquiry> enquiries = (List<Enquiry>) em.createQuery("SELECT e FROM Enquiry e").getResultList();

        view.addObject("enquiries", enquiries);
        return view;
    }

    @PostMapping("/customerSupport/submit")
    public ModelAndView storeCustomerSupport(Authentication auth,
                                             @RequestParam(name="ticketTitle", defaultValue = "")String ticketTitle,
                                             @RequestParam(name="ticketEnquiry", defaultValue = "")String ticketEnquiry,
                                             @RequestParam(name="bookingNumber", defaultValue = "") String bookingNumber,
                                             @RequestParam(name="email", defaultValue = "")String email) {
        ModelAndView view = new ModelAndView("Users/customerSupport");
        String emailSearch = "'" + auth.getName() + "'";
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
        List<Enquiry> enquiries = (List<Enquiry>) em.createQuery("SELECT e FROM Enquiry e").getResultList();
        List<Booking> booking = (List<Booking>) em.createQuery("SELECT b FROM Booking b WHERE userID=" + user.getUserID()).getResultList();
        Enquiry newEnquiry = new Enquiry();
        newEnquiry.setDescription(ticketEnquiry);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        newEnquiry.setEnquiryDate(date);
        newEnquiry.setUserID(user.getUserID());
        newEnquiry.setTitle(ticketTitle);
        boolean bookingNumExists =false;
        if(booking.size()>0){
            for(int i=0;i<booking.size();i++){
                if(booking.get(i).getBookingID().equalsIgnoreCase(bookingNumber)){
                    bookingNumExists = true;
                }
            }
            if(bookingNumExists) {
                newEnquiry.setBookingID(String.valueOf(bookingNumber));
            }
        }
        enquiries.add(newEnquiry);
        em.getTransaction().begin();
        em.merge(newEnquiry);
        em.getTransaction().commit();
        view.addObject("enquiries", enquiries);
        return view;
    }

    @PostMapping("/customerSupport/update")
    public ModelAndView updateCustomerSupport(Authentication auth,
                                              @RequestParam(name="ticketEnquiryNew", defaultValue = "")String ticketEnquiryNew,
                                              @RequestParam(name="ticketID", defaultValue = "")String ticketID) {
        ModelAndView view = new ModelAndView("Users/customerSupport");
        List<Enquiry> enquiries = (List<Enquiry>) em.createQuery("SELECT e FROM Enquiry e").getResultList();
        for(int i=0;i<enquiries.size();i++) {
            if (enquiries.get(i).getEnquiryID().equalsIgnoreCase(ticketID)) {
                enquiries.get(i).setDescription(ticketEnquiryNew);
                em.getTransaction().begin();
                em.merge(enquiries.get(i));
                em.getTransaction().commit();
            }
        }
        view.addObject("enquiries", enquiries);
        return view;
    }


    //wishlist Get
    @GetMapping("/wishList")
    public ModelAndView displayWishList(Authentication auth) {
        ModelAndView view = new ModelAndView("Users/wishList");
        String emailSearch = "'" + auth.getName() + "'";
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
        List<WishListEntry> wishList = (List<WishListEntry>) em.createQuery("SELECT w FROM WishListEntry w WHERE w.userID=" + user.getUserID()).getResultList();
        List<Country> countries = (List<Country>) em.createQuery("SELECT c FROM Country c").getResultList();
        view.addObject("countries", countries);
        view.addObject("wishList", wishList);
        return view;
    }

    //wishlist Post
    @PostMapping("/wishList")
    public ModelAndView updateWishList(Authentication auth,
                                       @RequestParam(name="countryCode", defaultValue = "AUS") String countryCode) {
        ModelAndView view = new ModelAndView("Users/wishList");
        String emailSearch = "'" + auth.getName() + "'";
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
        List<WishListEntry> wishList = (List<WishListEntry>) em.createQuery("SELECT w FROM WishListEntry w WHERE w.userID=" + user.getUserID()).getResultList();
        List<Country> countries = (List<Country>) em.createQuery("SELECT c FROM Country c").getResultList();

        boolean alreadyExists = false;
        for(int i=0;i<wishList.size();i++){
            if(wishList.get(i).getCountryCode3().equalsIgnoreCase(countryCode)){
                alreadyExists=true;
            }
        }
        if(!alreadyExists){
            WishListEntry newWishlist = new WishListEntry();
            newWishlist.setCountryCode3(countryCode);
            newWishlist.setUserID(Integer.valueOf(user.getUserID()));
            for(int i=0;i<countries.size();i++) {
                if(countries.get(i).getCountryCode3().equalsIgnoreCase(countryCode)) {
                    newWishlist.setCountryName(countries.get(i).getCountryName());
                }
            }
            wishList.add(newWishlist);
            em.getTransaction().begin();
            em.merge(newWishlist);
            em.getTransaction().commit();
        }

        view.addObject("countries", countries);
        view.addObject("wishList", wishList);
        return view;
    }

    //wishlist Post
    @PostMapping("/wishList/remove")
    public ModelAndView removeFromWishList(Authentication auth,
                                           @RequestParam(name="country", defaultValue = "AUS") String countryCode) {
        ModelAndView view = new ModelAndView("Users/wishList");
        String emailSearch = "'" + auth.getName() + "'";
        //Retrieve the user's information
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
        List<WishListEntry> wishList = (List<WishListEntry>) em.createQuery("SELECT w FROM WishListEntry w WHERE w.userID=" + user.getUserID()).getResultList();
        List<Country> countries = (List<Country>) em.createQuery("SELECT c FROM Country c").getResultList();

        for(int i=0;i<wishList.size();i++){
            if(wishList.get(i).getCountryCode3().equalsIgnoreCase(countryCode)){
                em.getTransaction().begin();
                em.remove(wishList.get(i));
                em.getTransaction().commit();
                wishList.remove(i);
            }
        }

        view.addObject("countries", countries);
        view.addObject("wishList", wishList);
        return view;
    }


}
