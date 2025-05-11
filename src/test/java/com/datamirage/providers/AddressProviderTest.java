package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddressProviderTest {
    private AddressProvider addressProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        addressProvider = new AddressProvider(new RandomService());
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void streetName_ShouldWorkForAllLocales(DataMirageLocale locale) {
        DataLoader.setLocale(locale);
        assertTrue(DataLoader.hasLocaleSpecificField("address", "streets"), 
                   "Locale " + locale + " is missing required 'streets' data in address.yaml");
        
        AddressProvider provider = new AddressProvider(new RandomService());
        
        String streetName = provider.streetName();
        assertNotNull(streetName);
        assertFalse(streetName.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void district_ShouldWorkForAllLocales(DataMirageLocale locale) {
        DataLoader.setLocale(locale);
        assertTrue(DataLoader.hasLocaleSpecificField("address", "districts"), 
                   "Locale " + locale + " is missing required 'districts' data in address.yaml");
        
        AddressProvider provider = new AddressProvider(new RandomService());
        
        String district = provider.district();
        assertNotNull(district);
        assertFalse(district.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void fullAddress_ShouldWorkForAllLocales(DataMirageLocale locale) {
        DataLoader.setLocale(locale);
        assertTrue(DataLoader.hasLocaleSpecificField("address", "full_patterns"), 
                   "Locale " + locale + " is missing required 'full_patterns' data in address.yaml");
        
        AddressProvider provider = new AddressProvider(new RandomService());
        
        String fullAddress = provider.fullAddress();
        assertNotNull(fullAddress);
        assertFalse(fullAddress.isEmpty());
    }

    @RepeatedTest(20)
    void buildingNumber_ShouldReturnValidNumber() {
        assertTrue(DataLoader.hasLocaleSpecificField("address", "building_number"), 
                   "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'building_number' data in address.yaml");
        
        String buildingNumber = addressProvider.buildingNumber();
        assertNotNull(buildingNumber);
        assertTrue(buildingNumber.matches("\\d+"));
        int number = Integer.parseInt(buildingNumber);
        assertTrue(number >= 1 && number <= 50);
    }

    @RepeatedTest(20)
    void postalCode_ShouldReturnValidPostalCode() {
        assertTrue(DataLoader.hasLocaleSpecificField("address", "postal_codes"), 
                   "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'postal_codes' data in address.yaml");
        
        String postalCode = addressProvider.postalCode();
        assertNotNull(postalCode);
        assertTrue(postalCode.matches("\\d+"), "Postal code should contain only digits: " + postalCode);
    }

    @RepeatedTest(20)
    void city_ShouldReturnValidCity() {
        assertTrue(DataLoader.hasLocaleSpecificField("address", "cities"), 
                   "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'cities' data in address.yaml");
        
        String city = addressProvider.city();
        assertNotNull(city);
        assertFalse(city.isEmpty());
    }

    @RepeatedTest(20)
    void streetSuffix_ShouldReturnValidSuffix() {
        assertTrue(DataLoader.hasLocaleSpecificField("address", "street_suffixes"), 
                   "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'street_suffixes' data in address.yaml");
        
        String suffix = addressProvider.streetSuffix();
        assertNotNull(suffix);
        assertFalse(suffix.isEmpty());
    }

    @RepeatedTest(20)
    void streetAddress_ShouldReturnValidFormat() {
        assertTrue(DataLoader.hasLocaleSpecificField("address", "street_patterns"), 
                   "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'street_patterns' data in address.yaml");
        
        String streetAddress = addressProvider.streetAddress();
        assertNotNull(streetAddress);
        assertFalse(streetAddress.isEmpty());
    }

    @RepeatedTest(20)
    void streetNumber_ShouldReturnValidNumber() {
        String streetNumber = addressProvider.streetNumber();
        assertNotNull(streetNumber);
        assertTrue(streetNumber.matches("\\d+"));
        int number = Integer.parseInt(streetNumber);
        assertTrue(number >= 1 && number <= 999);
    }

    @RepeatedTest(20)
    void state_ShouldReturnValidState() {
        assertTrue(DataLoader.hasLocaleSpecificField("address", "states"), 
                   "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'states' data in address.yaml");
        
        String state = addressProvider.state();
        assertNotNull(state);
        assertFalse(state.isEmpty());
    }

    @RepeatedTest(20)
    void country_ShouldReturnValidCountry() {
        assertTrue(DataLoader.hasLocaleSpecificField("address", "countries"), 
                   "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'countries' data in address.yaml");
        
        String country = addressProvider.country();
        assertNotNull(country);
        assertFalse(country.isEmpty());
    }

    @RepeatedTest(20)
    void zipCode_ShouldReturnValidFormat() {
        assertTrue(DataLoader.hasLocaleSpecificField("address", "postal_codes"), 
                   "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'postal_codes' data in address.yaml");
        
        String zipCode = addressProvider.zipCode();
        assertNotNull(zipCode);
        assertTrue(zipCode.matches("\\d+"), "Zip code should contain only digits: " + zipCode);
    }

    @RepeatedTest(20)
    void latitude_ShouldReturnValidFormat() {
        String latitude = addressProvider.latitude();
        assertNotNull(latitude);
        assertTrue(latitude.matches("-?\\d+(\\.\\d+)?"), "Latitude format invalid: " + latitude);
        double lat = Double.parseDouble(latitude);
        assertTrue(lat >= -90 && lat <= 90);
    }

    @RepeatedTest(20)
    void longitude_ShouldReturnValidFormat() {
        String longitude = addressProvider.longitude();
        assertNotNull(longitude);
        assertTrue(longitude.matches("-?\\d+(\\.\\d+)?"), "Longitude format invalid: " + longitude);
        double lon = Double.parseDouble(longitude);
        assertTrue(lon >= -180 && lon <= 180);
    }

    @RepeatedTest(20)
    void coordinates_ShouldReturnValidFormat() {
        String coordinates = addressProvider.coordinates();
        assertNotNull(coordinates);
        assertTrue(coordinates.matches("-?\\d+(\\.\\d+)?\\, -?\\d+(\\.\\d+)?"), 
                   "Coordinates format invalid: " + coordinates);
    }
    
    @Test
    void allLocalesShouldHaveRequiredAddressFields() {
        List<String> requiredFields = Arrays.asList(
            "streets", 
            "street_suffixes", 
            "districts", 
            "cities", 
            "countries", 
            "postal_codes", 
            "states",
            "street_patterns",
            "building_number",
            "building",
            "apartment",
            "full_patterns"
        );
        
        Map<DataMirageLocale, List<String>> missingFields = new HashMap<>();
        
        for (DataMirageLocale locale : DataMirageLocale.values()) {
            DataLoader.setLocale(locale);
            
            for (String field : requiredFields) {
                if (!DataLoader.hasLocaleSpecificField("address", field)) {
                    if (!missingFields.containsKey(locale)) {
                        missingFields.put(locale, new ArrayList<>());
                    }
                    missingFields.get(locale).add(field);
                }
            }
        }
        
        assertTrue(missingFields.isEmpty(), 
                   "The following locales are missing required address fields: " + missingFields);
    }
} 