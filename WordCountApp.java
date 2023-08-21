import java.util.Scanner;

public class WordCountApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your text:");
        String input = scanner.nextLine();

        int wordCount = countWords(input);
        int pageCount = countPages(input);
        int paragraphCount = countParagraphs(input);
        int lineCount = countLines(input);
        int characterCount = countCharacters(input);

        System.out.println("Word count: " + wordCount);
        System.out.println("Page count: " + pageCount);
        System.out.println("Paragraph count: " + paragraphCount);
        System.out.println("Line count: " + lineCount);
        System.out.println("Character count: " + characterCount);

        scanner.close();
    }

    public static int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        String[] words = text.split("\\s+");
        return words.length;
    }

    public static int countPages(String text) {
        // Assuming an average of 250 words per page
        int wordsPerPage = 250;
        int wordCount = countWords(text);
        return (int) Math.ceil((double) wordCount / wordsPerPage);
    }

    public static int countParagraphs(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        String[] paragraphs = text.split("\\n\\s*\\n");
        return paragraphs.length;
    }

    public static int countLines(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        String[] lines = text.split("\\n");
        return lines.length;
    }

    public static int countCharacters(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        return text.length();
    }
}

