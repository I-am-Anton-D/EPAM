package aggregation.text;

/**
 * Class for word entity
 * Immutable class
 */

public class Word {
    private final String value;
    private final int size;

    public Word(String value) {
        this.value = value;
        size = value.length();
    }

    public String getValue() {
        return value;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return value;
    }
}

