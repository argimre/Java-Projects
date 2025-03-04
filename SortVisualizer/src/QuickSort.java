public class QuickSort implements ISortingAlgorithm {
    private int currentBarIndex;
    private SortVisualizer visualizer;

    public void sort(int[] array, SortVisualizer visualizer) {
        this.visualizer = visualizer;
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            currentBarIndex = j;
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);

                visualizer.repaint();
                visualizer.sleep();
            }
        }
        swap(array, i + 1, high);

        visualizer.repaint();
        visualizer.sleep();

        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int getCurrentBarIndex() {
        return currentBarIndex;
    }
}
