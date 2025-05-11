package com.datamirage.providers;

import com.datamirage.util.RandomService;

/**
 * A provider class for generating boolean values.
 * This class provides methods to generate random boolean values with or without
 * specified probability.
 */
public class BoolProvider {
    private final RandomService random;

    /**
     * Constructs a new BoolProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public BoolProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random boolean value with equal probability (50% true, 50% false).
     *
     * @return A randomly generated boolean value
     */
    public boolean bool() {
        return random.nextBoolean();
    }

    /**
     * Generates a random boolean value with the specified probability of being true.
     *
     * @param probability The probability of returning true (between 0.0 and 1.0)
     * @return A randomly generated boolean value with the specified probability
     */
    public boolean bool(double probability) {
        return random.nextDouble(0.0, 1.0) < probability;
    }

    /**
     * Main method for testing the functionality of BoolProvider.
     * This method demonstrates the usage of boolean generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        BoolProvider boolProvider = new BoolProvider(new RandomService());
        System.out.println("Random boolean: " + boolProvider.bool());
        System.out.println("Boolean with 30% probability: " + boolProvider.bool(0.3));
    }
} 