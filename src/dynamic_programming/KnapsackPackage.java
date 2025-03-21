package dynamic_programming;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KnapsackPackage {
  public static long countKnapsackCombinations(int[] weights, int maxWeight) {
    Map<Integer, Long> dp = new HashMap<>();
    dp.put(0, 1L);

    for (int weight : weights) {
      Map<Integer, Long> newDp = new HashMap<>();
      for (Map.Entry<Integer, Long> entry : dp.entrySet()) {
        int currentWeight = entry.getKey();
        long c = entry.getValue();

        int newWeight = currentWeight + weight;
        if (newWeight <= maxWeight) {
          newDp.put(newWeight, newDp.getOrDefault(newWeight, 0L) + c);
        }
      }
      for (Map.Entry<Integer, Long> entry : newDp.entrySet()) {
        dp.put(entry.getKey(), dp.getOrDefault(entry.getKey(), 0L) + entry.getValue());
      }
    }

    long res = 0;
    for (Map.Entry<Integer, Long> entry : dp.entrySet()) {
      res += entry.getValue();
    }
    return res;

  }

//  30
//          983456783 876543210 765432198 654321987 543219876 432198765 321987654 219876543 109876432 99876543
//          98765432 87654321 76543210 65432109 54321098 43210987 32109876 21098765 10987654 9876543
//          8765432 7654321 6543210 5432109 4321098 3210987 2109876 1098765 987654 876543
//          123456780


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] weights = new int[n];
    for (int i = 0; i < n; i++) {
      weights[i] = sc.nextInt();
    }
    int maxWeight = sc.nextInt();

    long startTime = System.nanoTime();

    System.out.println(countKnapsackCombinations(weights, maxWeight));

    long endTime = System.nanoTime();
    System.out.println("\n\nTime: " + (endTime - startTime) / 1000000 + "ms");
  }
}
