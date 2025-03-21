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

    parent[0] = 0;
    for (int v = 0; v < n; v++) {
      up[v][0] = parent[v];
      if (v != 0) {
        depth[v] = depth[parent[v]] + 1;
      }
      for (int j = 1; j < LOG; j++) {
        up[v][j] = up[up[v][j - 1]][j - 1];
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
    int n = 12;
    int[] parent = new int[]{0, 0, 1, 0, 1, 0, 5, 4, 7, 8, 9, 7};
    KthAncestor kthAncestor = new KthAncestor(n, parent);
    int res = kthAncestor.getKthAncestor(6, 1);
    System.out.println("Expected: 5\t\tActual: " + res);
  }
}
