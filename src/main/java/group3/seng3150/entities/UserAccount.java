package group3.seng3150.entities;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "UserAccount")
public class UserAccount {

    @Id
    @Column(name = "UserID")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String UserID;

    @Column(name = "UserName")
    @Basic(optional = false)
    private String userName;

    @Column(name = "email")
    @Basic(optional = false)
    private String email;

    @Column(name = "phone")
    @Basic(optional = false)
    private int phone;

    @Column(name = "DateOfBirth")
    @Basic(optional = false)
    private Date dateOfBirth;

    //Security needs to be addressed on this
    @Column(name = "Password")
    @Basic(optional = false)
    private String password;

    //Constructors
    public UserAccount(String userName, String email, int phone, Date dateOfBirth, String password) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public UserAccount() {
    }


    //Getters and Setters
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Override Methods
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "UserID='" + UserID + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password + '\'' +
                '}';
    }
}
