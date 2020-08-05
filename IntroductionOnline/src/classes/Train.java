package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for train entity
 */

public class Train {
    private String destination;
    private int number;
    private Date departureTime;

    public Train(String destination, int number, Date departureTime) {
        this.destination = destination;
        this.number = number;
        this.departureTime = departureTime;
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

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Train{" +
            "destination='" + destination + '\'' +
            ", number=" + number +
            ", departureTime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(departureTime) +
            '}';
    }

}
