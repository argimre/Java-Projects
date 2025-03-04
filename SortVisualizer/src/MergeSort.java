public class MergeSort implements ISortingAlgorithm {
    private int currentBarIndex;
    private SortVisualizer visualizer;

    public void sort(int[] array, SortVisualizer visualizer) {
        this.visualizer = visualizer;
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int lb = mid - left + 1;
        int rb = right - mid;

        int[] leftArray = new int[lb];
        int[] rightArray = new int[rb];

        for (int i = 0; i < lb; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < rb; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < lb && j < rb) {
            currentBarIndex = k;
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;

            visualizer.repaint();
            visualizer.sleep();
        }

        while (i < lb) {
            currentBarIndex = k;
            array[k] = leftArray[i];
            i++;
            k++;

            visualizer.repaint();
            visualizer.sleep();
        }

        while (j < rb) {
            currentBarIndex = k;
            array[k] = rightArray[j];
            j++;
            k++;

            visualizer.repaint();
            visualizer.sleep();
        }
    }

    @Override
    public int getCurrentBarIndex() {
        return currentBarIndex;
    }
}
