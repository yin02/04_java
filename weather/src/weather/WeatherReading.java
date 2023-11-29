package weather;
// design the weatherReading class

public class WeatherReading {
    // 4 parameters
    private int temperature;
    private int dewPoint;
    private int windSpeed;
    private int totalRain;

    // Constructors initialize the fields of the class with the values provided as
    // arguments.
    public WeatherReading(int temperature, int dewPoint, int windSpeed, int totalRain) {
        if (dewPoint > temperature) {
            throw new IllegalArgumentException("dewPoint cannot be greater than air temperature");
        }
        if (windSpeed < 0 || totalRain < 0) {
            throw new IllegalArgumentException("windSpeed and totalRain cannot be smaller than 0");
        }
        this.temperature = temperature;
        this.dewPoint = dewPoint;
        this.windSpeed = windSpeed;
        this.totalRain = totalRain;
    }

    // getter methods
    public int getTemperature() {
        return temperature;
    }

    public int getDewPoint() {
        return dewPoint;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getTotalRain() {
        return totalRain;
    }

    public int getRelativeHumidity() {
        return 100 - 5 * (temperature - dewPoint);
    }

    public double getHeatIndex() {
        double T = temperature;
        double R = getRelativeHumidity();
        return -8.78469475556 + (1.61139411 * T) + (2.33854883889 * R) + (-0.14611605 * T * R) + (-0.012308094 * T * T)
                + (-0.0164248277778 * R * R) + (0.002211732 * T * T * R) + (0.00072546 * T * R * R)
                + (-0.000003582 * T * T * R * R);
    }

    public double getWindChill() {
        double T = (temperature * 9.0 / 5) + 32;
        double v = windSpeed;
        return 35.74 + (0.6215 * T) - (35.75 * Math.pow(v, 0.16)) + (0.4275 * T * Math.pow(v, 0.16));
    }

    @Override
    public String toString() {
        return String.format("Reading: T = %d, D = %d, v = %d, rain = %d", temperature, dewPoint, windSpeed, totalRain);
    }
}