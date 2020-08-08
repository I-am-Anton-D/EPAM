package oop.flowers.flower;

public class Daisy extends Flower {

    public Daisy() {
        super(10);
    }

    @Override
    public String toString() {
        return "Flower Daisy, price="+ price;
    }
}
