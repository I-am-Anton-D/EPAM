package aggregation.automobile;

/**
 * Class for car
 */

public class Automobile {
    private final String model;
    private final Engine engine;
    private Wheel[] wheels;

    public Automobile(String model, Engine engine, Wheel[] wheels) {
        this.model = model;
        this.engine = engine;
        this.wheels = wheels;
    }

    public void move() {
        engine.start();
        for (Wheel wheel : wheels) {
            wheel.rotate();
        }
        System.out.println("Car are moving");
    }

    public void refill() {
        System.out.println("Car refilling");
    }

    public void changeWheel(int wheelNumber, Wheel wheel) {
        if (wheelNumber>3 || wheelNumber<0) return;
        wheels[wheelNumber] = wheel;
        System.out.println("Wheel number "+ wheelNumber + " was changed");
    }

    public void printModel() {
        System.out.println(this.model);
    }

}
