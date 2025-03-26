package lca_rmq;

public class KthAncestor {
  private int[][] up;
  private int[] depth;
  private int LOG;

  public KthAncestor(int n, int[] parent) {
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

  public int getKthAncestor(int node, int k) {
    if (depth[node] < k) return -1;

    for (int j = LOG - 1; j >= 0; j--) {
      if (k >= (1 << j)) {
        node = up[node][j];
        k -= 1 << j;
      }
    }
    return node;
  }

  public static void main(String[] args) {
    int n = 3;
    int[] parent = new int[]{0, 0, 0};
    KthAncestor kthAncestor = new KthAncestor(n, parent);
    int res = kthAncestor.getKthAncestor(1,2);
    System.out.println("Expected: 0\t\tActual: " + res);
  }
}
