package classes;

/**
 * Test class with 2 variables
 */

public class Test1 {
    private int a;
    private int b;

    public Test1(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int sum() {
        return a+b;
    }

    public int compare() {
        return Math.max(a, b);
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Test1{" +
            "a=" + a +
            ", b=" + b +
            '}';
    }
}
