package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating weather-related data.
 * This class provides methods to generate various weather information such as
 * temperature, wind speed, wind direction, humidity, and weather descriptions.
 */
public class WeatherProvider {
    private static final int DEFAULT_MIN_TEMP_C = -30;
    private static final int DEFAULT_MAX_TEMP_C = 60;
    private static final int DEFAULT_MIN_TEMP_F = -22;
    private static final int DEFAULT_MAX_TEMP_F = 100;
    private static final int DEFAULT_MIN_WIND_SPEED_KM = 0;
    private static final int DEFAULT_MAX_WIND_SPEED_KM = 100;
    private static final int DEFAULT_MIN_WIND_SPEED_MPH = 0;
    private static final int DEFAULT_MAX_WIND_SPEED_MPH = 70;

    private static final String CELCIUS_PARAM = " °C";
    private static final String FAHRENHEIT_PARAM = " °F";
    private static final String KM_PARAM = " km/h";
    private static final String MPH_PARAM = " mph";

    private final RandomService random;
    private List<String> descriptions;
    private List<String> windDirections;

    /**
     * Constructs a new WeatherProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public WeatherProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random weather description.
     *
     * @return A random weather description as a string
     */
    public String getDescription() {
        descriptions = LazyLoader.load("weatherDescriptions", () -> DataLoader.getListData("weather", "descriptions"));
        return random.randomElement(descriptions);
    }

    /**
     * Generates a random temperature in Celsius using default range (-30°C to 60°C).
     *
     * @return A random temperature in Celsius as a string
     */
    public String temperatureCelsius() {
        return random.nextInt(DEFAULT_MIN_TEMP_C, DEFAULT_MAX_TEMP_C) + CELCIUS_PARAM;
    }

    /**
     * Generates a random temperature in Celsius using the specified range.
     *
     * @param minTemperature The minimum temperature in Celsius
     * @param maxTemperature The maximum temperature in Celsius
     * @return A random temperature in Celsius as a string
     */
    public String temperatureCelsius(int minTemperature, int maxTemperature) {
        return temperature(minTemperature, maxTemperature, CELCIUS_PARAM);
    }

    /**
     * Generates a random temperature in Fahrenheit using default range (-22°F to 100°F).
     *
     * @return A random temperature in Fahrenheit as a string
     */
    public String temperatureFahrenheit() {
        return random.nextInt(DEFAULT_MIN_TEMP_F, DEFAULT_MAX_TEMP_F) + FAHRENHEIT_PARAM;
    }

    /**
     * Generates a random temperature in Fahrenheit using the specified range.
     *
     * @param minTemperature The minimum temperature in Fahrenheit
     * @param maxTemperature The maximum temperature in Fahrenheit
     * @return A random temperature in Fahrenheit as a string
     */
    public String temperatureFahrenheit(int minTemperature, int maxTemperature) {
        return temperature(minTemperature, maxTemperature, FAHRENHEIT_PARAM);
    }

    /**
     * Generates a random temperature with the specified range and unit.
     *
     * @param minTemperature The minimum temperature
     * @param maxTemperature The maximum temperature
     * @param degreeKey The temperature unit (e.g., °C, °F)
     * @return A random temperature as a string
     */
    private String temperature(int minTemperature, int maxTemperature, String degreeKey) {
        return random.nextInt(minTemperature, maxTemperature) + degreeKey;
    }

    /**
     * Generates a random wind direction.
     *
     * @return A random wind direction as a string
     */
    public String getWindDirection() {
        windDirections = LazyLoader.load("weatherWindDirections", () -> DataLoader.getListData("weather", "wind_directions"));
        return random.randomElement(windDirections);
    }

    /**
     * Generates a random wind speed in kilometers per hour using default range (0 km/h to 100 km/h).
     *
     * @return A random wind speed in km/h as a string
     */
    public String windSpeedKm() {
        return windSpeed(DEFAULT_MIN_WIND_SPEED_KM, DEFAULT_MAX_WIND_SPEED_KM, KM_PARAM);
    }

    /**
     * Generates a random wind speed in kilometers per hour using the specified range.
     *
     * @param minSpeed The minimum wind speed in km/h
     * @param maxSpeed The maximum wind speed in km/h
     * @return A random wind speed in km/h as a string
     */
    public String windSpeedKm(int minSpeed, int maxSpeed) {
        return windSpeed(minSpeed, maxSpeed, KM_PARAM);
    }

    /**
     * Generates a random wind speed in miles per hour using default range (0 mph to 70 mph).
     *
     * @return A random wind speed in mph as a string
     */
    public String windSpeedMph() {
        return windSpeed(DEFAULT_MIN_WIND_SPEED_MPH, DEFAULT_MAX_WIND_SPEED_MPH, MPH_PARAM);
    }

    /**
     * Generates a random wind speed in miles per hour using the specified range.
     *
     * @param minSpeed The minimum wind speed in mph
     * @param maxSpeed The maximum wind speed in mph
     * @return A random wind speed in mph as a string
     */
    public String windSpeedMph(int minSpeed, int maxSpeed) {
        return windSpeed(minSpeed, maxSpeed, MPH_PARAM);
    }

    /**
     * Generates a random wind speed with the specified range and unit.
     *
     * @param minTemperature The minimum wind speed
     * @param maxTemperature The maximum wind speed
     * @param degreeKey The speed unit (e.g., km/h, mph)
     * @return A random wind speed as a string
     */
    private String windSpeed(int minTemperature, int maxTemperature, String degreeKey) {
        return random.nextInt(minTemperature, maxTemperature) + degreeKey;
    }

    /**
     * Generates a random relative humidity percentage (0% to 100%).
     *
     * @return A random relative humidity as a string
     */
    public String getRelativeHumidity() {
        return random.nextInt(0, 100) + "%";
    }

    /**
     * Generates a random absolute humidity in grams per cubic meter (0 g/m³ to 600 g/m³).
     *
     * @return A random absolute humidity as a string
     */
    public String getAbsoluteHumidity() {
        return random.nextInt(0, 600) + " g/m³";
    }

    /**
     * Generates a random humidity percentage between 0 and 100.
     *
     * @return A random humidity percentage as a string
     */
    public String humidity() {
        return random.nextInt(0, 100) + "%";
    }

    /**
     * Generates a random weather report containing temperature, wind speed, wind direction,
     * humidity, and weather description.
     *
     * @return A string containing a complete weather report
     */
    public String weatherReport() {
        return String.format("Temperature: %s, Wind: %s %s, Humidity: %s, Description: %s",
            temperatureCelsius(),
            windSpeedKm(),
            getWindDirection(),
            humidity(),
            getDescription()
        );
    }

    /**
     * Main method for testing the functionality of WeatherProvider.
     * This method demonstrates the usage of various weather-related data generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        WeatherProvider weatherProvider = new WeatherProvider(new RandomService());
        System.out.println("Temperature (C): " + weatherProvider.temperatureCelsius());
        System.out.println("Temperature (F): " + weatherProvider.temperatureFahrenheit());
        System.out.println("Wind Speed (km/h): " + weatherProvider.windSpeedKm());
        System.out.println("Wind Speed (mph): " + weatherProvider.windSpeedMph());
        System.out.println("Wind Direction: " + weatherProvider.getWindDirection());
        System.out.println("Humidity: " + weatherProvider.humidity());
        System.out.println("Description: " + weatherProvider.getDescription());
        System.out.println("Complete Report: " + weatherProvider.weatherReport());
    }
}