package oop.flowers.flower;

public class Tulip extends Flower {

    public Tulip() {
        super(12);
    }

    @Override
    public String toString() {
        return "Flower Tulip, price="+ price;
    }
}
