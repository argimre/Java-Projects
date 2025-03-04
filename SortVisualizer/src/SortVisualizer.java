import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SortVisualizer extends JPanel {
    private int[] array;
    private int delay = 100;
    private boolean sorting = false;
    private ISortingAlgorithm sorter;

    public SortVisualizer() {
        buildArray(10);
        setSorter(new BubbleSort());
    }

    public void setSorter(ISortingAlgorithm sorter) {
        stopSorting();
        this.sorter = sorter;
    }

    public void buildArray(int size) {
        stopSorting();
        array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(400) + 50;
        }
        repaint();
    }

    public void startSorting() {
        if (!sorting) {
            sorting = true;
            new Thread(() -> {
                sorter.sort(array, this);
                sorting = false;
            }).start();
        }
    }

    public void sleep() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void updateDelay(int delay) {
        this.delay = delay;
    }

    public void stopSorting() {
        sorting = false;
    }

    public boolean isSorting() {
        return sorting;
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        int gap = 3;
        int barWidth = (getWidth() - (array.length + 1) * gap) / array.length;

        graphics.setFont(new Font("Arial", Font.BOLD, 12));

        for (int i = 0; i < array.length; i++) {
            if (sorting && i == sorter.getCurrentBarIndex()) {
                graphics.setColor(Color.RED);
            } else {
                graphics.setColor(Color.BLACK);
            }

            int xPos = i * (barWidth + gap) + gap;
            int yPos = getHeight() - array[i];

            graphics.fillRect(xPos, yPos, barWidth, array[i]);

            if (array.length <= 25) {
                graphics.drawString(String.valueOf(array[i]), xPos + barWidth / 3, yPos - 5);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Sort Visualizer");
        SortVisualizer panel = new SortVisualizer();
        SortControlPanel controlPanel = new SortControlPanel(panel);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
