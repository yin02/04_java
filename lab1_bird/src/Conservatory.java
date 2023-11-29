import java.util.ArrayList;
import java.util.List;

public class Conservatory {
    private List<Aviary> aviaries;
    private final int MAX_AVIARIES = 20;
    // Constructor to initialize the Conservatory with an empty list of aviaries
    public Conservatory() {
        this.aviaries = new ArrayList<>();
    }

    public List<Aviary> getAviaries() {
        return aviaries;
    }
    // Method to set the size of the aviaries list
// Purpose: Allows resizing the number of aviaries in the conservatory.
// Arguments: newSize - The new size to set for the aviaries list.
// Return Value: None
// Exceptions: None
// Changes to Calling Object: Modifies the aviaries list to match the new size.
    public void setAviariesSize(int newSize) {
        if (newSize >= 0) {
            // Resize the aviaries list accordingly
            while (aviaries.size() < newSize) {
                aviaries.add(new Aviary("Generic Aviary", "Generic Location"));
            }
            while (aviaries.size() > newSize) {
                aviaries.remove(aviaries.size() - 1);
            }
        }
    }

    public int getAviariesSize() {
        return aviaries.size();
    }

    public int getMaxAviaries() {
        return MAX_AVIARIES;
    }
// Method to assign a bird to an aviary
// Purpose: Assigns a bird to an available aviary or creates a new one if needed.
// Arguments: bird - The bird to be assigned to an aviary.
// Return Value: True if the bird is successfully assigned, false otherwise.
// Exceptions: Throws a RuntimeException if the conservatory is full or the assignment is not possible.
// Changes to Calling Object: May modify the aviaries list to add a new aviary or add a bird to an existing aviary.
    public boolean assignBirdToAviary(Bird bird) {
        // Check if the conservatory has reached the maximum aviary limit
        if (aviaries.size() >= MAX_AVIARIES) {
            throw new RuntimeException("The conservatory is full and cannot assign more birds.");
        }

        Aviary targetAviary = findSuitableAviary(bird);

        // If no suitable aviary is found, create a new generic aviary
        if (targetAviary == null) {
            targetAviary = new Aviary("Generic Aviary", "Generic Location"); // Changed this line
            aviaries.add(targetAviary);
        }

        // Check if the aviary is full or if the bird is extinct
        if (!targetAviary.isFull() && !bird.isExtinct() && targetAviary.isCompatible(bird)) {
            return targetAviary.addBird(bird);
        } else {
            throw new RuntimeException("Cannot assign the bird to an aviary.");
        }
    }

    // Method to rescue a bird and assign it to an aviary
// Purpose: Rescues a bird and assigns it to an available aviary or creates a new one if needed.
// Arguments: bird - The bird to be rescued and assigned to an aviary.
// Return Value: True if the bird is successfully rescued and assigned, false otherwise.
// Exceptions: None
// Changes to Calling Object: May modify the aviaries list to add a new aviary or add a bird to an existing aviary.
    public boolean rescueBird(Bird bird) {
        Aviary targetAviary = findSuitableAviary(bird);

        if (targetAviary == null || aviaries.size() < MAX_AVIARIES) {
            // Create a generic aviary if there are fewer than 20 aviaries or no suitable aviary found
            targetAviary = new Aviary("Generic Aviary", "Generic Location"); // Changed this line
            aviaries.add(targetAviary);
        }

        return targetAviary.addBird(bird);
    }


// Method to calculate the total food needs of all birds in the conservatory
// Purpose: Calculates the total amount of food needed for all birds in all aviaries.
// Arguments: None
// Return Value: The total food needs as a double.
// Exceptions: None
// Changes to Calling Object: None


    public double calculateFoodNeeds() {
        double totalFoodNeeds = 0.0;
        for (Aviary aviary : aviaries) {
            totalFoodNeeds += aviary.calculateTotalFoodNeeds();
        }
        return totalFoodNeeds;
    }
    // Method to find an aviary based on the bird type
// Purpose: Searches for an aviary containing birds of a specific type.
// Arguments: birdType - The type of bird to search for.
// Return Value: The first aviary containing birds of the specified type, or null if not found.
// Exceptions: None
// Changes to Calling Object: None
    public Aviary findAviaryByBirdType(String birdType) {
        for (Aviary aviary : aviaries) {
            if (aviary.containsBird(birdType)) {
                return aviary;
            }
        }
        return null;
    }
    // Method to generate a sign for a given aviary
// Purpose: Generates a sign with information about the aviary and its birds.
// Arguments: aviary - The aviary for which to generate the sign.
// Return Value: A formatted sign as a string.
// Exceptions: None
// Changes to Calling Object: None
    public String generateSignForAviary(Aviary aviary) {
        StringBuilder sign = new StringBuilder();
        sign.append("======= AVIARY SIGN =======\n");
        sign.append("Aviary Type: ").append(aviary.getAviaryType()).append("\n");
        for (Bird bird : aviary.getBirds()) {
            sign.append(bird.toString()).append("\n");
        }
        sign.append("===========================\n");
        return sign.toString();
    }


    // Method to generate an aviary map for the conservatory
// Purpose: Generates a map of aviaries in the conservatory, including their locations and contained bird types.
// Arguments: None
// Return Value: A formatted string representing the aviary map.
// Exceptions: None
// Changes to Calling Object: None
    public String generateAviaryMap() {
        StringBuilder map = new StringBuilder();
        map.append("Conservatory Aviary Map:\n");
        for (Aviary aviary : aviaries) {
            map.append(aviary.getLocation()).append(" - ").append(aviary.getAviaryType()).append(": ");
            for(Bird bird : aviary.getBirds()) {
                map.append(bird.getType()).append(", ");
            }
            // Remove trailing comma and space
            if (!aviary.getBirds().isEmpty()) {
                map.setLength(map.length() - 2);
            }
            map.append("\n");
        }
        return map.toString();
    }

// Method to print an index of bird types in the conservatory
// Purpose: Prints an index of bird types sorted alphabetically.
// Arguments: None
// Return Value: None
// Exceptions: None
// Changes to Calling Object: None

    public void printIndex() {
        aviaries.stream().flatMap(aviary -> aviary.getBirds().stream()).sorted((b1, b2) -> b1.getType().compareTo(b2.getType())).forEach(bird -> System.out.println(bird.getType()));
    }
    // Private method to find a suitable aviary for a bird
// Purpose: Searches for an available aviary that can accommodate the given bird.
// Arguments: bird - The bird for which to find a suitable aviary.
// Return Value: The first available aviary that can accommodate the bird, or null if none is found.
// Exceptions: None
// Changes to Calling Object: None
    private Aviary findSuitableAviary(Bird bird) {
        for (Aviary aviary : aviaries) {
            if (!aviary.isFull() && aviary.isCompatible(bird)) {
                return aviary;
            }
        }
        return null;
    }




}
