import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AviaryTest {

    private Aviary aviary;

    @Before
    public void setUp() {
        aviary = new Aviary("Test Aviary", "Test Location");
    }

    @Test
    public void testAddBird() {
        Bird bird = new Bird(Bird.BirdType.GENERAL, "Sample Bird", false, 2, Arrays.asList("2.0", "Seeds"));
        assertTrue(aviary.addBird(bird));
    }

    @Test(expected = IllegalStateException.class)
    public void testAddExceedingMaxBirds() {
        for (int i = 0; i < 6; i++) {
            Bird bird = new Bird(Bird.BirdType.GENERAL, "Sample Bird " + i, false, 2, Arrays.asList("2.0", "Seeds"));
            aviary.addBird(bird);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddIncompatibleBird() {
        Bird bird1 = new Bird(Bird.BirdType.FLIGHTLESS, "Sample Flightless Bird", false, 2, Arrays.asList("2.0", "Seeds"));
        Bird bird2 = new Bird(Bird.BirdType.BIRD_OF_PREY, "Sample Bird of Prey", false, 2, Arrays.asList("2.0", "Meat"));
        aviary.addBird(bird1);
        aviary.addBird(bird2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddExtinctBird() {
        Bird extinctBird = new Bird(Bird.BirdType.GENERAL, "Dodo", true, 2, Arrays.asList("2.0", "Seeds"));
        aviary.addBird(extinctBird);
    }

    @Test
    public void testCalculateTotalFoodNeeds() {
        Bird bird1 = new Bird(Bird.BirdType.GENERAL, "Sample Bird 1", false, 2, Arrays.asList("2.0", "Seeds"));
        Bird bird2 = new Bird(Bird.BirdType.GENERAL, "Sample Bird 2", false, 2, Arrays.asList("2.5", "Seeds"));
        aviary.addBird(bird1);
        aviary.addBird(bird2);

        double expectedTotal = bird1.getFoodRequirement() + bird2.getFoodRequirement();
        assertEquals(expectedTotal, aviary.calculateTotalFoodNeeds(), 0.001);  // 0.001 is the delta (margin of error).
    }
}
