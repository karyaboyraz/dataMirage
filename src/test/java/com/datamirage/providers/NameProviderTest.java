package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NameProviderTest {
    private NameProvider nameProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        nameProvider = new NameProvider(new RandomService());
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void firstName_ShouldWorkForAllLocales(DataMirageLocale locale) {
        DataLoader.setLocale(locale);
        assertTrue(DataLoader.hasLocaleSpecificField("name", "first_names"), 
                  "Locale " + locale + " is missing required 'first_names' data in name.yaml");
        
        NameProvider provider = new NameProvider(new RandomService());
        String firstName = provider.firstName();
        assertNotNull(firstName);
        assertFalse(firstName.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void lastName_ShouldWorkForAllLocales(DataMirageLocale locale) {
        DataLoader.setLocale(locale);
        assertTrue(DataLoader.hasLocaleSpecificField("name", "last_names"), 
                  "Locale " + locale + " is missing required 'last_names' data in name.yaml");
        
        NameProvider provider = new NameProvider(new RandomService());
        String lastName = provider.lastName();
        assertNotNull(lastName);
        assertFalse(lastName.isEmpty());
    }

    @RepeatedTest(20)
    void fullName_ShouldReturnValidFormat() {
        assertTrue(DataLoader.hasLocaleSpecificField("name", "first_names"), 
                  "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'first_names' data in name.yaml");
        assertTrue(DataLoader.hasLocaleSpecificField("name", "last_names"), 
                  "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'last_names' data in name.yaml");
        
        String fullName = nameProvider.fullName();
        assertNotNull(fullName);
        assertFalse(fullName.isEmpty());
        assertTrue(fullName.contains(" "));
    }

    @RepeatedTest(20)
    void prefix_ShouldReturnValidPrefix() {
        assertTrue(DataLoader.hasLocaleSpecificField("name", "prefixes"), 
                  "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'prefixes' data in name.yaml");
        
        String prefix = nameProvider.prefix();
        assertNotNull(prefix);
        assertFalse(prefix.isEmpty());
    }

    @RepeatedTest(20)
    void suffix_ShouldReturnValidSuffix() {
        assertTrue(DataLoader.hasLocaleSpecificField("name", "suffixes"), 
                  "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'suffixes' data in name.yaml");
        
        String suffix = nameProvider.suffix();
        assertNotNull(suffix);
        assertFalse(suffix.isEmpty());
    }

    @RepeatedTest(20)
    void title_ShouldReturnValidTitle() {
        assertTrue(DataLoader.hasLocaleSpecificField("name", "titles"), 
                  "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'titles' data in name.yaml");
        
        String title = nameProvider.title();
        assertNotNull(title);
        assertFalse(title.isEmpty());
    }

    @RepeatedTest(20)
    void gender_ShouldReturnValidGender() {
        assertTrue(DataLoader.hasLocaleSpecificField("name", "gender"), 
                  "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'gender' data in name.yaml");
        
        String gender = nameProvider.gender();
        assertNotNull(gender);
        assertFalse(gender.isEmpty());
    }

    @RepeatedTest(20)
    void username_ShouldReturnValidFormat() {
        assertTrue(DataLoader.hasLocaleSpecificField("name", "first_names"), 
                  "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'first_names' data in name.yaml");
        assertTrue(DataLoader.hasLocaleSpecificField("name", "last_names"), 
                  "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'last_names' data in name.yaml");
        
        String username = nameProvider.username();
        assertNotNull(username);
        assertFalse(username.isEmpty());
        assertTrue(username.matches("[a-zA-Z0-9._-]+"));
    }

    @RepeatedTest(20)
    void jobTitle_ShouldReturnValidTitle() {
        assertTrue(DataLoader.hasLocaleSpecificField("name", "titles"), 
                  "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'titles' data in name.yaml");
        
        String jobTitle = nameProvider.jobTitle();
        assertNotNull(jobTitle);
        assertFalse(jobTitle.isEmpty());
    }
    
    @Test
    void allLocalesShouldHaveRequiredNameFields() {
        List<String> requiredFields = Arrays.asList(
            "first_names",
            "last_names",
            "prefixes",
            "suffixes",
            "titles",
            "gender"
        );
        
        Map<DataMirageLocale, List<String>> missingFields = new HashMap<>();
        
        for (DataMirageLocale locale : DataMirageLocale.values()) {
            DataLoader.setLocale(locale);
            
            for (String field : requiredFields) {
                if (!DataLoader.hasLocaleSpecificField("name", field)) {
                    if (!missingFields.containsKey(locale)) {
                        missingFields.put(locale, new ArrayList<>());
                    }
                    missingFields.get(locale).add(field);
                }
            }
        }
        
        assertTrue(missingFields.isEmpty(), 
                  "The following locales are missing required name fields: " + missingFields);
    }
} 