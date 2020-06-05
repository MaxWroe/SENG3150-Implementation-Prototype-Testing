//Class: Airport
//Author: Angus Simmons
// Description: This is an entity that uses the hibernate framework to extract data from a database
// in this instance it is plane types


package group3.seng3150.entities;

import javax.persistence.*;

@Entity
@Table(name = "PlaneType")
public class Plane {

    @Id
    @Column(name = "PlaneCode")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String planeId;

    @Column(name = "Details")
    @Basic(optional = false)
    private String details;

    @Column(name = "NumFirstClass")
    @Basic(optional = false)
    private int numFirstClass;

    @Column(name = "NumBusiness")
    @Basic(optional = false)
    private int numBusClass;

    @Column(name = "NumPremiumEconomy")
    @Basic(optional = false)
    private int numPremEcClass;

    @Column(name = "Economy")
    @Basic(optional = false)
    private int Economy;

    //still to be considered
    //disabilitySupport, internetAvailibility, chargingPorts, entertainment, toilets


    //Constructors
    public Plane(String details, int numFirstClass, int numBusClass, int numPremEcClass, int economy) {
        this.details = details;
        this.numFirstClass = numFirstClass;
        this.numBusClass = numBusClass;
        this.numPremEcClass = numPremEcClass;
        Economy = economy;
    }

    public Plane() {
    }

    //Getters and Setters
    public String getPlaneId() {
        return planeId;
    }

    public void setPlaneId(String planeId) {
        this.planeId = planeId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getNumFirstClass() {
        return numFirstClass;
    }

    public void setNumFirstClass(int numFirstClass) {
        this.numFirstClass = numFirstClass;
    }

    public int getNumBusClass() {
        return numBusClass;
    }

    public void setNumBusClass(int numBusClass) {
        this.numBusClass = numBusClass;
    }

    public int getNumPremEcClass() {
        return numPremEcClass;
    }

    public void setNumPremEcClass(int numPremEcClass) {
        this.numPremEcClass = numPremEcClass;
    }

    public int getEconomy() {
        return Economy;
    }

    public void setEconomy(int economy) {
        Economy = economy;
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
        return "Plane{" +
                "planeId='" + planeId + '\'' +
                ", details='" + details + '\'' +
                ", numFirstClass=" + numFirstClass +
                ", numBusClass=" + numBusClass +
                ", numPremEcClass=" + numPremEcClass +
                ", Economy=" + Economy +
                '}';
    }
}
