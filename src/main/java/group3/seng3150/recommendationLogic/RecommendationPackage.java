package group3.seng3150.recommendationLogic;

import group3.seng3150.FlightPlan;
import group3.seng3150.entities.HolidayPackages;

public class RecommendationPackage {
    private HolidayPackages hp;
    private FlightPlan fp;

    public RecommendationPackage(HolidayPackages hp, FlightPlan fp) {
        this.hp = hp;
        this.fp = fp;
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
}
