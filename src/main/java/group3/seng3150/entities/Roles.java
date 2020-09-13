//Class: Roles
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is representing the security roles of the system

package group3.seng3150.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "Roles")
public class Roles implements GrantedAuthority{

    @Id
    @Column(name = "ROLEID")
    @Basic(optional = false)
    private String ROLEID;

    @Column(name = "USER")
    @Basic(optional = false)
    private String USER;

    @Column(name = "READ_PRIVILEGE")
    @Basic(optional = false)
    private String READ_PRIVILEGE;

    @Column(name = "WRITE_PRIVILEGE")
    @Basic(optional = false)
    private String WRITE_PRIVILEGE;

    public Roles() {
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getWRITE_PRIVILEGE() {
        return WRITE_PRIVILEGE;
    }

    public void setWRITE_PRIVILEGE(String WRITE_PRIVILEGE) {
        this.WRITE_PRIVILEGE = WRITE_PRIVILEGE;
    }

    public String getREAD_PRIVILEGE() {
        return READ_PRIVILEGE;
    }

    public void setREAD_PRIVILEGE(String READ_PRIVILEGE) {
        this.READ_PRIVILEGE = READ_PRIVILEGE;
    }

    public String getROLEID() {
        return ROLEID;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return Objects.equals(ROLEID, roles.ROLEID) &&
                Objects.equals(USER, roles.USER) &&
                Objects.equals(READ_PRIVILEGE, roles.READ_PRIVILEGE) &&
                Objects.equals(WRITE_PRIVILEGE, roles.WRITE_PRIVILEGE);
    }

    @Override
    public String toString() {
        return "Roles{" +
                "ROLEID='" + ROLEID + '\'' +
                ", USER='" + USER + '\'' +
                ", READ_PRIVILEGE='" + READ_PRIVILEGE + '\'' +
                ", WRITE_PRIVILEGE='" + WRITE_PRIVILEGE + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return USER;
    }
}
