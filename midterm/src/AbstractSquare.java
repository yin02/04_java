public abstract class AbstractSquare implements Square {
    protected Color color;

    public AbstractSquare(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public abstract int getArea();

    @Override
    public Square transform() {
        Color newColor;
        switch (color) {
            case RED:
                newColor = Color.GREEN;
                break;
            case GREEN:
                newColor = Color.BLUE;
                break;
            case BLUE:
                newColor = Color.RED;
                break;
            default:
                throw new IllegalArgumentException("Invalid color");
        }
        return transformWithNewColor(newColor);
    }

    protected abstract Square transformWithNewColor(Color newColor);
}