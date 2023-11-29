import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name;
    private int baseAttack;
    private int baseDefense;
    private List<Item> items;

    public Character(String name, int baseAttack, int baseDefense) {
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.items = new ArrayList<>();
    }

    public void addItem(Item newItem) throws ItemLimitExceededException {
        if (newItem instanceof HeadGear) {
            replaceOrAddItem(newItem, HeadGear.class);
        } else if (newItem instanceof HandGear) {
            addOrCombineItem(newItem, HandGear.class, 2);
        } else if (newItem instanceof Footwear) {
            addOrCombineItem(newItem, Footwear.class, 2);
        }
    }
    public boolean canAddItem(Item item) {
        if (item instanceof HeadGear) {
            return hasLessThanMax(HeadGear.class, 1);
        } else if (item instanceof HandGear) {
            return hasLessThanMax(HandGear.class, 2);
        } else if (item instanceof Footwear) {
            return hasLessThanMax(Footwear.class, 2);
        }
        return false;
    }

    private boolean hasLessThanMax(Class<?> itemType, int maxCount) {
        int count = 0;
        for (Item existingItem : items) {
            if (itemType.isInstance(existingItem)) {
                count++;
            }
        }
        return count < maxCount;
    }

    public int getTotalAttack() {
        int totalAttack = baseAttack;
        for (Item item : items) {
            totalAttack += item.getAttackStrength();
        }
        return totalAttack;
    }

    public int getTotalDefense() {
        int totalDefense = baseDefense;
        for (Item item : items) {
            totalDefense += item.getDefenseStrength();
        }
        return totalDefense;
    }

    private void replaceOrAddItem(Item newItem, Class<?> itemType) {
        for (int i = 0; i < items.size(); i++) {
            Item existingItem = items.get(i);
            if (itemType.isInstance(existingItem)) {
                items.set(i, newItem); // Replace existing item
                return;
            }
        }
        items.add(newItem); // Add if no existing item of this type
    }

//    private void addOrCombineItem(Item newItem, Class<?> itemType, int maxCount) {
//        int count = 0;
//        for (Item item : items) {
//            if (itemType.isInstance(item)) {
//                count++;
//            }
//        }
//
//        if (count < maxCount) {
//            items.add(newItem); // Add if less than maxCount
//        } else {
//            for (int i = 0; i < items.size(); i++) {
//                Item existingItem = items.get(i);
//                if (itemType.isInstance(existingItem)) {
//                    String combinedName = existingItem.getName() + ", " + newItem.getName();
//                    int combinedAttack = existingItem.getAttackStrength() + newItem.getAttackStrength();
//                    int combinedDefense = existingItem.getDefenseStrength() + newItem.getDefenseStrength();
//                    Item combinedItem = new CombinedItem(combinedName, combinedAttack, combinedDefense);
//                    items.set(i, combinedItem);
//                    break;
//                }
//            }
//        }
//    }
private void addOrCombineItem(Item newItem, Class<?> itemType, int maxCount) {
    List<Item> itemsOfType = new ArrayList<>();

    for (Item item : items) {
        if (itemType.isInstance(item)) {
            itemsOfType.add(item);
        }
    }

    if (itemsOfType.size() < maxCount) {
        items.add(newItem); // Add if less than maxCount
    } else {
        // Combine with the first item of the same type
        Item itemToCombine = itemsOfType.get(0); // Get the first item of this type
        String combinedName = itemToCombine.getName() + ", " + newItem.getName();
        int combinedAttack = itemToCombine.getAttackStrength() + newItem.getAttackStrength();
        int combinedDefense = itemToCombine.getDefenseStrength() + newItem.getDefenseStrength();

        // Remove the old item and add the combined one
        items.remove(itemToCombine);
        Item combinedItem = new CombinedItem(combinedName, combinedAttack, combinedDefense);
        items.add(combinedItem);
    }
}


    public String getName() {
        return name;
    }

    // Additional methods as needed
}
