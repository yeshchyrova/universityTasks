package dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LIS {

  public static List<Integer> findLis(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    int[] prev = new int[n];

    Arrays.fill(dp, 1);
    Arrays.fill(prev, -1);

    int maxLength = 0;
    int endIndex = -1;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
          dp[i] = dp[j] + 1;
          prev[i] = j;
        }
      }
      if (dp[i] >= maxLength) {
        maxLength = dp[i];
        endIndex = i;
      }
    }

    System.out.println(maxLength);


    List<Integer> lis = new ArrayList<>();
    while (endIndex != -1) {
      lis.add(0, nums[endIndex]);
      endIndex = prev[endIndex];
    }

    return lis;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] nums = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      nums[i] = sc.nextInt();
    }

    List<Integer> lis = findLis(nums);
    for (int num : lis) {
      System.out.println(num);
    }
  }
}
