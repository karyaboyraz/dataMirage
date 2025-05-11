package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating film-related data.
 * This class provides methods to generate various film information such as
 * titles, directors, actors, genres, and character names.
 */
public class FilmProvider {
    private final RandomService random;
    private List<String> filmTitles;
    private List<String> filmDirectors;
    private List<String> filmActors;
    private List<String> filmGenres;
    private List<String> firstNames;
    private List<String> quotes;

    /**
     * Constructs a new FilmProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public FilmProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random film title.
     *
     * @return A randomly selected film title
     */
    public String title() {
        filmTitles = LazyLoader.load("filmTitles", () -> DataLoader.getListData("film", "titles"));
        return random.randomElement(filmTitles);
    }

    /**
     * Generates a random film director name.
     *
     * @return A randomly selected film director name
     */
    public String director() {
        filmDirectors = LazyLoader.load("filmDirectors", () -> DataLoader.getListData("film", "directors"));
        return random.randomElement(filmDirectors);
    }

    /**
     * Generates a random actor name.
     *
     * @return A randomly selected film actor name
     */
    public String actor() {
        filmActors = LazyLoader.load("filmActors", () -> DataLoader.getListData("film", "actors"));
        return random.randomElement(filmActors);
    }

    /**
     * Generates a random film genre.
     *
     * @return A randomly selected film genre
     */
    public String genre() {
        filmGenres = LazyLoader.load("filmGenres", () -> DataLoader.getListData("film", "genres"));
        return random.randomElement(filmGenres);
    }

    /**
     * Generates a random character name.
     *
     * @return A random character name as a string
     */
    public String character() {
        firstNames = LazyLoader.load("nameFirstNames", () -> DataLoader.getListData("name", "first_names"));
        return random.randomElement(firstNames);
    }

    /**
     * Generates a random film quote.
     *
     * @return A randomly selected film quote
     */
    public String quote() {
        quotes = LazyLoader.load("filmQuotes", () -> DataLoader.getListData("film", "quotes"));
        return random.randomElement(quotes);
    }

    /**
     * Generates a random film year between 1920 and the current year.
     *
     * @return A random film year
     */
    public int year() {
        return random.nextInt(1920, java.time.Year.now().getValue());
    }

    /**
     * Generates a random film rating between 1.0 and 10.0.
     *
     * @return A random film rating with one decimal place
     */
    public String rating() {
        double rating = random.nextDouble(1.0, 10.0);
        return String.format("%.1f", rating);
    }

    /**
     * Main method for testing the functionality of FilmProvider.
     * This method demonstrates the usage of various film-related data generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        FilmProvider filmProvider = new FilmProvider(new RandomService());
        System.out.println("Title: " + filmProvider.title());
        System.out.println("Genre: " + filmProvider.genre());
        System.out.println("Director: " + filmProvider.director());
        System.out.println("Actor: " + filmProvider.actor());
        System.out.println("Quote: " + filmProvider.quote());
        System.out.println("Year: " + filmProvider.year());
        System.out.println("Rating: " + filmProvider.rating());
    }
} 