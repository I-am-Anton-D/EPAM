package oop.flowers;

import java.util.ArrayList;
import oop.flowers.flower.Flower;
import oop.flowers.packing.Packing;

/**
 * Composition of flowers
 */

public class Composition {
    private final ArrayList<Flower> flowers = new ArrayList<>();
    private Packing packing;

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public boolean removeFlower(int i) {
        if (i>flowers.size()-1 || i<0) { return false; }
        flowers.remove(i);
        return true;
    }

    public void addPacking(Packing packing) {
        this.packing = packing;
    }

    public void removePacking() {
        this.packing = null;
    }

    public int size() {
        return flowers.size();
    }

    public boolean noPacking() {
        return packing==null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (flowers.size()==0) {
            sb.append("No flowers\n");
        } else {
            for (int i = 0; i <flowers.size() ; i++) {
                sb.append(i).append("-").append(flowers.get(i).toString()).append("\n");
            }
        }
        sb.append(packing == null ? "No packing" : packing.toString());
        return sb.toString();
    }
}
