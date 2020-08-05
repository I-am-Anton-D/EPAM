package classes;

/**
 * Second test class with 2 variables
 */

public class Test2 {
    int a, b;

    public Test2(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Test2() {
        a = 1;
        b = 1;
    }

    @Override
    public String toString() {
        return "Test2{" +
            "a=" + a +
            ", b=" + b +
            '}';
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
}
