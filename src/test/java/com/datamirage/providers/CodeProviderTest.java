package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class CodeProviderTest {
    private CodeProvider codeProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        codeProvider = new CodeProvider(new RandomService());
    }

    @RepeatedTest(20)
    void isbn_ShouldReturnValidIsbn() {
        String isbn = codeProvider.isbn();
        assertNotNull(isbn);
        assertFalse(isbn.isEmpty());
        assertTrue(isbn.matches("^\\d{13}$"));
    }

    @RepeatedTest(20)
    void ean_ShouldReturnValidEan() {
        String ean = codeProvider.ean();
        assertNotNull(ean);
        assertFalse(ean.isEmpty());
        assertTrue(ean.matches("^\\d{13}$"));
    }

    @RepeatedTest(20)
    void asin_ShouldReturnValidAsin() {
        String asin = codeProvider.asin();
        assertNotNull(asin);
        assertFalse(asin.isEmpty());
        assertTrue(asin.matches("^[A-Z0-9]{10}$"));
    }

    @RepeatedTest(20)
    void issn_ShouldReturnValidIssn() {
        String issn = codeProvider.issn();
        assertNotNull(issn);
        assertFalse(issn.isEmpty());
        assertTrue(issn.matches("^\\d{4}-\\d{3}[0-9X]$"));
    }
} 