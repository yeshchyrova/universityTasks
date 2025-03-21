package dynamic_programming;

import java.util.Scanner;
import java.util.*;

import java.util.Arrays;

public class KR_3 {
  public static int maxGroups(int[] nums) {
    int n = nums.length / 2;
    int[] left = Arrays.copyOfRange(nums, 0, n);
    int[] right = Arrays.copyOfRange(nums, n, nums.length);

    Set<Integer> sums1 = new HashSet<>();
    Set<Integer> sums2 = new HashSet<>();

    // Перебор комбинаций для левой половины
    generateSums(left, 0, 0, sums1);

    // Перебор комбинаций для правой половины
    generateSums(right, 0, 0, sums2);

    // Сортировка множества сумм правой половины
    List<Integer> list2 = new ArrayList<>(sums2);
    Collections.sort(list2);

    int count = 0;
    for (int sum1 : sums1) {
      int target = 10 - sum1;
      // Бинарный поиск в отсортированном множестве sums2
      if (Collections.binarySearch(list2, target) >= 0) {
        count++;
      }
    }

    return count;
  }
  private static void generateSums(int[] nums, int index, int sum, Set<Integer> sums) {
    if (index == nums.length) {
      sums.add(sum);
      return;
    }

    generateSums(nums, index + 1, sum, sums);
    generateSums(nums, index + 1, sum + nums[index], sums);
  }

  public static void main(String[] args) {
    int[] numbers = {1,9,9,7,9,3};
    System.out.println("Max groups with sum 10: " + maxGroups(numbers));
  }
}

