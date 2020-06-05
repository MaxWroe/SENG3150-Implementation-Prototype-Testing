//Class: Airport
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is representing user enquiries for customer support


package group3.seng3150.entities;

import javax.persistence.*;

@Entity
@Table(name = "Enquiry")
public class Enquiry {

    @Id
    @Column(name = "EnquiryID")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String enquiryID;

    @Column(name = "Description")
    @Basic(optional = false)
    private String description;

    public Enquiry() {
    }

    public String getEnquiryID() {
        return enquiryID;
    }

    public void setEnquiryID(String enquiryID) {
        this.enquiryID = enquiryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "Enquiry{" +
                "enquiryID='" + enquiryID + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

