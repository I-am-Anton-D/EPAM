package oop.dragon;

import java.util.ArrayList;

/**
 * Store of Treasury
 */
public class DragonStorehouse {
    private final ArrayList<Treasury> store = new ArrayList<>();

    public DragonStorehouse() {
    }

    /**
     * Add treasury to the store
     * @param treasury to add
     */

    public void addTreasury(Treasury treasury) {
        store.add(treasury);
    }

    /**
     * Getting Treasury by id
     * @param id of treasury
     * @return Treasury instance
     */

    public Treasury getTreasuryById(int id) {
        for (Treasury t: store) {
           if (t.getId() == id) {
               return t;
           }
        }
        return null;
    }

    /**
     * Find most expensive treasure in store
      */

    public Treasure getExpensive() {
        int max = 0;
        Treasure current = null;
        for (Treasury t: store) {
            if (t.getMaxPrice().getPrice()>max) {
                current = t.getMaxPrice();
                max = current.getPrice();
            }
        }
        return current;
    }

    /**
     * Getting array of treasury
     */

    public ArrayList<Treasury> getStore() {
        return store;
    }


    /**
     * Getting total count of treasuries in all treasury
     */
    public int totalCountOfTreasuries() {
        return store.stream().mapToInt(e->e.getTreasuries().size()).sum();
    }

    /**
     * Sum of price of all treasuries
     */

    public int totalPrice() {
        int sum = 0;
        for (Treasury t: store) {
            for (Treasure tr: t.getTreasuries()) {
                sum+=tr.getPrice();
            }
        }
        return sum;
    }

    /**
     * Getting list of all treasuries in all treasury
     */

    public ArrayList<Treasure> getAllTreasuries() {
        ArrayList<Treasure> all = new ArrayList<>();
        for (Treasury t: store) {
            all.addAll(t.getTreasuries());
        }
        return all;
    }
}
