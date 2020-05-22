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
