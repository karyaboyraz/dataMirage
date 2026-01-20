package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating fake food-related data.
 * This class provides methods to generate various food-related information,
 * including ingredients, spices, measurements, and dish names.
 */
public class FoodProvider extends AbstractProvider {

    /**
     * Constructs a new FoodProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public FoodProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new FoodProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #FoodProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public FoodProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random food ingredient.
     *
     * @return Randomly selected food ingredient
     */
    public String ingredient() {
        return randomFromLocaleList("foodIngredients", "food", "ingredients");
    }

    /**
     * Generates a random spice.
     *
     * @return A randomly selected spice
     */
    public String spice() {
        return randomFromLocaleList("foodSpices", "food", "spices");
    }

    /**
     * Generates a random measurement unit for cooking.
     *
     * @return A randomly selected measurement unit
     */
    public String measurement() {
        return randomFromLocaleList("foodMeasurements", "food", "measurements");
    }

    /**
     * Generates a random dish name.
     *
     * @return A randomly selected dish name
     */
    public String dish() {
        return randomFromLocaleList("foodDishes", "food", "dishes");
    }

    /**
     * Main method for testing the FoodProvider functionality.
     * This method demonstrates the usage of various food-related generation methods.
     *
     * @param args Command line arguments (not used)
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        FoodProvider foodProvider = new FoodProvider(new RandomService());
        System.out.println("Random Ingredient: " + foodProvider.ingredient());
        System.out.println("Random Spice: " + foodProvider.spice());
        System.out.println("Random Measurement: " + foodProvider.measurement());
        System.out.println("Random Dish: " + foodProvider.dish());
    }
}
