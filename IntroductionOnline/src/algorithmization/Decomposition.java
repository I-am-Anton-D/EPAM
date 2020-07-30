package algorithmization;

import static utils.Utils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Decomposition {

    /**
     * Task 1. Find LCM for 2 numbers
     * <p>
     * I solved it at last task prev part. See Utils.class
     */

    public static void lcmTask(int a, int b) {
        System.out.println("-----------------------------------------------");
        System.out.printf("[Task 1] LCM for %d and %d%n", a, b);
        System.out.println("GCD = " + gcd(a, b));
        System.out.println("LCM = " + lcm(a, b));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 2. Find GCD for 4 ints or more
     */

    public static void gcdOfArray(int... a) {
        int result;
        if (a.length < 2) {
            throw new IllegalArgumentException();
        }
        result = gcd(a[0], a[1]);
        if (a.length > 2) {
            for (int i = 2; i < a.length; i++) {
                result = gcd(result, a[i]);
            }
        }
        System.out.println("[Task 2] GCD for: " + Arrays.toString(a));
        System.out.println("GCD = " + result);
        System.out.println("-----------------------------------------------");

    }

    /**
     * Find square of 6 sides polygon
     *
     * @param a - side of polygon
     */

    public static void squarePolygon(int a) {
        System.out.printf("[Task 3] Square of polygon with side = %d%n", a);
        System.out.println("Square = " + 6 * squareOfTriangle(a));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Square of triangle from polygon
     *
     * @param a - side of triangle
     */

    public static double squareOfTriangle(int a) {
        return (a * a * Math.sin(Math.toRadians(60))) / 2;
    }

    public static void maxDistanceBetweenPoints(int[] x, int[] y) throws IllegalArgumentException {
        if (x.length != y.length || x.length < 2) {
            throw new IllegalArgumentException();
        }
        System.out
            .println("Task 4. Max distance for points, x: " + Arrays.toString(x) + " y: " + Arrays.toString(y));
        double max = distance2points(x[1], x[0], y[1], y[0]);
        for (int i = 1; i < x.length - 1; i++) {
            double curDistance;
            for (int j = i + 1; j < x.length; j++) {
                curDistance = distance2points(x[i], x[j], y[i], y[j]);
                if (curDistance > max) {
                    max = curDistance;
                }
            }
        }
        System.out.println("Max distance = " + max);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Distance between 2 points
     */

    public static double distance2points(int x2, int x1, int y2, int y1) {
        return Math.hypot(x2 - x1, y2 - y1);
    }

    /**
     * Task 5. Second maximum in array
     */

    public static void secondMaximumInArray(int[] array) {
        System.out.println("[Task 5] Second maximum");
        System.out.println("Input array = " + Arrays.toString(array));
        int l = array.length;
        Arrays.sort(array);
        System.out.println("Second maximum = " + array[l - 2]);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 6. Check mutually prime of 3 numbers
     */
    public static void checkPrime(int a, int b, int c) {
        System.out.printf("[Task 6] Check mutually prime of %d, %d, %d%n", a, b, c);
        int result = gcd(a, b);
        result = gcd(result, c);
        if (result == 1) {
            System.out.println("Numbers are mutually prime");
        } else {
            System.out.println("Numbers not mutually prime");
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 7. Find sum of factorials of odd numbers from 1 to n
     *
     * @param n - border
     */

    public static void sumOfOddFactorials(int n) {
        int[] oddNumbers = IntStream.iterate(1, i -> i + 2).limit(n / 2 + n % 2).toArray();
        System.out.println("[Task 7] Sum of factorials");
        System.out.println("Odd numbers: " + Arrays.toString(oddNumbers));
        int sum = 0;
        for (int oddNumber : oddNumbers) {
            int factorial = IntStream.rangeClosed(1, oddNumber).reduce((a, b) -> a * b).orElse(1);
            System.out.printf("Factorial of %d = %d%n", oddNumber, factorial);
            sum += factorial;
        }
        System.out.println("Sum of factorials = " + sum);
        System.out.println("-----------------------------------------------");

    }

    /**
     * Task 8. Sum three consecutive elements in segment
     *
     * @param array input
     * @param k     left border of segment
     * @param m     right border of segment
     * @throws IllegalArgumentException if segment bounds are wrong
     */

    public static void sumsInArray(int[] array, int k, int m) throws IllegalArgumentException {
        if (m + 2 > array.length - 1 || m < k || k < 0) {
            throw new IllegalArgumentException();
        }
        System.out.println("[Task 8] Sums in array");
        System.out.println("Input array = " + Arrays.toString(array));

        for (int i = k; i <= m; i++) {
            System.out
                .printf("The sum of three consecutive elements at index %d = %d%n", i, sumOf3Element(array, i));
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * Sum of 3 consecutive elements in array
     *
     * @param array input array
     * @param i     start index
     * @throws IllegalArgumentException for protection against Out of bounds
     */

    public static int sumOf3Element(int[] array, int i) throws IllegalArgumentException {
        if (i < 0 || array.length - 1 < i + 2) {
            throw new IllegalArgumentException();
        }
        return array[i] + array[i + 1] + array[i + 2];
    }

    /**
     * Task 9. Square of Quadrangle. Angle between side a and side b equal 90 degrees
     *
     * @param a side
     * @param b side
     * @param c side
     * @param d side
     */

    public static void squareQuadrangle(int a, int b, int c, int d) throws IllegalArgumentException {
        if (a <= 0 || b <= 0 || c <= 0 || d <= 0) {
            throw new IllegalArgumentException();
        }
        System.out.printf("[Task 9] Square of quadrangle with sides %d, %d, %d, %d%n", a, b, c, d);
        double squareOfRectangleTriangle = a * b * 0.5;
        double h = Math.hypot(a, b); //hypotenuse
        double p = (h + c + d) / 2;
        double squareOfTriangleBy3sides = Math.sqrt(p * (p - h) * (p - c) * (p - d));
        double squareOfQuadrangle = squareOfRectangleTriangle + squareOfTriangleBy3sides;
        System.out.println("Square of quadrangle = " + squareOfQuadrangle);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 10. Generate array by digits of number
     */

    public static void arrayFromDigitOfNumber(int n) {
        System.out.println("[Task 10] Array of digits of " + n);
        System.out.println(
            "Array from number: " + Arrays.toString(("" + n).chars().map(Character::getNumericValue).toArray()));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 11. Digits in 2 numbers
     */

    public static void digitsInNumbers(int a, int b) {
        System.out.printf("[Task 11] Count of digits in %d and %d%n", a, b);
        System.out.printf("In number %d - %d digits%n", a, ("" + a).length());
        System.out.printf("In number %d - %d digits%n", b, ("" + b).length());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 11. Numbers with sum of digits = k and <= n
     *
     * @param k specific parameter
     * @param n specific parameter
     */

    public static void arrayT12(int k, int n) {
        System.out.printf("[Task 12] Numbers with sum of digits = %d and <= %d%n", k, n);
        StringBuilder sb = new StringBuilder("Numbers: " + k + ": ");
        for (int i = 0; i <= n; i++) {
            int sumOfDigits = (i + "").chars().map(Character::getNumericValue).sum();
            if (sumOfDigits == k) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 13. Twins of segment
     *
     * @param n - segment from n tp 2*n
     */

    public static void twinsNumber(int n) {
        System.out.println("[Task 13] Twins numbers");
        for (int i = n; i <= 2 * n; i++) {
            System.out.println("Twins for " + i + ": " + Arrays.toString(getTwins(i)));
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * Getting twins
     */

    public static int[] getTwins(int n) {
        return new int[]{n - 1, n + 1};
    }

    /**
     * Task 14. Armstrong numbers from 1 to k
     *
     * @param k - border of search
     */

    public static void getArmstrongNumbers(int k) {
        System.out.println("[Task 14] Armstrong numbers from 1 to " + k + ": " + Arrays.toString(getNumbers(k)));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Getting Armstrong numbers
     */

    public static int[] getNumbers(int n) {
        int[] result;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int degree = getDegree(i);
            int input = i;
            int sum = 0;
            int k;
            do {
                k = input % 10;
                int a = 1;
                for (int j = 0; j < degree; j++) {
                    a *= k;
                }
                sum += a;
            } while ((input /= 10) > 0);
            if (sum == i) {
                list.add(i);
            }
        }
        result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * Degree of number
     */

    public static int getDegree(int i) {
        int degree = 0;
        do {
            degree += 1;
        } while ((i /= 10) > 0);
        return degree;
    }

    /**
     * Task 15. Numbers in asc sequence with n digits
     *
     * @param n - size of sequence
     */

    public static void numbersInSequence(int n) {
        System.out.printf("[Task 15] Numbers in sequence with %d digits%n", n);
        StringBuilder sb = new StringBuilder("Numbers in sequence: ");
        for (int i = 0; i <= 9 - n; i++) {
            sb.append(getSequenceOfDigits(i, n)).append(" ");
        }
        System.out.println(sb.toString());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Getting sequence of digits
     *
     * @param k start at k
     * @param n count of digits
     */

    public static int getSequenceOfDigits(int k, int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += (k + i) * Math.pow(10, n - i);
        }
        return result;
    }

    /**
     * Task 16. Find sum of numbers with odd digits and count of digits = n
     *
     * @param n - count of digits in a number
     */

    public static void sumOfNumbersWithOddDigits(int n) {
        System.out.printf("[Task 16] Sum of numbers with odd digits and count of digits = %d%n", n);
        int sum = 0;
        for (int i = (int) Math.pow(10, n - 1); i < (int) Math.pow(10, n); i++) {
            if (checkOddDigits(i, n)) {
                sum += i;
            }
        }
        int countOfEvenNumbersInSum = (int) ("" + sum).chars().map(Character::getNumericValue)
            .filter(e -> e % 2 == 0).count();
        System.out.println("Sum = " + sum);
        System.out.printf("In number %d - %d even digit(s)%n", sum, countOfEvenNumbersInSum);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Checking all digits in number = i and count of digits = n
     *
     * @param i - checking number
     * @param n - count of digits
     * @return true, if all digits are odd, otherwise - false;
     */

    public static boolean checkOddDigits(int i, int n) {
        int countOdd = (int) ("" + i).chars().map(Character::getNumericValue).filter(e -> e % 2 != 0).count();
        return countOdd == n;
    }

    /**
     * Task 17. Find count of subtraction while n>0 (n minus sum of digits in n)
     */

    public static void countOfSubtraction(int n) {
        System.out.printf("[Task 17] Count of subtraction for number %d%n", n);
        int i = 0;
        while (n > 0) {
            n -= getSumOfDigitsInNumber(n);
            i++;
        }
        System.out.println("Count of subtraction = " + i);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Sum of digits in a number;
     */

    public static int getSumOfDigitsInNumber(int n) {
        return ("" + n).chars().map(Character::getNumericValue).sum();
    }

    public static void main(String[] args) {
        lcmTask(12, 8);
        gcdOfArray(4, 8, 12, 20);
        squarePolygon(1);
        maxDistanceBetweenPoints(getRandomIntsArray(1, 5, 5), getRandomIntsArray(1, 5, 5));
        secondMaximumInArray(getRandomIntsArray(1, 25, 10));
        checkPrime(1, 3, 5);
        sumOfOddFactorials(9);
        sumsInArray(getRandomIntsArray(1, 25, 20), 3, 6);
        squareQuadrangle(1, 2, 3, 4);
        arrayFromDigitOfNumber(123456789);
        digitsInNumbers(123, 12344566);
        arrayT12(10, 60);
        twinsNumber(5);
        getArmstrongNumbers(10000);
        numbersInSequence(4);
        sumOfNumbersWithOddDigits(3);
        countOfSubtraction(123);
    }

}
