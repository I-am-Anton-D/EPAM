package oop.flowers;

import java.util.Scanner;
import oop.flowers.flower.FlowerFactory;
import oop.flowers.flower.TypeFlower;

import oop.flowers.packing.PackingFactory;
import oop.flowers.packing.TypePacking;

/**
 * Task 5. Flowers composition
 */

public class Main {

    private static final Composition composition = new Composition();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            greeting(composition);
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            if (!input.contains(" ")) {
                System.out.println("Wrong input");
            } else if (input.equals("no packing")) {
                composition.removePacking();
                System.out.println("Packing removed");
            } else if (input.startsWith("add")) {
                System.out.println(addFlower(input.split(" ")[1]));
            } else if (input.startsWith("remove")) {
                System.out.println(removeFlower(input.split(" ")[1]));
            } else if (input.startsWith("pack")) {
                System.out.println(addPacking(input.split(" ")[1]));
            } else {
                System.out.println("Unknown command");
            }

        }
    }

    private static String addPacking(String s) {
        s = s.toUpperCase();
        for (int i = 0; i < TypePacking.values().length; i++) {
            if (TypePacking.values()[i].toString().equals(s)) {
                composition.addPacking(PackingFactory.getPacking(TypePacking.values()[i]));
                return "Packing added";
            }
        }
        return "Wrong input";
    }

    private static String removeFlower(String s) {
        try {
            int i = Integer.parseInt(s);
            if (composition.removeFlower(i)) {
                return "Flower removed";
            } else {
                return "Wrong input";
            }

        } catch (NumberFormatException e) {
            return "Wrong input";
        }
    }

    private static String addFlower(String s) {
        s = s.toUpperCase();
        for (int i = 0; i < TypeFlower.values().length; i++) {
            if (TypeFlower.values()[i].toString().equals(s)) {
                composition.addFlower(FlowerFactory.getFlower(TypeFlower.values()[i]));
                return "Flower added";
            }
        }
        return "Wrong input";
    }

    public static void greeting(Composition composition) {
        System.out.println("------------------------------------------");
        System.out.println("Your composition: ");
        System.out.println(composition.toString());
        System.out.println();
        System.out.print("You can add flower: ");
        for (int i = 0; i < TypeFlower.values().length; i++) {
            System.out.print(TypeFlower.values()[i]);
            if (i + 1 != TypeFlower.values().length) {
                System.out.print(", ");
            }
        }

        System.out.print("\nYou can change packing: ");
        for (int i = 0; i < TypePacking.values().length; i++) {
            System.out.print(TypePacking.values()[i]);
            if (i + 1 != TypePacking.values().length) {
                System.out.print(", ");
            }
        }

        System.out.println("\n\nType 'add XXXX', where XXXX name of flower");
        System.out.println("Type 'pack XXXX', where XXXX name of packing");

        if (composition.size() != 0) {
            System.out.println("You can remove flower by index (type 'remove XX', where XX index of flower)");
        }

        if (!composition.noPacking()) {
            System.out.println("You can remove packing (type 'no packing')");
        }

        System.out.println("For exit just press Enter");
    }

}
