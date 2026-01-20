package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating artist-related data.
 * This class provides methods to generate various artist information such as
 * names, genres, nationalities, and artworks.
 */
public class ArtistProvider extends AbstractProvider {

    /**
     * Constructs a new ArtistProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public ArtistProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new ArtistProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #ArtistProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public ArtistProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random artist name.
     *
     * @return A randomly selected artist name
     */
    public String name() {
        return randomFromLocaleList("artistName", "artist", "names");
    }

    /**
     * Generates a random artist genre.
     *
     * @return A randomly selected artist genre
     */
    public String genre() {
        return randomFromLocaleList("artistGenre", "artist", "genres");
    }

    /**
     * Generates a random artist nationality.
     *
     * @return A randomly selected artist nationality
     */
    public String nationality() {
        return randomFromLocaleList("artistNationality", "artist", "nationalities");
    }

    /**
     * Generates a random artwork name.
     *
     * @return A randomly selected artwork name
     */
    public String artwork() {
        return randomFromLocaleList("artistArtwork", "artist", "artworks");
    }

    /**
     * Main method for testing the functionality of ArtistProvider.
     * This method demonstrates the usage of various artist-related data generation methods.
     *
     * @param args Command line arguments (not used)
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        ArtistProvider artistProvider = new ArtistProvider(new RandomService());
        System.out.println("Random Artist Name: " + artistProvider.name());
        System.out.println("Random Artist Genre: " + artistProvider.genre());
        System.out.println("Random Artist Nationality: " + artistProvider.nationality());
        System.out.println("Random Artwork: " + artistProvider.artwork());
    }
}
