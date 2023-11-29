package bignumber;

public interface BigNumber {
    int length();
    void shiftLeft(int positions);
    void shiftRight(int positions);
    void addDigit(int digit) throws IllegalArgumentException;
    int getDigitAt(int position) throws IllegalArgumentException;
    BigNumber copy();
    BigNumber add(BigNumber other);
    int compareTo(BigNumber other);
}
