package dynamic_programming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KR_5 {

  public static void findCards(int sums, int[] weights) {
    int amountOfCards = weights.length;
    int[][] dp = new int[amountOfCards + 1][sums + 1];

    for (int i = 0; i <= amountOfCards; i++) {
      dp[i][0] = 1;
    }

    for (int i = 1; i <= amountOfCards; i++) {
      for (int j = 1; j <= sums; j++) {
        dp[i][j] = dp[i - 1][j];
        if (j >= weights[i - 1]) {
          dp[i][j] += dp[i - 1][j - weights[i - 1]];
        }
      }
    }

    int ways = dp[amountOfCards][sums];

    if (ways == 0) {
      System.out.println(0);
    } else if (ways > 1) {
      System.out.println(-1);
    } else {
      List<Integer> unused = new ArrayList<>();
      int k = sums;

      for (int i = amountOfCards; i > 0; i--) {
        if (k >= weights[i - 1] && dp[i - 1][k - weights[i - 1]] > 0) {
          k -= weights[i - 1];
        } else {
          unused.add(i);
        }
      }

      for (int i = unused.size() - 1; i >= 0; i--) {
        System.out.print(unused.get(i) + " ");
      }
    }
  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int k = sc.nextInt();
    int[] K = new int[k];
    for (int i = 0; i < k; i++) {
      K[i] = sc.nextInt();
    }
    findCards(N, K);
  }
}
