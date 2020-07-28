package algorithmization;

import static utils.Utils.*;

import java.util.Arrays;
import java.util.stream.IntStream;
import javax.sound.midi.Soundbank;

public class SortArrays {

    /**
     * Task 1. Insert in array second array between k and k + 1 element
     *
     * @param arrReceiver array receiver
     * @param array       inserting array
     * @param k           specific K
     */

    public static void union2Array(int[] arrReceiver, int[] array, int k) {
        System.out.println("-----------------------------------------------");
        System.out.println("[Task 1] Union 2 arrays");
        System.out.println("Array receiver: " + Arrays.toString(arrReceiver));
        System.out.println("Array to insert: " + Arrays.toString(array));
        System.out.println("Specific K = " + k);

        int length = array.length;
        for (int i = k + 1; i < length + k + 1; i++) {
            arrReceiver[i + length] = arrReceiver[i];
            arrReceiver[i] = array[i - k - 1];
        }
        System.out.println("Union of arrays: " + Arrays.toString(arrReceiver));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 2. Union 2 sequence in asc order
     */

    public static void union2Sequence(int[] sequence1, int[] sequence2) {
        System.out.println("[Task 2] Union 2 sequence");
        System.out.println("Sequence 1: " + Arrays.toString(sequence1));
        System.out.println("Sequence 2: " + Arrays.toString(sequence2));
        System.out.println("Union of sequence: " + Arrays
            .toString(IntStream.concat(IntStream.of(sequence1), IntStream.of(sequence2)).sorted().toArray()));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 3. Selection sort
     */

    public static void sortSelection(int[] array) {
        System.out.println("[Task 3] Selection sort");
        System.out.println("Array to sort: " + Arrays.toString(array));

        for (int i = 0; i < array.length; i++) {
            int pos = i;
            int max = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] > max) {
                    pos = j;
                    max = array[j];
                }
            }
            array[pos] = array[i];
            array[i] = max;
        }
        System.out.println("Sorted array: " + Arrays.toString(array));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 4. Swap sort
     */

    public static void sortSwap(int[] array) {
        System.out.println("[Task 4] Swap sort");
        System.out.println("Array to sort: " + Arrays.toString(array));
        boolean needIteration = true;
        int countOfSwap = 0;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i - 1);
                    needIteration = true;
                    countOfSwap++;
                }
            }
        }
        System.out.println("Sorted array: " + Arrays.toString(array));
        System.out.println("Count of swap = " + countOfSwap);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 5. Classic sort by inserting
     */

    public static void sortInsert(int[] array) {
        System.out.println("[Task 5] Insertion sort");
        System.out.println("Array to sort: " + Arrays.toString(array));
        for (int left = 0; left < array.length; left++) {
            int value = array[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    break;
                }
            }
            array[i + 1] = value;
        }
        System.out.println("Sorted array: " + Arrays.toString(array));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 6. Shell sort
     */

    public static void sortShell(int[] array) {
        System.out.println("[Task 6] Shell sort");
        System.out.println("Array to sort: " + Arrays.toString(array));

        int gap = array.length / 2;
        while (gap >= 1) {
            for (int right = 0; right < array.length; right++) {
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (array[c] > array[c + gap]) {
                        swap(array, c, c + gap);
                    }
                }
            }
            gap = gap / 2;
        }
        System.out.println("Sorted array: " + Arrays.toString(array));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 7. Find indexes for inserting
     */

    public static void union2SequenceT7(int[] sequence1, int[] sequence2) {
        System.out.println("[Task 7] Indexes for inserting");
        System.out.println("Sequence 1: " + Arrays.toString(sequence1));
        System.out.println("Sequence 2: " + Arrays.toString(sequence2));
        StringBuilder sb = new StringBuilder("Indexes for insert and count of inserting: ");

        int k = 0;
        for (int i = 0; i < sequence1.length; i++) {
            if (k < sequence2.length && sequence1[i] > sequence2[k]) {
                sb.append(" ").append(i);
                k++;
                i--;
            }
        }
        System.out.println(sb.toString());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 8. Sort fraction
     */

    public static void sortFraction(String... fractions) throws IllegalArgumentException {
        System.out.println("[Task 8] Sort fractions");
        int length = fractions.length;
        if (length<2) throw new IllegalArgumentException();

        int[] numerator = new int[length];
        int[] denominator = new int[length];
        StringBuilder sb = new StringBuilder("Input fraction: ");

        for (int i = 0; i < length; i++) {
            numerator[i] = Integer.parseInt(fractions[i].split("/")[0]);
            denominator[i] = Integer.parseInt(fractions[i].split("/")[1]);
            sb.append(fractions[i]).append(" ");
        }
        System.out.println(sb.toString());

        int lcm = lcm(denominator[0], denominator[1]);
        for (int i = 2; i < length; i++) {
            lcm = lcm(lcm, denominator[i]);
        }
        System.out.println("Common denominator = " + lcm);

        sb.setLength(0);
        for (int i = 0; i < length; i++) {
            numerator[i] = numerator[i] * (lcm / denominator[i]);
            sb.append(numerator[i]).append("/").append(lcm).append(" ");
        }
        System.out.println("Fractions with common denominator: " + sb.toString());

        Arrays.sort(numerator);
        sb.setLength(0);
        for (int i = 0; i < length; i++) {
            sb.append(numerator[i]).append("/").append(lcm).append(" ");
        }

        System.out.println("Sorted fractions: " + sb.toString());
        System.out.println("-----------------------------------------------");
    }

    public static void main(String[] args) {
        union2Array(getRandomIntsArray(0, 15, 15), getRandomIntsArray(1, 5, 5), 3);
        union2Sequence(getIntsSequence(n -> n + 2, 10), getIntsSequence(n -> n + 1, 10));
        sortSelection(getRandomIntsArray(0, 15, 10));
        sortSwap(getRandomIntsArray(0, 15, 10));
        sortInsert(getRandomIntsArray(1, 25, 15));
        sortShell(getRandomIntsArray(1, 25, 15));
        union2SequenceT7(getIntsSequence(n -> n + 2, 10), getIntsSequence(n -> n + 1, 10));
        sortFraction("7/8", "2/3", "1/2", "3/4");
    }

}
