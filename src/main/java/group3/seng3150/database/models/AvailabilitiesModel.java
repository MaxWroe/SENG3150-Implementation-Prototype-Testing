//used to hold available flights


package group3.seng3150.database.models;

public class AvailabilitiesModel implements java.io.Serializable {

    //database fields
    private String airlineCode;
    private String flightNumber;
    private String departureTime;
    private String classCode;
    private String ticketCode;
    private int numberAvailableSeatsLeg1;
    private int numberAvailableSeatsLeg2;


    //constructor
//    public AvailabilitiesModel(String airlineCode, String flightNumber, String departureTime, String classCode,
//                               String ticketCode, int numberAvailableSeatsLeg1, int numberAvailableSeatsLeg2) {
//        AirlineCode = airlineCode;
//        FlightNumber = flightNumber;
//        DepartureTime = departureTime;
//        ClassCode = classCode;
//        TicketCode = ticketCode;
//        NumberAvailableSeatsLeg1 = numberAvailableSeatsLeg1;
//        NumberAvailableSeatsLeg2 = numberAvailableSeatsLeg2;
//    }

    public AvailabilitiesModel() {
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public int getNumberAvailableSeatsLeg1() {
        return numberAvailableSeatsLeg1;
    }

    public void setNumberAvailableSeatsLeg1(int numberAvailableSeatsLeg1) {
        this.numberAvailableSeatsLeg1 = numberAvailableSeatsLeg1;
    }

    public int getNumberAvailableSeatsLeg2() {
        return numberAvailableSeatsLeg2;
    }

    public void setNumberAvailableSeatsLeg2(int numberAvailableSeatsLeg2) {
        this.numberAvailableSeatsLeg2 = numberAvailableSeatsLeg2;
    }
}
