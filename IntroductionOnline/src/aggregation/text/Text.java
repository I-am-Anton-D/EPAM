package aggregation.text;

import java.util.ArrayList;

/**
 * Task 1.
 *
 * Class to aggregate sentences with specific methods
 */

public class Text {
    private final String header;
    private final ArrayList<Sentence> sentences = new ArrayList<>();

    public Text(String header) {
        this.header = header;
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public void printHeader() {
        System.out.println(this.header);
    }

    public void printText() {
        sentences.forEach(sentence -> System.out.print(sentence+" "));
        System.out.println();
    }

}
