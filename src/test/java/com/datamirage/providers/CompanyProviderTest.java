package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class CompanyProviderTest {
    private CompanyProvider companyProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        companyProvider = new CompanyProvider(new RandomService());
    }

    @RepeatedTest(20)
    void name_ShouldReturnValidName() {
        String name = companyProvider.name();
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }

    @RepeatedTest(20)
    void suffix_ShouldReturnValidSuffix() {
        String suffix = companyProvider.suffix();
        assertNotNull(suffix);
        assertFalse(suffix.isEmpty());
    }

    @RepeatedTest(20)
    void catchPhrase_ShouldReturnValidPhrase() {
        String catchPhrase = companyProvider.catchPhrase();
        assertNotNull(catchPhrase);
        assertFalse(catchPhrase.isEmpty());
    }

    @RepeatedTest(20)
    void industry_ShouldReturnValidIndustry() {
        String industry = companyProvider.industry();
        assertNotNull(industry);
        assertFalse(industry.isEmpty());
    }
} 