//package dynamic_programming;
//
//import java.util.Scanner;
//
//public class LongestCommonSubsequence {
//
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    String a = sc.nextLine();
//    String b = sc.nextLine();
//
//    int[][] lsc = new int[a.length() + 1][b.length() + 1];
//    String[][] arrows = new String[a.length() + 1][b.length() + 1];
//
//    for (int i = 0; i <= a.length(); i++) {
//      lsc[i][0] = 0;
//    }
//    for (int i = 0; i <= b.length(); i++) {
//      lsc[0][i] = 0;
//    }
//
//    for (int i = 1; i < a.length(); i++) {
//      for (int j = 1; j < b.length(); j++) {
//        if (a.charAt(i) == b.charAt(j)) {
//          lsc[i][j] = 1 + lsc[i - 1][j - 1];
//          arrows[i][j] = "diag";
//        } else {
//          if (lsc[i][j - 1] >= lsc[i - 1][j]) {
//            arrows[i][j] = "left";
//            lsc[i][j] = lsc[i][j - 1];
//          } else {
//            arrows[i][j] = "top";
//            lsc[i][j] = lsc[i - 1][j];
//          }
//        }
//      }
//    }
//
//    System.out.println(lsc[b.length()][a.length()]);
//  }
//}
