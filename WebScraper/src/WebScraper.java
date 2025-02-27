import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraper {

    public static void buildHeader(FileWriter writer, String url) throws IOException {
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<title>Web Word and Link Counter</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<h1>Words and Links Counted from: " + url + "</h1>");
    }

    public static void buildWordsTable(FileWriter writer, Map<String, Integer> wordMap) throws IOException {
        writer.write("<h2>Words Counted</h2>");
        writer.write("<hr>");
        writer.write("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\">");
        writer.write("<thead><tr><th>Word</th><th>Count</th></tr></thead>");
        writer.write("<tbody>");

        ArrayList<String> words = new ArrayList<>(wordMap.keySet());
        Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
        for (String word : words) {
            writer.write("<tr>");
            writer.write("<td>" + word + "</td>");
            writer.write("<td>" + wordMap.get(word) + "</td>");
            writer.write("</tr>");
        }
        writer.write("</tbody>");
        writer.write("</table>");
    }

    public static void buildLinksTable(FileWriter writer, ArrayList<String> links) throws IOException {
        writer.write("<h2>Links Extracted</h2>");
        writer.write("<hr>");
        writer.write("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\">");
        writer.write("<thead><tr><th>Link</th></tr></thead>");
        writer.write("<tbody>");
        for (String link : links) {
            writer.write("<tr>");
            writer.write("<td><a href=\"" + link + "\">" + link + "</a></td>");
            writer.write("</tr>");
        }
        writer.write("</tbody>");
        writer.write("</table>");
    }

    public static void buildFooter(FileWriter writer) throws IOException {
        writer.write("</body>");
        writer.write("</html>");
    }

    public static void countWordsAndExtractLinks(Map<String, Integer> wordMap, ArrayList<String> links, String url) {
        try {
            URL pageUrl = new URL(url);
            Scanner scanner = new Scanner(pageUrl.openStream());
            Pattern linkPattern = Pattern.compile("href=[\"'](https?://.*?)[\"']");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                line = line.replace("-", " ");
                String[] words = line.split("[^a-zA-Z'-]+");
                for (String word : words) {
                    word = word.toLowerCase().trim();
                    if (!word.isEmpty()) {
                        wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                    }
                }
                Matcher matcher = linkPattern.matcher(line);
                while (matcher.find()) {
                    String link = matcher.group(1);
                    links.add(link);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Error reading from URL: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<String, Integer> wordMap = new HashMap<>();
        ArrayList<String> links = new ArrayList<>();

        System.out.print("Please enter the URL to read from: ");
        String url = input.nextLine();

        countWordsAndExtractLinks(wordMap, links, url);

        try (FileWriter writer = new FileWriter("Java-Projects\\WebScraper\\data\\index.html")) {
            buildHeader(writer, url);
            buildWordsTable(writer, wordMap);
            buildLinksTable(writer, links);
            buildFooter(writer);
            System.out.println("Output written to Java-Projects\\WebScraper\\data\\index.html");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        input.close();
    }
}
