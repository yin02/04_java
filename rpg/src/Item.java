public abstract class Item {
    private String name;
    private int attackStrength;
    private int defenseStrength;

    public Item(String name, int attackStrength, int defenseStrength) {
        this.name = name;
        this.attackStrength = attackStrength;
        this.defenseStrength = defenseStrength;
    }

    public String getName() {
        return name;
    }

    public int getAttackStrength() {
        return attackStrength;
    }

    public int getDefenseStrength() {
        return defenseStrength;
    }

    @Override
    public String toString() {
        return name + " -- Attack Strength: " + attackStrength + ", Defense Strength: " + defenseStrength;
    }
}
