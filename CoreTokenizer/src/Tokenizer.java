import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tokenizer {
    private TokenMapSetup tokenMapSetup = new TokenMapSetup();
    private BufferedReader reader;
    private List<String> tokens;
    private int pointer;

    private Map<String, Integer> wordMap;
    private Map<String, Integer> symbolMap;

    public Tokenizer(String filePath) {
        wordMap = tokenMapSetup.initializeWords();
        symbolMap = tokenMapSetup.initializeSymbols();
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

            if (i < line.length() - 1) {
                String twoCharSymbol = line.substring(i, i + 2);
                if (symbolMap.containsKey(twoCharSymbol)) {
                    tokens.add(twoCharSymbol);
                    i += 2;
                    continue;
                }
            }

            if (symbolMap.containsKey(symbol)) {
                int nextIndex = i + 1;

                while (nextIndex < line.length() && symbolMap.containsKey(String.valueOf(line.charAt(nextIndex)))) {
                    String invalidSequence = line.substring(i, nextIndex + 1);

                    if (symbolMap.containsKey(invalidSequence)) {
                        tokens.add(invalidSequence);
                        i = nextIndex + 1;
                        break;
                    }

                    nextIndex++;
                }

                if (nextIndex > i + 1) {
                    tokens.add("ERROR");
                    System.err.println("Error: Invalid symbol sequence '" + line.substring(i, nextIndex) + "'");
                    i = nextIndex;
                    continue;
                }

                tokens.add(symbol);
                i++;
                continue;
            }

            if (Character.isLetter(currChar)) {

                if (Character.isLetter(currChar)) {
                    StringBuilder token = new StringBuilder();
                    if (Character.isUpperCase(currChar)) {
                        boolean flag = true;
                        while (i < line.length() && (Character.isLetterOrDigit(line.charAt(i)))) {
                            if (Character.isUpperCase(line.charAt(i)) || Character.isDigit(line.charAt(i))) {
                                token.append(line.charAt(i));
                            } else {
                                flag = false;
                            }
                            i++;
                        }
                        if (flag) {
                            tokens.add(token.toString());
                        } else {
                            tokens.add("ERROR");
                        }
                        continue;
                    } else {
                        while (i < line.length() && (Character.isLetterOrDigit(line.charAt(i)))) {
                            token.append(line.charAt(i));
                            i++;
                        }
                        if (symbolMap.containsKey(token.toString()) || wordMap.containsKey(token.toString())) {
                            tokens.add(token.toString());

                        } else {
                            tokens.add("ERROR");
                        }
                        continue;
                    }
                }
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
        if (wordMap.containsKey(token)) {
            return wordMap.get(token);
        }
        if (symbolMap.containsKey(token)) {
            return symbolMap.get(token);
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
