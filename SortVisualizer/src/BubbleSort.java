public class BubbleSort implements ISortingAlgorithm {
    private int currentBarIndex = -1;

    public void sort(int[] array, SortVisualizer visualizer) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {

                if (!visualizer.isSorting()) {
                    return;
                }

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    currentBarIndex = j + 1;
                }

                visualizer.repaint();
                visualizer.sleep();
            }
        }
        visualizer.stopSorting();
        visualizer.repaint();
    }

    public int getCurrentBarIndex() {
        return currentBarIndex;
    }
}
