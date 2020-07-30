package strings;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StringAndStringBuilder {

    /**
     * Task 1. Find max consecutive spaces
     */

    public static void findMaxSpaces(String source) {
        System.out.println("-----------------------------------------------");
        System.out.println("[Task 1] Spaces in string: '" + source + "'");
        int max = 0, curmax = 0;
        for (char c : source.toCharArray()) {
            if (c == ' ') {
                curmax++;
                if (curmax > max) {
                    max = curmax;
                }
            } else {
                curmax = 0;
            }
        }
        System.out.println("Max count of spaces = " + max);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 2. Insert specific symbol after another specific symbol
     *
     * @param source inout string
     * @param insert symbol to inserting
     * @param after  symbol after that insert another symbol
     */

    public static void insertSymbolAfter(String source, char insert, char after) {
        System.out.println("[Task 2] Insert symbol in: '" + source + "'");
        StringBuilder sb = new StringBuilder(source);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == after) {
                sb.insert(i + 1, insert);
                i++;
            }
        }
        System.out.println("Result: " + sb.toString());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 3. Check palindrome for word
     */

    public static void checkPalindrome(String source) {
        System.out.println("[Task 3] Check palindrome for : '" + source + "'");
        System.out.println("Result: " + source.equals((new StringBuilder(source)).reverse().toString()));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 4. Generate new word from source
     */

    public static void generateNewWord(String source, String word) {
        System.out.println("[Task 4] Generate '" + word + "' from '" + source + "'");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            int beginIndex = source.indexOf(word.charAt(i));
            sb.append(source, beginIndex, beginIndex + 1);
        }
        System.out.println("Generating word: '" + sb.toString() + "'");
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 5. Count of specific symbol
     */

    public static void countOfSymbol(String source, char symbol) {
        System.out.println("[Task 5] Count of '" + symbol + "' in '" + source + "'");
        System.out.println("Result = " + source.chars().filter(c -> c == symbol).count());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 6. Double chars in the string
     */

    public static void doubleCharsInString(String source) {
        System.out.println("[Task 6] Double all chars in: '" + source + "'");
        StringBuilder sb = new StringBuilder(source);
        for (int i = 1; i < sb.length(); i++) {
            sb.insert(i, sb.charAt(i - 1));
            i++;
        }
        System.out.println("Result: " + sb.toString());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 7. Delete space and get only distinct chars in string
     */

    public static void deleteSpaceAndGetDistinctChars(String source) {
        System.out.println("[Task 7] Delete space and get distinct chars in: '" + source + "'");
        int[] chars = source.replaceAll("\\s", "").chars().distinct().toArray();
        StringBuilder sb = new StringBuilder();
        for (int aChar : chars) {
            sb.append((char) aChar);
        }
        System.out.println("Result: " + sb.toString());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 8. Find word wih max length
     */

    public static void findMaxLengthWord(String source) {
        System.out.println("[Task 8] Find word with max length: '" + source + "'");
        String[] strings = source.replaceAll("\\p{Punct}", "").split(" ");
        Arrays.sort(strings, Comparator.comparing(String::length).reversed());

        System.out.println("Word with max length '" + strings[0] + "'");
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 9. Count of chars in upper and lowe case
     */

    public static void countOfUpperAndLowerCaseChars(String source) {
        System.out.println("[Task 9] Count of chars in upper and lower case in: '" + source + "'");
        source.replaceAll("(\\p{Punct})|(\\d)", "");
        int countUpperCase = (int) source.chars().filter(Character::isUpperCase).count();
        int countLowerCase = (int) source.chars().filter(Character::isLowerCase).count();
        System.out.printf("In upper case %d chars, in lower case %d chars%n", countUpperCase, countLowerCase);
        System.out.println("-----------------------------------------------");
    }

    /**
     * Task 10. Count of sentence
     */

    public static void countOfSentence(String source) {
        System.out.println("[Task 10] Count of sentence in: '" + source + "'");
        int count = (int) (source.chars().filter(c -> c == '.').count() +
            source.chars().filter(c -> c == '!').count()
                + source.chars().filter(c -> c == '?').count());
        System.out.println("Result = " + count);
    }

    public static void main(String[] args) {
        findMaxSpaces("  Please,       buy me 2  pepsi, 1 cola     and 10 beers        ");
        insertSymbolAfter("Please, buy me 2 pepsi, 1 cola and 10 beers", 'b', 'a');
        checkPalindrome("level");
        generateNewWord("информатика", "торт");
        countOfSymbol("Please, buy me 2 pepsi, 1 cola and 10 beers", 'a');
        doubleCharsInString("Please, buy me 2 pepsi, 1 cola and 10 beers");
        deleteSpaceAndGetDistinctChars("abc cde def");
        findMaxLengthWord("Please, buy me 2 pepsi, 1 cola and 10 beers");
        countOfUpperAndLowerCaseChars("Please, buy me 2 Pepsi, 1 Cola and 10 beers");
        countOfSentence(
            "Please, buy me 2 pepsi, 1 cola and 10 beers. Please, buy me 2 pepsi, "
                + "1 cola and 10 beers! I say! Do you hear me?");
    }
}
