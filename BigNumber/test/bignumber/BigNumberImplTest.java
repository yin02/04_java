package bignumber;
import org.junit.Test;
import org.junit.Assert;



public class BigNumberImplTest {

    @Test
    public void testBuildingNumber() {
        // Start with 0.
        BigNumber number = new BigNumberImpl();

        // Left-shift by 1 position, and add 3
        number.shiftLeft(1);
        number.addDigit(3);
        Assert.assertEquals("3", number.toString());

        // Left-shift by 1 position (to get 30) and add 2 (to get 32)
        number.shiftLeft(1);
        number.addDigit(2);
        Assert.assertEquals("32", number.toString());

        // Left-shift by 1 position (to get 320) and add 4 (to get 324)
        number.shiftLeft(1);
        number.addDigit(4);
        Assert.assertEquals("324", number.toString());

        // Left-shift by 1 position (to get 3240) and add 1 (to get 3241)
        number.shiftLeft(1);
        number.addDigit(1);
        Assert.assertEquals("3241", number.toString());

        // Left-shift by 1 position (to get 32410) and add 1 (to get 32411)
        number.shiftLeft(1);
        number.addDigit(1);
        Assert.assertEquals("32411", number.toString());
    }
}
