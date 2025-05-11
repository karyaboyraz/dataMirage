package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class AnimalProviderTest {
    private static final long TEST_SEED = 12345L;
    private AnimalProvider animalProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        animalProvider = new AnimalProvider(new RandomService(TEST_SEED));
    }

    @RepeatedTest(20)
    void animal_ShouldReturnValidAnimal() {
        String animal = animalProvider.animal();
        assertNotNull(animal);
        assertFalse(animal.isEmpty());
        assertTrue(animal.matches("^[\\p{L}\\s]+$"));
    }

    @RepeatedTest(20)
    void animalType_ShouldReturnValidType() {
        String type = animalProvider.animalType();
        assertNotNull(type);
        assertFalse(type.isEmpty());
        assertTrue(type.matches("^[\\p{L}\\s]+$"));
    }

    @RepeatedTest(20)
    void animalScientificName_ShouldReturnValidName() {
        String name = animalProvider.animalScientificName();
        assertNotNull(name);
        assertFalse(name.isEmpty());
        assertTrue(name.matches("^[A-Z][a-z]+\\s[a-z]+$"));
    }

    @RepeatedTest(20)
    void animalFamily_ShouldReturnValidFamily() {
        String family = animalProvider.animalFamily();
        assertNotNull(family);
        assertFalse(family.isEmpty());
        assertTrue(family.matches("^[\\p{L}\\s]+$"));
    }

    @RepeatedTest(20)
    void animalKingdom_ShouldReturnValidKingdom() {
        String kingdom = animalProvider.animalKingdom();
        assertNotNull(kingdom);
        assertFalse(kingdom.isEmpty());
        assertTrue(kingdom.matches("^[\\p{L}\\s]+$"));
    }

    @RepeatedTest(20)
    void animalPhylum_ShouldReturnValidPhylum() {
        String phylum = animalProvider.animalPhylum();
        assertNotNull(phylum);
        assertFalse(phylum.isEmpty());
        assertTrue(phylum.matches("^[\\p{L}\\s]+$"));
    }

    @RepeatedTest(20)
    void animalClass_ShouldReturnValidClass() {
        String animalClass = animalProvider.animalClass();
        assertNotNull(animalClass);
        assertFalse(animalClass.isEmpty());
        assertTrue(animalClass.matches("^[\\p{L}\\s]+$"));
    }

    @RepeatedTest(20)
    void animalOrder_ShouldReturnValidOrder() {
        String order = animalProvider.animalOrder();
        assertNotNull(order);
        assertFalse(order.isEmpty());
        assertTrue(order.matches("^[\\p{L}\\s]+$"));
    }

    @RepeatedTest(20)
    void animalGenus_ShouldReturnValidGenus() {
        String genus = animalProvider.animalGenus();
        assertNotNull(genus);
        assertFalse(genus.isEmpty());
        assertTrue(genus.matches("^[A-Z][a-z]+$"));
    }

    @RepeatedTest(20)
    void animalSpecies_ShouldReturnValidSpecies() {
        String species = animalProvider.animalSpecies();
        assertNotNull(species);
        assertFalse(species.isEmpty());
        assertTrue(species.matches("^[a-z]+$"));
    }
} 