import lookandsay.LookAndSayIterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;

public class LookAndSayIteratorTest {

    private LookAndSayIterator defaultIterator;
    private LookAndSayIterator customIterator;

    @Before
    public void setUp() {
        defaultIterator = new LookAndSayIterator();
        customIterator = new LookAndSayIterator(new BigInteger("1211"), new BigInteger("2000"));
    }

    @Test
    public void testDefaultConstructorStartsWithOne() {
        assertTrue("Default constructor should start with 1", BigInteger.ONE.equals(defaultIterator.next()));
    }

    @Test
    public void testCustomConstructorStart() {
        assertEquals("Custom constructor should start with provided seed", new BigInteger("1211"), customIterator.next());
    }



    @Test
    public void testHasNextAtStart() {
        assertTrue("Has next should return true at the start", defaultIterator.hasNext());
    }

    @Test
    public void testHasPreviousAfterMovingForward() {
        defaultIterator.next();
        assertTrue("Has previous should return true after moving forward", defaultIterator.hasPrevious());
    }

    @Test
    public void testNextSequence() {
        BigInteger first = defaultIterator.next(); // 1
        BigInteger second = defaultIterator.next(); // 11
        BigInteger third = defaultIterator.next(); // 21

        assertEquals("Second element in sequence should be 11", new BigInteger("11"), second);
        assertEquals("Third element in sequence should be 21", new BigInteger("21"), third);
    }

    @Test
    public void testPrevSequence() {
        defaultIterator.next(); // 1
        defaultIterator.next(); // 11
        BigInteger third = defaultIterator.next(); // 21
        BigInteger second = defaultIterator.prev();

        assertEquals("Prev should return the second element after three next calls", new BigInteger("11"), second);
    }

    @Test
    public void testBoundaryCondition() {
        LookAndSayIterator boundaryIterator = new LookAndSayIterator(new BigInteger("1999"), new BigInteger("2000"));
        assertTrue("Next should be available for 1999", boundaryIterator.hasNext());
        boundaryIterator.next();
        assertFalse("Next should not be available after reaching the end", boundaryIterator.hasNext());
    }

    // Add more tests as needed to cover edge cases and other scenarios.
}
