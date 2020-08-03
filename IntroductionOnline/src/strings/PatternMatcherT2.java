package strings;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Task 2. Parsing XML document
 */

public class PatternMatcherT2 {

    private static final String XML = "<notes>"
        + "  <note id = \"1\">"
        + "    <to>Вася</to>"
        + "    <from>Света</from>"
        + "    <heading>Напоминание</heading>"
        + "    <body>Позвони мне завтра!</body>"
        + "  </note>"
        + "  <note id = \"2\">"
        + "    <to>Петя</to>"
        + "    <from>Маша</from>"
        + "    <heading>Важное напоминание</heading>"
        + "    <body/>"
        + "  </note>"
        + "  <note id = \"3\">"
        + "    <to>Иван</to>"
        + "    <from>Аня</from>"
        + "    <heading>Я тебя люблю!</heading>"
        + "    <body/>"
        + "  </note>"
        + "  <note id = \"4\">"
        + "    <to>Аня</to>"
        + "    <from>Иван</from>"
        + "    <heading>Я тоже тебя люблю!</heading>"
        + "    <body/>"
        + "  </note>"
        + "</notes>";

    public static void main(String[] args) {
        ParserXML parserXML = new ParserXML(XML);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to get next node or any symbol to exit:");
        while (true) {
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                break;
            }
            String text = parserXML.getNextNode();
            if (text.isEmpty()) {
                System.out.println("End of XML");
                break;
            } else {
                System.out.println(text);
            }
        }
    }
}


/**
 * Class for parser
 */

class ParserXML {
    private final String textXML;
    private final Matcher matcher;

    /**
     * Constructor. Init text, pattern and mather
     */

    public ParserXML(String textXML) {
        this.textXML = textXML.replaceAll("\n", "").replaceAll("\\s+", " ");
        Pattern pattern = Pattern.compile("<[^/].+?>");
        matcher = pattern.matcher(this.textXML);
    }

    /**
     * Getting next node in document
     * @return string with info about tag
     */

    public String getNextNode() {
        StringBuilder sb = new StringBuilder();
        String tag, closingTag;
        if (matcher.find()) {
            tag = textXML.substring(matcher.start(), matcher.end());
            if (!isAlone(tag)) {
                closingTag = getClosingTag(tag);
                sb.append("Open tag:    ").append(tag).append("\n");
                sb.append("Closing tag: ").append(closingTag).append("\n");
                sb.append("Body of tag: ")
                    .append(textXML.substring(matcher.end(), textXML.indexOf(closingTag, matcher.start())).trim())
                    .append("\n");
            } else {
                sb.append("Alone tag:   ").append(tag);
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * Getting closing tag
     */

    public String getClosingTag(String tag) {
        return tag.contains(" ") ? "</" + tag.substring(1, tag.indexOf(" ")) + ">" : "</" + tag.substring(1);
    }

    /**
     * Checking stand alone tag
     */

    public boolean isAlone(String tag) {
        return tag.endsWith("/>");
    }
}
