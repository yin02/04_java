// AutomaticTransmission.java
package transmission;

public class AutomaticTransmission implements Transmission {
    private int speed;
    private int[] thresholds;

    public AutomaticTransmission(int... thresholds) {
        // Ensure that exactly 5 thresholds are provided.
        if (thresholds.length != 5) {
            throw new IllegalArgumentException("Exactly 5 thresholds are required.");
        }

        int prevThreshold = 0;

        // Iterate through each threshold
        for (int threshold : thresholds) {
            // Ensure that the current threshold is greater than the previous one.
            // This ensures that the thresholds are strictly increasing.
            if (threshold <= prevThreshold) {
                throw new IllegalArgumentException("Thresholds must be in increasing order.");
            }
            prevThreshold = threshold;// Update the previous threshold for the next iteration
        }
        // Assign the validated thresholds to the instance variable
        this.thresholds = thresholds;
        this.speed = 0;
    }
    // Increase the speed of the transmission by 2 units.
    @Override
    public Transmission increaseSpeed() {
        this.speed += 2;
        return this;
    }
    // Decrease the speed of the transmission by 2 units.
    // If the speed goes below 0, an exception is thrown.
    @Override
    public Transmission decreaseSpeed() {
        this.speed -= 2;
        if (this.speed < 0) {
            throw new IllegalStateException("Speed cannot be negative.");
        }
        return this;
    }
    // Getter for the speed of the transmission.
    @Override
    public int getSpeed() {
        return speed;
    }
    // Determine the gear based on the current speed and thresholds.
    // If the speed is below a threshold, the gear corresponding to that threshold is selected.
    @Override
    public int getGear() {
        if (speed == 0) {
            return 0; // Return gear 0 if speed is 0
        }
        for (int i = 0; i < thresholds.length; i++) {
            if (speed < thresholds[i]) {
                return i + 1;// Gears are 1-based, so add 1
            }
        }
        return 6;  // For speeds greater than the last threshold.
    }
    // Provide a string representation of the current state of the transmission,
    // including the speed and gear.
    @Override
    public String toString() {
        return String.format("Transmission (speed = %d, gear = %d)", speed, getGear());
    }
}
