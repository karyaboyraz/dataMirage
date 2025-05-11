package com.datamirage.locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DataMirageLocaleTest {

    @Test
    void getCode_ShouldReturnCorrectCode() {
        assertEquals("tr_TR", DataMirageLocale.TR_TR.getCode());
        assertEquals("en_US", DataMirageLocale.EN_US.getCode());
        assertEquals("de_DE", DataMirageLocale.DE_DE.getCode());
        assertEquals("fr_FR", DataMirageLocale.FR_FR.getCode());
        assertEquals("es_ES", DataMirageLocale.ES_ES.getCode());
        assertEquals("it_IT", DataMirageLocale.IT_IT.getCode());
        assertEquals("ru_RU", DataMirageLocale.RU_RU.getCode());
    }

    @Test
    void toString_ShouldReturnCode() {
        assertEquals("tr_TR", DataMirageLocale.TR_TR.toString());
        assertEquals("en_US", DataMirageLocale.EN_US.toString());
        assertEquals("de_DE", DataMirageLocale.DE_DE.toString());
        assertEquals("fr_FR", DataMirageLocale.FR_FR.toString());
        assertEquals("es_ES", DataMirageLocale.ES_ES.toString());
        assertEquals("it_IT", DataMirageLocale.IT_IT.toString());
        assertEquals("ru_RU", DataMirageLocale.RU_RU.toString());
    }

    @ParameterizedTest
    @MethodSource("validLocaleCodes")
    void fromCode_ShouldReturnCorrectLocaleForValidCodes(String code, DataMirageLocale expectedLocale) {
        assertEquals(expectedLocale, DataMirageLocale.fromCode(code));
    }

    @ParameterizedTest
    @MethodSource("validLocaleCodesIgnoreCase")
    void fromCode_ShouldHandleCaseInsensitively(String code, DataMirageLocale expectedLocale) {
        assertEquals(expectedLocale, DataMirageLocale.fromCode(code));
    }

    @Test
    void fromCode_ShouldThrowExceptionForInvalidCode() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> DataMirageLocale.fromCode("invalid_LOCALE"));
        assertTrue(exception.getMessage().contains("Unsupported locale"));
    }

    @Test
    void fromCode_ShouldThrowExceptionForNullCode() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> DataMirageLocale.fromCode(null));
        assertTrue(exception.getMessage().contains("Unsupported locale"));
    }

    @Test
    void allLocales_ShouldHaveValidFormats() {
        DataMirageLocale[] allLocales = DataMirageLocale.values();
        
        for (DataMirageLocale locale : allLocales) {
            String code = locale.getCode();
            assertTrue(code.matches("[a-z]{2}_[A-Z]{2}"), 
                "Locale code should match pattern: language_COUNTRY, found: " + code);
        }
    }

    private static Stream<Arguments> validLocaleCodes() {
        return Stream.of(
            Arguments.of("tr_TR", DataMirageLocale.TR_TR),
            Arguments.of("en_US", DataMirageLocale.EN_US),
            Arguments.of("de_DE", DataMirageLocale.DE_DE),
            Arguments.of("fr_FR", DataMirageLocale.FR_FR),
            Arguments.of("es_ES", DataMirageLocale.ES_ES),
            Arguments.of("it_IT", DataMirageLocale.IT_IT),
            Arguments.of("ru_RU", DataMirageLocale.RU_RU)
        );
    }
    
    private static Stream<Arguments> validLocaleCodesIgnoreCase() {
        return Stream.of(
            Arguments.of("TR_tr", DataMirageLocale.TR_TR),
            Arguments.of("eN_uS", DataMirageLocale.EN_US),
            Arguments.of("De_dE", DataMirageLocale.DE_DE)
        );
    }
} 