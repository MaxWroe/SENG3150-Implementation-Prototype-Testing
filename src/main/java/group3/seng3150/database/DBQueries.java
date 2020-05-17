package group3.seng3150.database;
import group3.seng3150.database.models.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBQueries {

    //returns a list of all availabilities
    public List<AvailabilitiesModel> getAvailabilities()throws SQLException {

        //Store as a list
        List<AvailabilitiesModel> availaibilityList = new LinkedList<>();


        //query for all availabilities
        String query = "SELECT * FROM Availability";

        try{
            Connection connection = DBConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                AvailabilitiesModel available = new AvailabilitiesModel();

                available.setAirlineCode(rs.getString("AirlineCode"));
                available.setFlightNumber(rs.getString("FlightNumber"));
                available.setDepartureTime(rs.getString("DepartureTime"));
                available.setClassCode(rs.getString("ClassCode"));
                available.setTicketCode(rs.getString("TicketCode"));
                available.setNumberAvailableSeatsLeg1(rs.getInt("NumberAvailableSeatsLeg1"));
                available.setNumberAvailableSeatsLeg1(rs.getInt("NumberAvailableSeatsLeg2"));

                availaibilityList.add(available);

            }



        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{}
        //ResultSet rs = conn.prepareStatement("SHOW SCHEMAS").executeQuery();

//
//        Statement s = conn.createStatement();
//
//        ResultSet rs = s.executeQuery(query);
//
////        while(rs.next()){
////            String out = rs.getString("FlightNumber");
////            System.out.println(out);
////        }
//        rs.next();
//        String data = rs.getString("countryName");
//        System.out.println(data);

        return  availaibilityList;
    }
}
