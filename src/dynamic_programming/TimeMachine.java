//package dynamic_programming;
//
//import java.util.*;
//
//public class TimeMachine {
//  private static int[] minOperations;
//  private static int[] fromWhere;
//  private static int n = 1; // years
//
//
//  public static void createDynamicBase() {
//    minOperations = new int[n + 1];
//    minOperations[1] = 0;
//    fromWhere = new int[n + 1];
//    fromWhere[1] = 0;
//  }
//
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    n = sc.nextInt();
//
//    createDynamicBase();
//
//    for (int i = 2; i <= n; i++) {
//      fromWhere[i] = i - 1;
//      minOperations[i] = minOperations[i - 1] + 1;
//
//      if (i % 2 == 0 && minOperations[i / 2] + 1 < minOperations[i]) {
//        fromWhere[i] = i / 2;
//        minOperations[i] = minOperations[i / 2] + 1;
//      }
//
//      if (i % 3 == 0 && minOperations[i / 3] + 1 < minOperations[i]) {
//        fromWhere[i] = i / 3;
//        minOperations[i] = minOperations[i / 3] + 1;
//      }
//    }
//    System.out.println(minOperations[n]);
//    List<Integer> result = new ArrayList<>();
//
//    for (int i = n; i > 0; ) {
//      result.add(i);
//      i = fromWhere[i];
//    }
//
//    for (int i = result.size() - 1; i > 0; i--) {
//      System.out.print(result.get(i) + " ");
//    }
//    System.out.println(result.get(0));
//
//
//  }
//}
