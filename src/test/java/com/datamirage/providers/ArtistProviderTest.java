package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class ArtistProviderTest {
    private ArtistProvider artistProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        artistProvider = new ArtistProvider(new RandomService());
    }

    @RepeatedTest(20)
    void name_ShouldReturnValidName() {
        String name = artistProvider.name();
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }

    @RepeatedTest(20)
    void genre_ShouldReturnValidGenre() {
        String genre = artistProvider.genre();
        assertNotNull(genre);
        assertFalse(genre.isEmpty());
    }

    @RepeatedTest(20)
    void nationality_ShouldReturnValidNationality() {
        String nationality = artistProvider.nationality();
        assertNotNull(nationality);
        assertFalse(nationality.isEmpty());
    }

    @RepeatedTest(20)
    void artwork_ShouldReturnValidArtwork() {
        String artwork = artistProvider.artwork();
        assertNotNull(artwork);
        assertFalse(artwork.isEmpty());
    }
} 