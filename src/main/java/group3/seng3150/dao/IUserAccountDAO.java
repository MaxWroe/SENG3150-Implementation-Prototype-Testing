package group3.seng3150.dao;
import group3.seng3150.entities.UserAccount;


public interface IUserAccountDAO extends IEntityDAO<UserAccount, String> {
    UserAccount getAccountFromEmail(String email);
}