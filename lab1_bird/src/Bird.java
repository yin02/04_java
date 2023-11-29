import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


// Bird class represents a generic bird with common attributes
public class Bird {
    private BirdType type;
    private String characteristic;
    private boolean isExtinct;
    private int numberOfWings;
    private List<String> preferredFood;
    private String description;
    public enum BirdType {
        GENERAL, //General
        BIRD_OF_PREY,
        FLIGHTLESS,
        OWL,
        PARROT,
        PIGEON,
        SHOREBIRD,
        WATERFOWL,
    }
    /**
     * Returns a string representation of the Bird object, including its type, characteristics,
     * extinction status, number of wings, and preferred food.
     *
     * @return A string containing information about the Bird object.
     */
    @Override
    public String toString() {
        return "Type: " + type +
                ", Characteristic: " + characteristic +
                ", Is Extinct: " + (isExtinct ? "Yes" : "No") +
                ", Number of Wings: " + numberOfWings +
                ", Preferred Food: " + preferredFood;
    }

//construtor
    /**
     * Constructs a Bird object with the given attributes.
     *
     * @param type           The type of the bird (e.g., BIRD_OF_PREY, PARROT, etc.).
     * @param characteristic A description of the bird's characteristics.
     * @param isExtinct      Whether the bird is extinct (true) or not (false).
     * @param numberOfWings  The number of wings of the bird.
     * @param preferredFood  A list of preferred foods for the bird.
     * @throws IllegalArgumentException If the provided bird type is invalid or if the preferred food
     *                                  list is null or empty.
     */
    public Bird(BirdType type, String characteristic, boolean isExtinct, int numberOfWings, List<String> preferredFood) {
        if (type == null || !Arrays.asList(BirdType.values()).contains(type)) {
            throw new IllegalArgumentException("Invalid bird type provided.");
        }
        if (preferredFood == null || preferredFood.isEmpty()) {
            throw new IllegalArgumentException("Preferred food list cannot be null or empty.");
        }
        this.type = type;
        this.characteristic = characteristic;
        this.isExtinct = isExtinct;
        this.numberOfWings = numberOfWings;
        this.preferredFood = preferredFood;
    }



    // setters
    /**
     * Sets the type of the bird.
     *
     * @param type The type of the bird (e.g., BIRD_OF_PREY, PARROT, etc.).
     * @throws IllegalArgumentException If the provided bird type is invalid.
     */
    public void setType(BirdType type) {
        if (type == null || !Arrays.asList(BirdType.values()).contains(type)) {
            throw new IllegalArgumentException("Invalid bird type provided.");
        }
        this.type = type;
    }

    /**
     * Sets the characteristic description of the bird.
     *
     * @param characteristic A description of the bird's characteristics.
     */
    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }
    /**
     * Sets the extinction status of the bird.
     *
     * @param extinct Whether the bird is extinct (true) or not (false).
     */
    public void setExtinct(boolean extinct) {
        isExtinct = extinct;
    }
    /**
     * Sets the number of wings of the bird.
     *
     * @param numberOfWings The number of wings of the bird.
     * @throws IllegalArgumentException If the provided number of wings is negative.
     */
    public void setNumberOfWings(int numberOfWings) {
        if (numberOfWings < 0) {
            throw new IllegalArgumentException("Number of wings cannot be negative.");
        }
        this.numberOfWings = numberOfWings;
    }
    /**
     * Sets the preferred food list for the bird.
     *
     * @param preferredFood A list of preferred foods for the bird.
     * @throws IllegalArgumentException If the provided preferred food list is null or empty.
     */
    public void setPreferredFood(List<String> preferredFood) {
        if (preferredFood == null || preferredFood.isEmpty()) {
            throw new IllegalArgumentException("Preferred food list cannot be null or empty.");
        }
        this.preferredFood = new ArrayList<>(preferredFood); // Defensive copy
    }

    /**
     * Sets the description for the bird.
     *
     * @param description A description of the bird.
     * @throws IllegalArgumentException If the provided description is null or empty after trimming.
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
    }


    // getters

    public BirdType getType() {
        return type;
    }
    public String getCharacteristic() {
        return characteristic;
    }

    public boolean isExtinct() {
        return isExtinct;
    }

    public int getNumberOfWings() {
        return numberOfWings;
    }

    public List<String> getPreferredFood() {
        return preferredFood;
    }
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the food requirement of the bird. The food requirement is assumed to be the first item
     * in the preferredFood list and is returned as a double.
     *
     * @return The food requirement as a double. If the food requirement cannot be parsed as a double,
     *         a default value of 0.0 is returned.
     */
    public double getFoodRequirement() {
        // Assuming the food requirement is the first item in the preferredFood list
        // You can modify this logic based on your requirements
        if (preferredFood != null && !preferredFood.isEmpty()) {
            String foodRequirement = preferredFood.get(0);
            try {
                // Attempt to parse the food requirement as a double
                return Double.parseDouble(foodRequirement);
            } catch (NumberFormatException e) {
                // Handle the case where the food requirement cannot be parsed as a double
                // You can return a default value or handle the error as needed
                return 0.0; // Default value
            }
        }
        return 0.0; // or return a default value
    }
}
//解析成double 类型看看可不可以 不可以就是默认值
//异常处理：如果转换过程中出现NumberFormatException，这表明字符串不能转换为double（例如，它可能不是一个数字字符串）。在这种情况下，函数返回默认值0.0。

