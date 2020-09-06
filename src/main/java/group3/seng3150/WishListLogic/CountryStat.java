package group3.seng3150.WishListLogic;

import group3.seng3150.entities.WishListEntry;

public class CountryStat implements Comparable{
    private String CountryCode3;
    private int popularity;

    public CountryStat() {
    }

    public CountryStat(String countryCode3, int popularity) {
        CountryCode3 = countryCode3;
        this.popularity = popularity;
    }


    public String getCountryCode3() {
        return CountryCode3;
    }

    public void setCountryCode3(String countryCode3) {
        CountryCode3 = countryCode3;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(((CountryStat) o).getPopularity(), this.getPopularity());
    }
}
