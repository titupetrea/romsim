package radet.publisher;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Titu
 */
public class PdfParser {

    public static void main(String[] args) throws IOException {
        String s = IOUtils.toString(new FileReader("src/main/resources/test"));
        System.out.println(s);
        System.out.println("");

        s = s.replaceAll("\r", "");
        s = s.replaceAll("\n", "");
        parse(s);
    }

    public static ThermalAgentStoppages parse(String s) throws IOException {
        ThermalAgentStoppages result = new ThermalAgentStoppages();

        Pattern streetPattern = Pattern.compile("(al(\\.)?|bd(\\.)?|sos(\\.)?|str(\\.)?)(?<street>[\\.\\s\\w]+)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
        Pattern blocksPattern = Pattern.compile("bl\\s*\\.(?<blocks>(?:\\s*(([A-Z]+\\d+[A-Z]+)|(\\d+[A-Z]+)|([A-Z]+\\d+)|(\\d+))\\s*[,;]?)+)\\s*", Pattern.CASE_INSENSITIVE);
        Pattern numbersPattern = Pattern.compile("(imobil|imob\\.)(?<numbers>(?:\\s*nr\\.\\s*\\d+\\s*[,;]?)+)\\s*", Pattern.CASE_INSENSITIVE);
        Pattern startDatePattern = Pattern.compile("(?<startDate>\\d{1,2}\\.\\d{1,2}\\.\\d{1,2}/\\d{1,2}\\.\\d{1,2})", Pattern.CASE_INSENSITIVE);
        Pattern stopDatePattern = Pattern.compile("(?<stopDate>\\d{1,2}\\.\\d{1,2}\\.\\d{1,2}/\\d{1,2}\\.\\d{1,2})", Pattern.CASE_INSENSITIVE);
        Pattern p = Pattern.compile(
                "(" + streetPattern.pattern() + "(" + blocksPattern.pattern() + "|" + numbersPattern.pattern() + ")+" + ")+"
                + "\\s*(ACC/INC|ACC|INC)\\s*"
                + startDatePattern.pattern() + "\\s*"
                + stopDatePattern.pattern() + "\\s*"
                + "(ARP)",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);

        matchBlocks(blocksPattern, s);
//        matchNumbers(numbersPattern, s);

        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println("found " + m.group());
            Pattern streetBlocksNumbersPattern = Pattern.compile(
                    streetPattern.pattern() + "(" + blocksPattern.pattern() + "|" + numbersPattern.pattern() + ")+",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
            Matcher streetBlocksNumbersMatcher = streetBlocksNumbersPattern.matcher(m.group());
            while (streetBlocksNumbersMatcher.find()) {
                String street = streetBlocksNumbersMatcher.group("street");
                String blocks = streetBlocksNumbersMatcher.group("blocks");
                String numbers = streetBlocksNumbersMatcher.group("numbers");
                System.out.println("  " + street + "  " + blocks + " " + (numbers == null ? "" : numbers));
            }
            System.out.println(" " + m.group("startDate"));
            System.out.println(" " + m.group("stopDate"));
        }

        return result;
    }

    private static void matchNumbers(Pattern numbersPattern, String s) {
        Matcher numbersMatcher = numbersPattern.matcher(s);
        while (numbersMatcher.find()) {
            System.out.println(numbersMatcher.group());
        }
    }

    private static void matchBlocks(Pattern blocksPattern, String s) {
        Matcher blocksMatcher = blocksPattern.matcher(s);
        while (blocksMatcher.find()) {
            System.out.println(blocksMatcher.group());
        }
    }

    private static void matchStreet(Pattern streetPattern, String s) {
        Matcher streetMatcher = streetPattern.matcher(s);
        while (streetMatcher.find()) {
            System.out.println(streetMatcher.group());
        }
    }
}
