package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating fake food-related data.
 * This class provides methods to generate various food-related information,
 * including ingredients, spices, measurements, and dish names.
 */
public class FoodProvider {
    private final RandomService random;
    private List<String> ingredients;
    private List<String> spices;
    private List<String> measurements;
    private List<String> dishes;

    /**
     * Constructs a new FoodProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public FoodProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random food ingredient.
     *
     * @return Randomly selected food ingredient
     */
    public String ingredient() {
        ingredients = LazyLoader.load("foodIngredients", () -> DataLoader.getListData("food", "ingredients"));
        return random.randomElement(ingredients);
    }

    /**
     * Generates a random spice.
     *
     * @return A randomly selected spice
     */
    public String spice() {
        spices = LazyLoader.load("foodSpices", () -> DataLoader.getListData("food", "spices"));
        return random.randomElement(spices);
    }

    /**
     * Generates a random measurement unit for cooking.
     *
     * @return A randomly selected measurement unit
     */
    public String measurement() {
        measurements = LazyLoader.load("foodMeasurements", () -> DataLoader.getListData("food", "measurements"));
        return random.randomElement(measurements);
    }

    /**
     * Generates a random dish name.
     *
     * @return A randomly selected dish name
     */
    public String dish() {
        dishes = LazyLoader.load("foodDishes", () -> DataLoader.getListData("food", "dishes"));
        return random.randomElement(dishes);
    }

    /**
     * Main method for testing the FoodProvider functionality.
     * This method demonstrates the usage of various food-related generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        FoodProvider foodProvider = new FoodProvider(new RandomService());
        System.out.println("Random Ingredient: " + foodProvider.ingredient());
        System.out.println("Random Spice: " + foodProvider.spice());
        System.out.println("Random Measurement: " + foodProvider.measurement());
        System.out.println("Random Dish: " + foodProvider.dish());
    }
} 