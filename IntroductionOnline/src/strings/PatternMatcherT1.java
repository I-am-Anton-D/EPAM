package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Task 1. Application for work with text
 */

public class PatternMatcherT1 {

    final static String TEXT = "This section summarizes the updated language features in Java SE 9 and subsequent"
        + " releases.\n"
        + "Java SE 14 introduces pattern matching for the instanceof operator; if the result of the instanceof "
        + "operator is true, then the object being tested is automatically assigned to a variable that you "
        + "previously declared. See Pattern Matching for the instanceof Operator. This release also introduces "
        + "records, which are a new kind of type declaration that's ideal for \"plain data carriers,\" classes "
        + "that contain data not meant to be altered and only the most fundamental methods such as constructors "
        + "and accessors. See Records.\n"
        + "Text blocks accept two more escape sequences (see Programmer's Guide to Text Blocks), and Switch "
        + "Expressions is now a permanent language feature.\n"
        + "Java SE 13 introduces text blocks, which are multiline string literals that don't require common "
        + "escape sequences; see Programmer's Guide to Text Blocks. It also introduces one change to switch "
        + "expressions: To specify their value, use the new yield statement instead of the break statement; "
        + "see Switch Expressions in Java Platform, Standard Edition Java Language Updates, Release 13.\n"
        + "Java SE 12 introduces switch expressions, plus a new kind of case label that prevents fall through. "
        + "This is available as a preview feature. See Switch Expressions in Java Platform, Standard Edition Java "
        + "Language Updates, Release 12.\n"
        + "Java SE 11 lets you declare formal parameters of implicitly typed lambda expressions with the var "
        + "identifier; see Local Variable Type Inference.\n"
        + "The Java Platform module system introduces a new kind of Java programing component, the module, which "
        + "is a named, self-describing collection of code and data. Its code is organized as a set of packages "
        + "containing types, Java classes and interfaces; its data includes resources and other kinds of "
        + "static information. Modules can either export or encapsulate packages, and they express dependencies "
        + "on other modules explicitly.\n";

    public static void main(String[] args) {
        TextUtils textUtils = new TextUtils(TEXT);
        System.out.println("-----------------------------------------------");
        System.out.println("Original text:");
        System.out.println("-----------------------------------------------");
        System.out.println(textUtils.getOriginalText());
        System.out.println("-----------------------------------------------");
        System.out.println("Sort text by count of sentence: ");
        System.out.println("-----------------------------------------------");
        System.out.println(textUtils.sortParagraphsBySentenceCount());
        System.out.println("-----------------------------------------------");
        System.out.println("Sort words by length: ");
        System.out.println("-----------------------------------------------");
        System.out.println(textUtils.sortWordsByLength());
        System.out.println("-----------------------------------------------");
        System.out.println("Sort words by char count: ");
        System.out.println("-----------------------------------------------");
        System.out.println(textUtils.sortWordsByChar('e'));
    }
}

/**
 * Class for working with text
 */

class TextUtils {

    /**
     * Class for sentence
     */

    private static class Sentence {

        private final ArrayList<String> words = new ArrayList<>();

        public Sentence(String sentence) {
            words.addAll(Arrays.asList(sentence.replaceAll("\\p{Punct}", "").split("\\s")));
        }
    }

    /**
     * Class for paragraph
     */

    private static class Paragraph {

        private final ArrayList<Sentence> sentences = new ArrayList<>();
        private final String pText;

        public Paragraph(String paragraph) {
            pText = paragraph;
            Pattern pattern = Pattern.compile("([^.!?]+[.!?]\\s)");
            Matcher matcher = pattern.matcher(pText);
            while (matcher.find()) {
                sentences.add(new Sentence(pText.substring(matcher.start(), matcher.end())));
            }
        }
    }

    /**
     * Array of paragraphs in original text
     */

    private final ArrayList<Paragraph> paragraphs = new ArrayList<>();

    /**
     * Original text
     */

    private final String text;

    /**
     * Constructor divide the text by paragraphs. Paragraphs dividing by sentences. Sentences by words
     */

    public TextUtils(String text) {
        this.text = text;
        Pattern pattern = Pattern.compile(".+\n");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            paragraphs.add(new Paragraph(text.substring(matcher.start(), matcher.end())));
        }
    }

    /**
     * Just return original text
     */

    public String getOriginalText() {
        return text;
    }

    /**
     * Sorting paragraphs by sentence count. Just using Stream API
     * @return new text with sorting
     */

    public String sortParagraphsBySentenceCount() {
        StringBuilder sb = new StringBuilder();
        paragraphs.stream().sorted(Comparator.comparing(p -> p.sentences.size())).forEach(
            p -> sb.append("[Count of sentence = ").append(p.sentences.size()).append("]").append(p.pText));
        return sb.toString();
    }

    /**
     * Sorting all words in sentences by their length
     * @return new text with sorting words
     */

    public String sortWordsByLength() {
        StringBuilder sb = new StringBuilder();
        paragraphs.forEach(p -> p.sentences.forEach(s -> {
            s.words.stream().sorted(Comparator.comparing(String::length))
                .forEach(w -> sb.append(w).append(" "));
            sb.append("\n");
        }));
        return sb.toString();
    }

    /**
     * Sorting all words in sentences by count of specific char
     * @param c - specific char
     * @return new text with sorting words
     */

    public String sortWordsByChar(char c) {
        StringBuilder sb = new StringBuilder();
        paragraphs.forEach(p -> p.sentences.forEach(s -> {
            s.words.stream().sorted(
                Comparator.comparingInt((String word) -> word.split(String.valueOf(c)).length-1).reversed()
                    .thenComparing(String::compareTo)).forEach(w -> sb.append(w).append(" "));
            sb.append("\n");
        }));
        return sb.toString();
    }
}


