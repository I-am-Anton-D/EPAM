package oop.flowers.flower;

public class Lily extends Flower{

    public Lily() {
        super(20);
    }

    @Override
    public String toString() {
        return "Flower Lily, price="+ price;
    }
}
