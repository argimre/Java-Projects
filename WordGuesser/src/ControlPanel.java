import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControlPanel {
    private JPanel gridPanel;
    private JTextField[][] grid;
    private int rows;
    private int cols;
    private WordGuesser wordGuesser;

    public ControlPanel(JTextField[][] grid, int rows, int cols, WordGuesser wordGuesser) {
        this.grid = grid;
        this.rows = rows;
        this.cols = cols;
        this.wordGuesser = wordGuesser;
        initializePanel();
    }

    private void initializePanel() {
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols, 5, 5));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new JTextField();
                grid[i][j].setHorizontalAlignment(JTextField.CENTER);
                grid[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                grid[i][j].setEditable(i == 0);
                grid[i][j].addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        JTextField source = (JTextField) e.getSource();
                        if (Character.isLetter(e.getKeyChar()) && source.getText().length() == 0) {
                            wordGuesser.moveToNextCell();
                        }
                    }

                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            wordGuesser.handleGuess();
                        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                            JTextField source = (JTextField) e.getSource();
                            if (!source.getText().isEmpty()) {
                                source.setText("");
                            } else {
                                wordGuesser.moveToPreviousCell();
                            }
                        }
                    }

                });
                gridPanel.add(grid[i][j]);
            }
        }
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }
}
