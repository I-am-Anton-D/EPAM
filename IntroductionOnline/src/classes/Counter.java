package classes;

/**
 * Class for counter
 */

public class Counter {
    private final int DEFAULT_MIN = 0;
    private final int DEFAULT_MAX = 10;

    private final int min;
    private final int max;
    private int current;

    public Counter(int min, int max, int current) {
        min = (min>max)? min + max - (max = min) : min;
        this.min = min;
        this.max = max;
        this.current = current>max ? max : Math.max(current, min);
    }

    public Counter() {
        min = DEFAULT_MIN;
        max = DEFAULT_MAX;
        current = DEFAULT_MIN;
    }

    public int getCurrent() {
        return current;
    }

    public int inc() {
        return current==max ? (current=min) : ++current;
    }

    public int dec() {
        return current==min ? (current=max) : --current;
    }

    @Override
    public String toString() {
        return "Counter {" +
            "min=" + min +
            ", max=" + max +
            ", current=" + current +
            '}';
    }
}
