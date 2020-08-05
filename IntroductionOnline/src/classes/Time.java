package classes;

/**
 * Class for time entity
 */

public class Time {

    private int hours, minutes, seconds;

    public Time(int hours, int minutes, int seconds) {
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = (hours > 23 || hours < 0) ? 0 : hours;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = (minutes > 59 || minutes < 0) ? 0 : minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = (seconds > 59 || seconds < 0) ? 0 : seconds;
    }

    public void modify (int hours, int minutes, int seconds){
        setHours(getHours()+hours);
        setMinutes(getMinutes()+minutes);
        setSeconds(getSeconds()+seconds);
    }

    @Override
    public String toString() {
        return "Time {" +
            "hours=" + hours +
            ", minutes=" + minutes +
            ", seconds=" + seconds +
            '}';
    }
}