class BirdOfPrey extends Bird {
    private String beakType;
    private boolean nostrilsVisible;
    /**
     * Constructs a BirdOfPrey object with the given attributes, including its type, characteristics,
     * extinction status, number of wings, preferred food, beak type, and nostrils visibility.
     *
     * @param type            The type of the bird of prey (e.g., BIRD_OF_PREY).
     * @param characteristic  A description of the bird of prey's characteristics.
     * @param isExtinct       Whether the bird of prey is extinct (true) or not (false).
     * @param numberOfWings   The number of wings of the bird of prey.
     * @param preferredFood   A list of preferred foods for the bird of prey.
     * @param beakType        The type of beak the bird of prey possesses.
     * @param nostrilsVisible Whether the nostrils of the bird of prey are visible (true) or not (false).
     */
    public BirdOfPrey(BirdType type, String characteristic, boolean isExtinct, int numberOfWings, List<String> preferredFood, String beakType, boolean nostrilsVisible) {
        super(type, characteristic, isExtinct, numberOfWings, preferredFood);//继承
        this.beakType = beakType;
        this.nostrilsVisible = nostrilsVisible;
    }



    // getters for BirdOfPrey specific attributes
    public String getBeakType() {
        return beakType;
    }

    public boolean isNostrilsVisible() {
        return nostrilsVisible;
    }

    // setters for BirdOfPrey specific attributes
    public void setBeakType(String beakType) {
        // You can add validation here if needed
        this.beakType = beakType;
    }

    public void setNostrilsVisible(boolean nostrilsVisible) {
        this.nostrilsVisible = nostrilsVisible;
    }
}


/**
 * The FlightlessBird class represents a type of bird that is flightless. It extends the Bird class
 * and adds attributes specific to flightless birds.
 */
class FlightlessBird extends Bird {
    private boolean isFlightless;
    /**
     * Constructs a FlightlessBird object with the given attributes, including its type, characteristics,
     * extinction status, number of wings, preferred food, and flightless status.
     *
     * @param type           The type of the flightless bird (e.g., FLIGHTLESS).
     * @param characteristic A description of the flightless bird's characteristics.
     * @param isExtinct      Whether the flightless bird is extinct (true) or not (false).
     * @param numberOfWings  The number of wings of the flightless bird.
     * @param preferredFood  A list of preferred foods for the flightless bird.
     * @param isFlightless   Whether the flightless bird is indeed flightless (true) or not (false).
     */
    public FlightlessBird(BirdType type, String characteristic, boolean isExtinct, int numberOfWings, List<String> preferredFood, boolean isFlightless) {
        super(type, characteristic, isExtinct, numberOfWings, preferredFood);
        this.isFlightless = isFlightless;
    }

    // getters for FlightlessBird specific attributes
    public boolean isFlightless() {
        return isFlightless;
    }

    // setters for FlightlessBird specific attributes
    public void setFlightless(boolean isFlightless) {
        this.isFlightless = isFlightless;
    }
}

/**
 * The Owl class represents a type of bird known as an owl. It extends the Bird class
 * and adds attributes specific to owls.
 */
class Owl extends Bird {
    private boolean facialDisks;
    /**
     * Constructs an Owl object with the given attributes, including its type, characteristics,
     * extinction status, number of wings, preferred food, and the presence of facial disks.
     *
     * @param type           The type of the owl (e.g., OWL).
     * @param characteristic A description of the owl's characteristics.
     * @param isExtinct      Whether the owl is extinct (true) or not (false).
     * @param numberOfWings  The number of wings of the owl.
     * @param preferredFood  A list of preferred foods for the owl.
     * @param facialDisks    Whether the owl possesses facial disks (true) or not (false).
     */
    public Owl(BirdType type, String characteristic, boolean isExtinct, int numberOfWings, List<String> preferredFood, boolean facialDisks) {
        super(type, characteristic, isExtinct, numberOfWings, preferredFood);
        this.facialDisks = facialDisks;
    }

    // getters for Owl specific attributes
    public boolean isFacialDisks() {
        return facialDisks;
    }

    // setters for Owl specific attributes
    public void setFacialDisks(boolean facialDisks) {
        this.facialDisks = facialDisks;
    }
}

/**
 * Constructs a Parrot object with the given attributes, including its type, characteristics,
 * extinction status, number of wings, preferred food, vocabulary size, and favorite saying.
 *
 * @param type           The type of the parrot (e.g., PARROT).
 * @param characteristic A description of the parrot's characteristics.
 * @param isExtinct      Whether the parrot is extinct (true) or not (false).
 * @param numberOfWings  The number of wings of the parrot.
 * @param preferredFood  A list of preferred foods for the parrot.
 * @param vocabularySize The size of the parrot's vocabulary.
 * @param favoriteSaying The favorite saying or phrase of the parrot.
 */
