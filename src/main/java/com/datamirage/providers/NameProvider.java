package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.util.DataLoader;
import com.datamirage.util.LazyLoader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A provider class for generating fake name-related data.
 * This class provides methods to generate various components of names,
 * including first names, last names, prefixes, suffixes, and more.
 */
public class NameProvider {
    private final RandomService random;
    private List<String> firstNames;
    private List<String> lastNames;
    private List<String> prefixes;
    private List<String> suffixes;
    private List<String> titles;
    private List<String> genders;

    /**
     * Constructs a new NameProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public NameProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random first name.
     *
     * @return A randomly selected first name
     */
    public String firstName() {
        firstNames = LazyLoader.load("nameFirstNames", () -> DataLoader.getListData("name", "first_names"));
        return random.randomElement(firstNames);
    }

    /**
     * Generates a random last name.
     *
     * @return A randomly selected last name
     */
    public String lastName() {
        lastNames = LazyLoader.load("nameLastNames", () -> DataLoader.getListData("name", "last_names"));
        return random.randomElement(lastNames);
    }

    /**
     * Generates a full name by combining a first name and last name.
     *
     * @return A string containing a first name and last name
     */
    public String fullName() {
        return firstName() + " " + lastName();
    }

    /**
     * Generates a random name prefix (e.g., Mr., Mrs., Dr.).
     *
     * @return A randomly selected name prefix
     */
    public String prefix() {
        prefixes = LazyLoader.load("namePrefixes", () -> DataLoader.getListData("name", "prefixes"));
        return random.randomElement(prefixes);
    }

    /**
     * Generates a random name suffix (e.g., Jr., Sr., III).
     *
     * @return A randomly selected name suffix
     */
    public String suffix() {
        suffixes = LazyLoader.load("nameSuffixes", () -> DataLoader.getListData("name", "suffixes"));
        return random.randomElement(suffixes);
    }

    /**
     * Generates a random title (e.g., Professor, Doctor, Engineer).
     *
     * @return A randomly selected title
     */
    public String title() {
        titles = LazyLoader.load("nameTitles", () -> DataLoader.getListData("name", "titles"));
        return random.randomElement(titles);
    }

    /**
     * Generates a random gender.
     *
     * @return A randomly selected gender
     */
    public String gender() {
        genders = LazyLoader.load("nameGenders", () -> DataLoader.getListData("name", "gender"));
        return random.randomElement(genders);
    }

    /**
     * Generates a random job title.
     * This is an alias for the title() method.
     *
     * @return A randomly selected job title
     */
    public String jobTitle() {
        return title();
    }

    /**
     * Generates a random username based on first and last names.
     * The username may include:
     * - First name only
     * - Last name only
     * - First name and last name with a dot separator
     * - Last name and first name with a dot separator
     * - Random numbers appended to any of the above
     *
     * @return A randomly generated username
     */
    public String username() {
        String firstName = firstName().toLowerCase().replaceAll("[^a-z0-9]", "");
        String lastName = lastName().toLowerCase().replaceAll("[^a-z0-9]", "");
        
        StringBuilder result = new StringBuilder();
        
        if (random.nextBoolean()) {
            result.append(firstName);
            if (random.nextBoolean()) {
                result.append(".");
                result.append(lastName);
            }
        } else {
            result.append(lastName);
            if (random.nextBoolean()) {
                result.append(".");
                result.append(firstName);
            }
        }
        
        if (random.nextBoolean()) {
            result.append(random.nextInt(0, 999));
        }
        
        return result.toString();
    }

    /**
     * Generates a valid Turkish government ID number (TC ID No).
     * The generated ID number follows the official validation rules:
     * - First digit cannot be 0
     * - 10th digit is calculated based on the first 9 digits
     * - 11th digit is the checksum of the first 10 digits
     *
     * @return A valid Turkish government ID number
     */
    public String turkeyGovIDN() {
        int[] digits = IntStream.concat(
            IntStream.of(random.nextInt(1, 9)),
            IntStream.concat(
                IntStream.range(0, 8).map(i -> random.nextInt(0, 9)),
                IntStream.of(0, 0)
            )
        ).toArray();
        int sumOdd = IntStream.range(0, 9).filter(i -> i % 2 == 0).map(i -> digits[i]).sum();
        int sumEven = IntStream.range(0, 9).filter(i -> i % 2 == 1).map(i -> digits[i]).sum();
        digits[9] = Math.floorMod(sumOdd * 7 - sumEven, 10);
        digits[10] = Arrays.stream(digits, 0, 10).sum() % 10;
        return Arrays.stream(digits)
                     .mapToObj(String::valueOf)
                     .collect(Collectors.joining());
    }

    /**
     * Main method for testing the NameProvider functionality.
     * This method demonstrates the usage of various name generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        NameProvider nameProvider = new NameProvider(new RandomService());
        System.out.println("First Name: " + nameProvider.firstName());
        System.out.println("Last Name: " + nameProvider.lastName());
        System.out.println("Full Name: " + nameProvider.fullName());
        System.out.println("Prefix: " + nameProvider.prefix());
        System.out.println("Suffix: " + nameProvider.suffix());
        System.out.println("Title: " + nameProvider.title());
        System.out.println("Gender: " + nameProvider.gender());
        System.out.println("Job Title: " + nameProvider.jobTitle());
        System.out.println("Username: " + nameProvider.username());
        System.out.println("Turkey Gov ID: " + nameProvider.turkeyGovIDN());
    }
}
