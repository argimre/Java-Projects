import java.util.HashMap;
import java.util.Map;

public class TokenMapSetup {
    public Map<String, Integer> initializeTokenMap() {
        Map<String, Integer> tokenMap = new HashMap<>();

        // Reserved words (1-11)
        tokenMap.put("program", 1);
        tokenMap.put("begin", 2);
        tokenMap.put("end", 3);
        tokenMap.put("int", 4);
        tokenMap.put("if", 5);
        tokenMap.put("then", 6);
        tokenMap.put("else", 7);
        tokenMap.put("while", 8);
        tokenMap.put("loop", 9);
        tokenMap.put("read", 10);
        tokenMap.put("write", 11);

        // Special symbols (12-30)
        tokenMap.put(";", 12);
        tokenMap.put(",", 13);
        tokenMap.put("=", 14);
        tokenMap.put("!", 15);
        tokenMap.put("[", 16);
        tokenMap.put("]", 17);
        tokenMap.put("&&", 18);
        tokenMap.put("||", 19);
        tokenMap.put("(", 20);
        tokenMap.put(")", 21);
        tokenMap.put("+", 22);
        tokenMap.put("-", 23);
        tokenMap.put("*", 24);
        tokenMap.put("!=", 25);
        tokenMap.put("==", 26);
        tokenMap.put("<", 27);
        tokenMap.put(">", 28);
        tokenMap.put("<=", 29);
        tokenMap.put(">=", 30);

        return tokenMap;
    }

}
