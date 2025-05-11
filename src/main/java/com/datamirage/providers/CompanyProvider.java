package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating fake company-related data.
 * This class provides methods to generate various components of company information,
 * including company names, suffixes, industries, and catchphrases.
 */
public class CompanyProvider {
    private final RandomService random;
    private List<String> companyNames;
    private List<String> companySuffixes;
    private List<String> companyIndustries;
    private List<String> companyCatchPhrases;

    /**
     * Constructs a new CompanyProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public CompanyProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random company name.
     *
     * @return A randomly selected company name
     */
    public String name() {
        companyNames = LazyLoader.load("companyNames", () -> DataLoader.getListData("company", "names"));
        return random.randomElement(companyNames);
    }

    /**
     * Generates a random company suffix (e.g., Inc., Ltd., LLC).
     *
     * @return A randomly selected company suffix
     */
    public String suffix() {
        companySuffixes = LazyLoader.load("companySuffixes", () -> DataLoader.getListData("company", "suffixes"));
        return random.randomElement(companySuffixes);
    }

    /**
     * Generates a random company industry.
     *
     * @return A randomly selected company industry
     */
    public String industry() {
        companyIndustries = LazyLoader.load("companyIndustries", () -> DataLoader.getListData("company", "industries"));
        return random.randomElement(companyIndustries);
    }

    /**
     * Generates a random company catchphrase.
     *
     * @return A randomly selected company catchphrase
     */
    public String catchPhrase() {
        companyCatchPhrases = LazyLoader.load("companyCatchPhrases", () -> DataLoader.getListData("company", "catch_phrases"));
        return random.randomElement(companyCatchPhrases);
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
    public static void main(String[] args) {
        CompanyProvider companyProvider = new CompanyProvider(new RandomService());
        System.out.println("Company Name: " + companyProvider.name());
        System.out.println("Company Suffix: " + companyProvider.suffix());
        System.out.println("Company Industry: " + companyProvider.industry());
        System.out.println("Company Catch Phrase: " + companyProvider.catchPhrase());
        System.out.println("Full Company Name: " + companyProvider.fullName());
    }
}
