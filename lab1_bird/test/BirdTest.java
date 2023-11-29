import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

import java.util.Arrays;

public class BirdTest {

    @Test
    public void testBirdCreation() {
        Bird bird = new Bird(Bird.BirdType.GENERAL, "Small and fast", false, 2, Arrays.asList("seeds", "insects"));
        assertEquals(Bird.BirdType.GENERAL, bird.getType());
        assertEquals("Small and fast", bird.getCharacteristic());
        assertFalse(bird.isExtinct());
        assertEquals(2, bird.getNumberOfWings());
        assertTrue(bird.getPreferredFood().contains("seeds"));
        assertTrue(bird.getPreferredFood().contains("insects"));}

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeNumberOfWings() {
        Bird bird = new Bird(Bird.BirdType.GENERAL, "Typical bird", false, 2, Arrays.asList("seeds"));
        bird.setNumberOfWings(-1);  // This should throw the exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBirdType() {
        Bird bird = new Bird(null, "Some characteristic", false, 2, Arrays.asList("seeds", "insects"));
    }

    @Test
    public void testEmptyPreferredFood() {
        try {
            Bird bird = new Bird(Bird.BirdType.GENERAL, "Small and fast", false, 2, Arrays.asList());
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Preferred food list cannot be null or empty.", e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullPreferredFood() {
        Bird bird = new Bird(Bird.BirdType.GENERAL, "Small and fast", false, 2, null);
    }

    @Test
    public void testFoodRequirement() {
        Bird bird = new Bird(Bird.BirdType.GENERAL, "Small and fast", false, 2, Arrays.asList("5.5", "insects"));
        assertEquals(5.5, bird.getFoodRequirement(), 0.01);
    }

    @Test
    public void testInvalidFoodRequirement() {
        Bird bird = new Bird(Bird.BirdType.GENERAL, "Small and fast", false, 2, Arrays.asList("seeds", "insects"));
        assertEquals(0.0, bird.getFoodRequirement(), 0.01);}

    @Test
    public void testBirdOfPreyCreation() {
        BirdOfPrey birdOfPrey = new BirdOfPrey(
                Bird.BirdType.BIRD_OF_PREY,
                "Large and predatory",
                false,
                2,
                Arrays.asList("small mammals"),
                "Sharp beak",
                true
        );

        assertEquals(Bird.BirdType.BIRD_OF_PREY, birdOfPrey.getType());
        assertEquals("Large and predatory", birdOfPrey.getCharacteristic());
        assertFalse(birdOfPrey.isExtinct());
        assertEquals(2, birdOfPrey.getNumberOfWings());
        assertTrue(birdOfPrey.getPreferredFood().contains("small mammals"));
        assertEquals("Sharp beak", birdOfPrey.getBeakType());
        assertTrue(birdOfPrey.isNostrilsVisible());
    }
    @Test
    public void testFlightlessBirdCreation() {
        FlightlessBird flightlessBird = new FlightlessBird(
                Bird.BirdType.FLIGHTLESS,
                "Cannot fly",
                false,
                2,
                Arrays.asList("plants"),
                true
        );

        assertEquals(Bird.BirdType.FLIGHTLESS, flightlessBird.getType());
        assertEquals("Cannot fly", flightlessBird.getCharacteristic());
        assertFalse(flightlessBird.isExtinct());
        assertEquals(2, flightlessBird.getNumberOfWings());
        assertTrue(flightlessBird.getPreferredFood().contains("plants"));
        assertTrue(flightlessBird.isFlightless());
    }

    @Test
    public void testOwlCreation() {
        Owl owl = new Owl(
                Bird.BirdType.OWL,
                "Nocturnal predator",
                false,
                2,
                Arrays.asList("small mammals"),
                true
        );

        assertEquals(Bird.BirdType.OWL, owl.getType());
        assertEquals("Nocturnal predator", owl.getCharacteristic());
        assertFalse(owl.isExtinct());
        assertEquals(2, owl.getNumberOfWings());
        assertTrue(owl.getPreferredFood().contains("small mammals"));
        assertTrue(owl.isFacialDisks());
    }

    @Test
    public void testParrotCreation() {
        Parrot parrot = new Parrot(
                Bird.BirdType.PARROT,
                "Colorful and talkative",
                false,
                2,
                Arrays.asList("nuts", "fruits"),
                100,
                "Polly wants a cracker"
        );

        assertEquals(Bird.BirdType.PARROT, parrot.getType());
        assertEquals("Colorful and talkative", parrot.getCharacteristic());
        assertFalse(parrot.isExtinct());
        assertEquals(2, parrot.getNumberOfWings());
        assertTrue(parrot.getPreferredFood().contains("nuts"));
        assertTrue(parrot.getPreferredFood().contains("fruits"));
        assertEquals(100, parrot.getVocabularySize());
        assertEquals("Polly wants a cracker", parrot.getFavoriteSaying());
    }


    @Test
    public void testPigeonCreation() {
        Pigeon pigeon = new Pigeon(
                Bird.BirdType.PIGEON,
                "Urban bird",
                false,
                2,
                Arrays.asList("seeds", "bread"),
                true
        );

        assertEquals(Bird.BirdType.PIGEON, pigeon.getType());
        assertEquals("Urban bird", pigeon.getCharacteristic());
        assertFalse(pigeon.isExtinct());
        assertEquals(2, pigeon.getNumberOfWings());
        assertTrue(pigeon.getPreferredFood().contains("seeds"));
        assertTrue(pigeon.getPreferredFood().contains("bread"));
        assertTrue(pigeon.isBirdMilk());
    }

    @Test
    public void testShorebirdCreation() {
        Water waterBody = new Water("Lake");
        Shorebird shorebird = new Shorebird(
                Bird.BirdType.SHOREBIRD,
                "Water bird",
                false,
                2,
                Arrays.asList("fish", "insects"),
                waterBody
        );

        assertEquals(Bird.BirdType.SHOREBIRD, shorebird.getType());
        assertEquals("Water bird", shorebird.getCharacteristic());
        assertFalse(shorebird.isExtinct());
        assertEquals(2, shorebird.getNumberOfWings());
        assertTrue(shorebird.getPreferredFood().contains("fish"));
        assertTrue(shorebird.getPreferredFood().contains("insects"));
        assertEquals(waterBody, shorebird.getWaterBody());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidShorebirdType() {
        Water waterBody = new Water("Lake");
        Shorebird shorebird = new Shorebird(
                Bird.BirdType.GENERAL,
                "Some characteristic",
                false,
                2,
                Arrays.asList("seeds", "insects"),
                waterBody
        );
    }
    @Test
    public void testWaterfowlCreation() {
        Water waterBody = new Water("River");
        Waterfowl waterfowl = new Waterfowl(
                Bird.BirdType.WATERFOWL,
                "Aquatic bird",
                false,
                2,
                Arrays.asList("fish"),
                waterBody
        );

        assertEquals(Bird.BirdType.WATERFOWL, waterfowl.getType());
        assertEquals("Aquatic bird", waterfowl.getCharacteristic());
        assertFalse(waterfowl.isExtinct());
        assertEquals(2, waterfowl.getNumberOfWings());
        assertTrue(waterfowl.getPreferredFood().contains("fish"));
        assertEquals(waterBody, waterfowl.getWaterBody());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidWaterfowlType() {
        Water waterBody = new Water("River");
        Waterfowl waterfowl = new Waterfowl(
                Bird.BirdType.GENERAL,
                "Some characteristic",
                false,
                2,
                Arrays.asList("seeds", "insects"),
                waterBody
        );
    }
}

