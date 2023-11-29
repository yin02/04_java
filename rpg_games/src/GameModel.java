import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameModel {
    private Character playerCharacter;
    private List<Item> items;

    public GameModel() {
        // Initialize the player's character with name, attack, defense, and starting position (x, y)
        this.playerCharacter = new Character("Hero", 10, 5, 100, 100);
        this.items = new ArrayList<>();
        loadItems();
    }

    public Character getPlayerCharacter() {
        return playerCharacter;
    }

    private void loadItems() {
        // Initialize items with their names and stats (attack and defense)

        // Helmet
        Item helmet = new Item("Helmet", 0, 2);
        helmet.setImagePath("img/helmet.png"); // 设置图像路径
        items.add(helmet);

        // Gloves
        Item gloves = new Item("Gloves", 3, 0);
        gloves.setImagePath("img/gloves.png"); // 设置图像路径
        items.add(gloves);

        // Boots
        Item boots = new Item("Boots", 1, 1);
        boots.setImagePath("img/boots.png"); // 设置图像路径
        items.add(boots);

        // Shield
        Item shield = new Item("Shield", 0, 3);
        shield.setImagePath("img/shield.png"); // 设置图像路径
        items.add(shield);

        // Sword
        Item sword = new Item("Sword", 4, 0);
        sword.setImagePath("img/sword.png"); // 设置图像路径
        items.add(sword);

        // Hat
        Item hat = new Item("Hat", 0, 1);
        hat.setImagePath("img/hat.png"); // 设置图像路径
        items.add(hat);

        // Cloak
        Item cloak = new Item("Cloak", 0, 2);
        cloak.setImagePath("img/cloak.png"); // 设置图像路径
        items.add(cloak);

        // Amulet
        Item amulet = new Item("Amulet", 1, 1);
        amulet.setImagePath("img/amulet.png"); // 设置图像路径
        items.add(amulet);

        // Ring
        Item ring = new Item("Ring", 1, 0);
        ring.setImagePath("img/ring.png"); // 设置图像路径
        items.add(ring);

        // Belt
        Item belt = new Item("Belt", 0, 1);
        belt.setImagePath("img/belt.png"); // 设置图像路径
        items.add(belt);
    }




    public void equipItem(Item item) {
        playerCharacter.equipItem(item);
    }

    public String[] getItemNames() {
        return items.stream()
                .map(Item::getName)
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }
    public List<Item> getItems() {
        return items;
    }

    // Inner Character class
    class Character {
        private String name;
        private int attack;
        private int defense;
        private int x, y;
        private List<Item> equippedItems;

        public Character(String name, int attack, int defense, int x, int y) {
            this.name = name;
            this.attack = attack;
            this.defense = defense;
            this.x = x;
            this.y = y;
            this.equippedItems = new ArrayList<>();
        }

        public void equipItem(Item item) {
            if (!equippedItems.contains(item)) {
                equippedItems.add(item);
                this.attack += item.getAttackStrength();
                this.defense += item.getDefenseStrength();
            }
        }

        public void unequipItem(Item item) {
            if (equippedItems.contains(item)) {
                equippedItems.remove(item);
                this.attack -= item.getAttackStrength();
                this.defense -= item.getDefenseStrength();
            }
        }
        public int calculateDamage(int opponentAttack) {
            // 计算伤害的逻辑
            int totalAttack = attack;
            for (Item item : equippedItems) {
                totalAttack += item.getAttackStrength();
            }

            // 计算伤害并返回
            int damage = totalAttack - opponentAttack;
            return Math.max(damage, 0); // 伤害不能为负数
        }
        public String getName() {
            return name;
        }



        public void moveCharacterUp() {
            // Movement logic
            this.y -= 5;
        }

        public void moveCharacterDown() {
            this.y += 5;
        }

        public void moveCharacterLeft() {
            this.x -= 5;
        }

        public void moveCharacterRight() {
            this.x += 5;
        }

        // Getters and setters
        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getAttack() {
            return attack;
        }

        public int getDefense() {
            return defense;
        }
    }

    // Inner Item class
     class Item {
        private String name;
        private int attackStrength;
        private int defenseStrength;
        private String imagePath; // 新增图像路径属性

        public Item(String name, int attackStrength, int defenseStrength) {
            this.name = name;
            this.attackStrength = attackStrength;
            this.defenseStrength = defenseStrength;
        }

        // Getter method for name
        public String getName() {
            return name;
        }

        // Getter methods for attackStrength and defenseStrength
        public int getAttackStrength() {
            return attackStrength;
        }

        public int getDefenseStrength() {
            return defenseStrength;
        }

        // Getter and setter for image path
        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }

}
