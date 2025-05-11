package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.LazyLoader;
import com.datamirage.util.RandomService;

import java.util.List;

/**
 * A provider class for generating fake book-related data.
 * This class provides methods to generate various components of book information,
 * including titles, authors, publishers, genres, and ISBN numbers.
 */
@SuppressWarnings("ALL")
public class BookProvider {
    private final RandomService random;
    private List<String> titles;
    private List<String> authors;
    private List<String> publishers;
    private List<String> genres;

    /**
     * Constructs a new BookProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public BookProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random book title.
     * The titles are loaded from a data source and converted to strings.
     *
     * @return A randomly selected book title
     */
    public String title() {
        titles = LazyLoader.load("bookTitles", () -> {
            List<?> rawTitles = DataLoader.getListData("book", "titles");
            return rawTitles.stream()
                    .map(Object::toString)
                    .toList();
        });
        return random.randomElement(titles);
    }

    /**
     * Generates a random book author name.
     *
     * @return A randomly selected book author name
     */
    public String author() {
        authors = LazyLoader.load("bookAuthors", () -> DataLoader.getListData("book", "authors"));
        return random.randomElement(authors);
    }

    /**
     * Generates a random book publisher name.
     *
     * @return A randomly selected book publisher name
     */
    public String publisher() {
        publishers = LazyLoader.load("bookPublishers", () -> DataLoader.getListData("book", "publishers"));
        return random.randomElement(publishers);
    }

    /**
     * Generates a random book genre.
     *
     * @return A randomly selected book genre
     */
    public String genre() {
        genres = LazyLoader.load("bookGenre", () -> DataLoader.getListData("book", "genres"));
        return random.randomElement(genres);
    }

    /**
     * Generates a random 13-digit ISBN number.
     *
     * @return A string representation of a 13-digit ISBN number
     */
    public String isbn() {
        NumberProvider numberProvider = new NumberProvider(random);
        return numberProvider.digits(13);
    }

    /**
     * Main method for testing the BookProvider functionality.
     * This method demonstrates the usage of various book information generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        BookProvider bookProvider = new BookProvider(new RandomService());
        System.out.println("Random Book Title: " + bookProvider.title());
        System.out.println("Random Book Author: " + bookProvider.author());
        System.out.println("Random Book Publisher: " + bookProvider.publisher());
        System.out.println("Random Book Genre: " + bookProvider.genre());
        System.out.println("Random Book ISBN: " + bookProvider.isbn());
    }
}