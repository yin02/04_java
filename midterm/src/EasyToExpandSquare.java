public class EasyToExpandSquare extends AbstractSquare {
    private final int side;

    public EasyToExpandSquare(Color color, int side) {
        super(color);
        this.side = side;
    }

    @Override
    public int getArea() {
        return side * side;
    }

    @Override
    protected Square transformWithNewColor(Color newColor) {
        return new EasyToExpandSquare(newColor, side * 3);
    }

    @Override
    public int computeHashCode() {
        return 0;
    }
}
