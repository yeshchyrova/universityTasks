package dynamic_programming;

//? Задача: знайти кількість послідовностей довжиною n, що складається з 0 та 1. При чому в цій
//? послідовності не може бути дві одиниці підряд

//! покрыть тестами

public class BinaryCodeSequence {
  private final long[] values;
  private int amountOfCalls = 0;

//  returns an amount of getAmountOfSequences calls
  public int getAmountOfCalls() {
    return amountOfCalls;
  }

  public BinaryCodeSequence(int n) {
    if(n<=0) throw new IllegalArgumentException("'n' must be bigger than zero");
    values = new long[n];

//  визначається база динаміки
    values[0] = 2; // для n = 1
    if (n > 1) values[1] = 3; // для n = 2
  }

  public long getAmountOfSequences(int n) {
    amountOfCalls++;
    if (values[n - 1] != 0) return values[n - 1];
    values[n - 1] = getAmountOfSequences(n - 1) + getAmountOfSequences(n - 2);
    return values[n - 1];
  }


}
