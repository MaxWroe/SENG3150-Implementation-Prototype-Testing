package group3.seng3150.recommendationLogic;

import group3.seng3150.FlightPlan;
import group3.seng3150.entities.HolidayPackages;

public class RecommendationPackage {
    private HolidayPackages hp;
    private FlightPlan fp;
    private String departureLocation;
    private String cc;
    private int groupSize;

    public RecommendationPackage(HolidayPackages hp, FlightPlan fp, String departureLocation, String cc, int groupSize) {
        this.hp = hp;
        this.fp = fp;
        this.departureLocation = departureLocation;
        this.cc = cc;
        this.groupSize = groupSize;
    }

    public HolidayPackages getHp() {
        return hp;
    }

    public void setHp(HolidayPackages hp) {
        this.hp = hp;
    }

    public FlightPlan getFp() {
        return fp;
    }

    public void setFp(FlightPlan fp) {
        this.fp = fp;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }
}
