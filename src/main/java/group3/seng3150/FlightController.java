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
@RequestMapping("search")
public class FlightController{
//    private FlightsHolder flights;
    private EntityManager em;

    @Autowired
    public FlightController(EntityManager em){this.em =em;}

    @GetMapping
    @RequestMapping("search")
    public ModelAndView search(
            @RequestParam(name="departureLocation", defaultValue="") String departureLocation,
            @RequestParam(name="arrivalLocation", defaultValue="") String arrivalLocation,
            @RequestParam(name="departureDate", defaultValue="") String departureDate,
            @RequestParam(name="arrivalDate", defaultValue="") String arrivalDate,
            @RequestParam(name="classType", defaultValue="") String classType)
    {
        ModelAndView view = new ModelAndView("search");
        List<Flight> flights = em.createQuery("SELECT f FROM Flight f WHERE f.departureLocation=" + departureLocation +
                        " AND SELECT f FROM Flight f WHERE f.arrivalLocation=" + arrivalLocation +
                        " AND SELECT f FROM Flight f WHERE f.departureDate=" + departureDate +
                        " AND SELECT f FROM Flight f WHERE f.arrivalDate=" + arrivalDate +
                        " AND SELECT f FROM Flight f WHERE f.classCode=" + classType, Flight.class).getResultList();
        view.addObject("flights", flights);
        return view;
    }



}
