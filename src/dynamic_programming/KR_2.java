package dynamic_programming;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KR_2 {
  private static Map<Long, Long> res = new HashMap<>();

  public static long f(long n) {
    if (n <= 2) return 1;
    if (res.containsKey(n)) return res.get(n);

    long s;

    if (n % 2 == 1) {
      s = f((long) Math.floor(6.0 * n / 7)) + f((long) Math.floor(2.0 * n / 3));
    } else {
      s = f(n - 1) + f(n - 3);
    }
    res.put(n, s % (long) Math.pow(2, 32));
    s = s % (long) Math.pow(2, 32);
    return s;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long n = sc.nextLong();

    System.out.println(f(n));
  }
}
