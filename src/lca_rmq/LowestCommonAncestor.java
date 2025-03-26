package lca_rmq;

public class LowestCommonAncestor {
  private final int[][] up;
  private final int[] depth;
  private int LOG;

  public LowestCommonAncestor(int n, int[] parent) {
    LOG = 0;
    while ((1 << LOG) <= n) {
      LOG++;
    }
    up = new int[n][LOG];
    depth = new int[n];

    for (int i = 0; i < n; i++) {
      up[i][0] = parent[i];
      if (i != 0) {
        depth[i] = depth[parent[i]] + 1;
      }
      for (int j = 1; j < LOG; j++) {
        up[i][j] = up[up[i][j - 1]][j - 1];
      }
    }
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


  public static void main(String[] args) {
    int n = 10;
    int[] parent = new int[]{0, 0, 8, 0, 0, 3, 0, 3, 1, 5};
    LowestCommonAncestor lca = new LowestCommonAncestor(n, parent);
    int res = lca.getLCA(9,7);
    System.out.println(res);
  }
}