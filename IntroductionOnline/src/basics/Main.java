package basics;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    final static double EPSILON = 1E-12;  // Epsilon of calculations

    /*
     * Part 1. Linear programs
     */

    /**
     * Task 1.1 Find the value of expression z = ((a – 3 ) * b / 2) + c
     */

    public static double calculateValueP1T1(double a, double b, double c) {
        return (a - 3) * b / 2 + c;
    }

    /**
     * Task 1.2. Find the value of expression (b + (b^2 + 4ac)^1/2)/2a - a^3*c + b ^(-2)
     *
     * @param a must not be zero
     * @param b must not be zero
     * @param c any value
     * @return value of expression or null, dependently of parameters
     */

    public static Double calculateValueP1T2(double a, double b, double c) {
        return a < EPSILON || b < EPSILON ? null
            : (b + Math.sqrt(b * b + 4 * a * c)) / (2 * a) - Math.pow(a, 3) * c + 1 / (b * b);
    }

    /**
     * Task 1.3. Find the value of expression (sin(x) + cos(y))/(cos(x) - sin(y)) * tg(xy)
     *
     * @param x angle in radians
     * @param y angle in radians
     * @return value of expression or null, if x = y = Pi/4
     */

    public static Double calculateValueP1T3(double x, double y) {
        return Math.abs(x - Math.PI / 4) < EPSILON && Math.abs(y - Math.PI / 4) < EPSILON ? null
            : (Math.sin(x) + Math.cos(y)) / (Math.cos(x) - Math.sin(y)) * Math.tan(x * y);
    }

    /**
     * Task 1.4. Swap the integer amd decimal parts of number
     *
     * @param number in nnn.ddd format
     * @return number like ddd.nnn
     */

    public static double swapIntegerWithDecimal(double number) {
        double scale = 1000;
        return Math.round((int) number + (number - (int) number) * scale * scale) / scale;
    }

    /**
     * Task 1.5. Transform count of seconds to the string by pattern
     *
     * @param t count of seconds. If t >= 360000 - overflow the pattern. Checking and fix it to save only 2 digits
     *          in hours
     * @return string in HHчас MMмин SSс
     */

    public static String transformSecondsToPattern(int t) {
        t = t >= 360000 ? t - 360000 : t;
        long hour = t / 3600, min = t / 60 % 60, sec = t % 60;
        return String.format("%02dчас %02dмин %02dс", hour, min, sec);
    }

    /**
     * Task 1.6. Checking point in area
     *
     * @return true, if point in area, false - otherwise
     */

    public static boolean checkPointInArea(double x, double y) {
        return ((x > -4 && x < 4 && y <= 0 && y > -3) || (y < 4 && y >= 0 && x > -2 && x < 2));
    }

    /*
     * Part 2. Branching programs
     */

    /**
     * Task 2.1 Checking the triangle
     *
     * @param angleA angle in degrees
     * @param angleB angle in degrees
     * @return result of checking
     */

    public static String checkTriangle(int angleA, int angleB) {
        return angleA + angleB >= 180 ? "Triangle do not exist"
            : angleA == 90 || angleB == 90 || angleA + angleB == 90 ? "Rectangular triangle" : "Regular triangle";
    }

    /**
     * Task 2.2. Find max{min(a, b), min(c, d)}
     */

    public static int findMaxFromMin(int a, int b, int c, int d) {
        return Math.max(Math.min(a, b), Math.min(c, d));
    }

    /**
     * Task 2.3 Is three points on a line?
     */

    public static boolean isThreePointsOnLine(double x1, double y1, double x2, double y2, double x3, double y3) {
        double k = (y1 - y2) / (x1 - x2);
        double b = y1 - k * x1;
        return x1 != x2 && (y3 - k * x3 + b) < EPSILON;
    }

    /**
     * Task 2.4 Can the brick move though hole?
     *
     * @param a     size of hole
     * @param b     size of hole
     * @param sizes sizes of brick
     * @return true, if brick can move through hole, false - otherwise
     */

    public static boolean brickThroughHole(int a, int b, int... sizes) {
        a = a > b ? a + b - (b = a) : a;
        Arrays.sort(sizes);
        return a >= sizes[0] && b >= sizes[1];
    }

    /**
     * Task 2.4 Find the value of expression, dependently of parameter
     */

    public static double calculateValueP2T5(double x) {
        return x >= 3 ? x * x - 3 * x + 9 : 1 / (Math.pow(x, 3) + 6);
    }

    /*
     * Part 3. Programs with cycles
     */

    /**
     * Task 3.1 Sum of consecutive numbers
     *
     * @return sum, using known expression
     */

    public static int sumOfConsecutiveNumbers() {
        System.out.println("Sum of consecutive numbers \nEnter positive integer number grather 1: ");
        Scanner scanner = new Scanner(System.in);
        int border;
        while (true) {
            try {
                border = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format number. Please, repeat:");
            }
        }
        return border * (border + 1) / 2;
    }

    /**
     * Task 3.2 Calculate the function values on the segment
     *
     * Swap borders and getting absolute value of step, if input parameters are wrong
     *
     * @param a border of segment
     * @param b border of segment
     * @param h calculating step
     * @return values of function
     */

    public static String calculateValuesOfFunction(int a, int b, int h) {
        a = a > b ? a + b - (b = a) : a;
        h = h < 0 ? -h : h;
        int[] result = new int[(b - a) / h + 1];
        for (int i = a, t = 0; i <= b; i += h, t++) {
            result[t] = i > 2 ? i : -i;
        }
        return Arrays.toString(result);
    }

    /**
     * Task 3.3 Sum of squares from 1 to n
     *
     * Using using known expression
     */

    public static int sumOfSquares(int n) {
        return n * (n + 1) * (2 * n + 1) / 6;
    }

    /**
     * Task 3.4 Multiplication of squares
     *
     * Seriously? 200 numbers? Ok, no problem... just use 200 in parameter
     */

    public static String multiplicationOfSquares(int n) {
        BigInteger multiplication = BigInteger.ONE;
        BigInteger operand = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            operand = operand.add(BigInteger.ONE);
            multiplication = multiplication.multiply(operand.multiply(operand));
        }
        return multiplication.toString();
    }

    /**
     * Task 3.5 Sum of sequence
     *
     * @param e some border for member
     */

    public static double sumOfSequence(double e) {
        double an;
        double sum = 0;
        for (int n = 1; ; n++) {
            an = 1 / (Math.pow(2, n)) + 1 / Math.pow(3, n);
            if (an - e >= EPSILON) {
                sum += an;
            } else {
                break;
            }
        }
        return sum;
    }

    /**
     * Task 3.6 Integers codes of chars
     *
     * Use only ANSI codes. Start from 33 ('!') - first valuable character and finish on 127
     */

    public static String charsAndInts() {
        StringBuilder sb = new StringBuilder();
        for (char c = 33; c <= 127; c++) {
            sb.append((int) c).append(" - ").append(c).append(", ");
        }
        return sb.toString();
    }


    /**
     * Task 3.7. Dividers of segment
     *
     * Input from console
     */

    public static String dividersOfSegment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter borders of segment (two positive integers separating by space): ");
        int m, n;
        while (true) {
            try {
                String[] inputs = scanner.nextLine().split(" ");
                if (inputs.length != 2) {
                    throw new NumberFormatException();
                } else {
                    m = Integer.parseInt(inputs[0]);
                    n = Integer.parseInt(inputs[1]);
                    if (m < 0 || n < 0) {
                        throw new NumberFormatException();
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format number. Please, repeat:");
            }
        }

        m = m > n ? m + n - (n = m) : m;

        StringBuilder sb = new StringBuilder();
        for (int j = m; j <= n; j++) {
            sb.append("Dividers of ").append(j).append(": ");
            for (int i = 2; i < j; i++) {
                if (j % i == 0) {
                    sb.append(i).append(", ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Task 3.8 Common digits in two numbers
     */

    public static String commonDigitsOfNumbers(int a, int b) {
        return Arrays.toString(("" + a + b).chars().distinct().map(Character::getNumericValue).sorted().toArray());
    }

    public static void main(String[] args) {
        System.out.println("[Task 1.1] The value of expression from task = " + calculateValueP1T1(1, 2, 3));
        System.out.println("[Task 1.2] The value of expression from task = " + calculateValueP1T2(1, 2, 3));
        System.out.println("[Task 1.3] The value of expression from task = " + calculateValueP1T3(1, 2));
        System.out.println("[Task 1.4] Swap the parts of number: " + swapIntegerWithDecimal(123.456));
        System.out.println("[Task 1.5] Transformation seconds to pattern: " + transformSecondsToPattern(123456));
        System.out.println("[Task 1.6] Point in area? Answer:  " + checkPointInArea(1, 2));
        System.out.println("[Task 2.1] Check the triangle: " + checkTriangle(30, 60));
        System.out.println("[Task 2.2] The value of expression from task = " + findMaxFromMin(1, 2, 3, 4));
        System.out.println("[Task 2.3] Is three points on a line? Answer: "
            + isThreePointsOnLine(1, 2, 3, 4, 5, 6));
        System.out.println("[Task 2.4] Can the brick move though the hole? Answer: "
            + brickThroughHole(1, 2, 3, 4, 5));
        System.out.println("[Task 2.5] The value of expression from task = " + calculateValueP2T5(3));
        // Console input
        //System.out.println("[Task 3.1] Sum of consecutive numbers = " + sumOfConsecutiveNumbers());
        System.out.println("[Task 3.2] Values of function: " + calculateValuesOfFunction(-5, 10, 3));
        System.out.println("[Task 3.3] Sum of squares  = " + sumOfSquares(100));
        System.out.println("[Task 3.4] Multiplication of squares = " + multiplicationOfSquares(50));
        System.out.println("[Task 3.5] Sum of sequence = " + sumOfSequence(1 / 123D));
        System.out.println("[Task 3.6] Integer codes of chars: " + charsAndInts());
        //Console input
        //System.out.println("[Task 3.7] Dividers of segment: \n" + dividersOfSegment());
        System.out.println("[Task 3.8] Common digits in two numbers: " + commonDigitsOfNumbers(123, 123456));
    }

}
