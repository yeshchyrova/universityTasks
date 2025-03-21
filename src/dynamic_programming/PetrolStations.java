package dynamic_programming;

import java.util.Scanner;


public class PetrolStations {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // Входные данные
    int n = sc.nextInt();  // количество домов
    int m = sc.nextInt();  // количество зарядных станций
    int[] houses = new int[n];  // координаты домов

    for (int i = 0; i < n; i++) {
      houses[i] = sc.nextInt();
    }

    // Таблица для хранения стоимости размещения станции на отрезке домов
    int[][] cost = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        int mid = (i + j) / 2;  // выбираем средний дом для минимизации расстояний
        for (int k = i; k <= j; k++) {
          cost[i][j] += Math.abs(houses[k] - houses[mid]);
        }
      }
    }

    // Динамическое программирование
    int[][] dp = new int[m + 1][n + 1];  // dp[заправок][домов]
    int[][] stations = new int[m + 1][n + 1];  // для хранения координат заправок

    // Инициализация таблицы dp с максимальными значениями
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        dp[i][j] = Integer.MAX_VALUE;
      }
    }
    dp[0][0] = 0;

    // Заполнение таблицы DP
    for (int k = 1; k <= m; k++) {
      for (int i = k; i <= n; i++) {
        for (int j = k - 1; j < i; j++) {
          int currentCost = dp[k - 1][j] + cost[j][i - 1];
          if (currentCost < dp[k][i]) {
            dp[k][i] = currentCost;
            stations[k][i] = j;  // запоминаем позицию последней станции
          }
        }
      }
    }

    // Общая минимальная сумма расстояний
    System.out.println(dp[m][n]);

    // Восстановление координат заправок
    int[] result = new int[m];
    int idx = n;
    for (int i = m; i > 0; i--) {
      idx = stations[i][idx];
      result[i - 1] = houses[(idx + n) / 2];
    }

    // Вывод координат заправок в возрастающем порядке
    for (int i = 0; i < m; i++) {
      System.out.print(result[i] + " ");
    }
  }}
