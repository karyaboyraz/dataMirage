package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating music-related data.
 * This class provides methods to generate various music-related information such as
 * genres, artists, albums, songs, instruments, musical notes, scales, chords, and more.
 */
public class MusicProvider extends AbstractProvider {

    /**
     * Constructs a new MusicProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public MusicProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new MusicProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #MusicProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public MusicProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random music genre.
     *
     * @return A random music genre as a string
     */
    public String genre() {
        return randomFromLocaleList("musicGenres", "music", "genres");
    }

    /**
     * Generates a random artist name.
     *
     * @return A random artist name as a string
     */
    public String artist() {
        return randomFromLocaleList("musicArtists", "music", "artists");
    }

    /**
     * Generates a random album name.
     *
     * @return A random album name as a string
     */
    public String album() {
        return randomFromLocaleList("musicAlbums", "music", "albums");
    }

    /**
     * Generates a random song title.
     *
     * @return A random song title as a string
     */
    public String song() {
        return randomFromLocaleList("musicSongs", "music", "songs");
    }

    /**
     * Generates a random musical instrument name.
     *
     * @return A random musical instrument name as a string
     */
    public String instrument() {
        return randomFromLocaleList("musicInstruments", "music", "instruments");
    }

    /**
     * Generates a random musical key (note and scale combination).
     *
     * @return A random musical key as a string
     */
    public String key() {
        return note() + " " + scale();
    }

    /**
     * Generates a random chord (note and chord type combination).
     *
     * @return A random chord as a string
     */
    public String chord() {
        return note() + " " + random.randomElement(getLocaleList("musicChordTypes", "music", "chordTypes"));
    }

    /**
     * Generates a random musical note.
     *
     * @return A randomly selected musical note
     */
    public String note() {
        return randomFromLocaleList("musicNotes", "music", "notes");
    }

    /**
     * Generates a random musical scale.
     *
     * @return A randomly selected musical scale
     */
    public String scale() {
        return randomFromLocaleList("musicScales", "music", "scales");
    }

    /**
     * Generates a random tempo marking.
     *
     * @return A randomly selected tempo marking
     */
    public String tempo() {
        return randomFromLocaleList("musicTempos", "music", "tempos");
    }

    /**
     * Generates a random dynamic marking.
     *
     * @return A randomly selected dynamic marking
     */
    public String dynamic() {
        return randomFromLocaleList("musicDynamics", "music", "dynamics");
    }

    /**
     * Generates a random time signature.
     *
     * @return A randomly selected time signature
     */
    public String timeSignature() {
        int numerator = random.nextInt(1, 60);
        int denominator = random.nextInt(1, 16);
        return numerator + "/" + denominator;
    }

    /**
     * Generates a random chord type.
     *
     * @return A randomly selected chord type
     */
    public String chordType() {
        return randomFromLocaleList("musicChordTypes", "music", "chord_types");
    }

    /**
     * Generates a random chord progression.
     * The progression consists of 4 chords.
     *
     * @return A string containing a random chord progression
     */
    public String chordProgression() {
        StringBuilder progression = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (i > 0) {
                progression.append(" - ");
            }
            progression.append(chordType());
        }
        return progression.toString();
    }

    /**
     * Generates a random musical phrase.
     * The phrase consists of 4 to 8 random notes.
     *
     * @return A string containing a random musical phrase
     */
    public String musicalPhrase() {
        StringBuilder phrase = new StringBuilder();
        int phraseLength = random.nextInt(4, 8);

        for (int i = 0; i < phraseLength; i++) {
            if (i > 0) {
                phrase.append(" ");
            }
            phrase.append(note());
        }
        return phrase.toString();
    }

    /**
     * Main method for testing the functionality of MusicProvider.
     * This method demonstrates the usage of various music-related data generation methods.
     *
     * @param args Command line arguments (not used)
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        MusicProvider musicProvider = new MusicProvider(new RandomService());
        System.out.println("Genre: " + musicProvider.genre());
        System.out.println("Artist: " + musicProvider.artist());
        System.out.println("Album: " + musicProvider.album());
        System.out.println("Song: " + musicProvider.song());
        System.out.println("Instrument: " + musicProvider.instrument());
        System.out.println("Key: " + musicProvider.key());
        System.out.println("Chord: " + musicProvider.chord());
        System.out.println("Note: " + musicProvider.note());
        System.out.println("Scale: " + musicProvider.scale());
        System.out.println("Tempo: " + musicProvider.tempo());
        System.out.println("Dynamic: " + musicProvider.dynamic());
        System.out.println("Time Signature: " + musicProvider.timeSignature());
        System.out.println("Chord Progression: " + musicProvider.chordProgression());
        System.out.println("Musical Phrase: " + musicProvider.musicalPhrase());
    }
}
