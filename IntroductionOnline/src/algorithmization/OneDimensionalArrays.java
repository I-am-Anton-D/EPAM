package algorithmization;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;


public class OneDimensionalArrays {

    final static int N = 10; //size of arrays

    /**
     * Task 1. Find the sum of elements, that dividing by K
     */

    public static void sumElementsDivideByNumber(int[] array, int k) {
        System.out.println("-----------------------------------------------");
        System.out.println("[Task 1] Sum of elements dividing by K");
        System.out.println("Original array: " + Arrays.toString(array));
        System.out.println("Specific K = " + k);
        System.out.println("Sum = " + IntStream.of(array).filter(e -> e % k == 0).sum());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 2. Change the all elements by Z, if element value > Z;
     */

    public static void swapValuesOnZ(int[] array, int z) {
        System.out.println("[Task 2] Change values grather Z");
        System.out.println("Original sequence : " + Arrays.toString(array));
        System.out.println("Specific Z = " + z);
        int[] result = IntStream.of(array).map(e -> e = Math.min(e, z)).toArray();
        System.out.println(
            "Changing array: " + Arrays.toString(result));
        System.out.println("Count of changes: " + IntStream.of(result).filter(e -> e == z).count());
        System.out.println("-----------------------------------------------");
    }


    /**
     * Task 3. Count of positive, negative ant zeroes elements;
     */

    public static void countOfPositiveNegativeZero(int[] array) {
        System.out.println("[Task 3] Positive, negative, zeroes");
        System.out.println("Original array: " + Arrays.toString(array));
        int countPositive = (int) IntStream.of(array).filter(e -> e > 0).count();
        int countNegative = (int) IntStream.of(array).filter(e -> e < 0).count();
        int countZero = (int) IntStream.of(array).filter(e -> e == 0).count();
        System.out.printf("Count of positive elements = %d, negative elements = %d, zeroes = %d%n", countPositive,
            countNegative, countZero);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 4. Swap max and min elements in array
     */

    public static void swapMaxMin(int[] array) {
        System.out.println("[Task 4] Swap max and min elements in array");
        System.out.println("Original array: " + Arrays.toString(array));

        int max = IntStream.of(array).max().orElse(0);
        int min = IntStream.of(array).min().orElse(0);
        System.out.printf("Max element = %d, Min element = %d%n", max, min);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == min) {
                array[i] = max;
                continue;
            }
            if (array[i] == max) {
                array[i] = min;
            }
        }
        System.out.println("Modified array: " + Arrays.toString(array));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 5. Filter elements. Value of element must be grather his index in sequence
     */

    public static void filterElements(int[] array) {
        System.out.println("[Task 5] Output elements that values > index: ");
        System.out.println("Original sequence : " + Arrays.toString(array));
        System.out.print("Filter elements:");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (array[i] > i + 1) {
                sb.append(" ").append(array[i]);
            }
        }
        System.out.println(sb.toString());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 6. Sum of elements with prime index
     *
     * But we can use the simple list of prime numbers. Over 500 numbers was founded. Not enough?
     */

    public static void sumElementsWithPrimeIndex(double[] array) {
        System.out.println("[Task 6] Sum with prime index: ");
        System.out.println("Original array: " + Arrays.toString(array));
        double sum = 0;
        StringBuilder sb = new StringBuilder("Prime index are:");
        for (int n = 3; n <= array.length; n++) {
            boolean isPrime = true;
            for (int i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                sb.append(" ").append(n);
                sum += array[n - 1];
            }
        }
        System.out.println(sb.toString());
        System.out.println("Sum of elements with prime index = " + sum);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 7. Generating new sequence from source and find max element in them
     */

    public static void maxFromNewSequence(int[] array) {
        System.out.println("[Task 7] Find max from new sequence: ");
        System.out.println("Original sequence: " + Arrays.toString(array));
        int[] newSequence = new int[array.length - 1];
        for (int i = 1; i < array.length; i++) {
            newSequence[i - 1] = array[i - 1] + array[i];
        }
        System.out.println("New generating sequence: " + Arrays.toString(newSequence));
        System.out.println("Max element = " + IntStream.of(newSequence).max().orElse(0));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 8. Generate new sequence - drop min elements from source sequence.
     * <p>
     * Remark: It's will be more interesting, if in sequence more than one same min elements
     */

    public static void dropMinElements(int[] array) {
        System.out.println("[Task 8] Drop min sequence: ");
        System.out.println("Original sequence: " + Arrays.toString(array));
        int min = IntStream.of(array).min().orElse(0);
        System.out.println("Sequence without min element: " + Arrays
            .toString(IntStream.of(array).filter(e -> e != min).toArray()));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 9. Calculate the frequency of elements in array. If frequency same - get element with min value
     */

    public static void findMaxFrequency(int[] array) {
        System.out.println("[Task 9] Calculate max frequency of elements: ");
        System.out.println("Original sequence: " + Arrays.toString(array));
        List<Integer> list = IntStream.of(array).boxed().collect(Collectors.toList());
        HashMap<Integer, Integer> mapFreq = new HashMap<>();
        for (int value : array) {
            mapFreq.putIfAbsent(value, Collections.frequency(list, value));
        }
        System.out.print("Frequency of elements:");

        int maxFreq = 0;
        int minElement = Integer.MAX_VALUE;
        for (Entry<Integer, Integer> pair : mapFreq.entrySet()) {
            System.out.print(" " + pair.getKey() + " - " + pair.getValue() + ";");
            if (pair.getValue() > maxFreq) {
                maxFreq = pair.getValue();
                minElement = pair.getKey();
                continue;
            }
            if (pair.getValue() == maxFreq && pair.getKey() < minElement) {
                minElement = pair.getKey();
            }
        }

        System.out.printf("%nMax frequency = %d, min element = %d%n", maxFreq, minElement);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 10. Drop even elements in array. Free places replace by 0
     */

    public static void dropEvenElements(int[] array) {
        System.out.println("[Task 10] Drop even elements ");
        System.out.println("Original sequence: " + Arrays.toString(array));
        int c = array.length % 2 == 0 ? array.length / 2 : array.length / 2 + 1;
        for (int i = 1; i < array.length; i++) {
            if (i < c) {
                System.arraycopy(array, i + 1, array, i, array.length - 1 - i);
            } else {
                array[i] = 0;
            }
        }
        System.out.println("Modified array: " + Arrays.toString(array));
        System.out.println("-----------------------------------------------");
    }

    public static void main(String[] args) {
        Random random = new Random();
        // Inputs arrays
        int[] randomInts = random.ints(0, 10).limit(N).toArray();
        int[] sequence = IntStream.iterate(1, n -> n + 2).limit(N).toArray();
        int[] negativePositivesValues = random.ints(-5, 5).limit(N).toArray();
        int[] bigRandomInts = random.ints(0, 30).distinct().limit(N).toArray();
        double[] doubleSequence = DoubleStream.iterate(1, d -> d + 1.25).limit(10).toArray();
        int[] randomIntsLongList = random.ints(0, 5).limit(2 * N).toArray();

        sumElementsDivideByNumber(randomInts, 3);
        swapValuesOnZ(sequence, 16);
        countOfPositiveNegativeZero(negativePositivesValues);
        swapMaxMin(bigRandomInts);
        filterElements(sequence);
        sumElementsWithPrimeIndex(doubleSequence);
        maxFromNewSequence(sequence);
        dropMinElements(sequence);
        findMaxFrequency(randomIntsLongList);
        dropEvenElements(sequence);
    }
}
