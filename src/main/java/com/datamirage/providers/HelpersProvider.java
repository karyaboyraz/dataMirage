package com.datamirage.providers;

import com.datamirage.util.RandomService;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * A utility provider class that offers helper methods for random data generation.
 * This class provides various utility methods for working with lists, arrays, maps,
 * and string patterns to generate random data.
 */
public class HelpersProvider {
    private final RandomService random;

    /**
     * Constructs a new HelpersProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public HelpersProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Returns a random element from the given list.
     *
     * @param <T> The type of elements in the list
     * @param list The list from which to select a random element
     * @return A randomly selected element from the list
     */
    public <T> T randomElement(List<T> list) {
        return random.randomElement(list);
    }

    /**
     * Returns a random element from the given array.
     *
     * @param <T> The type of elements in the array
     * @param array The array from which to select a random element
     * @return A randomly selected element from the array
     */
    public <T> T randomElement(T[] array) {
        return random.randomElement(array);
    }

    /**
     * Returns a random key from the given map.
     *
     * @param <K> The type of keys in the map
     * @param <V> The type of values in the map
     * @param map The map from which to select a random key
     * @return A randomly selected key from the map
     */
    public <K, V> K randomKey(Map<K, V> map) {
        return random.randomElement(new ArrayList<>(map.keySet()));
    }

    /**
     * Returns a random value from the given map.
     *
     * @param <K> The type of keys in the map
     * @param <V> The type of values in the map
     * @param map The map from which to select a random value
     * @return A randomly selected value from the map
     */
    public <K, V> V randomValue(Map<K, V> map) {
        return random.randomElement(new ArrayList<>(map.values()));
    }

    /**
     * Randomizes a string pattern by replacing special characters with random values.
     * The pattern can contain '#' for numbers and '?' for letters.
     *
     * @param pattern The string pattern to randomize
     * @return A string with randomized values based on the pattern
     */
    public String randomize(String pattern) {
        return random.randomize(pattern);
    }

    /**
     * Replaces '#' characters in the pattern with random numbers.
     *
     * @param pattern The string pattern containing '#' characters
     * @return A string with random numbers replacing '#' characters
     */
    public String numerify(String pattern) {
        return random.numerify(pattern);
    }

    /**
     * Replaces '?' characters in the pattern with random letters.
     *
     * @param pattern The string pattern containing '?' characters
     * @return A string with random letters replacing '?' characters
     */
    public String letterify(String pattern) {
        return random.letterify(pattern);
    }

    /**
     * Replaces both '#' and '?' characters in the pattern with random numbers and letters respectively.
     *
     * @param pattern The string pattern containing '#' and '?' characters
     * @return A string with random numbers and letters replacing '#' and '?' characters
     */
    public String bothify(String pattern) {
        return random.bothify(pattern);
    }

    public static void main(String[] args) {
        HelpersProvider helpersProvider = new HelpersProvider(new RandomService());
        List<String> list = List.of("apple", "banana", "cherry");
        System.out.println("Random element from list: " + helpersProvider.randomElement(list));

        String[] array = {"dog", "cat", "mouse"};
        System.out.println("Random element from array: " + helpersProvider.randomElement(array));

        Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
        System.out.println("Random key from map: " + helpersProvider.randomKey(map));
        System.out.println("Random value from map: " + helpersProvider.randomValue(map));

        String pattern = "##-##-##";
        System.out.println("Randomized pattern: " + helpersProvider.randomize(pattern));
        System.out.println("Numerified pattern: " + helpersProvider.numerify(pattern));
        System.out.println("Letterified pattern: " + helpersProvider.letterify(pattern));
        System.out.println("Bothified pattern: " + helpersProvider.bothify(pattern));

    }
} 