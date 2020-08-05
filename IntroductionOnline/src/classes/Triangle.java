package classes;

/**
 * Triangle class
 */

public class Triangle {

    double a, b, c; //sides
    int angleA, angleB, angleC; //angles

    /**
     * Creating by 3 sides
     *
     * @param a first side
     * @param b second side
     * @param c third side
     */

    public Triangle(double a, double b, double c) {
        if (a < b + c && b < a + c && c < a + b) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.angleA = (int) ((180/Math.PI)* Math.acos((b * b + c * c - a * a) / (2 * b * c)));
            this.angleB = (int) ((180/Math.PI) * Math.acos((a * a + c * c - b * b) / (2 * a * c)));
            this.angleC = 180 - angleA - angleB;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Creating by 2 sides and angle between 2 sides
     *
     * @param a      first side
     * @param angleC angel between sides
     * @param b      second side
     */

    public Triangle(double a, int angleC, double b) {
        this.a = a;
        this.b = b;
        this.angleC = angleC;
        this.c = Math.sqrt(a * a + b * b + 2 * a * b * Math.cos(Math.toRadians(angleC)));
        this.angleA = (int) ((180/Math.PI) * Math.acos((b * b + c * c - a * a) / (2 * b * c)));
        angleB = 180 - this.angleC - this.angleA;
    }


    /**
     * Creating by 2 sides and angle opposite side b
     *
     * @param angleB angle opposit side b
     * @param b      side b
     * @param c      side c
     */

    public Triangle(int angleB, double b, double c) {
        double d = (c / b) * Math.sin(Math.toRadians(angleB));
        if (d > 1) {
            throw new IllegalArgumentException();
        }
        this.angleB = angleB;
        this.b = b;
        this.c = c;

        if (d == 1) {
            this.angleC = 90;
            this.angleA = 180 - this.angleC - this.angleB;
            this.a = Math.sqrt(b * b + c * c);
        }

        if (d < 1) {
            this.angleC = (int) ((180/Math.PI) * Math.asin(d));
            this.angleA = 180 - this.angleC - this.angleB;
            this.a = this.b * (Math.sin(Math.toRadians(this.angleA)) / Math.sin(Math.toRadians(this.angleB)));
        }
    }

    /**
     * Creating by side and two angles
     *
     * @param c      side
     * @param angleA first angle
     * @param angleB second angle
     */

    public Triangle(double c, int angleA, int angleB) {
        if (angleA + angleB >= 180) {
            throw new IllegalArgumentException();
        }
        this.angleA = angleA;
        this.angleB = angleB;
        this.angleC = 180 - angleA - angleB;
        this.c = c;
        this.a = c * (Math.sin(Math.toRadians(this.angleA))) / (Math.sin(Math.toRadians(this.angleC)));
        this.b = c * (Math.sin(Math.toRadians(this.angleB))) / (Math.sin(Math.toRadians(this.angleC)));

    }

    /**
     * Calculate square of triangle
     */

    public double getSquare() {
        return 0.5 * a * b * Math.sin(Math.toRadians(angleA));
    }

    /**
     * Calculate perimeter of triangle
     */

    public double getPerimeter() {
        return a + b + c;
    }

    @Override
    public String toString() {
        return "Triangle{" +
            "a=" + a +
            ", b=" + b +
            ", c=" + c +
            ", angleA=" + angleA +
            ", angleB=" + angleB +
            ", angleC=" + angleC +
            '}';
    }
}

