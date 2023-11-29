import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleDriver {
    private GameModel.Character player1;
    private GameModel.Character player2;
    private List<GameModel.Item> availableItems;
    private Random random;

    public BattleDriver(GameModel.Character player1, GameModel.Character player2, List<GameModel.Item> availableItems) {
        this.player1 = player1;
        this.player2 = player2;
        this.availableItems = new ArrayList<>(availableItems); // Copy of available items
        this.random = new Random();
    }

    // Method to conduct the battle
    public void conductBattle() {
        // Conduct the battle by having players choose items alternately
        for (int turn = 1; turn <= 10; turn++) {
            System.out.println("Turn " + turn + ":");
            chooseItemsForTurn(player1);
            chooseItemsForTurn(player2);
        }

        // Calculate and print the battle result
        int player1Damage = player2.calculateDamage(player1.getAttack());
        int player2Damage = player1.calculateDamage(player2.getAttack());

        System.out.println("Battle Results:");
        System.out.println("Player 1 damage: " + player1Damage);
        System.out.println("Player 2 damage: " + player2Damage);

        if (player1Damage < player2Damage) {
            System.out.println("Player 1 wins!");
        } else if (player2Damage < player1Damage) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // Method to choose items for a player's turn
    private void chooseItemsForTurn(GameModel.Character player) {
        List<GameModel.Item> availableSlots = getAvailableSlots(player);
        if (!availableSlots.isEmpty()) {
            GameModel.Item chosenItem = chooseRandomItem(availableSlots);
            player.equipItem(chosenItem);
            availableItems.remove(chosenItem);
            System.out.println(player.getName() + " is picking up a piece of " + chosenItem.getName() +
                    " -- defense strength: " + chosenItem.getDefenseStrength() +
                    ", attack strength: " + chosenItem.getAttackStrength());
        }
    }

    // Method to get available slots for a player
    private List<GameModel.Item> getAvailableSlots(GameModel.Character player) {
        List<GameModel.Item> availableSlots = new ArrayList<>();
        // Implement the logic to get available slots based on rules
        // For example, check how many hand gear, head gear, and footwear slots are available
        // Add items to availableSlots accordingly
        return availableSlots;
    }

    // Method to choose a random item from a list
    private GameModel.Item chooseRandomItem(List<GameModel.Item> items) {
        int randomIndex = random.nextInt(items.size());
        return items.get(randomIndex);
    }
}
