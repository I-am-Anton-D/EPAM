package oop.flowers.packing;

public class SoftPacking extends Packing {

    public SoftPacking() {
        super(5);
    }

    @Override
    public String toString() {
        return "Soft Packing, price = " + price;
    }
}
