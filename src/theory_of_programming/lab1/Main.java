package theory_of_programming.lab1;

import java.util.Arrays;

public class Main {
  static void main() {
    int[] initialArray = {3, 5, 1, 8, 0, 12, 7};
    ArraySorter arraySorter = new ArraySorter(initialArray);

    int[] sortedWithBubbleSort = arraySorter.bubbleSort();
    System.out.println("Sorted with bubble sort: " + Arrays.toString(sortedWithBubbleSort));

    int[] sortedWithMergeSort = arraySorter.mergeSort();
    System.out.println("Sorted with merge sort: " + Arrays.toString(sortedWithMergeSort));

    int[] sortedWithShellSort = arraySorter.shellSort();
    System.out.println("Sorted with shell sort: " + Arrays.toString(sortedWithShellSort));

    int[] sortedWithQuickSort = arraySorter.quickSort();
    System.out.println("Sorted with quick sort: " + Arrays.toString(sortedWithQuickSort));
  }
}
