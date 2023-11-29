import lookandsay.LookAndSayIterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;

public class LookAndSayIteratorTest {

    private LookAndSayIterator iterator;

    @Before
    public void setup() {
        iterator = new LookAndSayIterator();
    }

    @Test
    public void testValidConstructor() {
        try {
            LookAndSayIterator validIterator = new LookAndSayIterator(new BigInteger("1"), new BigInteger("999"));
        } catch (Exception e) {
            fail("Should not throw exception for valid constructor arguments.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructor() {
        new LookAndSayIterator(new BigInteger("0"), new BigInteger("999"));
    }

    @Test
    public void testNext() {
        BigInteger first = iterator.next();
        BigInteger second = iterator.next();
        assertEquals("Expected initial value to be 1", new BigInteger("1"), first);
        assertEquals("Expected second value to be 11", new BigInteger("11"), second);
    }

    @Test
    public void testPrev() {
        iterator.next(); // should return 1
        iterator.next(); // should return 11
        BigInteger previous = iterator.prev(); // should go back to 1
        assertEquals("Expected previous value to be 1", new BigInteger("1"), previous);
    }




    @Test
    public void testHasNextAtStart() {
        assertTrue("Iterator should have next at start", iterator.hasNext());
    }

    @Test
    public void testHasPreviousAtStart() {
        assertFalse("Iterator should not have previous at start", iterator.hasPrevious());
    }

}
