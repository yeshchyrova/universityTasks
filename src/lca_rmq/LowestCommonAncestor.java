package lca_rmq;

import java.util.Arrays;
import java.util.Scanner;

public class LowestCommonAncestor {
  private final int[][] up;
  private final int[] depth;
  private final boolean[] isDepthCounted;
  private int LOG;
  private static int x, y, z, n, m;
  private long result;

  public LowestCommonAncestor(int n, int[] parent) {

    LOG = 0;
    while ((1 << LOG) <= n) {
      LOG++;
    }

    isDepthCounted = new boolean[n];
    Arrays.fill(isDepthCounted, false);
    up = new int[n][LOG];
    depth = new int[n];

    up[0][0] = 0;
    isDepthCounted[0] = true;
    depth[0] = 0;

    for (int i = 1; i < n; i++) {
      up[i][0] = parent[i];
    }

    computeDepths(parent);

    for (int j = 1; j < LOG; j++) {
      for (int i = 0; i < n; i++) {
        if (up[i][j - 1] != 0) {
          up[i][j] = up[up[i][j - 1]][j - 1];
        } else {
          up[i][j] = 0;
        }
      }
    }
  }

  void computeDepths(int[] parent) {
    for (int i = 1; i < n; i++) {
      if (!isDepthCounted[i]) {
        int node = i;
        int d = 0;
        while (!isDepthCounted[node]) {
          node = parent[node];
          d++;
        }
        depth[i] = d + depth[node];
        isDepthCounted[i] = true;
      }
    }
  }

  int getLCA(int a, int b) {
    if (depth[a] < depth[b]) {
      int с = a;
      a = b;
      b = с;
    }

    int k = depth[a] - depth[b];
    for (int j = LOG - 1; j >= 0; j--) {
      if ((k & (1 << j)) != 0) {
        a = up[a][j];
      }
    }

    if (a == b) return a;

    for (int j = LOG - 1; j >= 0; j--) {
      if (up[a][j] != up[b][j]) {
        a = up[a][j];
        b = up[b][j];
      }
    }
    return up[a][0];
  }

  void getResults(int a1, int a2) {
    result += getLCA(a1, a2);
    int prevAnswer = (int) result;

    int firstA = getNextNode(a1, a2);
    int secondA = getNextNode(a2, firstA);

    for (int i = 2; i <= m; i++) {
      int answer = getLCA((firstA + prevAnswer) % n, secondA);
      prevAnswer = answer;

      firstA = getNextNode(firstA, secondA);
      secondA = getNextNode(secondA, firstA);

      result += answer;
    }
  }

  int getNextNode(int e, int f) {
    return (int) (((long) x * e + (long) y * f + z) % n);
  }

  long getSum() {
    return result;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();

    int[] parent = new int[n];
    parent[0] = 0;

    for (int i = 1; i < n; i++) {
      parent[i] = sc.nextInt();
      if (parent[i] < 0 || parent[i] >= n) {
        throw new IllegalArgumentException("Invalid parent index");
      }
    }

    int a1 = sc.nextInt();
    int a2 = sc.nextInt();
    x = sc.nextInt();
    y = sc.nextInt();
    z = sc.nextInt();

    LowestCommonAncestor lca = new LowestCommonAncestor(n, parent);
    lca.getResults(a1, a2);
    System.out.println(lca.getSum());
  }
}
