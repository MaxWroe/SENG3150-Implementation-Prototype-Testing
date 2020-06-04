package group3.seng3150;

import group3.seng3150.entities.Booking;
import group3.seng3150.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
public class BookingsController {

    private EntityManager em;
    @Autowired
    public BookingsController(EntityManager em){this.em =em;}

    //get method show bookings
    @GetMapping("/flightBooking")
    public ModelAndView showBooking(HttpSession session) {
        ModelAndView view = new ModelAndView("flightBooking");
        em.getTransaction().begin();

        Booking newBooking = new Booking();
        newBooking.setAirlineCode("");
        newBooking.setAirlineCode2("");
        newBooking.setAirlineCode3("");
        newBooking.setAirlineCode4("");
        newBooking.setAirlineCode4("");
        //newBooking.setBookingDate(testDate);
        newBooking.setClassCode("");
        newBooking.setClassCode2("");
        newBooking.setClassCode3("");
        newBooking.setClassCode4("");
        //newBooking.setDepartureTime(testDate);
        //newBooking.setDepartureTime2(testDate);
        //newBooking.setDepartureTime3(testDate);
        //newBooking.setDepartureTime4(testDate);
        em.persist(newBooking);
        em.getTransaction().commit();

        return view;
    }

    //get method manage bookings
    @GetMapping("/manageBooking")
    public ModelAndView manageBooking(HttpSession session) {
        ModelAndView view = new ModelAndView("manageBooking");
        return view;
    }

    @GetMapping("/bookingtemp")
    public ModelAndView displayBooking(HttpSession session) {
        ModelAndView view = new ModelAndView("flightBooking");
        return view;
    }

}
