package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class StringProviderTest {
    private StringProvider stringProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        stringProvider = new StringProvider(new RandomService());
    }

    @RepeatedTest(20)
    void alpha_ShouldReturnValidAlphaString() {
        int count = 10;
        String alpha = stringProvider.alpha(count);
        assertNotNull(alpha);
        assertFalse(alpha.isEmpty());
        assertEquals(count, alpha.length());
        assertTrue(alpha.matches("^[a-zA-Z]+$"));
    }

    @RepeatedTest(20)
    void alphaNumeric_ShouldReturnValidAlphaNumericString() {
        int count = 10;
        String alphaNumeric = stringProvider.alphaNumeric(count);
        assertNotNull(alphaNumeric);
        assertFalse(alphaNumeric.isEmpty());
        assertEquals(count, alphaNumeric.length());
        assertTrue(alphaNumeric.matches("^[a-zA-Z0-9]+$"));
    }

    @RepeatedTest(20)
    void numeric_ShouldReturnValidNumericString() {
        int count = 10;
        String numeric = stringProvider.numeric(count);
        assertNotNull(numeric);
        assertFalse(numeric.isEmpty());
        assertEquals(count, numeric.length());
        assertTrue(numeric.matches("^\\d+$"));
    }

    @RepeatedTest(20)
    void hex_ShouldReturnValidHexString() {
        int count = 10;
        String hex = stringProvider.hex(count);
        assertNotNull(hex);
        assertFalse(hex.isEmpty());
        assertEquals(count, hex.length());
        assertTrue(hex.matches("^[0-9a-f]+$"));
    }

    @RepeatedTest(20)
    void binary_ShouldReturnValidBinaryString() {
        int count = 10;
        String binary = stringProvider.binary(count);
        assertNotNull(binary);
        assertFalse(binary.isEmpty());
        assertEquals(count, binary.length());
        assertTrue(binary.matches("^[01]+$"));
    }
} 