import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class WordGuesser {
    private static List<String> wordList = new ArrayList<>();;
    private String selectedWord;

    private GuesserControlPanel controlPanel;
    private JTextField[][] grid;
    private JFrame frame;

    private final int cols = 5;
    private final int rows = 6;
    private int currentRow = 0;
    private int currentColumn = 0;
    private boolean playing = true;

    public WordGuesser() {
        storeWords();
        selectWord();
        setFrame();
    }

    private static void storeWords() {
        File wordFile = new File("WordGuesser/lib/words.txt");

        if (!wordFile.exists()) {
            System.out.println("Error: Word file not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(wordFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordList.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private void selectWord() {
        if (wordList.isEmpty()) {
            throw new IllegalStateException("Word list is empty. Cannot select a word.");
        }
        int randomIndex = (int) (Math.random() * wordList.size());
        selectedWord = wordList.get(randomIndex);
    }

    private void setFrame() {
        frame = new JFrame("Word Guesser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        grid = new JTextField[rows][cols];
        controlPanel = new GuesserControlPanel(grid, rows, cols, this);

        frame.add(controlPanel.getGridPanel(), BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void handleGuess() {
        if (currentRow >= rows || !playing) {
            return;
        }

        StringBuilder guessBuilder = new StringBuilder();
        for (int i = 0; i < cols; i++) {
            guessBuilder.append(grid[currentRow][i].getText().toLowerCase());
        }

        String guess = guessBuilder.toString();
        if (guess.length() != cols) {
            return;
        }

        if (!wordList.contains(guess)) {
            System.out.println("Invalid word: '" + guess + "' is not in the word list.");
            return;
        }

        char[] wordChars = selectedWord.toCharArray();
        char[] guessChars = guess.toCharArray();

        int[] wordCharCounts = new int[26];
        for (char c : wordChars) {
            wordCharCounts[c - 'a']++;
        }

        for (int i = 0; i < cols; i++) {
            if (guessChars[i] == wordChars[i]) {
                grid[currentRow][i].setBackground(Color.GREEN);
                wordCharCounts[guessChars[i] - 'a']--;
            }
        }

        for (int i = 0; i < cols; i++) {
            if (grid[currentRow][i].getBackground() != Color.GREEN) {
                if (wordCharCounts[guessChars[i] - 'a'] > 0) {
                    grid[currentRow][i].setBackground(Color.YELLOW);
                    wordCharCounts[guessChars[i] - 'a']--;
                } else {
                    grid[currentRow][i].setBackground(Color.RED);
                }
            }
        }

        if (guess.equals(selectedWord)) {
            playing = false;
            System.out.println("You guessed correctly");
            return;
        }
        currentRow++;

        currentColumn = 0;
        if (currentRow >= rows) {
            playing = false;
            System.out.println("You lost");
            System.out.println("The word was: " + selectedWord);

            return;
        }

        for (int i = 0; i < cols; i++) {
            grid[currentRow][i].setEditable(true);
        }
        grid[currentRow][0].requestFocus();
    }

    public void moveToNextCell() {
        if (currentColumn < cols - 1) {
            currentColumn++;
        }
        grid[currentRow][currentColumn].requestFocus();
    }

    public boolean getPlaying() {
        return this.playing;
    }

    public void moveToPreviousCell() {
        if (currentColumn > 0) {
            currentColumn--;
        } else if (currentRow > 0) {
            currentRow--;
            currentColumn = cols - 1;
        }
        grid[currentRow][currentColumn].setText("");
        grid[currentRow][currentColumn].requestFocus();
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(WordGuesser::new);
    }
}
