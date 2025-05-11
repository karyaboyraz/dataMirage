package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class NumberProviderTest {
    private NumberProvider numberProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        numberProvider = new NumberProvider(new RandomService());
    }

    @RepeatedTest(20)
    void number_ShouldReturnValidNumber() {
        int number = numberProvider.number(0, 100);
        assertTrue(number >= 0 && number <= 100);
    }

    @RepeatedTest(20)
    void decimal_ShouldReturnValidDecimal() {
        double decimal = numberProvider.decimal(0.0, 1.0);
        assertTrue(decimal >= 0.0 && decimal <= 1.0);
    }

    @RepeatedTest(20)
    void decimalWithScale_ShouldReturnValidDecimal() {
        String decimal = numberProvider.decimal(0.0, 1.0, 2);
        assertTrue(decimal.matches("^\\d+\\.\\d{2}$"));
        double decimalValue = Double.parseDouble(decimal);
        assertTrue(decimalValue >= 0.0 && decimalValue <= 1.0);
    }

    @RepeatedTest(20)
    void digit_ShouldReturnValidDigit() {
        String digit = numberProvider.digit();
        assertNotNull(digit);
        assertFalse(digit.isEmpty());
        assertTrue(digit.matches("^\\d$"));
    }

    @RepeatedTest(20)
    void digits_ShouldReturnValidDigits() {
        String digits = numberProvider.digits(5);
        assertNotNull(digits);
        assertFalse(digits.isEmpty());
        assertTrue(digits.matches("^\\d{5}$"));
    }

    @RepeatedTest(20)
    void hex_ShouldReturnValidHex() {
        String hex = numberProvider.hex(8);
        assertNotNull(hex);
        assertFalse(hex.isEmpty());
        assertTrue(hex.matches("^[0-9a-f]{8}$"));
    }

    @RepeatedTest(20)
    void binary_ShouldReturnValidBinary() {
        String binary = numberProvider.binary(8);
        assertNotNull(binary);
        assertFalse(binary.isEmpty());
        assertTrue(binary.matches("^[01]{8}$"));
    }

    @RepeatedTest(20)
    void octal_ShouldReturnValidOctal() {
        String octal = numberProvider.octal(8);
        assertNotNull(octal);
        assertFalse(octal.isEmpty());
        assertTrue(octal.matches("^[0-7]{8}$"));
    }
} 