//Class: Airport
//Author: Angus Simmons
// Description: This is the entity creator that manages the entities and how they function within the hibernate framework


package group3.seng3150.entities;
//
//import org.hibernate.cfg.Environment;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class EntityManagerCreator {
//
//    private EntityManagerFactory emf;
//
//    public EntityManagerCreator() throws URISyntaxException {
//        Map<String, String> props = new HashMap<>();
//
//        if (System.getenv().containsKey("CLEARDB_DATABASE_URL")) {
//            URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
//            String username = dbUri.getUserInfo().split(":")[0];
//            String password = dbUri.getUserInfo().split(":")[1];
//            String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
//
//            props.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
//            props.put("javax.persistence.jdbc.url", dbUrl);
//            props.put("javax.persistence.jdbc.user", username);
//            props.put("javax.persistence.jdbc.password", password);
//
//            // Optional Hibernate connection pool
//            props.put(Environment.C3P0_MIN_SIZE, "1");
//            props.put(Environment.C3P0_MAX_SIZE, "4");
//            props.put(Environment.C3P0_TIMEOUT, "75");
//            props.put(Environment.C3P0_MAX_STATEMENTS, "50");
//            props.put(Environment.C3P0_IDLE_TEST_PERIOD, "30");
//            props.put(Environment.C3P0_ACQUIRE_INCREMENT, "1");
//
//            // Should use this
//            props.put("connection.autoReconnect", "true");
//            props.put("connection.autoReconnectForPools", "true");
//            props.put("connection.is-connection-validation-require", "true");
//
//        } else {
//            props.put("javax.persistence.jdbc.driver", "org.h2.Driver");
//            props.put("javax.persistence.jdbc.url", "jdbc:h2:mem:FlightPub;DB_CLOSE_DELAY=-1");
//        }
//
//        emf = Persistence.createEntityManagerFactory("group3-app", props);
//    }


//    public EntityManagerCreator(){
//        emf = Persistence.createEntityManagerFactory("group3-app");
//
//    }

//        <Resource name="jdbc/DBDataSource"
//    auth="Container"
//    type="javax.sql.DataSource"
//    maxTotal="10"
//    maxIdle="5"
//    maxWaitMillis="10000"
//    username="root"
//    password="root"
//    driverClassName="org.mariadb.jdbc.Driver"
//    url="jdbc:mysql://localhost:3306/FlightPub"/>


//    public EntityManager create(){
//
//        return emf.createEntityManager();
//
//    }
//
//}


import org.hibernate.cfg.Environment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.net.URI;

public class EntityManagerCreator {
    private EntityManagerFactory emf;

    public EntityManagerCreator() throws URISyntaxException {
        Map<String, String> props = new HashMap<>();

       if (System.getenv().containsKey("CLEARDB_DATABASE_URL")) {
            URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
            System.out.println("DB CONNECTING -----------------------------------" + dbUrl);
            props.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
            props.put("javax.persistence.jdbc.url", dbUrl);
            props.put("javax.persistence.jdbc.user", username);
            props.put("javax.persistence.jdbc.password", password);
           System.out.println("PARAMS----------------------------------- u:" + username + " p:" + password);

            // Optional Hibernate connection pool
            props.put(Environment.C3P0_MIN_SIZE, "45");
            props.put(Environment.C3P0_MAX_SIZE, "60");
            props.put(Environment.C3P0_TIMEOUT, "10000");
            props.put(Environment.C3P0_MAX_STATEMENTS, "100");
            props.put(Environment.C3P0_IDLE_TEST_PERIOD, "60");
            props.put(Environment.C3P0_ACQUIRE_INCREMENT, "1");

            // Should use this
            props.put("connection.autoReconnect", "true");
            props.put("connection.autoReconnectForPools", "true");
            props.put("connection.is-connection-validation-require", "true");

        } else {
            System.out.println("DB CONNECTING ----------------------------------- using local");
            props.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
            props.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/FlightPub");
        }

        emf = Persistence.createEntityManagerFactory("group3-app", props);
    }

    public EntityManager create() {
        return emf.createEntityManager();
    }
}
