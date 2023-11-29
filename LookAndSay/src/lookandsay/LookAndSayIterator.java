package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;

public class LookAndSayIterator implements RIterator<BigInteger> {
    private BigInteger current;
    private final BigInteger end;
    private boolean isFirstElement;

    public LookAndSayIterator(BigInteger seed, BigInteger end) {
        // Check if the seed is positive
        if (seed.compareTo(BigInteger.ONE) < 0) {
            throw new IllegalArgumentException("Seed must be positive");
        }

        // Check if the seed is less than the end value
        if (seed.compareTo(end) >= 0) {
            throw new IllegalArgumentException("Seed must be less than the end value");
        }

        // Check if the seed contains any zeroes
        if (seed.toString().contains("0")) {
            throw new IllegalArgumentException("Seed must not contain zeroes");
        }

        // Initialize the iterator with the validated seed and end value
        this.current = seed;
        this.end = end;
        this.isFirstElement = true;
    }

    public LookAndSayIterator(BigInteger seed) {
        // Check if the seed is positive
        if (seed.compareTo(BigInteger.ONE) < 0) {
            throw new IllegalArgumentException("Seed must be positive");
        }

        // Check if the seed is less than the end value
        BigInteger endValue = new BigInteger("9".repeat(100));
        if (seed.compareTo(endValue) >= 0) {
            throw new IllegalArgumentException("Seed must be less than the end value");
        }

        // Check if the seed contains any zeroes
        if (seed.toString().contains("0")) {
            throw new IllegalArgumentException("Seed must not contain zeroes");
        }

        // Initialize the iterator with the validated seed and end value
        this.current = seed;
        this.end = endValue;
        this.isFirstElement = true;
    }


    public LookAndSayIterator() {
        // Initialize the iterator with a seed of 1 and the end value with 100 9s
        this(BigInteger.ONE, new BigInteger("9".repeat(100)));
    }


    @Override
    public boolean hasNext() {
        // Calculate the next number without actually advancing the iterator
        BigInteger next = generateNext(current);

        // Check if the next number is less than the end value
        return next.compareTo(end) < 0;
    }


    @Override
    public BigInteger next() {
        if (!hasNext()) throw new NoSuchElementException("No next element in sequence");

        if (isFirstElement) {
            isFirstElement = false;
        } else {
            current = generateNext(current);
        }
        return current;
    }

    @Override
    public boolean hasPrevious() {
        // Assuming it's always possible to go back unless it's the first element
        return !isFirstElement;
    }

    @Override
    public BigInteger prev() {
        if (!hasPrevious()) {
            throw new NoSuchElementException("No previous element in sequence");
        }

        // Store the current number
        BigInteger currentNumber = current;

        // Handling the first element
        if (isFirstElement) {
            isFirstElement = false;
            // If it's the first element, return it as is
        } else {
            if (current.compareTo(end) >= 0) {
                // If the current element is greater than or equal to end, return end
                current = end;
            } else {
                current = generatePrevious(current);
            }
        }

        // Update the isFirstElement flag based on the new state
        isFirstElement = determineIfFirstElement(current);

        // Return the stored current number
        return currentNumber;
    }




    private boolean determineIfFirstElement(BigInteger number) {
        // Implement logic to determine if the given number should be considered the first element
        // This might involve checking if the number is the start of the sequence or other criteria
        return number.equals(BigInteger.ONE); // Simple example, adjust as needed
    }



    private BigInteger generateNext(BigInteger current) {
        String currentStr = current.toString();
        StringBuilder nextStr = new StringBuilder();

        for (int i = 0; i < currentStr.length();) {
            char digit = currentStr.charAt(i);
            int count = 0;

            while (i < currentStr.length() && currentStr.charAt(i) == digit) {
                count++;
                i++;
            }

            nextStr.append(count).append(digit);
        }

        return new BigInteger(nextStr.toString());
    }

    private BigInteger generatePrevious(BigInteger current) {
        String currentStr = current.toString();
        StringBuilder previousStr = new StringBuilder();

        int i = 0;
        while (i < currentStr.length()) {
            int count = Character.getNumericValue(currentStr.charAt(i));
            char digit = currentStr.charAt(i + 1);

            for (int j = 0; j < count; j++) {
                previousStr.append(digit);
            }

            i += 2; // Move to the next pair of count and digit
        }

        return new BigInteger(previousStr.toString());
    }

}
