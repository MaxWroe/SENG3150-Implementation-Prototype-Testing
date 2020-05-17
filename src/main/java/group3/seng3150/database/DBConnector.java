//Class just for making a dtabase connection

package group3.seng3150.database;
import javax.naming.NamingException;
import java.sql.*;

public class DBConnector {

        public static Connection getConnection() throws  ClassNotFoundException, SQLException {

                Class.forName("com.mysql.cj.jdbc.Driver");
                String connectionUrl = "jdbc:mysql://localhost:3306/FlightPub?useUnicode=true&characterEncoding=UTF-8&user=root&password=root";
                Connection connection = DriverManager.getConnection(connectionUrl);
                return connection;

        }



}