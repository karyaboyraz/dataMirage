package com.datamirage.providers;

import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class ImageProviderTest {
    private ImageProvider imageProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        imageProvider = new ImageProvider();
    }

    @RepeatedTest(20)
    void imageUrl_ShouldReturnValidUrl() {
        String url = imageProvider.imageUrl(800, 600);
        assertNotNull(url);
        assertFalse(url.isEmpty());
        assertTrue(url.matches("^https://picsum\\.photos/800/600$"));
    }

    @RepeatedTest(20)
    void imageUrlWithCategory_ShouldReturnValidUrl() {
        String url = imageProvider.imageUrl(800, 600, "nature");
        assertNotNull(url);
        assertFalse(url.isEmpty());
        assertTrue(url.matches("^https://picsum\\.photos/800/600\\?category=nature$"));
    }

    @RepeatedTest(20)
    void imageUrlWithCategoryAndSearch_ShouldReturnValidUrl() {
        String url = imageProvider.imageUrl(800, 600, "nature", "mountain");
        assertNotNull(url);
        assertFalse(url.isEmpty());
        assertTrue(url.matches("^https://picsum\\.photos/800/600\\?category=nature&search=mountain$"));
    }

    @RepeatedTest(20)
    void imageUrlWithCategorySearchAndSeed_ShouldReturnValidUrl() {
        String url = imageProvider.imageUrl(800, 600, "nature", "mountain", "sunset");
        assertNotNull(url);
        assertFalse(url.isEmpty());
        assertTrue(url.matches("^https://picsum\\.photos/seed/sunset/800/600\\?category=nature&search=mountain$"));
    }
} 