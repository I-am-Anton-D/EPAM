package utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Utils {

    /**
     * Getting array with random ints
     * @param low border for random
     * @param high border for random
     * @param limit count of members
     * @return random ints array
     * @throws IllegalArgumentException if low>=high or limit<=0
     */

    public static int[] getRandomIntsArray(int low, int high, int limit) throws IllegalArgumentException {
        if (low>=high || limit<=0) {
            throw new IllegalArgumentException();
        }
        Random random = new Random();
        return random.ints(low, high).limit(limit).toArray();
    }

    /**
     * Generate the sequence of ints using by lambda in parameter
     * @param lambda for generating sequence
     * @param limit count of members in sequence, must be > 0
     * @return array of ints
     * @throws IllegalArgumentException if limit <=0
     */

    public static int[] getIntsSequence(IntUnaryOperator lambda, int limit) throws IllegalArgumentException {
        if (limit<=0) throw new IllegalArgumentException();
        return IntStream.iterate(1, lambda).limit(limit).toArray();
    }

    /**
     * Generate the sequence of doubles using by lambda in parameter
     * @param lambda for generating sequence
     * @param limit count of members in sequence, must be > 0
     * @return array of ints
     * @throws IllegalArgumentException if limit <=0
     */

    public static double[] getDoubleSequence(DoubleUnaryOperator lambda, int limit) throws IllegalArgumentException {
        if (limit<=0) throw new IllegalArgumentException();
        return DoubleStream.iterate(1, lambda).limit(limit).toArray();
    }

    /**
     * Printing matrix with format elements
     */

    public static void printMatrix(int[][] array) {
        int max = Arrays.stream(array).flatMapToInt(Arrays::stream).max().orElse(0);
        int min = Arrays.stream(array).flatMapToInt(Arrays::stream).min().orElse(0);
        int digits = String.valueOf(Math.max(Math.abs(max), Math.abs(min))).length();
        if (min<0) digits++;

        for (int i = 0; i <array.length ; i++) {
            StringBuilder sb = new StringBuilder("[");
            for (int j = 0; j <array[0].length ; j++) {
                sb.append(String.format("%"+digits+"d",array[i][j]));
                if (j!=array[0].length-1) {
                    sb.append(", ");
                }
            }
            System.out.println(sb.append("]").toString());
        }
    }

    /**
     * Generate new random matrix
     * @param columns count
     * @param rows count
     * @param low bound for random
     * @param high bound for random
     * @return matrix
     */

    public static int[][] generateMatrix(int columns, int rows, int low, int high) {
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            matrix[i] = getRandomIntsArray(low,high,columns);
        }
        return matrix;
    }


    /**
     * Rotating the matrix. Change columns and rows
     */

    public static int[][] rotateMatrix(int[][] array) {
        int[][] result = new int[array[0].length][array.length];
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array.length; j++) {
                result[i][j] = array[j][i];
            }
        }
        return result;
    }


    /**
     * Sort elements of rows in matrix
     *
     * @param array source array
     * @param desc  if true - sorting in desc order, else in asc order
     */

    public static void sortElementsInRows(int[][] array, boolean desc) {
        for (int i = 0; i < array.length; i++) {
            int[] row = array[i];
            if (desc) {
                row = IntStream.of(row).boxed().sorted(Comparator.reverseOrder()).mapToInt(e -> e).toArray();
            } else {
                row = IntStream.of(row).sorted().toArray();
            }
            array[i] = row;
        }
    }

    /**
     * Swap elements in array
     */

    public static void swap(int[] elements, int a, int b) {
        int tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }

    /**
     * Just transform array to matrix
     */

    public static int[][] getMatrixFromArray(int[] elements, int n) {
        int[][] matrix = new int[n][n];
        for (int k = 0; k <n ; k++) {
            System.arraycopy(elements, n * k, matrix[k], 0, n);
        }
        return matrix;
    }


    /**
     * Just transform list to matrix
     */

    public static int[][] getMatrixFromList(List<Integer> elements, int n) {
        int[][] matrixFromList = new int[n][n];
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <n ; j++) {
                matrixFromList[i][j] = elements.get(n*i + j);
            }
        }
        return matrixFromList;
    }

    /**Getting distinct random ints
     *
     * @param low bound for random
     * @param high bound for random
     * @param limit must be < low + high
     * @return distinct random array
     * @throws IllegalArgumentException if parameters are wrong
     */

    public static int[] getDistinctRandomIntsArray(int low, int high, int limit) throws IllegalArgumentException {
        if (low>=high || limit<=0) {
            throw new IllegalArgumentException();
        }
        Random random = new Random();
        return random.ints(low, high).distinct().limit(limit).toArray();
    }

    /**
     * Recursive GCD
     */

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * Getting LCM using known expression
     */
    public static int lcm(int a, int b) {
        return a / gcd(a, b) * b;

    }

    /**
     * Binary search, but searching not equal element, searching index for insertion
     *
     * @param array   source
     * @param from    start index
     * @param to      end index
     * @param element element to insert
     * @return index for insertion. array[@return-1] <= element <= array[@return+1]
     */
    public static int binarySearch(int[] array, int from, int to, int element) {
        int middle = (from + to) / 2;
        int comparison = Integer.compare(element, array[middle]);
        return middle == 0 ? middle : array[middle - 1] <= element && array[middle] >= element ? middle
            : comparison > 0 ? binarySearch(array, middle + 1, to, element)
                : binarySearch(array, from, middle - 1, element);
    }


}
