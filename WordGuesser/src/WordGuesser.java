import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class WordGuesser {

    // Reads words from the file and populates the word list
    public static void storeWords(List<String> wordList) {
        String line = "";
        File wordFile = new File("Java-Projects\\WordGuesser\\lib\\words.txt");

        if (!wordFile.exists() || !wordFile.isFile()) {
            System.out.println("Error: Word file not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(wordFile))) {
            while ((line = reader.readLine()) != null) {
                wordList.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Selects a random word from the word list
    public static String selectWord(List<String> wordList) {
        if (wordList.isEmpty()) {
            throw new IllegalStateException("Word list is empty. Cannot select a word.");
        }
        int randomIndex = (int) (Math.random() * wordList.size());
        return wordList.get(randomIndex);
    }

    // Prints the feedback grid
    public static void printGrid(String[][] feedbackGrid) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                System.out.print((feedbackGrid[row][col] != null ? feedbackGrid[row][col] : "-") + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Updates the grid and provides feedback for the current guess
    public static Set<Character> updateGrid(String[][] feedbackGrid, char[] wordChars, char[] guessChars, int attempt) {
        Set<Character> incorrectPositions = new HashSet<>();

        for (int i = 0; i < wordChars.length; i++) {
            if (wordChars[i] == guessChars[i]) {
                feedbackGrid[attempt][i] = String.valueOf(wordChars[i]);
            } else if (new String(wordChars).contains(String.valueOf(guessChars[i]))) {
                incorrectPositions.add(guessChars[i]);
                feedbackGrid[attempt][i] = "-";
            } else {
                feedbackGrid[attempt][i] = "-";
            }
        }
        return incorrectPositions;
    }

    // Displays hints about misplaced letters
    public static void displayHints(Set<Character> incorrectPositions, Set<Character> correctPositions) {
        if (!incorrectPositions.isEmpty()) {
            System.out.print("Hint: The following letters are in the word but in the wrong position: ");
            for (char c : incorrectPositions) {
                if (!correctPositions.contains(c)) {
                    System.out.print(c + " ");
                }
            }
            System.out.println();
        }
    }

    // Handles a single guessing attempt
    public static boolean handleAttempt(String[][] feedbackGrid, String word, String guess, int attempt) {
        char[] wordChars = word.toCharArray();
        char[] guessChars = guess.toCharArray();
        Set<Character> correctPositions = new HashSet<>();
        Set<Character> incorrectPositions = updateGrid(feedbackGrid, wordChars, guessChars, attempt);

        printGrid(feedbackGrid);
        displayHints(incorrectPositions, correctPositions);

        return guess.equals(word);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> wordList = new ArrayList<>();
        storeWords(wordList);

        if (wordList.isEmpty()) {
            System.out.println("No words available. Exiting the game.");
            input.close();
            return;
        }

        String word = selectWord(wordList).toLowerCase();
        // Debug: System.out.println("Word selected (debug): " + word);

        String[][] feedbackGrid = new String[6][5];
        System.out.println("Welcome to Word Guesser! You have 6 attempts to guess the word.");
        printGrid(feedbackGrid);

        boolean guessedCorrectly = false;

        for (int attempt = 0; attempt < 6; attempt++) {
            System.out.println("Attempt " + (attempt + 1) + ": Please guess a " + word.length() + "-letter word:");
            String guess = input.nextLine().toLowerCase();

            if (guess.length() != word.length() || !wordList.contains(guess)) {
                System.out.println("Invalid input. Please enter a valid word with " + word.length() + " letters.");
                attempt--; // Retry this attempt
                continue;
            }

            guessedCorrectly = handleAttempt(feedbackGrid, word, guess, attempt);

            if (guessedCorrectly) {
                System.out.println("Congratulations! You've guessed the word!");
                break;
            }
        }

        if (!guessedCorrectly) {
            System.out.println("Sorry! You've used all attempts. The correct word was: " + word);
        }

        input.close();
    }
}
