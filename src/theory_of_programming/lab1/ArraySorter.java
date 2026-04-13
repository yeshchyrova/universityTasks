package theory_of_programming.lab1;

import java.util.Arrays;

public class ArraySorter {
  private final int[] array;

  public ArraySorter(int[] values) {
    this.array = Arrays.copyOf(values, values.length);
  }

  public int[] bubbleSort() {
    int[] tempArray = Arrays.copyOf(array, array.length);
    int length = tempArray.length;
    boolean swapped;

    for (int i = 0; i < length - 1; i++) {
      swapped = false;

      for (int j = 0; j < length - i - 1; j++) {

        if (tempArray[j] > tempArray[j + 1]) {
          int tempValue = tempArray[j];
          tempArray[j] = tempArray[j + 1];
          tempArray[j + 1] = tempValue;

          swapped = true;
        }
      }

      // If no swaps occurred during the pass, the array is already sorted
      if (!swapped) {
        break;
      }
    }


    return tempArray;
  }


  //  -------------- MERGE SORT --------------
  public int[] mergeSort() {
    int[] tempArray = Arrays.copyOf(array, array.length);
    mergeSortRecursive(tempArray, 0, tempArray.length - 1);
    return tempArray;
  }

  // Recursively split the array until subarrays of size 1 remain
  private static void mergeSortRecursive(int[] arr, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;

      mergeSortRecursive(arr, left, mid);
      mergeSortRecursive(arr, mid + 1, right);

//      After sorting both halves, merge them into a single sorted segment
      merge(arr, left, mid, right);
    }
  }

  //  Merge halves of array into a single sorted segment
  private static void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] leftArr = new int[n1];
    int[] rightArr = new int[n2];

    for (int i = 0; i < n1; i++)
      leftArr[i] = arr[left + i];

    for (int j = 0; j < n2; j++)
      rightArr[j] = arr[mid + 1 + j];

    int i = 0, j = 0, k = left;

    // Merge two already sorted temporary arrays back into the original array
    while (i < n1 && j < n2) {
      if (leftArr[i] <= rightArr[j]) {
        arr[k] = leftArr[i];
        i++;
      } else {
        arr[k] = rightArr[j];
        j++;
      }
      k++;
    }

    // Copy remaining elements from the left subarray if the right one is exhausted
    while (i < n1) {
      arr[k] = leftArr[i];
      i++;
      k++;
    }

    // Copy remaining elements from the right subarray if the left one is exhausted
    while (j < n2) {
      arr[k] = rightArr[j];
      j++;
      k++;
    }
  }


  //  -------------- SHELL SORT --------------
  public int[] shellSort() {
    int[] tempArray = Arrays.copyOf(array, array.length);
    int n = tempArray.length;

//  Gradually reduce the gap, approaching standard insertion sort
    for (int gap = n / 2; gap > 0; gap /= 2) {

      for (int i = gap; i < n; i++) {
        int temp = tempArray[i];
        int j = i;

//      Shift elements until the correct position for temp is found in the current gap sequence
        while (j >= gap && tempArray[j - gap] > temp) {
          tempArray[j] = tempArray[j - gap];
          j -= gap;
        }

        tempArray[j] = temp;
      }
    }

    return tempArray;
  }


  //  -------------- QUICK SORT --------------
  public int[] quickSort() {
    int[] tempArray = Arrays.copyOf(array, array.length);
    quickSortRecursive(tempArray, 0, tempArray.length - 1);
    return tempArray;
  }

  //  After partitioning, the pivot element is in its final sorted position
  private static void quickSortRecursive(int[] arr, int low, int high) {
    if (low < high) {
      int pivotIndex = partition(arr, low, high);

      quickSortRecursive(arr, low, pivotIndex - 1);
      quickSortRecursive(arr, pivotIndex + 1, high);
    }
  }

  //  Partition the array so that elements <= pivot are on the left, others on the right
  private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
      if (arr[j] <= pivot) {
        i++;
        swap(arr, i, j);
      }
    }

//  Place the pivot between the smaller and larger parts of the array
    swap(arr, i + 1, high);
    return i + 1;
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
