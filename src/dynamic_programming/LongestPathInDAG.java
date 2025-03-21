package dynamic_programming;

import java.util.ArrayList;
import java.util.Scanner;

public class LongestPathInDAG {
  private int vertices;
  private ArrayList<Integer>[] edges;

  public LongestPathInDAG(int vertices) {
    this.vertices = vertices;
    edges = new ArrayList[vertices + 1];
    for (int i = 0; i <= vertices; i++) {
      edges[i] = new ArrayList<>();
    }
  }

  public void addEdge(int a, int b) {
    edges[a].add(b);
  }

  private void dfs(int node, int[] dp, boolean[] visited) {
    visited[node] = true;

    for (int neighbor : edges[node]) {
      if (!visited[neighbor]) {
        dfs(neighbor, dp, visited);
      }
      dp[node] = Math.max(dp[node], 1 + dp[neighbor]);
    }
  }

  public int findLongestPath() {
    int[] dp = new int[vertices + 1];
    boolean[] visited = new boolean[vertices + 1];

    for (int i = 1; i <= vertices; i++) {
      if (!visited[i]) {
        dfs(i, dp, visited);
      }
    }

    int longestPath = 0;
    for (int i = 1; i <= vertices; i++) {
      longestPath = Math.max(longestPath, dp[i]);
    }
    return longestPath;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();
    int m = scanner.nextInt();

    LongestPathInDAG graph = new LongestPathInDAG(n);
    for (int i = 0; i < m; i++) {
      int u = scanner.nextInt();
      int v = scanner.nextInt();
      graph.addEdge(u, v);
    }
    System.out.println(graph.findLongestPath());
    scanner.close();
  }
}
