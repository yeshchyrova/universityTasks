//package lca_rmq;
//import java.util.*;
//
//public class MinSubmatrix {
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    int n = sc.nextInt(), L = sc.nextInt();
//    int[][] matrix = new int[n][n];
//
//    for (int i = 0; i < n; i++) {
//      for (int j = 0; j < n; j++) {
//        matrix[i][j] = sc.nextInt();
//      }
//    }
//
//    int[][] rowMins = new int[n][n - L + 1];
//    for (int i = 0; i < n; i++) {
//      Deque<Integer> dq = new ArrayDeque<>();
//      for (int j = 0; j < n; j++) {
//        while (!dq.isEmpty() && matrix[i][dq.getLast()] >= matrix[i][j]) {
//          dq.pollLast();
//        }
//        dq.addLast(j);
//        if (j >= L - 1) {
//          if (dq.getFirst() < j - L + 1) {
//            dq.pollFirst();
//          }
//          rowMins[i][j - L + 1] = matrix[i][dq.getFirst()];
//        }
//      }
//    }
//
//    int[][] result = new int[n - L + 1][n - L + 1];
//    for (int j = 0; j < n - L + 1; j++) {
//      Deque<Integer> dq = new ArrayDeque<>();
//      for (int i = 0; i < n; i++) {
//        while (!dq.isEmpty() && rowMins[dq.getLast()][j] >= rowMins[i][j]) {
//          dq.pollLast();
//        }
//        dq.addLast(i);
//        if (i >= L - 1) {
//          if (dq.getFirst() < i - L + 1) {
//            dq.pollFirst();
//          }
//          result[i - L + 1][j] = rowMins[dq.getFirst()][j];
//        }
//      }
//    }
//
//    for (int i = 0; i < n - L + 1; i++) {
//      for (int j = 0; j < n - L + 1; j++) {
//        System.out.print(result[i][j] + " ");
//      }
//      System.out.println();
//    }
//  }
//}
