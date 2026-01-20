package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating fake company-related data.
 * This class provides methods to generate various components of company information,
 * including company names, suffixes, industries, and catchphrases.
 */
public class CompanyProvider extends AbstractProvider {

    /**
     * Constructs a new CompanyProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public CompanyProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new CompanyProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #CompanyProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public CompanyProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random company name.
     *
     * @return A randomly selected company name
     */
    public String name() {
        return randomFromLocaleList("companyNames", "company", "names");
    }

    /**
     * Generates a random company suffix (e.g., Inc., Ltd., LLC).
     *
     * @return A randomly selected company suffix
     */
    public String suffix() {
        return randomFromLocaleList("companySuffixes", "company", "suffixes");
    }

    /**
     * Generates a random company industry.
     *
     * @return A randomly selected company industry
     */
    public String industry() {
        return randomFromLocaleList("companyIndustries", "company", "industries");
    }

    /**
     * Generates a random company catchphrase.
     *
     * @return A randomly selected company catchphrase
     */
    public String catchPhrase() {
        return randomFromLocaleList("companyCatchPhrases", "company", "catch_phrases");
    }

    /**
     * Generates a full company name by combining a company name and suffix.
     *
     * @return A string containing a company name and suffix
     */
    public String fullName() {
        return name() + " " + suffix();
    }

    /**
     * Main method for testing the CompanyProvider functionality.
     * This method demonstrates the usage of various company information generation methods.
     *
     * @param args Command line arguments (not used)
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        CompanyProvider companyProvider = new CompanyProvider(new RandomService());
        System.out.println("Company Name: " + companyProvider.name());
        System.out.println("Company Suffix: " + companyProvider.suffix());
        System.out.println("Company Industry: " + companyProvider.industry());
        System.out.println("Company Catch Phrase: " + companyProvider.catchPhrase());
        System.out.println("Full Company Name: " + companyProvider.fullName());
    }
}
