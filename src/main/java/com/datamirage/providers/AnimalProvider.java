package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating fake animal-related data.
 * This class provides methods to generate various animal-related information,
 * including animal names, types, scientific names, and taxonomic classifications.
 */
public class AnimalProvider extends AbstractProvider {

    /**
     * Constructs a new AnimalProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public AnimalProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new AnimalProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #AnimalProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public AnimalProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random animal name.
     *
     * @return A randomly selected animal name
     */
    public String animal() {
        return randomFromLocaleList("animalNames", "animal", "animal_names");
    }

    /**
     * Generates a random animal type (e.g., mammal, bird, reptile).
     *
     * @return A randomly selected animal type
     */
    public String animalType() {
        return randomFromLocaleList("animalTypes", "animal", "types");
    }

    /**
     * Generates a random scientific name for an animal.
     * Scientific names follow the binomial nomenclature format (Genus species).
     *
     * @return A randomly selected scientific name
     */
    public String animalScientificName() {
        return randomFromLocaleList("animalScienceName", "animal", "scientific_names");
    }

    /**
     * Generates a random animal family name.
     *
     * @return A randomly selected animal family name
     */
    public String animalFamily() {
        return randomFromLocaleList("animalFamilies", "animal", "families");
    }

    /**
     * Generates a random animal kingdom name.
     *
     * @return A randomly selected animal kingdom name
     */
    public String animalKingdom() {
        return randomFromLocaleList("animalKingdoms", "animal", "kingdoms");
    }

    /**
     * Generates a random animal phylum name.
     *
     * @return A randomly selected animal phylum name
     */
    public String animalPhylum() {
        return randomFromLocaleList("animalPhyla", "animal", "phyla");
    }

    /**
     * Generates a random animal class name.
     *
     * @return A randomly selected animal class name
     */
    public String animalClass() {
        return randomFromLocaleList("animalClasses", "animal", "classes");
    }

    /**
     * Generates a random animal order name.
     *
     * @return A randomly selected animal order name
     */
    public String animalOrder() {
        return randomFromLocaleList("animalOrders", "animal", "orders");
    }

    /**
     * Generates a random animal genus name.
     *
     * @return A randomly selected animal genus name
     */
    public String animalGenus() {
        return randomFromLocaleList("animalGenera", "animal", "genera");
    }

    /**
     * Generates a random animal species name.
     *
     * @return A randomly selected animal species name
     */
    public String animalSpecies() {
        return randomFromLocaleList("animalSpecies", "animal", "species");
    }

    /**
     * Main method for testing the functionality of AnimalProvider.
     * This method demonstrates the usage of various animal-related data generation methods.
     *
     * @param args Command line arguments (not used)
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        AnimalProvider animalProvider = new AnimalProvider(new RandomService());
        System.out.println(animalProvider.animal());
        System.out.println(animalProvider.animalType());
        System.out.println(animalProvider.animalScientificName());
        System.out.println(animalProvider.animalFamily());
        System.out.println(animalProvider.animalKingdom());
        System.out.println(animalProvider.animalPhylum());
        System.out.println(animalProvider.animalClass());
        System.out.println(animalProvider.animalOrder());
        System.out.println(animalProvider.animalGenus());
        System.out.println(animalProvider.animalSpecies());
    }
}
