package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class FoodProviderTest {
    private FoodProvider foodProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        foodProvider = new FoodProvider(new RandomService());
    }

    @RepeatedTest(20)
    void ingredient_ShouldReturnValidIngredient() {
        String ingredient = foodProvider.ingredient();
        assertNotNull(ingredient);
        assertFalse(ingredient.isEmpty());
    }

    @RepeatedTest(20)
    void spice_ShouldReturnValidSpice() {
        String spice = foodProvider.spice();
        assertNotNull(spice);
        assertFalse(spice.isEmpty());
    }

    @RepeatedTest(20)
    void measurement_ShouldReturnValidMeasurement() {
        String measurement = foodProvider.measurement();
        assertNotNull(measurement);
        assertFalse(measurement.isEmpty());
    }

    @RepeatedTest(20)
    void dish_ShouldReturnValidDish() {
        String dish = foodProvider.dish();
        assertNotNull(dish);
        assertFalse(dish.isEmpty());
    }
} 