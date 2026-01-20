package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating film-related data.
 * This class provides methods to generate various film information such as
 * titles, directors, actors, genres, and character names.
 */
public class FilmProvider extends AbstractProvider {

    /**
     * Constructs a new FilmProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public FilmProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new FilmProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #FilmProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public FilmProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random film title.
     *
     * @return A randomly selected film title
     */
    public String title() {
        return randomFromLocaleList("filmTitles", "film", "titles");
    }

    /**
     * Generates a random film director name.
     *
     * @return A randomly selected film director name
     */
    public String director() {
        return randomFromLocaleList("filmDirectors", "film", "directors");
    }

    /**
     * Generates a random actor name.
     *
     * @return A randomly selected film actor name
     */
    public String actor() {
        return randomFromLocaleList("filmActors", "film", "actors");
    }

    /**
     * Generates a random film genre.
     *
     * @return A randomly selected film genre
     */
    public String genre() {
        return randomFromLocaleList("filmGenres", "film", "genres");
    }

    /**
     * Generates a random character name.
     *
     * @return A random character name as a string
     */
    public String character() {
        return randomFromLocaleList("nameFirstNames", "name", "first_names");
    }

    /**
     * Generates a random film quote.
     *
     * @return A randomly selected film quote
     */
    public String quote() {
        return randomFromLocaleList("filmQuotes", "film", "quotes");
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
    @SuppressWarnings("deprecation")
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
