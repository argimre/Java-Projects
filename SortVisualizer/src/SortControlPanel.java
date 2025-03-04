import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SortControlPanel extends JPanel {
    private JComboBox<String> algorithmSelector;
    private SortVisualizer panel;
    private JButton startButton;
    private JButton resetButton;
    private JSlider speedSlider;
    private JSlider sizeSlider;
    private ISortingAlgorithm sorter;
    private final Map<String, ISortingAlgorithm> algorithms;

    public SortControlPanel(SortVisualizer panel) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.panel = panel;

        algorithms = new HashMap<>();
        algorithms.put("Bubble Sort", new BubbleSort());
        algorithms.put("Insertion Sort", new InsertionSort());
        algorithms.put("Selection Sort", new SelectionSort());
        algorithms.put("Merge Sort", new MergeSort());
        algorithms.put("Quick Sort", new QuickSort());

        sorter = algorithms.get("Bubble Sort");
        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        algorithmSelector = new JComboBox<>(algorithms.keySet().toArray(new String[0]));
        speedSlider = new JSlider(1, 100, 1);
        sizeSlider = new JSlider(10, 150, 10);

        add(algorithmSelector);
        add(startButton);
        add(resetButton);
        add(new JLabel("Speed:"));
        add(speedSlider);
        add(new JLabel("Elements:"));
        add(sizeSlider);

        startButton.addActionListener(e -> panel.startSorting());
        resetButton.addActionListener(e -> init());

        algorithmSelector.addActionListener(e -> {
            sorter = algorithms.get((String) algorithmSelector.getSelectedItem());
            init();
        });

        speedSlider.addChangeListener(e -> {
            int speedValue = speedSlider.getValue();
            int adjustedDelay = (int) (10 * Math.pow(2, (100 - speedValue) / 25.0));
            panel.updateDelay(adjustedDelay);
        });

        sizeSlider.addChangeListener(e -> {
            if (!sizeSlider.getValueIsAdjusting()) {
                panel.buildArray(sizeSlider.getValue());
            }
        });

        init();
    }

    private void init() {
        panel.stopSorting();
        panel.buildArray(sizeSlider.getValue());
        panel.setSorter(sorter);
    }
}
