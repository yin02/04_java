public class PrecomputedSquare extends AbstractSquare {
    private final int area;

    public PrecomputedSquare(Color color, int area) {
        super(color);
        this.area = area;

    }

    @Override
    public int getArea() {
        return area;
    }

    @Override
    protected Square transformWithNewColor(Color newColor) {
        int transformarea = area * 9;
        return new PrecomputedSquare(newColor, transformarea);
    }


    @Override
    public int computeHashCode() {
        return 0;
    }
}