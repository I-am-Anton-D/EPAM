package oop.flowers.packing;

/**
 * Factory of Packing
 */

public class PackingFactory {

    public static Packing getPacking(TypePacking typePacking) {
        if (TypePacking.HARD == typePacking) {
            return new HardPacking();
        }
        if (TypePacking.SOFT == typePacking) {
            return new SoftPacking();
        }
        return null;
    }
}

