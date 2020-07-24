package algorithmization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ArraysOfArrays {

    final static int N = 10; //size of arrays
    final static double EPSILON = 1E-12;  // Epsilon of calculations

    /**
     * Task 1. Output odd columns of matrix if first element > last element
     */

    public static void oddColumnsWithFilter(int[][] array) {
        System.out.println("-----------------------------------------------");
        System.out.println("[Task 1] Output odd columns with filter");
        System.out.println("Original matrix:");
        printMatrix(array);
        array = rotateMatrix(array);
        System.out.println("Filtered columns: ");
        for (int i = 1; i < array.length; i = i + 2) {
            if (array[i][0] > array[i][N - 1]) {
                System.out.printf("Column number = %d: %s%n", i, Arrays.toString(array[i]));
            }
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 2. Output diagonal of matrix
     */

    public static void outputDiagonal(int[][] array) {
        System.out.println("[Task 2] Output diagonal of matrix");
        System.out.println("Original matrix:");
        printMatrix(array);
        StringBuilder sb = new StringBuilder("Elements in diagonal: ");
        for (int i = 0; i < N; i++) {
            sb.append(array[i][i]).append(" ");
        }
        System.out.println(sb.toString());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 3. Get the row at index K and column at index P
     *
     * @param array source matrix
     * @param k     index of row
     * @param p     index of column
     */

    public static void getRowKColumnP(int[][] array, int k, int p) {
        System.out.println("[Task 3] Row K and column P");
        System.out.println("Original matrix:");
        printMatrix(array);
        System.out.printf("Row with index %d: %s%n", k, Arrays.toString(array[k]));
        System.out.printf("Column with index %d: %s%n", p, Arrays.toString(rotateMatrix(array)[p]));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 4. Generate matrix by pattern
     */

    public static void generateMatrixByPatternT4() {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i] = ((i % 2 == 0) ? IntStream.rangeClosed(1, N)
                : IntStream.rangeClosed(-N, -1).map(Math::abs)).toArray();
        }
        System.out.println("[Task 4] Generated matrix: ");
        printMatrix(result);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 5. Generate matrix by pattern
     */

    public static void generateMatrixByPatternT5() {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - i; j++) {
                result[i][j] = i + 1;
            }
        }
        System.out.println("[Task 5] Generated matrix: ");
        printMatrix(result);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 6. Generate matrix by pattern
     */

    public static void generateMatrixByPatternT6() {
        int[][] result = new int[N][N];
        for (int i = 0; i < N / 2; i++) {
            for (int j = i; j < N - i; j++) {
                result[i][j] = 1;
            }
        }
        for (int i = N - 1; i >= N / 2; i--) {
            result[i] = result[N - i - 1];
        }

        System.out.println("[Task 6] Generated matrix: ");
        printMatrix(result);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 7. Generate matrix by pattern and find count of positives elements
     */

    public static void generateMatrixByPatternT7() {
        double dim = N * N;
        double[][] result = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = Math.round(Math.sin((i * i - j * j) / dim) * 100) / 100D;
            }
        }
        System.out.println("[Task 7] Generated matrix: ");
        for (double[] doubles : result) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println("Count of positive elements: " +
            Arrays.stream(result).flatMapToDouble(Arrays::stream).filter(e -> e > EPSILON).count());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 8. Swap columns. Indexes of columns entering by console;
     */

    public static void swapColumns(int[][] array) {
        System.out.println("[Task 8] Swap columns");
        System.out.println("Original matrix:");
        printMatrix(array);
        System.out.printf(
            "Enter indexes of column (two different positive integers separating by space and less than or equal to %d): ",
            N - 1);
        Scanner scanner = new Scanner(System.in);
        int m, n;
        while (true) {
            try {
                String[] inputs = scanner.nextLine().split(" ");
                if (inputs.length != 2) {
                    throw new NumberFormatException();
                } else {
                    m = Integer.parseInt(inputs[0]);
                    n = Integer.parseInt(inputs[1]);
                    if (m < 0 || n < 0 || m > N - 1 || n > N - 1 || m == n) {
                        throw new NumberFormatException();
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Wrong format number. Please, repeat: ");
            }
        }

        for (int i = 0; i < N; i++) {
            int tmp = array[i][m];
            array[i][m] = array[i][n];
            array[i][n] = tmp;
        }

        System.out.println("Modified matrix");
        printMatrix(array);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 9. Find sum of elements by column and find index of column with max sum
     */

    public static void sumElementsInColumns(int[][] array) {
        System.out.println("[Task 9] Sum in column");
        System.out.println("Original matrix:");
        printMatrix(array);
        int maxSum = 0;
        int indexOfMax = -1;
        array = rotateMatrix(array);
        for (int i = 0; i < N; i++) {
            int curMax = IntStream.of(array[i]).sum();
            System.out.printf("Sum in column %d = %d%n", i, curMax);
            if (curMax > maxSum) {
                maxSum = curMax;
                indexOfMax = i;
            }
        }
        System.out.printf("Column %d have max sum = %d%n", indexOfMax, maxSum);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 10. Find positive elements in diagonal of matrix
     */

    public static void positiveElementsOfDiagonal(int[][] array) {
        System.out.println("[Task 10] Positive elements in diagonal");
        System.out.println("Original matrix:");
        printMatrix(array);
        StringBuilder sb = new StringBuilder("Positive elements in diagonal: ");
        for (int i = 0; i < N; i++) {
            if (array[i][i] >= 0) {
                sb.append(array[i][i]).append(" ");
            }
        }
        System.out.println(sb.toString());
        System.out.println("-----------------------------------------------");
    }


    public static void generateMatrixByPatternT11() {
        Random random = new Random();
        int[][] result = new int[N][2 * N];
        for (int i = 0; i < N; i++) {
            result[i] = random.ints(0, 15).limit(2 * N).toArray();

        }
        System.out.println("[Task 11] Generated matrix: ");
        printMatrix(result);

        StringBuilder sb = new StringBuilder("Index of rows with elements with value (5) 3 or more times:");

        for (int i = 0; i < result.length; i++) {
            int count = (int) IntStream.of(result[i]).filter(e -> e == 5).count();
            if (count >= 3) {
                sb.append(" ").append(i).append(" (").append(count).append(" times), ");
            }
        }
        System.out.println(sb.toString());
        System.out.println("-----------------------------------------------");
    }


    /**
     * Task 12. Sort rows elements in matrix
     *
     * @param array source array
     * @param desc  if true - sorting in desc order, else in asc order
     */

    public static void sortMatrixRows(int[][] array, boolean desc) {
        System.out.println("[Task 12] Sort matrix rows");
        System.out.println("Original matrix:");
        printMatrix(array);
        sortElementsInRows(array, desc);
        System.out.println("Sorted matrix:");
        printMatrix(array);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 13. Sort matrix columns
     * <p>
     * Rotating the matrix and sort rows ant then rotate again
     *
     * @param array source array
     * @param desc  if true - sorting in desc order, else in asc order
     */

    public static void sortMatrixColumns(int[][] array, boolean desc) {
        System.out.println("[Task 13] Sort matrix columns");
        System.out.println("Original matrix:");
        printMatrix(array);
        array = rotateMatrix(array);
        sortElementsInRows(array, desc);
        array = rotateMatrix(array);
        System.out.println("Sorted matrix:");
        printMatrix(array);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 14. Generate matrix by pattern
     */

    public static void generateMatrixByPatternT14() {
         int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> intsList = new ArrayList<>();
            for (int j = 0; j <N ; j++) {
                intsList.add(0);
            }
            for (int j = 0; j <i; j++) {
                intsList.set(j,1);
            }
            Collections.shuffle(intsList);
            result[i] = intsList.stream().mapToInt(x->x).toArray();
        }

        System.out.println("[Task 14] Generated matrix: ");
        result = rotateMatrix(result);
        printMatrix(result);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 15. Fina max elements in matrix and chang elements with odd values by max
     */

    public static void findMaxAndChangeOdd(int[][] array){
        System.out.println("[Task 15] Find max element and change odd");
        System.out.println("Original matrix:");
        printMatrix(array);
        int max = Arrays.stream(array).flatMapToInt(Arrays::stream).max().orElse(0);
        for (int i = 0; i <array.length ; i++) {
            for (int j = 1; j <array[0].length ; j++) {
                if (array[i][j]%2!=0) {
                    array[i][j] = max;
                }
            }
        }
        System.out.println("Modified matrix: ");
        printMatrix(array);
        System.out.println("-----------------------------------------------");
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
     * Generating Matrix with positive and negative values
     */

    public static int[][] generateNegPosMatrix() {
        Random random = new Random();
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            matrix[i] = random.ints(-10, 10).limit(N).toArray();
        }
        return matrix;
    }

    /**
     * Just generate random matrix
     */

    public static int[][] generateMatrix() {
        Random random = new Random();
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            matrix[i] = random.ints(0, 10).limit(N).toArray();
        }

        return matrix;
    }

    /**
     * Print matrix
     */

    public static void printMatrix(int[][] array) {
        for (int[] ints : array) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * Rotating the matrix to change columns and rows
     */

    public static int[][] rotateMatrix(int[][] array) {
        int[][] result = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                result[i][j] = array[j][i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        oddColumnsWithFilter(generateMatrix());
        outputDiagonal(generateMatrix());
        getRowKColumnP(generateMatrix(), 3, 4);
        generateMatrixByPatternT4();
        generateMatrixByPatternT5();
        generateMatrixByPatternT6();
        generateMatrixByPatternT7();
        //Input from console
        //swapColumns(generateMatrix());
        sumElementsInColumns(generateMatrix());
        positiveElementsOfDiagonal(generateNegPosMatrix());
        generateMatrixByPatternT11();
        sortMatrixRows(generateMatrix(), false);
        sortMatrixColumns(generateMatrix(), true);
        generateMatrixByPatternT14();
        findMaxAndChangeOdd(generateMatrix());
    }
}
