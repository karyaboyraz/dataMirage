package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.LazyLoader;
import com.datamirage.util.RandomService;

import java.util.List;

/**
 * A provider class for generating fake animal-related data.
 * This class provides methods to generate various animal-related information,
 * including animal names, types, scientific names, and taxonomic classifications.
 */
public class AnimalProvider {
    private final RandomService random;
    private List<String> animals;
    private List<String> animalTypes;
    private List<String> animalScientificName;
    private List<String> animalFamilies;
    private List<String> animalKingdoms;
    private List<String> animalPhyla;
    private List<String> animalClasses;
    private List<String> animalOrders;
    private List<String> animalGenera;
    private List<String> animalSpecies;

    /**
     * Constructs a new AnimalProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public AnimalProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random animal name.
     *
     * @return A randomly selected animal name
     */
    public String animal() {
        animals = LazyLoader.load("animalNames", () -> DataLoader.getListData("animal", "animal_names"));
        return random.randomElement(animals);
    }

    /**
     * Generates a random animal type (e.g., mammal, bird, reptile).
     *
     * @return A randomly selected animal type
     */
    public String animalType() {
        animalTypes = LazyLoader.load("animalTypes", () -> DataLoader.getListData("animal", "types"));
        return random.randomElement(animalTypes);
    }

    /**
     * Generates a random scientific name for an animal.
     * Scientific names follow the binomial nomenclature format (Genus species).
     *
     * @return A randomly selected scientific name
     */
    public String animalScientificName() {
        animalScientificName = LazyLoader.load("animalScienceName", () -> DataLoader.getListData("animal", "scientific_names"));
        return random.randomElement(animalScientificName);
    }

    /**
     * Generates a random animal family name.
     *
     * @return A randomly selected animal family name
     */
    public String animalFamily() {
        animalFamilies = LazyLoader.load("animalFamilies", () -> DataLoader.getListData("animal", "families"));
        return random.randomElement(animalFamilies);
    }

    /**
     * Generates a random animal kingdom name.
     *
     * @return A randomly selected animal kingdom name
     */
    public String animalKingdom() {
        animalKingdoms = LazyLoader.load("animalKingdoms", () -> DataLoader.getListData("animal", "kingdoms"));
        return random.randomElement(animalKingdoms);
    }

    /**
     * Generates a random animal phylum name.
     *
     * @return A randomly selected animal phylum name
     */
    public String animalPhylum() {
        animalPhyla = LazyLoader.load("animalPhyla", () -> DataLoader.getListData("animal", "phyla"));
        return random.randomElement(animalPhyla);
    }

    /**
     * Generates a random animal class name.
     *
     * @return A randomly selected animal class name
     */
    public String animalClass() {
        animalClasses = LazyLoader.load("animalClasses", () -> DataLoader.getListData("animal", "classes"));
        return random.randomElement(animalClasses);
    }

    /**
     * Generates a random animal order name.
     *
     * @return A randomly selected animal order name
     */
    public String animalOrder() {
        animalOrders = LazyLoader.load("animalOrders", () -> DataLoader.getListData("animal", "orders"));
        return random.randomElement(animalOrders);
    }

    /**
     * Generates a random animal genus name.
     *
     * @return A randomly selected animal genus name
     */
    public String animalGenus() {
        animalGenera = LazyLoader.load("animalGenera", () -> DataLoader.getListData("animal", "genera"));
        return random.randomElement(animalGenera);
    }

    /**
     * Generates a random animal species name.
     *
     * @return A randomly selected animal species name
     */
    public String animalSpecies() {
        animalSpecies = LazyLoader.load("animalSpecies", () -> DataLoader.getListData("animal", "species"));
        return random.randomElement(animalSpecies);
    }

    /**
     * Main method for testing the functionality of AnimalProvider.
     * This method demonstrates the usage of various animal-related data generation methods.
     *
     * @param args Command line arguments (not used)
     */
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