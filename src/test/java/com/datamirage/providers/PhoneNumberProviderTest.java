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

class PhoneNumberProviderTest {
    private PhoneNumberProvider phoneNumberProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        phoneNumberProvider = new PhoneNumberProvider(new RandomService());
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void phoneNumber_ShouldWorkForAllLocales(DataMirageLocale locale) {
        DataLoader.setLocale(locale);
        assertTrue(DataLoader.hasLocaleSpecificField("phone", "landlineFormats") || 
                   DataLoader.hasLocaleSpecificField("phone", "cellPhoneFormats"),
                   "Locale " + locale + " is missing both 'landlineFormats' and 'cellPhoneFormats' data in phone.yaml");
        
        PhoneNumberProvider provider = new PhoneNumberProvider(new RandomService());
        String phoneNumber = provider.phoneNumber();
        assertNotNull(phoneNumber);
        assertFalse(phoneNumber.isEmpty());
        assertTrue(phoneNumber.matches("^\\+?[0-9\\s-()]+$"));
    }

    @RepeatedTest(20)
    void cellPhone_ShouldReturnValidFormat() {
        assertTrue(DataLoader.hasLocaleSpecificField("phone", "cellPhoneFormats"),
                   "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'cellPhoneFormats' data in phone.yaml");
        
        String cellPhone = phoneNumberProvider.cellPhone();
        assertNotNull(cellPhone);
        assertFalse(cellPhone.isEmpty());
        assertTrue(cellPhone.matches("^\\+?[0-9\\s-()]+$"));
    }

    @RepeatedTest(20)
    void landline_ShouldReturnValidFormat() {
        assertTrue(DataLoader.hasLocaleSpecificField("phone", "landlineFormats"),
                   "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'landlineFormats' data in phone.yaml");
        
        String landline = phoneNumberProvider.landline();
        assertNotNull(landline);
        assertFalse(landline.isEmpty());
        assertTrue(landline.matches("^\\+?[0-9\\s-()]+$"));
    }

    @RepeatedTest(20)
    void internationalPhoneFormat_ShouldReturnValidFormat() {
        assertTrue(DataLoader.hasLocaleSpecificField("phone", "internationalFormats"),
                   "Current locale " + DataLoader.getCurrentLocale() + " is missing required 'internationalFormats' data in phone.yaml");
        
        String internationalPhone = phoneNumberProvider.internationalPhoneFormat();
        assertNotNull(internationalPhone);
        assertFalse(internationalPhone.isEmpty());
        assertTrue(internationalPhone.matches("^\\+[0-9]{1,3}[0-9\\s-()]+$"));
    }
    
    @Test
    void allLocalesShouldHaveRequiredPhoneFields() {
        List<String> requiredFields = Arrays.asList(
            "landlineFormats",
            "cellPhoneFormats",
            "internationalFormats"
        );
        
        Map<DataMirageLocale, List<String>> missingFields = new HashMap<>();
        
        for (DataMirageLocale locale : DataMirageLocale.values()) {
            DataLoader.setLocale(locale);
            
            for (String field : requiredFields) {
                if (!DataLoader.hasLocaleSpecificField("phone", field)) {
                    if (!missingFields.containsKey(locale)) {
                        missingFields.put(locale, new ArrayList<>());
                    }
                    missingFields.get(locale).add(field);
                }
            }
        }
        
        assertTrue(missingFields.isEmpty(), 
                   "The following locales are missing required phone fields: " + missingFields);
    }
} 