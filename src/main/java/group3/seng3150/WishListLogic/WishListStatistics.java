//Class: ReccomendationPackage
//Author: Angus Simmons
// Description: this class returns a list of the countries that is ordered by popularity

package group3.seng3150.WishListLogic;

import group3.seng3150.entities.Booking;
import group3.seng3150.entities.WishListEntry;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.*;


public class WishListStatistics {


    private EntityManager em;
    private List<CountryStat> stats;

    @Autowired
    public WishListStatistics(EntityManager em) {
        this.em = em;
    }


    public List<CountryStat> getStats(){
        //returns some form of "hot location" data
        stats = new LinkedList<>();
        List<WishListEntry> wishlist = em.createQuery("SELECT w FROM WishListEntry w").getResultList();


        for (int i = 0; i < wishlist.size(); i++) {
            addCountry(wishlist.get(i));
        }

        Collections.sort(stats);
        return stats;
    }

    public void addCountry(WishListEntry wish){
        Iterator it = stats.iterator();
        boolean found = false;
        while(it.hasNext()){
            CountryStat temp = (CountryStat) it.next();
            if(wish.getCountryCode3().equals(temp.getCountryCode3())){
                temp.setPopularity(temp.getPopularity()+1);
                found = true;
            }
        }

        if(found ==false){
            stats.add(new CountryStat(wish.getCountryCode3(), 1));

        }

    }

}
