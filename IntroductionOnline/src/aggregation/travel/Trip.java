package aggregation.travel;

/**
 * Class for trip
 */

public class Trip {
    private String name;
    private int countOfDay;
    private TypeOfNutrition typeOfNutrition;
    private TypeOfTransport typeOfTransport;
    private TypeOfTrip typeOfTrip;
    private long price;

    public Trip(int countOfDay, TypeOfNutrition typeOfNutrition, TypeOfTransport typeOfTransport,
        TypeOfTrip typeOfTrip, String name, long price) {
        this.countOfDay = countOfDay;
        this.typeOfNutrition = typeOfNutrition;
        this.typeOfTransport = typeOfTransport;
        this.typeOfTrip = typeOfTrip;
        this.name = name;
        this.price = price;
    }

    public int getCountOfDay() {
        return countOfDay;
    }

    public TypeOfNutrition getTypeOfNutrition() {
        return typeOfNutrition;
    }

    public TypeOfTransport getTypeOfTransport() {
        return typeOfTransport;
    }

    public TypeOfTrip getTypeOfTrip() {
        return typeOfTrip;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Trip{" +
            "name='" + name + '\'' +
            ", countOfDay=" + countOfDay +
            ", typeOfNutrition=" + typeOfNutrition +
            ", typeOfTransport=" + typeOfTransport +
            ", typeOfTrip=" + typeOfTrip +
            ", price=" + price +
            '}';
    }
}
