import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RPGGameSimulator {
    public static void main(String[] args) throws ItemLimitExceededException {
        Character character1 = new Character("Warrior", 5, 5);
        Character character2 = new Character("Mage", 5, 5);

        List<Item> items = generateRandomItems();

        Battle battle = new Battle(character1, character2, items);
        battle.startBattle();
    }

    private static List<Item> generateRandomItems() {
        List<Item> items = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int itemType = random.nextInt(3); // Randomly choose between 0, 1, and 2
            String itemName = "Item" + i;
            int attackStrength = random.nextInt(5); // Random attack strength between 0 and 4
            int defenseStrength = random.nextInt(5); // Random defense strength between 0 and 4

            switch (itemType) {
                case 0:
                    items.add(new HeadGear(itemName, defenseStrength));
                    break;
                case 1:
                    items.add(new HandGear(itemName, attackStrength));
                    break;
                case 2:
                    items.add(new Footwear(itemName, attackStrength, defenseStrength));
                    break;
            }
        }

        return items;
    }
}
