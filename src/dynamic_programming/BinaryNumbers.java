package dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BinaryNumbers {
  private static int[] maxA;
  private static int[] maxB;
  private static int[] maxC;
  private static int[] minA;
  private static int[] minB;
  private static int[] minC;

  public static int[] intToBinary(int n) {
    int size = 0;
    int temp = n;
    while (temp > 0) {
      temp /= 2;
      size++;
    }

    int[] binaryArray = new int[size];
    for (int i = size - 1; i >= 0; i--) {
      binaryArray[i] = n % 2;
      n /= 2;
    }

    return binaryArray;
  }

  public static int binaryArrayToDecimal(int[] binaryArray) {
    int decimal = 0;
    for (int i = 0; i < binaryArray.length; i++) {
      decimal = decimal * 2 + binaryArray[i];
    }
    return decimal;
  }


  public static int[] padArray(int[] array, int maxLength) {
    int[] temp = new int[maxLength];
    System.arraycopy(array, 0, temp, maxLength - array.length, array.length);
    return temp;
  }

  public static int[][] createMatrix(int[] aBin, int[] bBin, int[] cBin) {
    int maxLength = Math.max(aBin.length, Math.max(bBin.length, cBin.length));

    aBin = padArray(aBin, maxLength);
    bBin = padArray(bBin, maxLength);
    cBin = padArray(cBin, maxLength);

    maxA = maxPossibleNumber(aBin);
    maxB = maxPossibleNumber(bBin);
    maxC = maxPossibleNumber(cBin);

    int[][] matrix = new int[4][maxLength];
    matrix[0] = new int[maxLength];
    minA = minPossibleNumber(aBin);
    minB = minPossibleNumber(bBin);
    minC = minPossibleNumber(cBin);

    matrix[1] = minA;
    matrix[2] = minB;
    matrix[3] = minC;
    return matrix;
  }

  public static boolean isCorrect(int[][] matrix) {
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
//        if (matrix[3][i] + 2 == sum) {
//          if (i > 0) {
//            matrix[0][i - 1] = 1;
//          } else return false;
//        } else return false;
//      }
//    }
//    return false;

    int sum = binaryArrayToDecimal(matrix[1]) + binaryArrayToDecimal(matrix[2]);
    return sum == binaryArrayToDecimal(matrix[3]);
  }

  public static void check(int[][] matrix) {
    while (true) {
      boolean isCorrect = isCorrect(matrix);

      if (isCorrect) {
        System.out.println(binaryArrayToDecimal(matrix[3]));
        return;
      }

      boolean isAMax = Arrays.equals(matrix[1], maxA);
      boolean isBMax = Arrays.equals(matrix[2], maxB);
      boolean isCMax = Arrays.equals(matrix[3], maxC);

      if (isAMax && isBMax) {
        if (isCMax) {
          System.out.println(-1);
          return;
        }
        minPossibleNumber(matrix[1]);
        minPossibleNumber(matrix[2]);
//        minimize(matrix[1], 1);
//        minimize(matrix[2], 2);
        increaseNumber(matrix[3]);
//        System.out.println("Updated a: " + Arrays.toString(matrix[1]));
//        System.out.println("Updated b: " + Arrays.toString(matrix[2]));
//        System.out.println("Updated c: " + Arrays.toString(matrix[3]));
      } else if (isAMax) {
        minPossibleNumber(matrix[1]);
//        minimize(matrix[1], 1);
        increaseNumber(matrix[2]);
//        System.out.println("Updated a: " + Arrays.toString(matrix[1]));
//        System.out.println("Updated b: " + Arrays.toString(matrix[2]));
      } else {
        increaseNumber(matrix[1]);
//        System.out.println("Updated a: " + Arrays.toString(matrix[1]));

      }
    }
  }

  public static void minimize(int[] n, int type) {
    if (type == 1) {
      System.arraycopy(minA, 0, n, 0, n.length);
      return;
    }
    if (type == 2) {
      System.arraycopy(minB, 0, n, 0, n.length);
      return;
    }
    if (type == 3) {
      System.arraycopy(minC, 0, n, 0, n.length);
    }
  }


  public static void increaseNumber(int[] n) {
    for (int i = n.length - 1; i >= 0; i--) {

      if (n[i] == 0) {
        int oneIdx = -1;
        for (int j = i + 1; j < n.length; j++) {
          if (n[j] == 1) {
            oneIdx = j;
            break;
          }
        }

        if (oneIdx != -1) {
          n[oneIdx] = 0;
          n[i] = 1;
          partMinimize(n, oneIdx);
          return;
        }
      }
    }
  }


  public static void partMinimize(int[]n, int idx) {
    int amountOfOnes = 0;
    for (int j = idx ; j < n.length; j++) {
      if (n[j] == 1) amountOfOnes++;
    }
//    System.out.println("Amount of ones in the beginning: " + amountOfOnes);


    for (int i = n.length - 1; i >= idx; i--) {
      if(amountOfOnes != 0 ) {
        n[i] = 1;
        amountOfOnes--;
      } else n[i] = 0;
    }
  }

  //public
  public static int[] minPossibleNumber(int[] n) {
    int amountOfOnes = 0;
    for (int j : n) {
      if (j == 1) amountOfOnes++;
    }

    for (int i = n.length - 1; i >= 0; i--) {
      if (i < n.length - amountOfOnes) n[i] = 0;
      else n[i] = 1;
    }
    return n;
  }


  public static int[] maxPossibleNumber(int[] n) {
    int amountOfOnes = 0;
    int[] t = new int[n.length];
    System.arraycopy(n, 0, t, 0, n.length);

    for (int j : n) {
      if (j == 1) amountOfOnes++;
    }

    for (int i = 0; i < n.length; i++) {
      if (i < amountOfOnes) t[i] = 1;
      else t[i] = 0;
    }
    return t;
  }


  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    int a = sc.nextInt();
//    int b = sc.nextInt();
//    int c = sc.nextInt();
    int a = 170016738;
    int b = 902057161;
    int c = 265484073;

    int[] aBin = intToBinary(a);
    int[] bBin = intToBinary(b);
    int[] cBin = intToBinary(c);

    int[][] matrix = createMatrix(aBin, bBin, cBin);
    check(matrix);
//int[] test = {0,1,1,1,0};
//increaseNumber(test);
//    System.out.println(Arrays.toString(test));
  }
}

