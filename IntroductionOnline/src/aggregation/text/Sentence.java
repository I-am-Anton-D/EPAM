package aggregation.text;


import java.util.ArrayList;

/**
 * Class for sentence
 * Save words and punctuation
 */

public class Sentence {

    private final ArrayList<Word> words = new ArrayList<>();
    private final ArrayList<String> punct = new ArrayList<>();

    public Sentence() {

    }

    public Sentence(String s) {
        String[] parts = (s+" ").split("\\p{P}?[ \\t\\n\\r]+");
        String[] p = s.split("\\w+");
        for (int i = 0; i < parts.length; i++) {
            words.add(new Word(parts[i]));
        }
        for (int i = 1; i <p.length ; i++) {
            punct.add(p[i]);
        }
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public void addPunct(String s) {
        punct.add(s);
    }

    public void printWords() {
        words.forEach(w->System.out.print(w.getValue() + " "));
        System.out.println();
    }

    public void printPunct() {
        punct.forEach(p->System.out.print(p+"|"));
        System.out.println();
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <words.size() ; i++) {
            sb.append(words.get(i)).append(punct.size() - 1 >= i ? punct.get(i) : "");
        }
        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <words.size() ; i++) {
            sb.append(words.get(i)).append(punct.size() - 1 >= i ? punct.get(i) : "");
        }
        return sb.toString();
    }
}
