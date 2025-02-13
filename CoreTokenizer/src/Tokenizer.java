import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tokenizer {
    private TokenMapSetup tokenMapSetup = new TokenMapSetup();
    private BufferedReader reader;
    private Map<String, Integer> tokenMap;
    private List<String> tokens;
    private int pointer;

    public Tokenizer(String filePath) {
        tokenMap = new HashMap<>();
        tokenMap = tokenMapSetup.initializeTokenMap();
        tokens = new ArrayList<>();
        pointer = 0;

        try {
            this.reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
            e.printStackTrace();
        }
        tokenizeLine();
        System.out.println(tokens);
    }

    private void tokenizeLine() {
        tokens.clear();
        pointer = 0;
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    parseTokens(line);

                    if (tokens.isEmpty()) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseTokens(String line) {
        int i = 0;
        while (i < line.length()) {
            char currChar = line.charAt(i);

            if (Character.isWhitespace(currChar)) {
                i++;
                continue;
            }

            String symbol = String.valueOf(currChar);
            if (tokenMap.containsKey(symbol)) {
                tokens.add(symbol);
                i++;
                continue;
            }

            if (i < line.length() - 1) {
                String twoCharSymbol = line.substring(i, i + 2);
                if (tokenMap.containsKey(twoCharSymbol)) {
                    tokens.add(twoCharSymbol);
                    i += 2;
                    continue;
                }
            }

            if (Character.isLetter(currChar)) {
                StringBuilder token = new StringBuilder();
                while (i < line.length() && (Character.isLetterOrDigit(line.charAt(i)))) {
                    token.append(line.charAt(i));
                    i++;
                }
                tokens.add(token.toString());
                continue;
            }

            if (Character.isDigit(currChar)) {
                StringBuilder token = new StringBuilder();
                while (i < line.length() && Character.isDigit(line.charAt(i))) {
                    token.append(line.charAt(i));
                    i++;
                }
                tokens.add(token.toString());
                continue;
            }

            System.err.println("Error: Unrecognized token '" + currChar + "'");
            tokens.add("ERROR");
            i++;
        }
    }

    public int getToken() {
        if (pointer >= tokens.size()) {
            return 33;
        }

        String token = tokens.get(pointer);

        if (token.equals("ERROR")) {
            return 34;
        }
        if (tokenMap.containsKey(token)) {
            return tokenMap.get(token);
        }
        if (token.matches("\\d+")) {
            return 31;
        }
        if (token.matches("[A-Z][A-Z0-9]*")) {
            return 32;
        }
        return 34;
    }

    public void skipToken() {
        if (getToken() == 33) {
            return;
        }
        if (getToken() == 34) {
            System.err.println("Error: Invalid token encountered.");
            System.exit(1);
        }
        pointer++;
        if (pointer >= tokens.size()) {
            tokenizeLine();
        }
    }

    public int intVal() {
        if (getToken() != 31) {
            System.err.println("Error: Current token is not an integer.");
            System.exit(1);
        }
        return Integer.parseInt(tokens.get(pointer));
    }

    public String idName() {
        if (getToken() != 32) {
            System.err.println("Error: Current token is not an identifier.");
            System.exit(1);
        }
        return tokens.get(pointer);
    }
}