class Parrot extends Bird {
    private int vocabularySize;
    private String favoriteSaying;

    public Parrot(BirdType type, String characteristic, boolean isExtinct, int numberOfWings, List<String> preferredFood, int vocabularySize, String favoriteSaying) {
        super(type, characteristic, isExtinct, numberOfWings, preferredFood);
        this.vocabularySize = vocabularySize;
        this.favoriteSaying = favoriteSaying;
    }

    // getters for Parrot specific attributes
    public int getVocabularySize() {
        return vocabularySize;
    }

    public String getFavoriteSaying() {
        return favoriteSaying;
    }

    // setters for Parrot specific attributes
    public void setVocabularySize(int vocabularySize) {
        this.vocabularySize = vocabularySize;
    }

    public void setFavoriteSaying(String favoriteSaying) {
        this.favoriteSaying = favoriteSaying;
    }
}
/**
 * The Pigeon class represents a type of bird known as a pigeon. It extends the Bird class
 * and adds attributes specific to pigeons.
 */
class Pigeon extends Bird {
    private boolean birdMilk;
    /**
     * Constructs a Pigeon object with the given attributes, including its type, characteristics,
     * extinction status, number of wings, preferred food, and whether it produces bird milk.
     *
     * @param type            The type of the pigeon (e.g., PIGEON).
     * @param characteristic  A description of the pigeon's characteristics.
     * @param isExtinct       Whether the pigeon is extinct (true) or not (false).
     * @param numberOfWings   The number of wings of the pigeon.
     * @param preferredFood   A list of preferred foods for the pigeon.
     * @param birdMilk        Whether the pigeon produces bird milk (true) or not (false).
     */
    public Pigeon(BirdType type, String characteristic, boolean isExtinct, int numberOfWings, List<String> preferredFood, boolean birdMilk) {
        super(type, characteristic, isExtinct, numberOfWings, preferredFood);
        this.birdMilk = birdMilk;
    }

    // getters for Pigeon specific attributes
    public boolean isBirdMilk() {
        return birdMilk;
    }


    // setters for Pigeon specific attributes
    public void setBirdMilk(boolean birdMilk) {
        this.birdMilk = birdMilk;
    }
}



/**
 * The Shorebird class represents a type of bird known as a shorebird. It extends the Bird class
 * and adds attributes specific to shorebirds, including the type of water body they inhabit.
 */
class Shorebird extends Bird {
    private Water waterBody;
    /**
     * Constructs a Shorebird object with the given attributes, including its type, characteristics,
     * extinction status, number of wings, preferred food, and the type of water body it inhabits.
     *
     * @param type            The type of the shorebird (must be SHOREBIRD).
     * @param characteristic  A description of the shorebird's characteristics.
     * @param isExtinct       Whether the shorebird is extinct (true) or not (false).
     * @param numberOfWings   The number of wings of the shorebird.
     * @param preferredFood   A list of preferred foods for the shorebird.
     * @param waterBody       The type of water body that the shorebird inhabits (e.g., freshwater, saltwater).
     * @throws IllegalArgumentException if the provided bird type is not SHOREBIRD.
     */

//constrcutor
    public Shorebird(BirdType type, String characteristic, boolean isExtinct, int numberOfWings, List<String> preferredFood, Water waterBody) {
        super(type, characteristic, isExtinct, numberOfWings, preferredFood);
        if (type != BirdType.SHOREBIRD) {
            throw new IllegalArgumentException("Invalid bird type for Shorebird.");
        }
        this.waterBody = waterBody;
    }

    public Water getWaterBody() {
        return waterBody;
    }

    public void setWaterBody(Water waterBody) {
        this.waterBody = waterBody;
    }
}
/**
 * The Waterfowl class represents a type of bird known as a waterfowl. It extends the Bird class
 * and adds attributes specific to waterfowl, including the type of water body they inhabit.
 */
class Waterfowl extends Bird {
    private Water waterBody;
    /**
     * Constructs a Waterfowl object with the given attributes, including its type, characteristics,
     * extinction status, number of wings, preferred food, and the type of water body it inhabits.
     *
     * @param type            The type of the waterfowl (must be WATERFOWL).
     * @param characteristic  A description of the waterfowl's characteristics.
     * @param isExtinct       Whether the waterfowl is extinct (true) or not (false).
     * @param numberOfWings   The number of wings of the waterfowl.
     * @param preferredFood   A list of preferred foods for the waterfowl.
     * @param waterBody       The type of water body that the waterfowl inhabits (e.g., freshwater, saltwater).
     * @throws IllegalArgumentException if the provided bird type is not WATERFOWL.
     */
    public Waterfowl(BirdType type, String characteristic, boolean isExtinct, int numberOfWings, List<String> preferredFood, Water waterBody) {
        super(type, characteristic, isExtinct, numberOfWings, preferredFood);
        if (type != BirdType.WATERFOWL) {
            throw new IllegalArgumentException("Invalid bird type for Waterfowl.");
        }
        this.waterBody = waterBody;
    }

    public Water getWaterBody() {
        return waterBody;
    }

    public void setWaterBody(Water waterBody) {
        this.waterBody = waterBody;
    }


}







