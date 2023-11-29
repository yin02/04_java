import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import weather.WeatherReading;

public class WeatherReadingTest {
    private WeatherReading wr;
// initialize the test data object
    @Before
    public void setUp() {
        wr = new WeatherReading(23, 12, 3, 12); // 初始化WeatherReading对象
    }
    // test 4 getter method
    @Test
    public void testGetTemperature() {
        assertEquals(23, wr.getTemperature());
    }

    @Test
    public void testGetDewPoint() {
        assertEquals(12, wr.getDewPoint());
    }

    @Test
    public void testGetWindSpeed() {
        assertEquals(3, wr.getWindSpeed());
    }

    @Test
    public void testGetTotalRain() {
        assertEquals(12, wr.getTotalRain());
    }

    // test toString method
    @Test
    public void testToString() {
        assertEquals("Reading: T = 23, D = 12, v = 3, rain = 12", wr.toString());
    }
// test Humidity calculation
    @Test
    public void testGetRelativeHumidity() {
        assertEquals(45, wr.getRelativeHumidity());
    }
// test Heat Index
    @Test
    public void testGetHeatIndex() {
        assertEquals(25.11214960844500, wr.getHeatIndex(), 0.00001); // 第三个参数是允许的误差范围
    }
// test wind Chill
    @Test
    public void testGetWindChill() {
        assertEquals(76.14651430332569, wr.getWindChill(), 0.00001); // 第三个参数是允许的误差范围
    }
}
