package classes.airline;

import java.util.Date;

/**
 * Class Airline fot Task 10
 */

public class Airline {
    private String destination;
    private int number;
    private TypeOFAirline typeOFAirline;
    private Date timeOfDeparture;
    private WeekDay weekDay;

    public Airline(String destination, int number, TypeOFAirline typeOFAirline, Date timeOfDeparture,
        WeekDay weekDay) {
        this.destination = destination;
        this.number = number;
        this.typeOFAirline = typeOFAirline;
        this.timeOfDeparture = timeOfDeparture;
        this.weekDay = weekDay;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TypeOFAirline getTypeOFAirline() {
        return typeOFAirline;
    }

    public void setTypeOFAirline(TypeOFAirline typeOFAirline) {
        this.typeOFAirline = typeOFAirline;
    }

    public Date getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(Date timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString() {
        return "Airline{" +
            "destination='" + destination + '\'' +
            ", number=" + number +
            ", typeOFAirline=" + typeOFAirline +
            ", timeOfDeparture=" + timeOfDeparture +
            ", weekDay=" + weekDay +
            '}';
    }
}
