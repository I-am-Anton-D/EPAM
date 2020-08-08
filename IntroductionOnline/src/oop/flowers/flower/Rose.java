package oop.flowers.flower;

public class Rose extends Flower {

    public Rose() {
        super(15);
    }

    @Override
    public String toString() {
        return "Flower Rose, price="+ price;
    }
}
