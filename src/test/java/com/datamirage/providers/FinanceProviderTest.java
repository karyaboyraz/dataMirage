package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class FinanceProviderTest {
    private FinanceProvider financeProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        financeProvider = new FinanceProvider(new RandomService());
    }

    @RepeatedTest(20)
    void visaCardNumber_ShouldReturnValidNumber() {
        String number = financeProvider.visaCardNumber();
        assertNotNull(number);
        assertFalse(number.isEmpty());
        assertTrue(number.matches("^4\\d{15}$"));
    }

    @RepeatedTest(20)
    void masterCardNumber_ShouldReturnValidNumber() {
        String number = financeProvider.masterCardNumber();
        assertNotNull(number);
        assertFalse(number.isEmpty());
        assertTrue(number.matches("^5\\d{15}$"));
    }

    @RepeatedTest(20)
    void amexCardNumber_ShouldReturnValidNumber() {
        String number = financeProvider.amexCardNumber();
        assertNotNull(number);
        assertFalse(number.isEmpty());
        assertTrue(number.matches("^34\\d{13}$"));
    }

    @RepeatedTest(20)
    void creditCardExpiryDate_ShouldReturnValidDate() {
        String date = financeProvider.creditCardExpiryDate();
        assertNotNull(date);
        assertFalse(date.isEmpty());
        assertTrue(date.matches("^(0[1-9]|1[0-2])/\\d{2}$"));
    }

    @RepeatedTest(20)
    void cvv_ShouldReturnValidCvv() {
        String cvv = financeProvider.cvv(3);
        assertNotNull(cvv);
        assertFalse(cvv.isEmpty());
        assertTrue(cvv.matches("^\\d{3}$"));
    }

    @RepeatedTest(20)
    void bankAccountNumber_ShouldReturnValidNumber() {
        String number = financeProvider.bankAccountNumber();
        assertNotNull(number);
        assertFalse(number.isEmpty());
        assertTrue(number.matches("^\\d{10}$"));
    }

    @RepeatedTest(20)
    void ibanBuilder_ShouldReturnValidIban() {
        String iban = financeProvider.ibanBuilder();
        assertNotNull(iban);
        assertFalse(iban.isEmpty());
        assertTrue(iban.matches("^TR\\d{24}$"));
    }

    @RepeatedTest(20)
    void amount_ShouldReturnValidAmount() {
        String amount = financeProvider.amount(100.0, 1000.0);
        assertNotNull(amount);
        assertFalse(amount.isEmpty());
        double value = Double.parseDouble(amount);
        assertTrue(value >= 100.0 && value <= 1000.0);
    }
} 