//Class: Airport
//Author: Angus Simmons
// Description: This is the entity creator that manages the entities and how they function within the hibernate framework


package group3.seng3150.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerCreator {

    private EntityManagerFactory emf;

    public EntityManagerCreator(){
        emf = Persistence.createEntityManagerFactory("group3-app");

    }


    public EntityManager create(){

        return emf.createEntityManager();

    }

}
