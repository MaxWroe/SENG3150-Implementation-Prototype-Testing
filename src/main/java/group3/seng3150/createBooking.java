package group3.seng3150;
import group3.seng3150.entities.Booking;
import group3.seng3150.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;


public class createBooking {

    private EntityManager em;
    @Autowired
    public createBooking(EntityManager em){this.em =em;}

    public void startBooking(String trip,
                        HttpSession session,
                        String position,
                        String adultsBookingS,
                        String childrenBooking,
                        String positionReturnS,
                        String returnClassBooking) {


        LinkedList<Booking> bookingsDeparture = new LinkedList<Booking>();
        LinkedList<Booking> bookingsReturn = new LinkedList<Booking>();


        Boolean returnTrip = false;
        if (trip.equals("return")) {
            returnTrip = true;
        }

        int adultsBooking = Integer.parseInt(adultsBookingS);
        int childrenBookingS = Integer.parseInt(childrenBooking);
        int positionReturn = Integer.parseInt(positionReturnS);
        int positionDeparture = Integer.parseInt(position);
        List<FlightPlan> searchResults = (List<FlightPlan>) session.getAttribute("departureFlights");
        FlightPlan departure = searchResults.get(positionDeparture - 1);
        LinkedList<Booking> departureBooking = new LinkedList<Booking>();
        int currentPosition = 1;


        for (int i = 0; i < adultsBooking + childrenBookingS; i++) {
            Booking newBooking = new Booking();
            newBooking.setGroupSize(adultsBooking + childrenBookingS);
            if (returnTrip) {
                newBooking.setReturnTrip(1);
            } else {
                newBooking.setReturnTrip(0);
            }
            //Works based on a flightPlan having no more than 4 flights, as per assumptions for bookings
            for (int j = 0; j < departure.getFlights().size(); j++) {
                if (j == 0) {
                    //set destination, departure, arrivalTime
                    newBooking.setAirlineCode(departure.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime(departure.getFlights().get(j).getDepartureDate());
                    newBooking.setBookingDate(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setFlightNumber(departure.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination(departure.getFlights().get(j).getDestination());
                    newBooking.setDeparture(departure.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode("D");
                    newBooking.setClassCode(returnClassBooking);
                } else if (j == 1) {
                    newBooking.setAirlineCode2(departure.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime2(departure.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber2(departure.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination2(departure.getFlights().get(j).getDestination());
                    newBooking.setDeparture2(departure.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime2(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode2("D");
                    newBooking.setClassCode2(returnClassBooking);
                } else if (j == 2) {
                    newBooking.setAirlineCode3(departure.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime3(departure.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber3(departure.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination3(departure.getFlights().get(j).getDestination());
                    newBooking.setDeparture3(departure.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime3(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode3("D");
                    newBooking.setClassCode3(returnClassBooking);
                } else if (j == 3) {
                    newBooking.setAirlineCode4(departure.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime4(departure.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber4(departure.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination4(departure.getFlights().get(j).getDestination());
                    newBooking.setDeparture4(departure.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime4(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode4("D");
                    newBooking.setClassCode4(returnClassBooking);
                }
            }
            bookingsDeparture.add(newBooking);
        }

    if(returnTrip){

        List<FlightPlan> searchReturn = (List<FlightPlan>) session.getAttribute("returnFlights");
        FlightPlan returns = searchResults.get(positionReturn-1);

        for (int i = 0; i < adultsBooking + childrenBookingS; i++) {
            Booking newBooking = new Booking();
            newBooking.setGroupSize(adultsBooking + childrenBookingS);
            newBooking.setReturnTrip(1);
            //Works based on a flightPlan having no more than 4 flights, as per assumptions for bookings
            for (int j = 0; j < returns.getFlights().size(); j++) {
                if (j == 0) {
                    newBooking.setAirlineCode(returns.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime(returns.getFlights().get(j).getDepartureDate());
                    newBooking.setBookingDate(returns.getFlights().get(j).getArrivalDate());
                    newBooking.setFlightNumber(returns.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination(returns.getFlights().get(j).getDestination());
                    newBooking.setDeparture(returns.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime(returns.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode("D");
                    newBooking.setClassCode(returnClassBooking);
                } else if (j == 1) {
                    newBooking.setAirlineCode2(returns.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime2(returns.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber2(returns.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination2(returns.getFlights().get(j).getDestination());
                    newBooking.setDeparture2(returns.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime2(returns.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode2("D");
                    newBooking.setClassCode2(returnClassBooking);
                } else if (j == 2) {
                    newBooking.setAirlineCode3(returns.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime3(returns.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber3(returns.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination3(returns.getFlights().get(j).getDestination());
                    newBooking.setDeparture3(returns.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime3(returns.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode3("D");
                    newBooking.setClassCode3(returnClassBooking);
                } else if (j == 3) {
                    newBooking.setAirlineCode4(returns.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime4(returns.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber4(returns.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination4(returns.getFlights().get(j).getDestination());
                    newBooking.setDeparture4(returns.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime4(returns.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode4("D");
                    newBooking.setClassCode4(returnClassBooking);
                }
            }
            bookingsReturn.add(newBooking);
        }
    }
        //departureBookings is a List<Booking> that holds the bookings created with the selected flight plan detail put in, but without the user data yet
        session.setAttribute("departureBookings", bookingsDeparture);
        //returnBookings is a List<Booking> that holds the bookings created with the selected flight plan detail put in, but without the user data yet if the search had a return booking included
        session.setAttribute("returnBookings", bookingsReturn);
        session.setAttribute("returnTrip", trip);
    }

    public void makeBooking (HttpSession session,
                                HttpServletRequest request,
                                Authentication auth,
                                int position,
                                String adultsBookingS,
                                String childrenBooking,
                                String positionReturnS,
                                String trip,
                                String returnClassBooking){

        //***********************************************************************************************************
        //int positionDeparture = Integer.parseInt(positionDepartureS);
        int positionReturn = Integer.parseInt(positionReturnS);
        LinkedList<Booking> bookingsDeparture = new LinkedList<Booking>();
        LinkedList<Booking> bookingsReturn = new LinkedList<Booking>();

        Boolean returnTrip = false;
        if(trip.equals("return")){
            returnTrip=true;
        }
        List<FlightPlan> searchResults = (List<FlightPlan>) session.getAttribute("departureFlights");
        FlightPlan departure = searchResults.get(position-1);
        LinkedList<Booking> departureBooking = new LinkedList<Booking>();
        int adultsBooking = Integer.parseInt(adultsBookingS);
        int childrenBookingS = Integer.parseInt(childrenBooking);
        String tempName = new String();
        String tempEmail = "'" + auth.getName() +"'";
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + tempEmail).getSingleResult();
        int currentPosition = 1;

        for (int i = 0; i < adultsBooking + childrenBookingS; i++) {
            Booking newBooking = new Booking();
            newBooking.setGroupSize(adultsBooking + childrenBookingS);
            if(returnTrip){
                newBooking.setReturnTrip(1);
            }else{
                newBooking.setReturnTrip(0);
            }

//            if(i<adultsBooking){
//                newBooking.setUserID(auth.getName());
//                if(i==0){
//                    newBooking.setFirstName(user.getFirstName());
//                    newBooking.setLastName(user.getLastName());
//                    newBooking.setDateOfBirth(user.getDateOfBirth());
//                }else{
//                    tempName = "adultFirstName" + Integer.toString(i);
//                    newBooking.setFirstName(request.getParameter(tempName));
//                    tempName = "adultLastName" + Integer.toString(i);
//                    newBooking.setLastName(request.getParameter(tempName));
//                    tempName = "adultDOB" + Integer.toString(i);
//                    //NEED JARRAD TO CHANGE THIS TO SESSION RATHER THAN REQUEST*********************************************
//                    String temp = request.getParameter(tempName);
//                    Date tempDate1 = Date.valueOf(temp);
//                    newBooking.setDateOfBirth(tempDate1);
//                    currentPosition = i;
//                }
//            }else{
//                tempName = "childFirstName" + Integer.toString(i-adultsBooking);
//                newBooking.setFirstName(request.getParameter(tempName));
//                tempName = "childLastName" + Integer.toString(i-adultsBooking);
//                newBooking.setLastName(request.getParameter(tempName));
//                tempName = "childDOB" + Integer.toString(i-adultsBooking);
//                String temp = request.getParameter(tempName);
//                Date tempDate1 = Date.valueOf(temp);
//                newBooking.setDateOfBirth(tempDate1);
//                currentPosition = i;
//                //departureBooking.add(newBooking);
//            }

            //Works based on a flightPlan having no more than 4 flights, as per assumptions for bookings
            for (int j = 0; j < departure.getFlights().size(); j++) {
                if (j == 0) {
                    //set destination, departure, arrivalTime
                    newBooking.setAirlineCode(departure.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime(departure.getFlights().get(j).getDepartureDate());
                    newBooking.setBookingDate(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setFlightNumber(departure.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination(departure.getFlights().get(j).getDestination());
                    newBooking.setDeparture(departure.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode("D");
                    newBooking.setClassCode(returnClassBooking);
                } else if (j == 1) {
                    newBooking.setAirlineCode2(departure.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime2(departure.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber2(departure.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination2(departure.getFlights().get(j).getDestination());
                    newBooking.setDeparture2(departure.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime2(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode2("D");
                    newBooking.setClassCode2(returnClassBooking);
                } else if (j == 2) {
                    newBooking.setAirlineCode3(departure.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime3(departure.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber3(departure.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination3(departure.getFlights().get(j).getDestination());
                    newBooking.setDeparture3(departure.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime3(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode3("D");
                    newBooking.setClassCode3(returnClassBooking);
                } else if (j == 3) {
                    newBooking.setAirlineCode4(departure.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime4(departure.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber4(departure.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination4(departure.getFlights().get(j).getDestination());
                    newBooking.setDeparture4(departure.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime4(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode4("D");
                    newBooking.setClassCode4(returnClassBooking);
                }
            }
            bookingsDeparture.add(newBooking);
        }

        if (returnTrip) {

            List<FlightPlan> searchReturn = (List<FlightPlan>) session.getAttribute("returnFlights");
            FlightPlan returns = searchResults.get(positionReturn-1);
            //returns.setFlightPlanPositions();
            //FlightPlan flightPlanR = searchReturn.getFlightPlansReturning().get(positionReturn);
            returns.getDepartureDate();

            for (int i = 0; i < adultsBooking + childrenBookingS; i++) {
                Booking newBooking = new Booking();
                newBooking.setGroupSize(adultsBooking + childrenBookingS);
                newBooking.setReturnTrip(1);
                //Works based on a flightPlan having no more than 4 flights, as per assumptions for bookings
                for (int j = 0; j < returns.getFlights().size(); j++) {
                    if (j == 0) {
                        newBooking.setAirlineCode(returns.getFlights().get(j).getAirlineCode());
                        newBooking.setDepartureTime(returns.getFlights().get(j).getDepartureDate());
                        newBooking.setBookingDate(returns.getFlights().get(j).getArrivalDate());
                        newBooking.setFlightNumber(returns.getFlights().get(j).getFlightNumber());
                        newBooking.setDestination(returns.getFlights().get(j).getDestination());
                        newBooking.setDeparture(returns.getFlights().get(j).getDepartureCode());
                        newBooking.setArrivalTime(returns.getFlights().get(j).getArrivalDate());
                        newBooking.setTicketCode("D");
                        newBooking.setClassCode(returnClassBooking);
                    } else if (j == 1) {
                        newBooking.setAirlineCode2(returns.getFlights().get(j).getAirlineCode());
                        newBooking.setDepartureTime2(returns.getFlights().get(j).getDepartureDate());
                        newBooking.setFlightNumber2(returns.getFlights().get(j).getFlightNumber());
                        newBooking.setDestination2(returns.getFlights().get(j).getDestination());
                        newBooking.setDeparture2(returns.getFlights().get(j).getDepartureCode());
                        newBooking.setArrivalTime2(returns.getFlights().get(j).getArrivalDate());
                        newBooking.setTicketCode2("D");
                        newBooking.setClassCode2(returnClassBooking);
                    } else if (j == 2) {
                        newBooking.setAirlineCode3(returns.getFlights().get(j).getAirlineCode());
                        newBooking.setDepartureTime3(returns.getFlights().get(j).getDepartureDate());
                        newBooking.setFlightNumber3(returns.getFlights().get(j).getFlightNumber());
                        newBooking.setDestination3(returns.getFlights().get(j).getDestination());
                        newBooking.setDeparture3(returns.getFlights().get(j).getDepartureCode());
                        newBooking.setArrivalTime3(returns.getFlights().get(j).getArrivalDate());
                        newBooking.setTicketCode3("D");
                        newBooking.setClassCode3(returnClassBooking);
                    } else if (j == 3) {
                        newBooking.setAirlineCode4(returns.getFlights().get(j).getAirlineCode());
                        newBooking.setDepartureTime4(returns.getFlights().get(j).getDepartureDate());
                        newBooking.setFlightNumber4(returns.getFlights().get(j).getFlightNumber());
                        newBooking.setDestination4(returns.getFlights().get(j).getDestination());
                        newBooking.setDeparture4(returns.getFlights().get(j).getDepartureCode());
                        newBooking.setArrivalTime4(returns.getFlights().get(j).getArrivalDate());
                        newBooking.setTicketCode4("D");
                        newBooking.setClassCode4(returnClassBooking);
                    }
                }
                bookingsReturn.add(newBooking);
            }
        }
        //*****************SUBJECT TO CHANGE**********************************
        //view.addObject("departureFlight", flightPlan.getFlights().get(0));
        //*****************SUBJECT TO CHANGE**********************************


        //departureBookings is a List<Booking> that holds the bookings created with the selected flight plan detail put in, but without the user data yet
        //session.setAttribute("departureBookings", bookingsDeparture);
        //returnBookings is a List<Booking> that holds the bookings created with the selected flight plan detail put in, but without the user data yet if the search had a return booking included
        //session.setAttribute("returnBookings", bookingsReturn);
        //This is a boolean that indicates whether there is a return trip or not. True for yes, false for no.
        /*
        session.setAttribute("returnTrip", returnTrip);
        view.addObject("trip", trip);
        view.addObject("departureBookings", bookingsDeparture);
        view.addObject("returnBookings", bookingsReturn);
        view.addObject("returnTrip", returnTrip);
        view.addObject("departurePrice", flightPlan.getPrice());
        view.addObject("returnPrice", flightPlanR.getPrice());
        view.addObject("departureClass", returnClassBooking);
        view.addObject("returnClass", returnClassBooking);
        view.addObject("adultsBooked", returnAdultsBooking);
        view.addObject("childrenBooked", returnChildrenBooking);
        //*****************SUBJECT TO CHANGE**********************************
        view.addObject("returnFlight", flightPlanR.getFlights().get(0));
        //*****************SUBJECT TO CHANGE**********************************
        */


        /* List<FlightPlan> searchResults = (List<FlightPlan>) session.getAttribute("departureFlights");
        FlightPlan departure = searchResults.get(position);
        LinkedList<Booking> departureBooking = new LinkedList<Booking>();
        int adultsBooking = Integer.parseInt(adultsBookingS);
        int childrenBookingS = Integer.parseInt(childrenBooking);
        String tempName = new String();
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + auth.getName()).getSingleResult();
        int currentPosition = 1;

        for(int i=1; i<=adultsBooking;i++) {
            Booking tempBooking = new Booking();
            tempBooking.setUserID(auth.getName());
            if(i==1){
                tempBooking.setFirstName(user.getFirstName());
                tempBooking.setLastName(user.getLastName());
                tempBooking.setDateOfBirth(user.getDateOfBirth());
            }else{
                tempName = "adultFirstName" + Integer.toString(i);
                tempBooking.setFirstName(request.getParameter(tempName));
                tempName = "adultLastName" + Integer.toString(i);
                tempBooking.setLastName(request.getParameter(tempName));
                tempName = "adultDOB" + Integer.toString(i);
                String temp = request.getParameter(tempName);
                Date tempDate1 = Date.valueOf(temp);
                tempBooking.setDateOfBirth(tempDate1);
                currentPosition = i;
            }
            departureBooking.add(tempBooking);
        }

        for(int j=1; j<=childrenBookingS;j++) {
            Booking tempBooking = new Booking();

            tempName = "childFirstName" + Integer.toString(j);
            tempBooking.setFirstName(request.getParameter(tempName));
            tempName = "childLastName" + Integer.toString(j);
            tempBooking.setLastName(request.getParameter(tempName));
            tempName = "childDOB" + Integer.toString(j);
            String temp = request.getParameter(tempName);
            Date tempDate1 = Date.valueOf(temp);
            tempBooking.setDateOfBirth(tempDate1);
            currentPosition = j;
            departureBooking.add(tempBooking);
        }
        for(int i =0; i<departureBooking.size();i++) {
            em.getTransaction().begin();
            em.merge(departureBooking.get(i));
            em.getTransaction().commit();
        }
*
*
* */
        //Booking tempBooking = new Booking();

    }

}
