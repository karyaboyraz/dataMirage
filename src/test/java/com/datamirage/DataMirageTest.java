package com.datamirage;

import com.datamirage.locale.DataMirageLocale;
import com.datamirage.providers.*;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DataMirageTest {
    private DataMirage datamirage;
    
    // Define required data files for each locale
    private static final Set<String> REQUIRED_DATA_FILES = new HashSet<>(Arrays.asList(
        "address", "name", "company", "food", "animal", "phone"
    ));

    @BeforeEach
    void setUp() {
        datamirage = new DataMirage(DataMirageLocale.TR_TR);
    }
    
    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void verifyRequiredDataFilesExist(DataMirageLocale locale) {
        for (String category : REQUIRED_DATA_FILES) {
            String localePath = "data/" + locale.getCode() + "/" + category + ".yaml";
            InputStream localeIs = getClass().getClassLoader().getResourceAsStream(localePath);
            assertNotNull(localeIs, "Required data file missing: " + localePath);
        }
    }

    @RepeatedTest(20)
    void constructor_ShouldInitializeWithDefaultLocale() {
        DataMirage defaultDataMirage = new DataMirage();
        assertNotNull(defaultDataMirage);
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void constructor_ShouldInitializeWithAllSupportedLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        assertNotNull(localizedDataMirage);
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void address_ShouldReturnAddressProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        AddressProvider provider = localizedDataMirage.address();
        assertNotNull(provider);
        assertInstanceOf(AddressProvider.class, provider);
        
        // Verify that the locale has at least one locale-specific address field
        DataLoader.setLocale(locale);
        boolean hasLocaleSpecificData = false;
        for (String field : new String[]{"cities", "streets", "states"}) {
            if (DataLoader.hasLocaleSpecificField("address", field)) {
                hasLocaleSpecificData = true;
                break;
            }
        }
        assertTrue(hasLocaleSpecificData, "Locale " + locale + " is missing locale-specific address data");
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void name_ShouldReturnNameProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        NameProvider provider = localizedDataMirage.name();
        assertNotNull(provider);
        assertInstanceOf(NameProvider.class, provider);
        
        // Verify that the locale has locale-specific name data
        DataLoader.setLocale(locale);
        boolean hasLocaleSpecificData = false;
        for (String field : new String[]{"first_names", "last_names"}) {
            if (DataLoader.hasLocaleSpecificField("name", field)) {
                hasLocaleSpecificData = true;
                break;
            }
        }
        assertTrue(hasLocaleSpecificData, "Locale " + locale + " is missing locale-specific name data");
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void company_ShouldReturnCompanyProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        CompanyProvider provider = localizedDataMirage.company();
        assertNotNull(provider);
        assertInstanceOf(CompanyProvider.class, provider);
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void internet_ShouldReturnInternetProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        InternetProvider provider = localizedDataMirage.internet();
        assertNotNull(provider);
        assertInstanceOf(InternetProvider.class, provider);
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void book_ShouldReturnBookProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        BookProvider provider = localizedDataMirage.book();
        assertNotNull(provider);
        assertInstanceOf(BookProvider.class, provider);
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void color_ShouldReturnColorProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        ColorProvider provider = localizedDataMirage.color();
        assertNotNull(provider);
        assertInstanceOf(ColorProvider.class, provider);
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void food_ShouldReturnFoodProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        FoodProvider provider = localizedDataMirage.food();
        assertNotNull(provider);
        assertInstanceOf(FoodProvider.class, provider);
        
        // Verify that the locale has locale-specific food data
        DataLoader.setLocale(locale);
        boolean hasLocaleSpecificData = DataLoader.hasLocaleSpecificField("food", "dishes");
        assertTrue(hasLocaleSpecificData, "Locale " + locale + " is missing locale-specific food dishes data");
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void music_ShouldReturnMusicProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        MusicProvider provider = localizedDataMirage.music();
        assertNotNull(provider);
        assertInstanceOf(MusicProvider.class, provider);
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void phoneNumber_ShouldReturnPhoneNumberProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        PhoneNumberProvider provider = localizedDataMirage.phoneNumber();
        assertNotNull(provider);
        assertInstanceOf(PhoneNumberProvider.class, provider);
        
        // Verify that the locale has locale-specific phone formats
        DataLoader.setLocale(locale);
        boolean hasLocaleSpecificData = DataLoader.hasLocaleSpecificField("phone", "landlineFormats") || 
                                       DataLoader.hasLocaleSpecificField("phone", "cellPhoneFormats");
        assertTrue(hasLocaleSpecificData, "Locale " + locale + " is missing locale-specific phone number formats");
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void weather_ShouldReturnWeatherProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        WeatherProvider provider = localizedDataMirage.weather();
        assertNotNull(provider);
        assertInstanceOf(WeatherProvider.class, provider);
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void film_ShouldReturnFilmProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        FilmProvider provider = localizedDataMirage.film();
        assertNotNull(provider);
        assertInstanceOf(FilmProvider.class, provider);
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void animal_ShouldReturnAnimalProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        AnimalProvider provider = localizedDataMirage.animal();
        assertNotNull(provider);
        assertInstanceOf(AnimalProvider.class, provider);
    }

    @ParameterizedTest
    @EnumSource(DataMirageLocale.class)
    void vehicle_ShouldReturnVehicleProviderForAllLocales(DataMirageLocale locale) {
        DataMirage localizedDataMirage = new DataMirage(locale);
        VehicleProvider provider = localizedDataMirage.vehicle();
        assertNotNull(provider);
        assertInstanceOf(VehicleProvider.class, provider);
    }
} 