package oop.flowers.flower;

/**
 * Factory for flowers
 */

public class FlowerFactory {

    public static Flower getFlower(TypeFlower typeFlower) {
        if (TypeFlower.ROSE == typeFlower) {
            return new Rose();
        }
        if (TypeFlower.DAISY == typeFlower) {
            return new Daisy();
        }
        if (TypeFlower.LILY == typeFlower) {
            return new Lily();
        }
        if (TypeFlower.TULIP == typeFlower) {
            return new Tulip();
        }
        return null;
    }

}
