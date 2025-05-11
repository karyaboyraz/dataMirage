package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating fake phone number-related data.
 * This class provides methods to generate various phone number formats,
 * including landline numbers, cell phone numbers, and international phone numbers.
 */
public class PhoneNumberProvider {
    private final RandomService random;
    private List<String> landlineFormats;
    private List<String> cellPhoneFormats;
    private List<String> internationalFormats;

    /**
     * Constructs a new PhoneNumberProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public PhoneNumberProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random phone number, either a cell phone or landline number.
     *
     * @return A randomly generated phone number
     */
    public String phoneNumber() {
        return random.nextBoolean() ? cellPhone() : landline();
    }

    /**
     * Generates a random landline phone number based on predefined formats.
     *
     * @return A randomly generated landline phone number
     */
    public String landline() {
        landlineFormats = LazyLoader.load("phoneLandlineFormats", () -> DataLoader.getListData("phone", "landlineFormats"));
        String format = random.randomElement(landlineFormats);
        return random.randomize(format);
    }

    /**
     * Generates a random cell phone number based on predefined formats.
     *
     * @return A randomly generated cell phone number
     */
    public String cellPhone() {
        cellPhoneFormats = LazyLoader.load("phoneCellPhoneFormats", () -> DataLoader.getListData("phone", "cellPhoneFormats"));
        String format = random.randomElement(cellPhoneFormats);
        return random.randomize(format);
    }

    /**
     * Generates a random international phone number based on predefined formats.
     *
     * @return A randomly generated international phone number
     */
    public String internationalPhoneFormat() {
        internationalFormats = LazyLoader.load("phoneInternationalFormats", () -> DataLoader.getListData("phone", "internationalFormats"));
        String format = random.randomElement(internationalFormats);
        return random.randomize(format);
    }

    /**
     * Main method for testing the functionality of PhoneNumberProvider.
     * This method demonstrates the usage of various phone number generation methods.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        PhoneNumberProvider phoneNumberProvider = new PhoneNumberProvider(new RandomService());
        System.out.println("Phone Number: " + phoneNumberProvider.cellPhone());
        System.out.println("Landline: " + phoneNumberProvider.landline());
        System.out.println("Phone Number: " + phoneNumberProvider.phoneNumber());
        System.out.println("International Phone Format: " + phoneNumberProvider.internationalPhoneFormat());
    }
}