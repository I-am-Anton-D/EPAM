package aggregation.travel;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * Class for filter and holding trips
 */
public class TripsHolder {

    private final ArrayList<Trip> trips = new ArrayList<>();

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public void filterAndPrintTrips(ArrayList<TypeOfNutrition> nutritious, ArrayList<TypeOfTransport> transports,
        ArrayList<TypeOfTrip> typeOfTrips, int minDay, int maxDay) {
        trips.stream().filter(trip -> nutritious.contains(trip.getTypeOfNutrition()) && transports
            .contains(trip.getTypeOfTransport()) && typeOfTrips.contains(trip.getTypeOfTrip())
            && trip.getCountOfDay() > minDay && trip.getCountOfDay()<maxDay).sorted(
            Comparator.comparing(Trip::getPrice)).forEach(System.out::println);
    }

    public void printAll() {
        trips.forEach(System.out::println);
    }


}
