package strings;

import java.util.Arrays;

public class StringLikeArray {

    /**
     * Task 1. Transform camel case to snake case
     */

    public static void camelCaseToSnakeCase(String[] strings) {
        System.out.println("-----------------------------------------------");
        System.out.println("[Task 1] Camel and snake cases");
        System.out.println("Input strings: " + Arrays.toString(strings));
        String[] result = new String[strings.length];
        for (int i = 0; i <strings.length ; i++) {
            StringBuilder sb = new StringBuilder(strings[i]);
            int k = 0;
            for (int j = 0; j <strings[i].length() ; j++) {
                if (Character.isUpperCase(strings[i].charAt(j))) {
                    sb.insert(j+k,"_");
                    k++;
                }
            }
            result[i] = sb.toString().toLowerCase();
        }
        System.out.println("Strings in snake case: " + Arrays.toString(result));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 2. Replace in string
     * @param source source string
     * @param search searching part
     * @param replace replacing part
     */

    public static void replaceInString(String source, String search, String replace) {
        System.out.println("[Task 2] Replace in string");
        System.out.printf("Input string = '%s'; search = '%s'; replace = '%s'%n", source,search,replace);
        System.out.println("Result = " + source.replaceAll(search,replace));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 3. Count of digits in string
     */

    public static void countOfDigitsInString(String source) {
        System.out.println("[Task 3] Count of digits in '" + source +"'");
        System.out.printf("In string %d digits%n", source.replaceAll("\\D","").length());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 4. Count of numbers in string
     */

    public static void countOfNumbers(String source) {
        System.out.println("[Task 4] Count of numbers in '" + source +"'");
        System.out.printf("In string %d numbers%n", source.replaceAll("\\s\\d+\\s","#").split("#").length-1);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 5. Replace spaces (two or more replace on one space and then trim)
     */

    public static void replaceSpaces(String source) {
        System.out.println("[Task 5] Replace multi spaces '" + source +"'");
        System.out.println("Result: '" + source.replaceAll("\\s+"," ").trim() + "'");
        System.out.println("-----------------------------------------------");
    }

    public static void main(String[] args) {
        camelCaseToSnakeCase(new String[]{"camelCase","camelCaseTwo", "camelCaseThreeAndFour"});
        replaceInString("String with word replace on letter. And again word to letter", "word", "letter");
        countOfDigitsInString("Please, buy me 2 pepsi, 1 cola and 10 beers");
        countOfNumbers("Please, buy me 2 pepsi, 1 cola and 10 beers and 20 chips. Take 10 dollars on a table;");
        replaceSpaces("  Please,       buy me 2  pepsi, 1 cola     and 10 beers        ");
    }
}
