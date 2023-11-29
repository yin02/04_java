package lookandsay;
import java.math.BigInteger;
import java.util.NoSuchElementException;

public class LookAndSayIterator implements RIterator<BigInteger> {
    BigInteger current;
    BigInteger endValue;
    public LookAndSayIterator(BigInteger seed, BigInteger endValue) {
        validateSeed(seed);
        if (seed.compareTo(endValue) >= 0) {
            throw new IllegalArgumentException("The input seed must be less than the end value");
        }
        this.current = seed;
        this.endValue = endValue;
    }
    public LookAndSayIterator(BigInteger seed) {
        this(seed, new BigInteger("9".repeat(100)));  // Default end value
    }

    public LookAndSayIterator() {
        this.current = BigInteger.ONE;
        this.endValue = new BigInteger("9".repeat(100));  // A large end value
    }

    private void validateSeed(BigInteger seed) {
        if (seed.compareTo(BigInteger.ONE) < 0 || seed.toString().contains("0")) {
            throw new IllegalArgumentException("Invalid seed: The input seed must be positive and contain no zero(s)");
        }
    }


    @Override
    public BigInteger prev() {
        if (this.current == null) {
            return null; // No previous element if current is null
        }

        BigInteger currentNumber = this.current;
        StringBuilder previousNumberBuilder = new StringBuilder();
        String currentNumberStr = currentNumber.toString();
        int index = 0;

        while (index < currentNumberStr.length()) {
            int count = currentNumberStr.charAt(index) - '0';
            index++;

            if (index < currentNumberStr.length()) {
                int digit = currentNumberStr.charAt(index) - '0';
                index++;

                for (int j = 0; j < count; j++) {
                    previousNumberBuilder.append(digit);
                }
            } else {
                this.current = null;
                return currentNumber;
            }
        }

        BigInteger previousNumber = new BigInteger(previousNumberBuilder.toString());
        this.current = previousNumber;

        return currentNumber;
    }



    @Override
    public boolean hasPrevious() {
        if (this.current == null) {
            return false;  // No previous element if current is null
        }

        String currentString = this.current.toString();
        StringBuilder previousSequenceBuilder = new StringBuilder();

        for (int i = 0; i < currentString.length(); i++) {
            if (i + 1 >= currentString.length()) {
                // If the string has an odd length or is in an incorrect format
                return false;  // Sequence is not valid for Look-and-Say reverse operation
            }

            int count = Character.getNumericValue(currentString.charAt(i));
            char digit = currentString.charAt(i + 1);
            previousSequenceBuilder.append(String.valueOf(digit).repeat(count));
            i++;  // Increment i to skip over the digit after processing
        }

        BigInteger previous = new BigInteger(previousSequenceBuilder.toString());
        return previous.compareTo(this.endValue) < 0;  // Check if the previous number is less than endValue
    }


    @Override
    public boolean hasNext() {
        return this.current != null && this.current.compareTo(this.endValue) < 0;
    }


    @Override
    public BigInteger next() {
        BigInteger originalCurrent = this.current;
        StringBuilder nextSequenceBuilder = new StringBuilder();
        String currentString = originalCurrent.toString();

        for (int i = 0; i < currentString.length();) {
            int digit = Character.getNumericValue(currentString.charAt(i));
            int count = 1;
            i++;

            while (i < currentString.length() && Character.getNumericValue(currentString.charAt(i)) == digit) {
                count++;
                i++;
            }

            nextSequenceBuilder.append(count).append(digit);
        }

        this.current = new BigInteger(nextSequenceBuilder.toString());
        return originalCurrent;
    }

}
