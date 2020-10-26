package group3.seng3150.dao;

import group3.seng3150.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class UserAccountDAO extends AbstractEntityDAO<UserAccount, String> implements IUserAccountDAO {

    @Autowired
    public UserAccountDAO(EntityManager em) {
        super(UserAccount.class, em);
    }

    public UserAccount getAccountFromEmail(String emailSearch)
    {
        return (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + emailSearch).getSingleResult();
    }
}