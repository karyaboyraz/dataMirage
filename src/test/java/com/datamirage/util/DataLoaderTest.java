package com.datamirage.util;

import com.datamirage.locale.DataMirageLocale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DataLoaderTest {

    private DataMirageLocale originalLocale;

    @BeforeEach
    void setUp() {
        originalLocale = DataLoader.getCurrentLocale();
    }

    @AfterEach
    void tearDown() {
        DataLoader.setLocale(originalLocale);
    }

    @Test
    void getCurrentLocale_ShouldReturnDefaultLocale() {
        assertEquals(DataMirageLocale.TR_TR, DataLoader.getCurrentLocale());
    }

    @Test
    void setLocale_ShouldChangeCurrentLocale() {
        DataLoader.setLocale(DataMirageLocale.EN_US);
        assertEquals(DataMirageLocale.EN_US, DataLoader.getCurrentLocale());
    }

    @ParameterizedTest
    @EnumSource(value = DataMirageLocale.class, names = {"TR_TR", "EN_US", "DE_DE", "FR_FR", "ES_ES", "IT_IT", "RU_RU"})
    void getListData_ShouldLoadDataForSupportedLocales(DataMirageLocale locale) {
        DataLoader.setLocale(locale);
        
        // Test common data categories that should be available in all locales
        assertDoesNotThrow(() -> {
            List<String> cityData = DataLoader.getListData("address", "cities");
            assertNotNull(cityData);
            assertFalse(cityData.isEmpty());
        }, "Failed to load cities data for locale: " + locale);
        
        assertDoesNotThrow(() -> {
            List<String> firstNameData = DataLoader.getListData("name", "first_names");
            assertNotNull(firstNameData);
            assertFalse(firstNameData.isEmpty());
        }, "Failed to load first names data for locale: " + locale);
    }
    
    @ParameterizedTest
    @EnumSource(value = DataMirageLocale.class)
    void verifyLocaleSpecificDataExists(DataMirageLocale locale) {
        DataLoader.setLocale(locale);
        
        // Define required categories and fields for each locale
        Map<String, String[]> requiredData = new HashMap<>();
        requiredData.put("address", new String[]{"cities", "streets", "states"});
        requiredData.put("name", new String[]{"first_names", "last_names"});
        requiredData.put("food", new String[]{"dishes", "ingredients"});
        
        for (Map.Entry<String, String[]> entry : requiredData.entrySet()) {
            String category = entry.getKey();
            String[] fields = entry.getValue();
            
            // Check if the category YAML file exists specifically for this locale
            String localePath = "data/" + locale.getCode() + "/" + category + ".yaml";
            InputStream localeIs = getClass().getClassLoader().getResourceAsStream(localePath);
            
            if (localeIs == null) {
                fail("Locale-specific data file does not exist: " + localePath);
            }
            
            // Verify each required field exists
            for (String field : fields) {
                try {
                    List<String> data = DataLoader.getListData(category, field);
                    assertNotNull(data, "Data should not be null for " + category + "." + field + " in locale " + locale);
                    assertFalse(data.isEmpty(), "Data should not be empty for " + category + "." + field + " in locale " + locale);
                } catch (RuntimeException e) {
                    fail("Missing required data field: " + category + "." + field + " in locale " + locale);
                }
            }
        }
    }

    @Test
    void getListData_ShouldReturnUnmodifiableList() {
        List<String> cities = DataLoader.getListData("address", "cities");
        assertThrows(UnsupportedOperationException.class, () -> cities.add("New City"));
    }

    @Test
    void getListData_ShouldThrowRuntimeExceptionForInvalidCategory() {
        assertThrows(RuntimeException.class, () -> DataLoader.getListData("invalid_category", "field"));
    }

    @Test
    void getListData_ShouldThrowRuntimeExceptionForInvalidField() {
        assertThrows(RuntimeException.class, () -> DataLoader.getListData("address", "invalid_field"));
    }

    @ParameterizedTest
    @EnumSource(value = DataMirageLocale.class, names = {"TR_TR", "EN_US", "DE_DE", "FR_FR"})
    void getListData_ShouldReadColorData(DataMirageLocale locale) {
        DataLoader.setLocale(locale);
        
        assertDoesNotThrow(() -> {
            List<String> data = DataLoader.getListData("color", "color_names");
            assertNotNull(data);
            assertFalse(data.isEmpty());
        }, "Failed to load color names data for locale: " + locale);
    }
} 