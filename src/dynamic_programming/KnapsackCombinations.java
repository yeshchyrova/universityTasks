package dynamic_programming;

import java.util.Scanner;

public class KnapsackCombinations {

  private static int countSubsetSums(int[] arr, int l, int r, int sum, int target) {
    if (l > r) {
      return sum <= target ? 1 : 0;
    }

    int include = countSubsetSums(arr, l + 1, r, sum + arr[l], target);
    int exclude = countSubsetSums(arr, l + 1, r, sum, target);

    return include + exclude;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scanner.nextInt();
    }

    int maxSize = scanner.nextInt();
    long startTime = System.nanoTime();
    int count = countSubsetSums(arr, 0, n - 1, 0, maxSize);

    System.out.println(count);
    long endTime = System.nanoTime();
    System.out.println("\n\nTime: " + (endTime - startTime) / 1000000 + "ms");

    scanner.close();
  }
}


