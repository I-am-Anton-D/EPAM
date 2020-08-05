package classes.airline;

import classes.AbstractHolder;
import java.util.Arrays;
import java.util.Date;
import javax.xml.crypto.Data;

/**
 * Holder of airlines for Task 10
 */
public class AirlinesHolder extends AbstractHolder<Airline> {

    public AirlinesHolder(int count) {
        super(Airline.class, count);
    }

    /**
     * Filter by destination
     */

    public void filterByDestination(String destination) {
        Arrays.stream(array).filter(airline -> airline.getDestination().equals(destination))
            .forEach(System.out::println);
    }

    /**
     * Filter by day of week
     */

    public void filterByDayOfWeek(WeekDay weekDay) {
        Arrays.stream(array).filter(airline -> airline.getWeekDay()==weekDay)
            .forEach(System.out::println);
    }

    /**
     * Filter by day of week and time
     */

    public void filterByDayAndTime(WeekDay weekDay, Date date) {
        Arrays.stream(array).filter(airline -> airline.getWeekDay()==weekDay && airline.getTimeOfDeparture().after(date))
            .forEach(System.out::println);
    }

}
