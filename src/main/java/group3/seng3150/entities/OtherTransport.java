//Class: OtherTransport
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is representing other forms of transport from an airport

package group3.seng3150.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "OtherTransport")
public class OtherTransport {

    @Id
    @Column(name = "OtherTransportID")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String otherTransportID;

    @Column(name = "Type")
    @Basic(optional = false)
    private String type;

    @Column(name = "Description")
    @Basic(optional = false)
    private String description;

    @Column(name = "Destination")
    @Basic(optional = false)
    private String destination;

    public String getOtherTransportID() {
        return otherTransportID;
    }

    public void setOtherTransportID(String otherTransportID) {
        this.otherTransportID = otherTransportID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "OtherTransport{" +
                "otherTransportID='" + otherTransportID + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
