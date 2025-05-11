package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.LazyLoader;
import com.datamirage.util.RandomService;

import java.util.List;

/**
 * A provider class for generating artist-related data.
 * This class provides methods to generate various artist information such as
 * names, genres, nationalities, and artworks.
 */
public class ArtistProvider {
    private final RandomService random;
    private List<String> artistName;
    private List<String> artistGenre;
    private List<String> artistNationality;
    private List<String> artistArtwork;

    /**
     * Constructs a new ArtistProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public ArtistProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random artist name.
     *
     * @return A randomly selected artist name
     */
    public String name() {
        artistName = LazyLoader.load("artistName", () -> DataLoader.getListData("artist", "names"));
        return random.randomElement(artistName);
    }

    /**
     * Generates a random artist genre.
     *
     * @return A randomly selected artist genre
     */
    public String genre() {
        artistGenre = LazyLoader.load("artistGenre", () -> DataLoader.getListData("artist", "genres"));
        return random.randomElement(artistGenre);
    }

    /**
     * Generates a random artist nationality.
     *
     * @return A randomly selected artist nationality
     */
    public String nationality() {
        artistNationality = LazyLoader.load("artistNationality", () -> DataLoader.getListData("artist", "nationalities"));
        return random.randomElement(artistNationality);
    }

    /**
     * Generates a random artwork name.
     *
     * @return A randomly selected artwork name
     */
    public String artwork() {
        artistArtwork = LazyLoader.load("artistArtwork", () -> DataLoader.getListData("artist", "artworks"));
        return random.randomElement(artistArtwork);
    }

    /**
     * Main method for testing the functionality of ArtistProvider.
     * This method demonstrates the usage of various artist-related data generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        ArtistProvider artistProvider = new ArtistProvider(new RandomService());
        System.out.println("Random Artist Name: " + artistProvider.name());
        System.out.println("Random Artist Genre: " + artistProvider.genre());
        System.out.println("Random Artist Nationality: " + artistProvider.nationality());
        System.out.println("Random Artwork: " + artistProvider.artwork());
    }
}