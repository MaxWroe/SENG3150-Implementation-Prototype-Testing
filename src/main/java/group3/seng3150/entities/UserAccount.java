//Class: Airport
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is representing user accounts and all the data attached to a user

package group3.seng3150.entities;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "UserAccount")
public class UserAccount {

    //first and last name
    //email address to PK
    //gender, usertype, citizenship to be added
    @Id
    @Column(name = "UserID")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String UserID;

    @Column(name = "FirstName")
    @Basic(optional = false)
    private String firstName;

    @Column(name = "MiddleNames")
    private String middleNames;

    @Column(name = "LastName")
    @Basic(optional = false)
    private String lastName;

    @Column(name = "Email")
    @Basic(optional = false)
    private String email;

    @Column(name = "Phone")
    @Basic(optional = false)
    private Integer phone;

    @Column(name = "DateOfBirth")
    @Basic(optional = false)
    private Date dateOfBirth;

    //0 for male, 1 for female
    @Column(name = "gender")
    @Basic(optional = false)
    private int gender;

    @Column(name = "Citizenship")
    @Basic(optional = false)
    private String Citizenship;

    //0 for personal, 1 for family, 2 for business
    @Column(name = "UserType")
    @Basic(optional = false)
    private int UserType;

    //Security needs to be addressed on this
    @Column(name = "Password")
    @Basic(optional = false)
    private String password;

    @Column(name = "FamilyMembers")
    private String familyMembers;

    @Column(name = "EmergencyContact")
    private String emergencyContact;

    @Column(name = "Address")
    private String address;

    @Column(name = "closestAirport")
    private String closestAirport;

    @Column(name = "ROLEID")
    @Basic(optional = false)
    private String ROLEDID;

    //Constructors
    public UserAccount() {
    }


    //Getters and Setters
    public String getUserID() {
        return UserID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(String middleNames) {
        this.middleNames = middleNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return Citizenship;
    }

    public void setCitizenship(String citizenship) {
        Citizenship = citizenship;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
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

    public String getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(String familyMembers) {
        this.familyMembers = familyMembers;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClosestAirport() {
        return closestAirport;
    }

    public void setClosestAirport(String closestAirport) {
        this.closestAirport = closestAirport;
    }

    public String getROLEDID() {
        return ROLEDID;
    }

    public void setROLEDID(String ROLEDID) {
        this.ROLEDID = ROLEDID;
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
                ", firstName='" + firstName + '\'' +
                ", middleNames='" + middleNames + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password + '\'' +
                '}';
    }
}
