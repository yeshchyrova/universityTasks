package dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KR_4 {

  public static void lis(int[] nums) {
    int[] d = new int[nums.length];
    int[] prev = new int[nums.length];

    Arrays.fill(prev, -1);
    Arrays.fill(d, 1);

    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j] && d[i] < d[j] + 1) {
          d[i] = d[j] + 1;
          prev[i] = j;
        }
      }
    }

    int max = d[0];
    for (int i = 1; i < nums.length; i++) {
      if (d[i] > max) {
        max = d[i];
      }
    }
    System.out.println(max);

    List<Integer> ew = new ArrayList<>();
    for (int i = nums.length - 1; i >= 0; i--) {
      if (d[i] == max) {
        int j = i;
        while (j != -1) {
          ew.add(nums[j]);
          j = prev[j];
        }
        break;
      }
    }

    for (int i = ew.size() - 1; i >= 0; i--) {
      System.out.println(ew.get(i));
    }

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
//    int mod = (int) Math.pow(2, 31) - 2;
    int[] nums = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      nums[i] = sc.nextInt();
    }

    lis(nums);
  }
}
