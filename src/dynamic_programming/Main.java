package dynamic_programming;

public class Main {
  public static void main(String[] args) {

//  -------------------------------- TASK 1 --------------------------------
    int n = 64;
    BinaryCodeSequence bcs = new BinaryCodeSequence(n);
    long res = bcs.getAmountOfSequences(n);
    System.out.println(res);
    System.out.println("amount of calls: " + bcs.getAmountOfCalls());
  }
}
