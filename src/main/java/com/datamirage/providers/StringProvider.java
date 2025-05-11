package com.datamirage.providers;

import com.datamirage.util.RandomService;

/**
 * A provider class for generating various types of strings.
 * This class provides methods to generate strings with different character sets
 * including alphabetic, alphanumeric, numeric, hexadecimal, binary, and octal characters.
 */
public class StringProvider {
    private final RandomService random;

    /**
     * Constructs a new StringProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public StringProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a string containing only alphabetic characters.
     *
     * @param count The length of the string to generate
     * @return A string containing random alphabetic characters
     */
    public String alpha(int count) {
        return random.randomString(count, false, true, true, false);
    }

    /**
     * Generates a string containing both alphabetic and numeric characters.
     *
     * @param count The length of the string to generate
     * @return A string containing random alphanumeric characters
     */
    public String alphaNumeric(int count) {
        return random.randomString(count, true, true, true, false);
    }

    /**
     * Generates a string containing only numeric characters.
     *
     * @param count The length of the string to generate
     * @return A string containing random numeric characters
     */
    public String numeric(int count) {
        return random.randomString(count, true, false, false, false);
    }

    /**
     * Generates a string containing sample characters.
     *
     * @param count The length of the string to generate
     * @return A string containing random sample characters
     */
    public String sample(int count) {
        return random.randomString(count, false, false, false, true);
    }

    /**
     * Generates a string containing hexadecimal characters (0-9, a-f).
     *
     * @param count The length of the string to generate
     * @return A string containing random hexadecimal characters
     */
    public String hex(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(Integer.toHexString(random.nextInt(0, 15)));
        }
        return result.toString();
    }

    /**
     * Generates a string containing binary characters (0-1).
     *
     * @param count The length of the string to generate
     * @return A string containing random binary characters
     */
    public String binary(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(random.nextInt(0, 1));
        }
        return result.toString();
    }

    /**
     * Generates a string containing octal characters (0-7).
     *
     * @param count The length of the string to generate
     * @return A string containing random octal characters
     */
    public String octal(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(random.nextInt(0, 7));
        }
        return result.toString();
    }

    /**
     * Generates a random UUID (Universally Unique Identifier).
     * The UUID follows the standard format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
     *
     * @return A randomly generated UUID as a string
     */
    public String uuid() {
        return String.format("%s-%s-%s-%s-%s",
            hex(8),
            hex(4),
            hex(4),
            hex(4),
            hex(12)
        );
    }

    /**
     * Generates a random string of specified length using alphanumeric characters.
     *
     * @param length The length of the string to generate
     * @return A random string of the specified length
     */
    public String randomString(int length) {
        StringBuilder result = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(random.nextInt(0, chars.length() - 1)));
        }
        
        return result.toString();
    }

    /**
     * Generates a random string of specified length using only alphabetic characters.
     *
     * @param length The length of the string to generate
     * @return A random alphabetic string of the specified length
     */
    public String randomAlphabetic(int length) {
        StringBuilder result = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(random.nextInt(0, chars.length() - 1)));
        }
        
        return result.toString();
    }

    /**
     * Generates a random string of specified length using only numeric characters.
     *
     * @param length The length of the string to generate
     * @return A random numeric string of the specified length
     */
    public String randomNumeric(int length) {
        StringBuilder result = new StringBuilder();
        String chars = "0123456789";
        
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(random.nextInt(0, chars.length() - 1)));
        }
        
        return result.toString();
    }

    /**
     * Generates a random string of specified length using alphanumeric characters and special characters.
     *
     * @param length The length of the string to generate
     * @return A random string with special characters of the specified length
     */
    public String randomWithSpecialChars(int length) {
        StringBuilder result = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{}|;:,.<>?";
        
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(random.nextInt(0, chars.length() - 1)));
        }
        
        return result.toString();
    }

    /**
     * Main method for testing the functionality of StringProvider.
     * This method demonstrates the usage of various string generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        StringProvider stringProvider = new StringProvider(new RandomService());
        System.out.println("Random String: " + stringProvider.randomString(10));
        System.out.println("Random Alphabetic: " + stringProvider.randomAlphabetic(10));
        System.out.println("Random Numeric: " + stringProvider.randomNumeric(10));
        System.out.println("Random with Special Chars: " + stringProvider.randomWithSpecialChars(10));
        System.out.println("UUID: " + stringProvider.uuid());
    }
} 