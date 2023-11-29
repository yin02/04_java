public class Water {
    private String name;
    /**
     * Constructs a Water object with the provided name.
     *
     * @param name The name of the water entity.
     */
    public Water(String name) {
        this.name = name;
    }
    /**
     * Gets the name of the water entity.
     *
     * @return The name of the water entity as a String.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the water entity to the provided value.
     *
     * @param name The new name for the water entity.
     */
    public void setName(String name) {
        this.name = name;
    }
}