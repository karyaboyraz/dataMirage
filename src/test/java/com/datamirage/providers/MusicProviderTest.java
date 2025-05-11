package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class MusicProviderTest {
    private MusicProvider musicProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        musicProvider = new MusicProvider(new RandomService());
    }

    @RepeatedTest(20)
    void genre_ShouldReturnValidGenre() {
        String genre = musicProvider.genre();
        assertNotNull(genre);
        assertFalse(genre.isEmpty());
    }

    @RepeatedTest(20)
    void instrument_ShouldReturnValidInstrument() {
        String instrument = musicProvider.instrument();
        assertNotNull(instrument);
        assertFalse(instrument.isEmpty());
    }

    @RepeatedTest(20)
    void key_ShouldReturnValidKey() {
        String key = musicProvider.key();
        assertNotNull(key);
        assertFalse(key.isEmpty());
        assertTrue(key.contains(" "));
    }

    @RepeatedTest(20)
    void chord_ShouldReturnValidChord() {
        String chord = musicProvider.chord();
        assertNotNull(chord);
        assertFalse(chord.isEmpty());
        assertTrue(chord.contains(" "));
    }

    @RepeatedTest(20)
    void note_ShouldReturnValidNote() {
        String note = musicProvider.note();
        assertNotNull(note);
        assertFalse(note.isEmpty());
    }

    @RepeatedTest(20)
    void scale_ShouldReturnValidScale() {
        String scale = musicProvider.scale();
        assertNotNull(scale);
        assertFalse(scale.isEmpty());
    }

    @RepeatedTest(20)
    void tempo_ShouldReturnValidTempo() {
        String tempo = musicProvider.tempo();
        assertNotNull(tempo);
        assertFalse(tempo.isEmpty());
    }

    @RepeatedTest(20)
    void dynamic_ShouldReturnValidDynamic() {
        String dynamic = musicProvider.dynamic();
        assertNotNull(dynamic);
        assertFalse(dynamic.isEmpty());
    }

    @RepeatedTest(20)
    void timeSignature_ShouldReturnValidTimeSignature() {
        String timeSignature = musicProvider.timeSignature();
        assertNotNull(timeSignature);
        assertFalse(timeSignature.isEmpty());
    }

    @RepeatedTest(20)
    void album_ShouldReturnValidAlbum() {
        String album = musicProvider.album();
        assertNotNull(album);
        assertFalse(album.isEmpty());
    }

    @RepeatedTest(20)
    void artist_ShouldReturnValidArtist() {
        String artist = musicProvider.artist();
        assertNotNull(artist);
        assertFalse(artist.isEmpty());
    }

    @RepeatedTest(20)
    void song_ShouldReturnValidSong() {
        String song = musicProvider.song();
        assertNotNull(song);
        assertFalse(song.isEmpty());
    }
} 