package com.datamirage.util;

import java.util.List;
import java.util.Random;

/**
 * A utility class that provides various random data generation methods.
 * This class serves as a wrapper around Java's Random class with additional functionality
 * for generating random numbers, strings, and selecting random elements from collections.
 */
public class RandomService {
    private final Random random;

    /**
     * Constructs a new RandomService with a random seed.
     */
    public RandomService() {
        this.random = new Random();
    }

    /**
     * Constructs a new RandomService with the specified seed.
     *
     * @param seed The seed value for the random number generator
     */
    public RandomService(long seed) {
        this.random = new Random(seed);
    }

    /**
     * Returns the underlying Random instance.
     *
     * @return The Random instance used by this service
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Generates a random integer between the specified minimum and maximum values (inclusive).
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (inclusive)
     * @return A random integer between min and max
     */
    public int nextInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Generates a random double between the specified minimum and maximum values.
     *
     * @param min The minimum value
     * @param max The maximum value
     * @return A random double between min and max
     */
    public double nextDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    /**
     * Generates a random boolean value.
     *
     * @return A random boolean value
     */
    public boolean nextBoolean() {
        return random.nextBoolean();
    }

    /**
     * Selects a random element from the specified list.
     *
     * @param <T> The type of elements in the list
     * @param list The list from which to select a random element
     * @return A randomly selected element from the list
     * @throws IllegalArgumentException if the list is null or empty
     */
    public <T> T randomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        return list.get(random.nextInt(list.size()));
    }

    /**
     * Selects a random element from the specified array.
     *
     * @param <T> The type of elements in the array
     * @param array The array from which to select a random element
     * @return A randomly selected element from the array, or null if the array is null or empty
     */
    public <T> T randomElement(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[random.nextInt(array.length)];
    }

    /**
     * Randomizes a string pattern by replacing '?' with random letters and '#' with random digits.
     *
     * @param pattern The pattern to randomize
     * @return The randomized string, or null if the pattern is null
     */
    public String randomize(String pattern) {
        if (pattern == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '?') {
                result.append((char) (random.nextInt(26) + 'A'));
            } else if (c == '#') {
                result.append(random.nextInt(10));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Replaces all '#' characters in the text with random digits.
     *
     * @param text The text to numerify
     * @return The numerified string
     * @throws IllegalArgumentException if the text is null
     */
    public String numerify(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == '#') {
                result.append(random.nextInt(10));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Replaces all '?' characters in the pattern with random letters.
     *
     * @param pattern The pattern to letterify
     * @return The letterified string, or null if the pattern is null
     */
    public String letterify(String pattern) {
        if (pattern == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '?') {
                result.append((char) (random.nextInt(26) + 'A'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Replaces all '?' characters with random letters and '#' characters with random digits.
     *
     * @param pattern The pattern to bothify
     * @return The bothified string, or null if the pattern is null
     */
    public String bothify(String pattern) {
        if (pattern == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '?') {
                result.append((char) (random.nextInt(26) + 'A'));
            } else if (c == '#') {
                result.append(random.nextInt(10));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Generates a random string with the specified characteristics.
     *
     * @param length The length of the string to generate
     * @param includeNumbers Whether to include numbers in the generated string
     * @param includeUppercaseLetters Whether to include uppercase letters in the generated string
     * @param includeLowercaseLetters Whether to include lowercase letters in the generated string
     * @param includeSpecialChars Whether to include special characters in the generated string
     * @return A random string with the specified characteristics
     * @throws IllegalArgumentException if length is less than or equal to 0, or if no character types are included
     */
    public String randomString(int length, boolean includeNumbers, boolean includeUppercaseLetters, boolean includeLowercaseLetters, boolean includeSpecialChars) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        StringBuilder result = new StringBuilder();
        String numbers = "0123456789";
        String uppercaseLetters = "ABCDEFGHJKLMNPRSTUVWXYZ";
        String lowercaseLetters = "abcdefghjklmnprstuvwxyz";
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        StringBuilder validChars = new StringBuilder();

        if (includeNumbers) {
            validChars.append(numbers);
        }
        if (includeUppercaseLetters) {
            validChars.append(uppercaseLetters);
        }
        if (includeLowercaseLetters) {
            validChars.append(lowercaseLetters);
        }
        if (includeSpecialChars) {
            validChars.append(specialChars);
        }

        if (validChars.isEmpty()) {
            throw new IllegalArgumentException("At least one character type must be included");
        }

        for (int i = 0; i < length; i++) {
            result.append(validChars.charAt(random.nextInt(validChars.length())));
        }

        return result.toString();
    }
} 