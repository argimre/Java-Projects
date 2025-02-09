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
                    System.out.println(tokens);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseTokens(String line) {
        String[] strings = line.split(" ");

        int i = 0;
        while (i < strings.length) {
            String currentToken = strings[i];
            if (tokenMap.containsKey(currentToken)) {
                tokens.add(currentToken);
                i++;
            } else {
                parseString(strings[i]);
                i++;
            }
        }
    }

    private void parseString(String currentToken) {
        StringBuilder token = new StringBuilder();

        int i = 0;
        token.setLength(0);
        char currChar = currentToken.charAt(i);

        if (Character.isDigit(currChar)) {
            while (i < currentToken.length() && Character.isDigit(currentToken.charAt(i))) {
                token.append(currentToken.charAt(i));
                i++;
            }

        } else if (Character.isLetter(currChar)) {
            while (i < currentToken.length()
                    && (Character.isUpperCase(currentToken.charAt(i)) || Character.isDigit(currentToken.charAt(i)))) {
                token.append(currentToken.charAt(i));
                i++;
            }
            String tokenString = token.toString();
            if (tokenString.matches("[A-Z][A-Z0-9]*")) {
                tokens.add(tokenString);
            } else {
                tokens.add("ERROR");
            }

        } else {
            token.setLength(0);
            token.append(currChar);
            if (tokenMap.containsKey(token.toString())) {
                tokens.add(token.toString());
            } else {
                System.err.println("Error: Unrecognized token '" + token + "' at position " + i);
                tokens.add("ERROR");
            }
        }
        i++;
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
