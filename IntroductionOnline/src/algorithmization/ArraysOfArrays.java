package algorithmization;

import static utils.Utils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
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
            if (array[i][0] > array[i][array[0].length - 1]) {
                System.out.printf("Column with index = %d: %s%n", i, Arrays.toString(array[i]));
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

    public static void generateMatrixByPatternT4(int n) throws IllegalArgumentException {
        if (n % 2 != 0 || n <= 1) {
            throw new IllegalArgumentException();
        }
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            result[i] = ((i % 2 == 0) ? IntStream.rangeClosed(1, n)
                : IntStream.rangeClosed(-n, -1).map(Math::abs)).toArray();
        }
        System.out.println("[Task 4] Generated matrix: ");
        printMatrix(result);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 5. Generate matrix by pattern
     */

    public static void generateMatrixByPatternT5(int n) throws IllegalArgumentException {
        if (n % 2 != 0 || n <= 1) {
            throw new IllegalArgumentException();
        }
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
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

    public static void generateMatrixByPatternT6(int n) throws IllegalArgumentException {
        if (n % 2 != 0 || n <= 1) {
            throw new IllegalArgumentException();
        }
        int[][] result = new int[n][n];
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i; j++) {
                result[i][j] = 1;
            }
        }
        for (int i = n - 1; i >= n / 2; i--) {
            result[i] = result[n - i - 1];
        }

        System.out.println("[Task 6] Generated matrix: ");
        printMatrix(result);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 7. Generate matrix by pattern and find count of positives elements
     */

    public static void generateMatrixByPatternT7(int n) {
        if (n % 2 != 0 || n <= 1) {
            throw new IllegalArgumentException();
        }
        double dim = n * n;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = Math.round(Math.sin((i * i - j * j) / dim) * 100) / 100D;
            }
        }
        System.out.println("[Task 7] Generated matrix: ");
        for (double[] doubles : result) {
            StringBuilder sb = new StringBuilder("[");
            for (int j = 0; j < doubles.length; j++) {
                sb.append(String.format("%5.2f", doubles[j]));
                if (j != doubles.length - 1) {
                    sb.append(", ");
                }
            }
            System.out.println(sb.append("]").toString());
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
            array[0].length - 1);
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
                    if (m < 0 || n < 0 || m > array[0].length - 1 || n > array[0].length - 1 || m == n) {
                        throw new NumberFormatException();
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Wrong format number. Please, repeat: ");
            }
        }

        for (int i = 0; i < array.length; i++) {
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
        for (int i = 0; i < array.length; i++) {
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
        for (int i = 0; i < array.length; i++) {
            if (array[i][i] >= 0) {
                sb.append(array[i][i]).append(" ");
            }
        }
        System.out.println(sb.toString());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 11. Generate matrix by pattern
     */

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

    public static void generateMatrixByPatternT14(int m, int n) {
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            ArrayList<Integer> intsList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                intsList.add(0);
            }
            for (int j = 0; j < i; j++) {
                intsList.set(j, 1);
            }
            Collections.shuffle(intsList);
            result[i] = intsList.stream().mapToInt(x -> x).toArray();
        }

        System.out.println("[Task 14] Generated matrix: ");
        result = rotateMatrix(result);
        printMatrix(result);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 15. Find max element in matrix and change elements with odd values by max
     */

    public static void findMaxAndChangeOdd(int[][] array) {
        System.out.println("[Task 15] Find max element and change odd");
        System.out.println("Original matrix:");
        printMatrix(array);
        int max = Arrays.stream(array).flatMapToInt(Arrays::stream).max().orElse(0);
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array[0].length; j++) {
                if (array[i][j] % 2 != 0) {
                    array[i][j] = max;
                }
            }
        }
        System.out.println("Modified matrix: ");
        printMatrix(array);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 16. Magic Square
     *
     * This task is known. There are different algorithms for different n, but there is no one common solution,
     * so I used to iterate through all possible permutations. I used Heap's algorithm to find all permutations,
     * then checked the resulting matrix. This algorithm is only suitable for n=3. for large n, you can use the
     * random method described below
     * @param n size of square magic matrix
     */

    public static void magicSquare(int n) {
        int[] elements = IntStream.rangeClosed(1, n * n).toArray();
        int[] indexes = new int[n * n];
        Arrays.fill(indexes, 0);
        int i = 0;
        while (i < n * n) {
            if (indexes[i] < i) {
                swap(elements, i % 2 == 0 ? 0 : indexes[i], i);
                if (check(elements)) {
                    System.out.printf("[Task 16] Magic square for n = %d:%n",n);
                    printMatrix(getMatrixFromArray(elements, n));
                    break;
                }
                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }
    }

    /**
     * Checking the resulting matrix
     */

    public static boolean check(int[] array) {
        int n = (int) Math.sqrt(array.length);
        int checkSum = n * (n * n + 1) / 2;
        int curSum, curSumColumns, curDiagonal = 0;
        int[][] matrix = getMatrixFromArray(array, n);

        for (int i = 0; i < n; i++) {
            curSum = 0;
            curSumColumns = 0;
            for (int j = 0; j < n; j++) {
                curSum += matrix[i][j];
                curSumColumns += matrix[j][i];

            }
            if (checkSum != curSum || curSumColumns != checkSum) {
                return false;
            }
            curDiagonal += matrix[i][i];
        }

        if (checkSum != curDiagonal) {
            return false;
        }

        curDiagonal = 0;
        for (int i = n - 1; i >= 0; i--) {
            curDiagonal += matrix[n - 1 - i][i];
        }
        return checkSum == curDiagonal;
    }

    /**
     * Task 16... continue....
     *
     * For n>3, you can use the random method. We can just use Collections.shuffle() to get a new permutation
     * and check it further. This method gives a result for n = 4 and n = 5 in a reasonable period of time and
     * depends on luck. For n = 4 - about 10-20 minutes, for n = 5 - about a night. For n = 3 same fast like full
     * search
     *
     * Try your luck =)
     *
     * @param n size of square magic matrix
     */

    public static void luckyStyle(int n) {
        List<Integer> elements = IntStream.rangeClosed(1, n * n).boxed().collect(Collectors.toList());
        long c = 0;
        while (c < Long.MAX_VALUE) {
            Collections.shuffle(elements);
            if (check(elements.stream().mapToInt(x -> x).toArray())) {
                System.out.printf("[Task 16] Magic square (lucky style) for n = %d:%n", n);
                printMatrix(getMatrixFromList(elements, n));
                break;
            }
            c++;
        }
    }

    public static void main(String[] args) {
        oddColumnsWithFilter(generateMatrix(N + 2, N + 3, 0, N));
        outputDiagonal(generateMatrix(N, N, 0, N));
        getRowKColumnP(generateMatrix(N, N, 0, N), 3, 4);
        generateMatrixByPatternT4(6);
        generateMatrixByPatternT5(8);
        generateMatrixByPatternT6(12);
        generateMatrixByPatternT7(6);
        //Input from console
        //swapColumns(generateMatrix(N+5,N,0,N));
        sumElementsInColumns(generateMatrix(N, N, 0, N));
        positiveElementsOfDiagonal(generateMatrix(N, N, -N, N));
        generateMatrixByPatternT11();
        sortMatrixRows(generateMatrix(N, N, 0, N), false);
        sortMatrixColumns(generateMatrix(N, N, 0, N), true);
        generateMatrixByPatternT14(8, 12);
        findMaxAndChangeOdd(generateMatrix(N, N, 0, N));
        magicSquare(3);
        luckyStyle(5);
    }
}
