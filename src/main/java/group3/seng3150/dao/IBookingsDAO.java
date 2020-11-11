package group3.seng3150.dao;

import group3.seng3150.entities.Booking;
import group3.seng3150.entities.UserAccount;

public interface IBookingsDAO extends IEntityDAO<Booking, String> {
    UserAccount getAccountFromEmail(String email);
}