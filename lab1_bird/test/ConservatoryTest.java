import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class ConservatoryTest {
    private Conservatory conservatory;
    private Bird bird;
    private Aviary aviary;
    private Bird createBirdForTest() {
        return new Bird(Bird.BirdType.GENERAL, "Sample Bird", false, 2, Arrays.asList("2.0", "Seeds"));
    }

    @Before
    public void setup() {
        conservatory = new Conservatory();
        aviary = new Aviary("Tropical", "Location A");
        Bird bird1 = new Bird(Bird.BirdType.GENERAL, "Characteristic 1", false, 2, Arrays.asList("Food1"));
        Bird bird2 = new Bird(Bird.BirdType.GENERAL, "Characteristic 2", false, 2, Arrays.asList("Food2"));
        aviary.addBird(bird1);
        aviary.addBird(bird2);
    }

    @Test
    public void testAssignBirdToAviary() {
        Bird bird = createBirdForTest();
        assertTrue(conservatory.assignBirdToAviary(bird));
    }

    @Test
    public void testAssignBirdToFullAviary() {
        // Fill the conservatory with the maximum number of aviaries
        conservatory.setAviariesSize(conservatory.getMaxAviaries());

        // Fill all aviaries with birds to ensure they are full
        for (int i = 0; i < conservatory.getMaxAviaries(); i++) {
            Aviary aviary = conservatory.getAviaries().get(i);
            while (!aviary.isFull()) {
                Bird birdToAssign = createBirdForTest();
                aviary.addBird(birdToAssign);
            }
        }

        // Now, try to assign one more bird, this should throw a RuntimeException
        assertThrows(RuntimeException.class, () -> conservatory.assignBirdToAviary(bird));
    }





    @Test
    public void testRescueBird() {
        Bird bird = createBirdForTest();
        assertTrue(conservatory.rescueBird(bird));
    }

    @Test
    public void testCalculateFoodNeeds() {
        Bird bird1 = createBirdForTest();
        Bird bird2 = createBirdForTest();
        bird2.setPreferredFood(Arrays.asList("2.5", "Seeds"));

        conservatory.assignBirdToAviary(bird1);
        conservatory.assignBirdToAviary(bird2);

        double expectedTotalFoodNeeds = bird1.getFoodRequirement() + bird2.getFoodRequirement();
        assertEquals(expectedTotalFoodNeeds, conservatory.calculateFoodNeeds(), 0.01);
    }

    @Test
    public void testFindAviaryByBirdType() {
        Bird bird = createBirdForTest();
        conservatory.assignBirdToAviary(bird);

        Aviary aviary = conservatory.findAviaryByBirdType(bird.getType().name());
        assertNotNull(aviary);
        assertTrue(aviary.containsBird(bird.getType().name()));
    }

    @Test
    public void testGenerateSignForAviary() {
        // Expected result based on the provided actual output
        String expected = "======= AVIARY SIGN =======\n"
                + "Aviary Type: Tropical\n"
                + "Type: GENERAL, Characteristic: Characteristic 1, Is Extinct: No, Number of Wings: 2, Preferred Food: [Food1]\n"
                + "Type: GENERAL, Characteristic: Characteristic 2, Is Extinct: No, Number of Wings: 2, Preferred Food: [Food2]\n"
                + "===========================\n";

        // Call the method
        String result = conservatory.generateSignForAviary(aviary);

        // Assert the expected result
        assertEquals(expected, result);
    }




    @Test
    public void testGenerateAviaryMap() {
        // Setup data
        Aviary aviary1 = new Aviary("Test Aviary 1", "North Wing");
        Bird bird1 = new Bird(Bird.BirdType.GENERAL, "Sample Bird 1", false, 2, Arrays.asList("2.0", "Seeds"));
        aviary1.addBird(bird1);
        conservatory.getAviaries().add(aviary1);

        Aviary aviary2 = new Aviary("Test Aviary 2", "South Wing");
        Bird bird2 = new Bird(Bird.BirdType.BIRD_OF_PREY, "Sample Bird of Prey", false, 2, Arrays.asList("2.0", "Meat"));
        aviary2.addBird(bird2);
        conservatory.getAviaries().add(aviary2);

        // Generate map
        String generatedMap = conservatory.generateAviaryMap();

        assertTrue(generatedMap.contains("North Wing - Test Aviary 1: GENERAL"));
        assertTrue(generatedMap.contains("South Wing - Test Aviary 2: BIRD_OF_PREY"));
    }



    @Test
    public void testPrintIndex() {
        Bird bird1 = createBirdForTest();
        Bird bird2 = createBirdForTest();
        conservatory.assignBirdToAviary(bird1);
        conservatory.assignBirdToAviary(bird2);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        conservatory.printIndex();
        String printedOutput = outputStream.toString();

        // Check if the printed output contains bird types
        assertTrue(printedOutput.contains(bird1.getType().name()));
        assertTrue(printedOutput.contains(bird2.getType().name()));

        // Restore System.out
        System.setOut(System.out);
    }


}