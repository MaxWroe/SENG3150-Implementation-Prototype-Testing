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


        LinkedList<Booking> bookingsDeparture = new LinkedList<>();
        LinkedList<Booking> bookingsReturn = new LinkedList<>();


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
                    newBooking.setTicketCode(String.valueOf(departure.getFlightITicketCode(j)));
                    newBooking.setClassCode(returnClassBooking);
                    newBooking.setOverallPrice((double) departure.getPrice());
                    newBooking.setPrice(departure.getFlightIPrice(j));
                } else if (j == 1) {
                    newBooking.setAirlineCode2(departure.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime2(departure.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber2(departure.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination2(departure.getFlights().get(j).getDestination());
                    newBooking.setDeparture2(departure.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime2(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode2(String.valueOf(departure.getFlightITicketCode(j)));
                    newBooking.setClassCode2(returnClassBooking);
                    newBooking.setPrice(departure.getFlightIPrice(j));
                } else if (j == 2) {
                    newBooking.setAirlineCode3(departure.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime3(departure.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber3(departure.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination3(departure.getFlights().get(j).getDestination());
                    newBooking.setDeparture3(departure.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime3(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode3(String.valueOf(departure.getFlightITicketCode(j)));
                    newBooking.setClassCode3(returnClassBooking);
                    newBooking.setPrice(departure.getFlightIPrice(j));
                } else if (j == 3) {
                    newBooking.setAirlineCode4(departure.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime4(departure.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber4(departure.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination4(departure.getFlights().get(j).getDestination());
                    newBooking.setDeparture4(departure.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime4(departure.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode4(String.valueOf(departure.getFlightITicketCode(j)));
                    newBooking.setClassCode4(returnClassBooking);
                    newBooking.setPrice(departure.getFlightIPrice(j));
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
                    newBooking.setTicketCode(String.valueOf(returns.getFlightITicketCode(j)));
                    newBooking.setClassCode(returnClassBooking);
                    newBooking.setOverallPrice((double) returns.getPrice());
                    newBooking.setPrice(returns.getFlightIPrice(j));
                } else if (j == 1) {
                    newBooking.setAirlineCode2(returns.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime2(returns.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber2(returns.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination2(returns.getFlights().get(j).getDestination());
                    newBooking.setDeparture2(returns.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime2(returns.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode2(String.valueOf(returns.getFlightITicketCode(j)));
                    newBooking.setClassCode2(returnClassBooking);
                    newBooking.setPrice(returns.getFlightIPrice(j));
                } else if (j == 2) {
                    newBooking.setAirlineCode3(returns.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime3(returns.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber3(returns.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination3(returns.getFlights().get(j).getDestination());
                    newBooking.setDeparture3(returns.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime3(returns.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode3(String.valueOf(returns.getFlightITicketCode(j)));
                    newBooking.setClassCode3(returnClassBooking);
                    newBooking.setPrice(returns.getFlightIPrice(j));
                } else if (j == 3) {
                    newBooking.setAirlineCode4(returns.getFlights().get(j).getAirlineCode());
                    newBooking.setDepartureTime4(returns.getFlights().get(j).getDepartureDate());
                    newBooking.setFlightNumber4(returns.getFlights().get(j).getFlightNumber());
                    newBooking.setDestination4(returns.getFlights().get(j).getDestination());
                    newBooking.setDeparture4(returns.getFlights().get(j).getDepartureCode());
                    newBooking.setArrivalTime4(returns.getFlights().get(j).getArrivalDate());
                    newBooking.setTicketCode4(String.valueOf(returns.getFlightITicketCode(j)));
                    newBooking.setClassCode4(returnClassBooking);
                    newBooking.setPrice(returns.getFlightIPrice(j));
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
                                String adultsBookingS,
                                String childrenBooking,
                                String trip){

        //***********************************************************************************************************
        //int positionDeparture = Integer.parseInt(positionDepartureS);
        //int positionReturn = Integer.parseInt(positionReturnS);
        LinkedList<Booking> bookingsDeparture = (LinkedList<Booking>) session.getAttribute("departureBookings");
        LinkedList<Booking> bookingsReturn = (LinkedList<Booking>) session.getAttribute("returnBookings");

        Boolean returnTrip = false;
        if(trip.equals("return")){
            returnTrip=true;
        }
        List<FlightPlan> searchResults = (List<FlightPlan>) session.getAttribute("departureFlights");
        int adultsBooking = Integer.parseInt(adultsBookingS);
        int childrenBookingS = Integer.parseInt(childrenBooking);
        String tempName = new String();
        String tempEmail = "'" + auth.getName() +"'";
        UserAccount user = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + tempEmail).getSingleResult();
        int currentPosition = 1;


        for(int j =0; j<2; j++) {
            if(j==0) {
                for (int i = 1; i < adultsBooking + childrenBookingS+1; i++) {
                    bookingsDeparture.get(i-1).setUserID(user.getUserID());
                    if (i <= adultsBooking) {

                        if (i == 1) {
                            bookingsDeparture.get(i-1).setFirstName(user.getFirstName());
                            bookingsDeparture.get(i-1).setLastName(user.getLastName());
                            bookingsDeparture.get(i-1).setDateOfBirth(user.getDateOfBirth());
                        } else {
                            tempName = "adultFirstName" + Integer.toString(i);
                            bookingsDeparture.get(i-1).setFirstName(request.getParameter(tempName));
                            tempName = "adultLastName" + Integer.toString(i);
                            bookingsDeparture.get(i-1).setLastName(request.getParameter(tempName));
                            tempName = "adultDOB" + Integer.toString(i);
                            //NEED JARRAD TO CHANGE THIS TO SESSION RATHER THAN REQUEST*********************************************
                            String temp = request.getParameter(tempName);
                            Date tempDate1 = Date.valueOf(temp);
                            bookingsDeparture.get(i-1).setDateOfBirth(tempDate1);
                            currentPosition = i;
                        }
                    } else {
                        tempName = "childFirstName" + Integer.toString(i - adultsBooking);
                        bookingsDeparture.get(i-1).setFirstName(request.getParameter(tempName));
                        tempName = "childLastName" + Integer.toString(i - adultsBooking);
                        bookingsDeparture.get(i-1).setLastName(request.getParameter(tempName));
                        tempName = "childDOB" + Integer.toString(i - adultsBooking);
                        String temp = request.getParameter(tempName);
                        Date tempDate1 = Date.valueOf(temp);
                        bookingsDeparture.get(i-1).setDateOfBirth(tempDate1);
                        currentPosition = i;
                        //departureBooking.add(newBooking);
                    }
                }
            }else if(returnTrip && j==1){
                for (int i = 1; i < adultsBooking + childrenBookingS+1; i++) {
                    bookingsReturn.get(i-1).setUserID(user.getUserID());
                    if (i <= adultsBooking) {

                        if (i == 1) {
                            bookingsReturn.get(i-1).setFirstName(user.getFirstName());
                            bookingsReturn.get(i-1).setLastName(user.getLastName());
                            bookingsReturn.get(i-1).setDateOfBirth(user.getDateOfBirth());
                        } else {
                            tempName = "adultFirstName" + Integer.toString(i);
                            bookingsReturn.get(i-1).setFirstName(request.getParameter(tempName));
                            tempName = "adultLastName" + Integer.toString(i);
                            bookingsReturn.get(i-1).setLastName(request.getParameter(tempName));
                            tempName = "adultDOB" + Integer.toString(i);
                            //NEED JARRAD TO CHANGE THIS TO SESSION RATHER THAN REQUEST*********************************************
                            String temp = request.getParameter(tempName);
                            Date tempDate1 = Date.valueOf(temp);
                            bookingsReturn.get(i-1).setDateOfBirth(tempDate1);
                            currentPosition = i;
                        }
                    } else {
                        tempName = "childFirstName" + Integer.toString(i - adultsBooking);
                        bookingsReturn.get(i-1).setFirstName(request.getParameter(tempName));
                        tempName = "childLastName" + Integer.toString(i - adultsBooking);
                        bookingsReturn.get(i-1).setLastName(request.getParameter(tempName));
                        tempName = "childDOB" + Integer.toString(i - adultsBooking);
                        String temp = request.getParameter(tempName);
                        Date tempDate1 = Date.valueOf(temp);
                        bookingsReturn.get(i-1).setDateOfBirth(tempDate1);
                        currentPosition = i;
                        //departureBooking.add(newBooking);
                    }

                }
            }

        }

        for(int i =0; i<bookingsDeparture.size();i++) {
            em.getTransaction().begin();
            em.merge(bookingsDeparture.get(i));
            em.getTransaction().commit();
        }
        if(returnTrip) {
            for (int i = 0; i < bookingsReturn.size(); i++) {
                em.getTransaction().begin();
                em.merge(bookingsReturn.get(i));
                em.getTransaction().commit();
            }
        }


    }


}
