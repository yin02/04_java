package transmission;

public interface Transmission {
    Transmission increaseSpeed();
    Transmission decreaseSpeed();
    /**
     * Get the current speed of the transmission.
     *
     * @return the current speed.
     */
    int getSpeed();

    /**
     * Get the current gear based on the speed.
     *
     * @return the current gear.
     */
    int getGear();

    /**
     * Get a string representation of the current state of the transmission.
     *
     * @return the string representation.
     */
    @Override
    String toString();
}
