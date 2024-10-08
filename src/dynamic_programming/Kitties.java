//package dynamic_programming;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class Kitties {
//  private static Map<Long, Long> groups = new HashMap<>();
//
//  public static long fd(long n, long k) {
//    if (groups.containsKey(n)) return groups.get(n);
//    if (n <= k) return 1;
//    else {
//      long g;
//      if (n % 2 == 0) {
//        g = fd(n / 2, k) + fd(n / 2, k);
//      } else g = fd(n / 2, k) + fd((n + 1) / 2, k);
//      groups.put(n, g);
//      return g;
//    }
//  }
//
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    long n = sc.nextLong();
//    long k = sc.nextLong();
////    long startTime = System.nanoTime();
//
//    long res = fd(n, k);
//    System.out.println(res);
////    long endTime = System.nanoTime();
////    System.out.println("\n\nTime: " + (endTime - startTime) / 1000000 + "ms");
//  }
//}
