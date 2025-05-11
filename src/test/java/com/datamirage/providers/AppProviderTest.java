package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class AppProviderTest {
    private AppProvider appProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        appProvider = new AppProvider(new RandomService());
    }

    @RepeatedTest(20)
    void name_ShouldReturnValidName() {
        String name = appProvider.name();
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }

    @RepeatedTest(20)
    void version_ShouldReturnValidVersion() {
        String version = appProvider.version();
        assertNotNull(version);
        assertFalse(version.isEmpty());
        assertTrue(version.matches("^\\d+\\.\\d+\\.\\d+$"));
    }

    @RepeatedTest(20)
    void platform_ShouldReturnValidPlatform() {
        String platform = appProvider.platform();
        assertNotNull(platform);
        assertFalse(platform.isEmpty());
    }

    @RepeatedTest(20)
    void category_ShouldReturnValidCategory() {
        String category = appProvider.category();
        assertNotNull(category);
        assertFalse(category.isEmpty());
    }
} 