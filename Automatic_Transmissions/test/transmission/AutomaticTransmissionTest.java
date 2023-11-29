package transmission;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
//Ensure the AutomaticTransmission class correctly initializes with valid input.
//        Validate that speed increases and decreases correctly.
//        Confirm that gear changes occur at the right thresholds.
//        Handle boundary and invalid inputs gracefully.
public class AutomaticTransmissionTest {

    private AutomaticTransmission transmission;

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidThresholdCount() {
        // Provide fewer than 5 thresholds, expecting an IllegalArgumentException
        new AutomaticTransmission(10, 20, 30);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonIncreasingThresholds() {
        // Provide thresholds that aren't strictly increasing, expecting an IllegalArgumentException
        new AutomaticTransmission(10, 20, 20, 40, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecreasingThresholds() {
        // Provide decreasing thresholds, expecting an IllegalArgumentException
        new AutomaticTransmission(50, 40, 30, 20, 10);
    }


    @Before
    public void setUp() {
        transmission = new AutomaticTransmission(10, 20, 30, 40, 50);
    }

    @Test
    public void testInitialValues() {
        assertEquals(0, transmission.getSpeed());
        assertEquals(1, transmission.getGear());
    }

    @Test
    public void testIncreaseSpeed() {
        transmission.increaseSpeed();
        assertEquals(2, transmission.getSpeed());
        assertEquals(1, transmission.getGear());

        for (int i = 0; i < 5; i++) {
            transmission.increaseSpeed();
        }
        assertEquals(12, transmission.getSpeed());
        assertEquals(2, transmission.getGear());
    }

    @Test
    public void testDecreaseSpeed() {
        for (int i = 0; i < 6; i++) {
            transmission.increaseSpeed();
        }
        transmission.decreaseSpeed();
        assertEquals(10, transmission.getSpeed());
        assertEquals(2, transmission.getGear());
    }

    @Test(expected = IllegalStateException.class)
    public void testNegativeSpeedException() {
        transmission.decreaseSpeed();
    }

    @Test
    public void testGears() {
        for (int i = 0; i < 9; i++) {
            transmission.increaseSpeed();
        }
        assertEquals(18, transmission.getSpeed());
        assertEquals(2, transmission.getGear());

        transmission.increaseSpeed();
        assertEquals(20, transmission.getSpeed());
        assertEquals(3, transmission.getGear());
    }

    @Test
    public void testToString() {
        assertEquals("Transmission (speed = 0, gear = 1)", transmission.toString());
        transmission.increaseSpeed();
        assertEquals("Transmission (speed = 2, gear = 1)", transmission.toString());
    }
}
