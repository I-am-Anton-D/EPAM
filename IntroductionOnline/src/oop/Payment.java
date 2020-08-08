package oop;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Task 2. Payment class with inner class
 */

public class Payment {
    static class Good {
        private String name;
        private int price;

        public Good(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    private final Map<Good, Integer> purchases = new HashMap<>();

    public Payment() {
    }

    public void addGood(Good good, int quantity) {
        purchases.put(good,quantity);
    }

    public void printPayment() {
        int sum = 0;
        System.out.printf("%-10s %3s %5s%n","Name", "QTY", "Price");
        System.out.println("---------------------");
        for (Entry<Good, Integer> pair: purchases.entrySet()) {
            System.out.printf("%-10s %3d %5d%n",pair.getKey().getName(),pair.getValue(),pair.getKey().getPrice());
            sum+=pair.getKey().getPrice();
        }
        System.out.println("---------------------");
        System.out.println("Sum = " + sum);
    }
}
