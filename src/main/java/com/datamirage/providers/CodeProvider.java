package com.datamirage.providers;

import com.datamirage.util.RandomService;

/**
 * A provider class for generating various types of codes such as ISBN, EAN, ASIN, and ISSN.
 * This class handles the generation of standardized codes with proper check digits.
 */
public class CodeProvider {
    private final RandomService random;
    private static final int[] ISBN_EAN_WEIGHTS = {1,3,1,3,1,3,1,3,1,3,1,3,0};

    /**
     * Constructs a new CodeProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public CodeProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a code with a check digit based on the specified length and weights.
     *
     * @param length The total length of the code including the check digit
     * @param weights The weights to use for calculating the check digit
     * @return A string containing the generated code with check digit
     */
    private String generateWithCheckDigit(int length, int[] weights) {
        StringBuilder result = new StringBuilder();
        int sum = 0;
        
        for (int i = 0; i < length - 1; i++) {
            int digit = random.nextInt(0, 9);
            result.append(digit);
            sum += digit * weights[i];
        }
        
        int checkDigit = (10 - (sum % 10)) % 10;
        result.append(checkDigit);
        
        return result.toString();
    }

    /**
     * Generates a random ISBN (International Standard Book Number) code.
     *
     * @return A 13-digit ISBN code as a string
     */
    public String isbn() {
        return generateWithCheckDigit(13, ISBN_EAN_WEIGHTS);
    }

    /**
     * Generates a random EAN (European Article Number) code.
     *
     * @return A 13-digit EAN code as a string
     */
    public String ean() {
        return generateWithCheckDigit(13, ISBN_EAN_WEIGHTS);
    }

    /**
     * Generates a random ASIN (Amazon Standard Identification Number) code.
     *
     * @return A 10-character ASIN code as a string
     */
    public String asin() {
        return random.randomString(10, true, true, false, false);
    }

    /**
     * Generates a random ISSN (International Standard Serial Number) code.
     *
     * @return An ISSN code in the format XXXX-XXXX with a check digit
     */
    public String issn() {
        StringBuilder result = new StringBuilder();
        int sum = 0;
        
        for (int i = 0; i < 7; i++) {
            int digit = random.nextInt(0, 9);
            result.append(digit);
            sum += digit * (8 - i);
        }
        
        int rem = sum % 11;
        char checkDigit = rem == 0 ? '0' : rem == 1 ? 'X' : (char) ('0' + (11 - rem));
        
        return result.substring(0, 4) + "-" + result.substring(4) + checkDigit;
    }

    /**
     * Main method for testing the functionality of CodeProvider.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        CodeProvider codeProvider = new CodeProvider(new RandomService());
        System.out.println("ISBN: " + codeProvider.isbn());
        System.out.println("EAN: " + codeProvider.ean());
        System.out.println("ASIN: " + codeProvider.asin());
        System.out.println("ISSN: " + codeProvider.issn());
    }
}