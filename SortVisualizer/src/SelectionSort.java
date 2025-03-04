public class SelectionSort implements ISortingAlgorithm {
    private int currentBarIndex;

    public void sort(int[] array, SortVisualizer visualizer) {
        for (int i = 0; i < array.length - 1; i++) {
            if (!visualizer.isSorting()) {
                return;
            }

            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }

                currentBarIndex = j;
                visualizer.repaint();
                visualizer.sleep();
            }

            if (min != i) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;

                currentBarIndex = i;

                visualizer.repaint();
                visualizer.sleep();
            }
        }

        visualizer.stopSorting();
        visualizer.repaint();
        visualizer.sleep();
    }

    @Override
    public int getCurrentBarIndex() {
        return currentBarIndex;
    }
}
