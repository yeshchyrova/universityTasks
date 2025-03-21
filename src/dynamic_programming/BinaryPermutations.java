//package dynamic_programming;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Scanner;
//
//public class BinaryPermutations {
//  private static int[] maxA;
//  private static int[] maxB;
//  private static int[] maxC;
//
//  public static int[] intToBinary(int n) {
//    if (n == 0) {
//      return new int[]{0};
//    }
//
//    ArrayList<Integer> binaryList = new ArrayList<>();
//    while (n > 0) {
//      binaryList.add(n % 2);
//      n /= 2;
//    }
//
//    Collections.reverse(binaryList);
//
//    int[] binaryArray = new int[binaryList.size()];
//    for (int i = 0; i < binaryList.size(); i++) {
//      binaryArray[i] = binaryList.get(i);
//    }
//    return binaryArray;
//  }
//
//  public static int binaryArrayToDecimal(int[] binaryArray) {
//    int decimal = 0;
//    int length = binaryArray.length;
//
//    for (int i = 0; i < length; i++) {
//      decimal += (binaryArray[i] * Math.pow(2, length - 1 - i));
//    }
//
//    return decimal;
//  }
//
//  public static int[] padArray(int[] array, int maxLength) {
//    int[] temp = new int[maxLength];
//    System.arraycopy(array, 0, temp, maxLength - array.length, array.length);
//    return temp;
//  }
//
//  public static int[][] createMatrix(int[] aBin, int[] bBin, int[] cBin) {
//    int maxLength = Math.max(aBin.length, Math.max(bBin.length, cBin.length));
//
//    aBin = padArray(aBin, maxLength);
//    bBin = padArray(bBin, maxLength);
//    cBin = padArray(cBin, maxLength);
//
//    maxA = maxPossibleNumber(aBin);
//    maxB = maxPossibleNumber(bBin);
//    maxC = maxPossibleNumber(cBin);
//
//    int[][] matrix = new int[4][maxLength];
//    matrix[0] = new int[maxLength];
//    matrix[1] = minPossibleNumber(aBin);
//    matrix[2] = minPossibleNumber(bBin);
//    matrix[3] = minPossibleNumber(cBin);
//
//    return matrix;
//  }
//
//  public static boolean isCorrect(int[][] matrix) {
//    int sum;
//    Arrays.fill(matrix[0], 0);
//    for (int i = matrix[0].length - 1; i >= -1; i--) {
//
//      if (i == -1) {
//        return true;
//      }
//
//      sum = matrix[0][i] + matrix[1][i] + matrix[2][i];
//
//      if (sum != matrix[3][i]) {
//        if (sum == matrix[3][i] + 2) {
//          if (i > 0) {
//            matrix[0][i - 1] = 1;
//          } else {
//            return false;
//          }
//        } else {
//          return false;
//        }
//      }
//    }
//    return true;
//  }
//
//  public static void check(int[][] matrix) {
//    while (true) {
//      boolean isCorrect = isCorrect(matrix);
//
//      if (isCorrect) {
//        System.out.println(binaryArrayToDecimal(matrix[3]));
//        return;
//      }
//
//      // Проверяем, достигли ли мы максимальных значений для всех массивов
//      if (Arrays.equals(matrix[1], maxA) && Arrays.equals(matrix[2], maxB)) {
//        if (Arrays.equals(matrix[3], maxC)) {
//          System.out.println(-1);
//          return;
//        }
//        minPossibleNumber(matrix[1]);
//        minPossibleNumber(matrix[2]);
//        increaseNumber(matrix[3]);
//      } else if (Arrays.equals(matrix[1], maxA)) {
//        minPossibleNumber(matrix[1]);
//        increaseNumber(matrix[2]);
//      } else {
//        increaseNumber(matrix[1]);
//      }
//    }
//  }
//
//  public static void increaseNumber(int[] n) {
//    for (int i = n.length - 1; i >= 0; i--) {
//      if (n[i] == 0) {
//        n[i] = 1;
//        for (int j = i + 1; j < n.length; j++) {
//          n[j] = 0;
//        }
//        return;
//      }
//    }
//  }
//
//  public static int[] minPossibleNumber(int[] n) {
//    int amountOfOnes = 0;
//    for (int j : n) {
//      if (j == 1) amountOfOnes++;
//    }
//
//    for (int i = n.length - 1; i >= 0; i--) {
//      if (i < n.length - amountOfOnes) n[i] = 0;
//      else n[i] = 1;
//    }
//
//    return n;
//  }
//
//  public static int[] maxPossibleNumber(int[] n) {
//    int amountOfOnes = 0;
//    int[] t = new int[n.length];
//    System.arraycopy(n, 0, t, 0, n.length);
//
//    for (int j : n) {
//      if (j == 1) amountOfOnes++;
//    }
//
//    for (int i = 0; i < n.length; i++) {
//      if (i < amountOfOnes) t[i] = 1;
//      else t[i] = 0;
//    }
//    return t;
//  }
//
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    int a = 170016738;
//    int b = 902057161;
//    int c = 265484073;
//
//    int[] aBin = intToBinary(a);
//    int[] bBin = intToBinary(b);
//    int[] cBin = intToBinary(c);
//
//    int[][] matrix = createMatrix(aBin, bBin, cBin);
//
//    check(matrix);
//  }
//}
