package oop.dragon;

import java.util.ArrayList;

/**
 * Treasury of dragon
 */

public class Treasury {
    private final int id;
    private final ArrayList<Treasure> treasuries = new ArrayList<>();

    public Treasury(int id) {
        this.id = id;
    }

    public void addTreasure(Treasure treasure) {
        treasuries.add(treasure);
    }

    public ArrayList<Treasure> getTreasuries() {
        return treasuries;
    }

    public int getId() {
        return id;
    }

    public Treasure getMaxPrice() {
        int max = 0;
        Treasure current = null;
        for (int i = 0; i <treasuries.size() ; i++) {
            if (treasuries.get(i).getPrice()>max) {
                max = treasuries.get(i).getPrice();
                current = treasuries.get(i);
            }
        }
        return current;
    }

    public void print() {
        treasuries.forEach(System.out::println);
    }
}
