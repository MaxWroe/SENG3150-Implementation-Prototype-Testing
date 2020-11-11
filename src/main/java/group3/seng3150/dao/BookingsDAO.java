package group3.seng3150.dao;

import group3.seng3150.entities.Booking;
import group3.seng3150.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class BookingsDAO extends AbstractEntityDAO<Booking, String> implements IBookingsDAO {

    @Autowired
    public BookingsDAO(EntityManager em) {
        super(Booking.class, em);
    }

    public UserAccount getAccountFromEmail(String emailSearch) {
        return (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
    }
}