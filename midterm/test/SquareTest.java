import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest {

    @Test
    public void testEasyToExpandSquare() {
        EasyToExpandSquare square = new EasyToExpandSquare(Color.RED, 2);

        assertEquals(Color.RED, square.getColor());
        assertEquals(4, square.getArea());

        Square transformedSquare = square.transform();

        assertEquals(Color.GREEN, transformedSquare.getColor());
        assertEquals(36, transformedSquare.getArea()); // side becomes 6, so area becomes 6*6 = 36
        assertEquals(Color.RED, square.getColor()); // original square should remain unchanged
        assertEquals(4, square.getArea());
        Square transformedSquare2 = transformedSquare.transform();
        assertEquals(Color.BLUE, transformedSquare2.getColor());
    }

    @Test
    public void testPrecomputedSquare() {
        PrecomputedSquare square = new PrecomputedSquare(Color.BLUE, 9);

        assertEquals(Color.BLUE, square.getColor());
        assertEquals(9, square.getArea());

        Square transformedSquare = square.transform();
// triple
        assertEquals(Color.RED, transformedSquare.getColor());
        assertEquals(81, transformedSquare.getArea()); // side becomes 9, so area becomes 9*9 = 81
        assertEquals(Color.BLUE, square.getColor()); // original square should remain unchanged
        assertEquals(9, square.getArea());
        Square transformedSquare2 = transformedSquare.transform();
        //one more change
        assertEquals(Color.GREEN, transformedSquare2.getColor());
    }
}
