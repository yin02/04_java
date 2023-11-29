import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class Battle {
    private Character character1;
    private Character character2;
    private List<Item> availableItems;
    private Random random;

    public Battle(Character character1, Character character2, List<Item> availableItems) {
        this.character1 = character1;
        this.character2 = character2;
        this.availableItems = availableItems;
        this.random = new SecureRandom();
    }

    public void startBattle() throws ItemLimitExceededException {
        for (int turn = 0; turn < 10; turn++) {
            Character currentCharacter = (turn % 2 == 0) ? character1 : character2;
            Item chosenItem = chooseItemForCharacter(currentCharacter);
            if (chosenItem != null) {
                currentCharacter.addItem(chosenItem);
                availableItems.remove(chosenItem);
                System.out.println("Character " + currentCharacter.getName() + " is picking up a piece of " +
                        chosenItem.getClass().getSimpleName() + ": " + chosenItem);
            }
        }

        determineWinner();
    }

    private void determineWinner() {
        int damageToCharacter1 = character2.getTotalAttack() - character1.getTotalDefense();
        int damageToCharacter2 = character1.getTotalAttack() - character2.getTotalDefense();

        System.out.println("The battle ends with Player 1 having " + (-damageToCharacter1) + " units of damage and Player 2 having " + (-damageToCharacter2) + " units of damage.");

        if (damageToCharacter1 < damageToCharacter2) {
            System.out.println("Player 1 wins.");
        } else if (damageToCharacter2 < damageToCharacter1) {
            System.out.println("Player 2 wins.");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private Item chooseItemForCharacter(Character character) {
        Item bestItem = null;
        int highestScore = -1;

        for (Item item : availableItems) {
            int score = calculateItemScore(character, item);
            if (score > highestScore) {
                highestScore = score;
                bestItem = item;
            } else if (score == highestScore) {
                bestItem = random.nextBoolean() ? bestItem : item; // Randomly pick one if scores are equal
            }
        }

        return bestItem;
    }

    private int calculateItemScore(Character character, Item item) {
        int score = 0;
        if (character.canAddItem(item)) {
            score += 1000; // High score for items that can be added
        }
        score += item.getAttackStrength() * 10; // Weight attack more
        score += item.getDefenseStrength();
        return score;
    }
}
