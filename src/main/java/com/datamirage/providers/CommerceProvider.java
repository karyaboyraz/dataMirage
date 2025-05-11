package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating e-commerce related data.
 * This class provides methods to generate various commerce-related information such as
 * product names, departments, materials, and promotion codes.
 */
@SuppressWarnings("ALL")
public class CommerceProvider {
    private final RandomService random;
    private List<String> productNames;
    private List<String> departments;
    private List<String> materials;

    /**
     * Constructs a new CommerceProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public CommerceProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random product name.
     *
     * @return A random product name as a string
     */
    public String productName() {
        productNames = LazyLoader.load("commerceProductNames", () -> DataLoader.getListData("commerce", "product_names"));
        return random.randomElement(productNames);
    }

    /**
     * Generates a random department name.
     *
     * @return A random department name as a string
     */
    public String department() {
        departments = LazyLoader.load("commerceDepartments", () -> DataLoader.getListData("commerce", "departments"));
        return random.randomElement(departments);
    }

    /**
     * Generates a random material name.
     *
     * @return A random material name as a string
     */
    public String material() {
        materials = LazyLoader.load("commerceMaterials", () -> DataLoader.getListData("commerce", "materials"));
        return random.randomElement(materials);
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
    public static void main(String[] args) {
        CommerceProvider commerceProvider = new CommerceProvider(new RandomService());
        System.out.println("Product Name: " + commerceProvider.productName());
        System.out.println("Department: " + commerceProvider.department());
        System.out.println("Material: " + commerceProvider.material());
        System.out.println("Promotion Code: " + commerceProvider.promotionCode());
    }
} 