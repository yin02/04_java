import java.util.ArrayList;
import java.util.List;

public class Aviary {
    private final int MAX_BIRDS = 5;
    private String aviaryType;
    private List<Bird> birds;
    private String location;

    public Aviary(String aviaryType, String location) {  // Add a location parameter
        this.aviaryType = aviaryType;
        this.birds = new ArrayList<>();
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public boolean addBird(Bird bird) {
        if (birds.size() >= MAX_BIRDS) {
            throw new IllegalStateException("Aviary is full. Cannot add more birds.");
        }
        if (bird.isExtinct() || !isCompatible(bird)) {
            throw new IllegalArgumentException("Bird is either extinct or not compatible with aviary.");
        }
        birds.add(bird);
        return true;
    }


    public boolean isFull() {
        return birds.size() == MAX_BIRDS;
    }

    public boolean containsBird(String birdType) {
        for (Bird bird : birds) {
            if (bird.getType().name().equalsIgnoreCase(birdType)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCompatible(Bird bird) {
        // Rule 1: No extinct birds can be added to an aviary.
        if (bird.isExtinct()) {
            return false;
        }

        // If the aviary is empty, any bird can be added.
        if (birds.isEmpty()) {
            return true;
        }

        // Get the type of the first bird in the aviary to determine the aviary type.
        Bird.BirdType currentType = birds.get(0).getType();

        // Rule 2: Flightless birds, birds of prey, and waterfowl should not be mixed with other bird types.
        if (currentType == Bird.BirdType.FLIGHTLESS || currentType == Bird.BirdType.BIRD_OF_PREY || currentType == Bird.BirdType.WATERFOWL) {
            return bird.getType() == currentType;
        } else {
            // If the aviary doesn't contain special bird types, ensure the new bird is also not a special type.

            Bird.BirdType birdType = bird.getType();
            return birdType != Bird.BirdType.FLIGHTLESS && birdType != Bird.BirdType.BIRD_OF_PREY && birdType != Bird.BirdType.WATERFOWL;
//            这里使用的是逻辑“与”操作（&&），表明加入的鸟必须同时不是这三种特殊类型中的任何一种。如果新的鸟属于这些类型中的任何一种，整个表达式都会返回false，意味着这只鸟不兼容，不能加入当前的鸟舍。
//
//            这种方式保证了只有当新的鸟既不是不能飞的鸟、也不是猛禽、也不是水禽时，它才能被添加到鸟舍中，前提是鸟舍中已有的鸟也不属于这些特殊类别
        }
    }



    public String getAviaryType() {
        return aviaryType;
    }

    public List<Bird> getBirds() {
        return birds;
    }

    public double calculateTotalFoodNeeds() {
        double total = 0;
        for (Bird bird : birds) {
            total += bird.getFoodRequirement();
        }
        return total;
    }


}
