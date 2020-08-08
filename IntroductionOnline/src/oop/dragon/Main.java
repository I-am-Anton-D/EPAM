package oop.dragon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 * Task 4. Dragon and treasuries
 */

public class Main {

    private static final DragonStorehouse store = new DragonStorehouse();

    /**
     * Generate store of dragon, output greeting (basic information about store) and wait for input: user can look
     * some treasury by id or can take some treasuries by specific sum.
     *
     * Console input by format
     */

    public static void main(String[] args) {
        generateStore();
        greeting();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            if (!input.startsWith("sum")) {
                try {
                    int i = Integer.parseInt(input);
                    printTreasury(i);
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect input");
                }

            }

            if (input.startsWith("sum")) {
                try {
                    int sum = Integer.parseInt(input.split(" ")[1]);
                    getTreasuriesOnSum(sum);
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect input");
                }
            }
        }
    }

    private static void getTreasuriesOnSum(int sum) {
        if (sum > store.totalPrice() || sum < 0) {
            System.out.println("Incorrect sum");
            return;
        }

        ArrayList<Treasure> all = store.getAllTreasuries();
        all.sort(Comparator.comparing(Treasure::getPrice));
        ArrayList<Treasure> result = new ArrayList<>();
        int sumList = 0;

        for (int i = 0; sumList <= sum; i++) {
            result.add(all.get(i));
            sumList += all.get(i).getPrice();
        }

        System.out.println("List ot treasuries on sum = " + sum);
        result.forEach(System.out::println);

    }

    private static void printTreasury(int i) {
        if (i > store.getStore().size() - 1 || i < 0) {
            System.out.println("Incorrect treasury index");
            return;
        }
        Treasury treasury = store.getTreasuryById(i);
        System.out.println("Treasury with index " + i);
        treasury.print();
        System.out.printf("Most expensive is %s with price %d%n", treasury.getMaxPrice().getName(),
            treasury.getMaxPrice().getPrice());
    }

    public static void generateStore() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Treasury treasury = new Treasury(i);
            for (int j = 0; j < 1 + random.nextInt(10); j++) {
                treasury.addTreasure(new Treasure("Treasure" + i * j, 1 + random.nextInt(100)));
            }
            store.addTreasury(treasury);
        }
    }

    public static void greeting() {
        System.out.printf("Dragon have %d treasury%n", store.getStore().size());
        System.out.println("Total treasuries in all treasury = " + store.totalCountOfTreasuries());
        System.out.println("Total treasuries price = " + store.totalPrice());
        System.out.printf("Most expensive is %s with price %d%n", store.getExpensive().getName(),
            store.getExpensive().getPrice());
        System.out.println("You can:");
        System.out.println("1. Look treasury. Enter id from 0 to " + store.getStore().size());
        System.out.println("2. Get treasuries by sum. Enter in format 'sum XXXX', where XXXX specific sum");
        System.out.println("3. Exit. Just press Enter");
    }


}
