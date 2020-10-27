/**
 * This class provides methods used in the interaction between the program and the flightpub database
 */

package group3.seng3150.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public abstract class AbstractEntityDAO<EntityType, KeyType> implements IEntityDAO<EntityType, KeyType> {

    protected final Class<EntityType> cls;
    protected final EntityManager em;

    public AbstractEntityDAO(Class<EntityType> cls, EntityManager em){
        this.cls = cls;
        this.em = em;
    }

    //Handles the creation of new Entitys in the database
    @Override
    public void create(EntityType entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    //Returns all instances of a particular EntityType in the database
    @Override
    public List<EntityType> findAll(int page, int length) {
        return em.createQuery("SELECT x FROM " + cls.getName() + " x", cls)
                .setFirstResult(page * length)
                .setMaxResults(length)
                .getResultList();
    }

    //Finds all instances of a Entity matching a particular primary key
    @Override
    public EntityType findById(KeyType id) {
        return em.find(cls, id);
    }

    //Returns all instances of a Entity matching the specified search parameters
    @Override
    public List<EntityType> queryAll(String query, Map<String, Object> params, int page, int length) {
        StringBuilder jqlQuery = new StringBuilder()
                .append("SELECT x FROM ")
                .append(cls.getName())
                .append(" x WHERE ")
                .append(query);

        TypedQuery<EntityType> emQuery = em.createQuery(jqlQuery.toString(), cls);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            emQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return emQuery.setFirstResult(page * length)
                .setMaxResults(length)
                .getResultList();
    }

    //Returns one instance of a Entity matching the specified search parameters
    @Override
    public EntityType queryOne(String query, Map<String, Object> params) {
        StringBuilder jqlQuery = new StringBuilder()
                .append("SELECT x FROM ")
                .append(cls.getName())
                .append(" x WHERE ")
                .append(query);

        TypedQuery<EntityType> emQuery = em.createQuery(jqlQuery.toString(), cls);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            emQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return emQuery.getSingleResult();
    }

    public EntityType queryOne(String query, String string, Object mappedObject) {
        StringBuilder jqlQuery = new StringBuilder()
                .append("SELECT x FROM ")
                .append(cls.getName())
                .append(" x WHERE ")
                .append(query);

        TypedQuery<EntityType> emQuery = em.createQuery(jqlQuery.toString(), cls).setParameter(string, mappedObject);
        return emQuery.getSingleResult();
    }

    //Overwrites a existing entity in the database with the properties of a newly inserted entity
    @Override
    public EntityType update(EntityType entity) {
        em.getTransaction().begin();
        entity = em.merge(entity);
        em.getTransaction().commit();
        return entity;
}

    //Handles the deletion of a record in the database matching the properties of the specified Entity
    @Override
    public void delete(EntityType entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    //Deletes a record in the database matching a specific primary key.
    @Override
    public void deleteById(KeyType key) {
        delete(findById(key));
    }
}
