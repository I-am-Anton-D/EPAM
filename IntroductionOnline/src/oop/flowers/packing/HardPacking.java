package oop.flowers.packing;

public class HardPacking extends Packing {

    public HardPacking() {
        super(10);
    }

    @Override
    public String toString() {
        return "Hard Packing, price = " + price;
    }
}
