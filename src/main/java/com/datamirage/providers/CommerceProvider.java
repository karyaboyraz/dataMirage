package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating e-commerce related data.
 * This class provides methods to generate various commerce-related information such as
 * product names, departments, materials, and promotion codes.
 */
@SuppressWarnings("ALL")
public class CommerceProvider extends AbstractProvider {

    /**
     * Constructs a new CommerceProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public CommerceProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new CommerceProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #CommerceProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public CommerceProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random product name.
     *
     * @return A random product name as a string
     */
    public String productName() {
        return randomFromLocaleList("commerceProductNames", "commerce", "product_names");
    }

    /**
     * Generates a random department name.
     *
     * @return A random department name as a string
     */
    public String department() {
        return randomFromLocaleList("commerceDepartments", "commerce", "departments");
    }

    /**
     * Generates a random material name.
     *
     * @return A random material name as a string
     */
    public String material() {
        return randomFromLocaleList("commerceMaterials", "commerce", "materials");
    }

    /**
     * Generates a random promotion code.
     * The code follows the format: PROMO-XXXX-#### where X is a letter and # is a digit.
     *
     * @return A random promotion code as a string
     */
    public String promotionCode() {
        String format = "PROMO-????-####";
        return random.bothify(format);
    }

    /**
     * Main method for testing the functionality of CommerceProvider.
     *
     * @param args Command line arguments (not used)
     */
    @Deprecated
    public static void main(String[] args) {
        CommerceProvider commerceProvider = new CommerceProvider(new RandomService());
        System.out.println("Product Name: " + commerceProvider.productName());
        System.out.println("Department: " + commerceProvider.department());
        System.out.println("Material: " + commerceProvider.material());
        System.out.println("Promotion Code: " + commerceProvider.promotionCode());
    }
}
