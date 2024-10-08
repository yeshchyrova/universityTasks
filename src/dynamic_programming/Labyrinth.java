package dynamic_programming;

import java.util.Scanner;

public class Labyrinth {
  private static int n;
  private static final int mod = 1000000007;

  public static void powerMatrix(int[][] d, int k) {
    int[][] res = new int[n][n];

    for (int i = 0; i < n; i++) {
      res[i][i] = 1;
    }

    int[][] temp = new int[n][n];

    while (k > 0) {
      if (k % 2 == 1) {
        multiply(res,d, temp);
        copy(temp, res);
      }
        multiply(d, d, temp);
        copy(temp, d);
        k /= 2;
    }
    copy(res, d);

  }


  public static void copy(int[][] from, int[][] to) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        to[i][j] = from[i][j];
      }
    }
  }


  public static void multiply(int[][] d1, int[][] d2, int[][] temp) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        temp[i][j] = 0;
        for (int p = 0; p < n; p++) {
          temp[i][j] = (temp[i][j] + (int)((long)d1[i][p] * d2[p][j] % mod)) % mod;
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt(); // amount of nodes
    int m = sc.nextInt(); // amount of edges
    int k = sc.nextInt(); // distance of each of rat

    int[][] adjacencyMatrix = new int[n][n];

    for (int i = 0; i < m; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      adjacencyMatrix[a - 1][b - 1]++;
    }

    powerMatrix(adjacencyMatrix, k);
    long sum = 0;
    for (int i = 0; i < n; i++) {
      sum = (sum + adjacencyMatrix[0][i]) % mod;
    }

    System.out.println(sum);
  }
}
