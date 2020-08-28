package group3.seng3150;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import group3.seng3150.entities.UserAccount;
import javax.persistence.EntityManager;
import java.util.Collection;

public class UserServices implements UserDetailsService {

    private EntityManager em;
    @Autowired
    public UserServices(EntityManager em){this.em =em;}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserAccount user = findUserbyUsername(email);

        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(email);
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.roles(user.getROLEDID());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }

    private UserAccount findUserbyUsername(String email) throws UsernameNotFoundException{
        String newEmail = "'" + email + "'";
        UserAccount userAcc = null;
        try{
            userAcc = (UserAccount) em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + newEmail).getSingleResult();
            if(userAcc==null){
                throw new UsernameNotFoundException("Email address not found");
            }
        }catch (Exception e) {
            throw new UsernameNotFoundException("Database error ");
        }
        return userAcc;
    }
}