package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating fake phone number-related data.
 * This class provides methods to generate various phone number formats,
 * including landline numbers, cell phone numbers, and international phone numbers.
 */
public class PhoneNumberProvider extends AbstractProvider {

    /**
     * Constructs a new PhoneNumberProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public PhoneNumberProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new PhoneNumberProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #PhoneNumberProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public PhoneNumberProvider(RandomService random) {
        super(random);
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
        String format = randomFromLocaleList("phoneLandlineFormats", "phone", "landlineFormats");
        return random.randomize(format);
    }

    /**
     * Generates a random cell phone number based on predefined formats.
     *
     * @return A randomly generated cell phone number
     */
    public String cellPhone() {
        String format = randomFromLocaleList("phoneCellPhoneFormats", "phone", "cellPhoneFormats");
        return random.randomize(format);
    }

    /**
     * Generates a random international phone number based on predefined formats.
     *
     * @return A randomly generated international phone number
     */
    public String internationalPhoneFormat() {
        String format = randomFromLocaleList("phoneInternationalFormats", "phone", "internationalFormats");
        return random.randomize(format);
    }

    /**
     * Main method for testing the functionality of PhoneNumberProvider.
     * This method demonstrates the usage of various phone number generation methods.
     *
     * @param args Command line arguments (not used)
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        PhoneNumberProvider phoneNumberProvider = new PhoneNumberProvider(new RandomService());
        System.out.println("Phone Number: " + phoneNumberProvider.cellPhone());
        System.out.println("Landline: " + phoneNumberProvider.landline());
        System.out.println("Phone Number: " + phoneNumberProvider.phoneNumber());
        System.out.println("International Phone Format: " + phoneNumberProvider.internationalPhoneFormat());
    }
}
