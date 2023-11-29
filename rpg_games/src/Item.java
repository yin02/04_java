public class Item {
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
