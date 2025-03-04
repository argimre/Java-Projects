public class InsertionSort implements ISortingAlgorithm {
    private int currentBarIndex;

    public void sort(int[] array, SortVisualizer visualizer) {
        for (int i = 1; i < array.length; i++) {
            if (!visualizer.isSorting()) {
                return;
            }

            int temp = array[i];
            int j = i - 1;
            currentBarIndex = i;

            while (j >= 0 && array[j] > temp) {
                if (!visualizer.isSorting()) {
                    return;
                }

                array[j + 1] = array[j];
                j--;
                currentBarIndex = j;

                visualizer.repaint();
                visualizer.sleep();
            }
            array[j + 1] = temp;
            currentBarIndex = j + 1;

            visualizer.repaint();
            visualizer.sleep();
        }

        visualizer.stopSorting();
        visualizer.repaint();
    }

    public int getCurrentBarIndex() {
        return currentBarIndex;
    }
}
