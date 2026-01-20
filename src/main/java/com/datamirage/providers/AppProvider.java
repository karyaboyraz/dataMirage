package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating application-related data.
 * This class provides methods to generate various application information such as
 * app names, platforms, categories, and version numbers.
 */
public class AppProvider extends AbstractProvider {

    /**
     * Constructs a new AppProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public AppProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new AppProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #AppProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public AppProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random application name.
     *
     * @return A random application name as a string
     */
    public String name() {
        return randomFromLocaleList("appNames", "app", "app_names");
    }

    /**
     * Generates a random application platform.
     *
     * @return A random application platform as a string
     */
    public String platform() {
        return randomFromLocaleList("appPlatform", "app", "platforms");
    }

    /**
     * Generates a random application category.
     *
     * @return A random application category as a string
     */
    public String category() {
        return randomFromLocaleList("appCategory", "app", "categories");
    }

    /**
     * Generates a random application version number in the format X.Y.Z.
     * X ranges from 1 to 10, Y ranges from 0 to 9, and Z ranges from 0 to 99.
     *
     * @return A random application version number as a string
     */
    public String version() {
        return String.format("%d.%d.%d",
                random.nextInt(1, 10),
                random.nextInt(0, 9),
                random.nextInt(0, 99));
    }

    /**
     * Main method for testing the functionality of AppProvider.
     *
     * @param args Command line arguments (not used)
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        AppProvider appProvider = new AppProvider(new RandomService());
        System.out.println("Random App Name: " + appProvider.name());
        System.out.println("Random App Version: " + appProvider.version());
        System.out.println("Random App Platform: " + appProvider.platform());
        System.out.println("Random App Category: " + appProvider.category());
    }
}
