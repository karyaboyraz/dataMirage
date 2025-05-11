package com.datamirage.providers;

import com.datamirage.util.RandomService;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A provider class for generating fake number-related data.
 * This class provides methods to generate various types of numbers,
 * including integers, decimals, digits, and numbers in different bases (hex, binary, octal).
 */
public class NumberProvider {
    private final RandomService random;

    /**
     * Constructs a new NumberProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public NumberProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random integer within the specified range.
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (inclusive)
     * @return A random integer between min and max
     */
    public int number(int min, int max) {
        return random.nextInt(min, max);
    }

    /**
     * Generates a random decimal number within the specified range.
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (inclusive)
     * @return A random double between min and max
     */
    public double decimal(double min, double max) {
        return random.nextDouble(min, max);
    }

    /**
     * Generates a random decimal number within the specified range with the given scale.
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (inclusive)
     * @param scale The number of decimal places
     * @return A string representation of the random decimal with the specified scale
     */
    public String decimal(double min, double max, int scale) {
        double value = random.nextDouble(min, max);
        BigDecimal bd = new BigDecimal(value).setScale(scale, RoundingMode.HALF_UP);
        return bd.toString();
    }

    /**
     * Generates a random single digit (0-9).
     *
     * @return A string containing a single random digit
     */
    public String digit() {
        return String.valueOf(random.nextInt(0, 9));
    }

    /**
     * Generates a string of random digits with the specified length.
     *
     * @param count The number of digits to generate
     * @return A string containing the specified number of random digits
     */
    public String digits(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(digit());
        }
        return result.toString();
    }

    /**
     * Generates a string of random hexadecimal digits with the specified length.
     *
     * @param count The number of hexadecimal digits to generate
     * @return A string containing the specified number of random hexadecimal digits
     */
    public String hex(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(Integer.toHexString(random.nextInt(0, 15)));
        }
        return result.toString();
    }

    /**
     * Generates a string of random binary digits with the specified length.
     *
     * @param count The number of binary digits to generate
     * @return A string containing the specified number of random binary digits
     */
    public String binary(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(random.nextInt(0, 1));
        }
        return result.toString();
    }

    /**
     * Generates a string of random octal digits with the specified length.
     *
     * @param count The number of octal digits to generate
     * @return A string containing the specified number of random octal digits
     */
    public String octal(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(random.nextInt(0, 7));
        }
        return result.toString();
    }

    /**
     * Main method for testing the NumberProvider functionality.
     * This method demonstrates the usage of various number generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        NumberProvider numberProvider = new NumberProvider(new RandomService());
        System.out.println("Random Number: " + numberProvider.number(1, 100));
        System.out.println("Random Decimal: " + numberProvider.decimal(1.0, 100.0));
        System.out.println("Random Decimal with Scale: " + numberProvider.decimal(1.0, 100.0, 2));
        System.out.println("Random Digit: " + numberProvider.digit());
        System.out.println("Random Digits: " + numberProvider.digits(5));
        System.out.println("Random Hex: " + numberProvider.hex(5));
        System.out.println("Random Binary: " + numberProvider.binary(5));
        System.out.println("Random Octal: " + numberProvider.octal(5));
    }
} 