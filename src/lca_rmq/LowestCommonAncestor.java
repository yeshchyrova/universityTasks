package lca_rmq;

import java.util.Arrays;
import java.util.Scanner;

public class LowestCommonAncestor {
  private final int[][] up;
  private final int[] depth;
  private final boolean[] isDepthCounted;
  private int LOG;
  private final int[] requestsData;
  private final int[] requestAnswers;
  private static int x, y, z, n, m;

  public LowestCommonAncestor(int n, int[] parent) {
    requestsData = new int[m * 2];
    requestAnswers = new int[m + 1];
    LOG = 0;
    while ((1 << LOG) <= n) {
      LOG++;
    }
    isDepthCounted = new boolean[n];
    Arrays.fill(isDepthCounted, false);
    isDepthCounted[0] = true;

    up = new int[n][LOG];
    depth = new int[n];
    depth[0] = 0;

    for (int i = 0; i < n; i++) {
      up[i][0] = parent[i];
      if (i != 0) {
//        depth[i] = depth[parent[i]] + 1;
        depth[i] = countDepth(i, parent);
        System.out.println("depth[" + i + "] = " + depth[i]);
      }
      for (int j = 1; j < LOG; j++) {
        up[i][j] = up[up[i][j - 1]][j - 1];
      }
    }
  }

  int countDepth(int n, int[] parent) {
    if (isDepthCounted[parent[n]]) {
      isDepthCounted[n] = true;
      return depth[parent[n]] + 1;
    }
    int r = countDepth(parent[n], parent);
    return depth[r] + 1;
  }

  int getLCA(int a, int b) {
    if (depth[a] < depth[b]) {
      int c = a;
      a = b;
      b = c;
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
    requestAnswers[1] = getLCA(a1, a2);

    requestsData[0] = a1;
    requestsData[1] = a2;

    for (int i = 2; i < m * 2; i++) {
      requestsData[i] = getNextNode(requestsData[i - 2], requestsData[i - 1]);
    }

    for (int i = 2; i <= m; i++) {
      int leftV = (requestsData[2 * i - 2] + requestAnswers[i - 1]) % n;
      requestAnswers[i] = getLCA(leftV, requestsData[2 * i - 1]);
    }

  }

  int getNextNode(int e, int f) {
    return (x * e + y * f + z) % n;
  }

  int getSum() {
    int s = 0;
    for (int v : requestAnswers) {
      s += v;
    }
    return s;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();

//    int n = 4;
//    int[] parent = new int[]{0, 0, 1, 2};
    int[] parent = new int[n];
    parent[0] = 0;

    for (int i = 1; i < n; i++) {
      parent[i] = sc.nextInt();
    }
//    int a1 = sc.nextInt();
//    int a2 = sc.nextInt();
//    x = sc.nextInt();
//    y = sc.nextInt();
//    z = sc.nextInt();

    LowestCommonAncestor lca = new LowestCommonAncestor(n, parent);

    System.out.println(lca.getLCA(2,3));

//    lca.getResults(a1, a2);
//    int res = lca.getSum();
//    System.out.println(res);
  }
}