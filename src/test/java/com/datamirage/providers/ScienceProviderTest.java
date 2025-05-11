package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class ScienceProviderTest {
    private ScienceProvider scienceProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        scienceProvider = new ScienceProvider(new RandomService());
    }

    @RepeatedTest(20)
    void unit_ShouldReturnValidUnit() {
        String unit = scienceProvider.unit();
        assertNotNull(unit);
        assertFalse(unit.isEmpty());
    }

    @RepeatedTest(20)
    void chemicalFormula_ShouldReturnValidFormula() {
        String formula = scienceProvider.chemicalFormula();
        assertNotNull(formula);
        assertFalse(formula.isEmpty());
        assertTrue(formula.matches("^[A-Z][a-z]?\\d*$"));
    }
} 