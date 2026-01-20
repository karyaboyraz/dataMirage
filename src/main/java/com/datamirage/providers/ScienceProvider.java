package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating science-related data.
 * This class provides methods to generate various scientific information such as
 * chemical elements, symbols, formulas, units, and unit prefixes.
 */
public class ScienceProvider extends AbstractProvider {

    /**
     * Constructs a new ScienceProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public ScienceProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new ScienceProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #ScienceProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public ScienceProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random chemical element name.
     *
     * @return A random chemical element name as a string
     */
    public String chemicalElement() {
        return randomFromLocaleList("scienceElements", "science", "elements");
    }

    /**
     * Generates a random chemical element symbol.
     *
     * @return A random chemical element symbol as a string
     */
    public String chemicalSymbol() {
        return randomFromLocaleList("scienceSymbols", "science", "symbols");
    }

    /**
     * Generates a random chemical formula.
     * The formula consists of a chemical symbol (first letter capitalized, second letter lowercase if present)
     * and optionally a random number between 1 and 9.
     *
     * @return A random chemical formula as a string
     */
    public String chemicalFormula() {
        StringBuilder formula = new StringBuilder();
        String symbol = chemicalSymbol();

        formula.append(Character.toUpperCase(symbol.charAt(0)));
        if (symbol.length() > 1) {
            formula.append(Character.toLowerCase(symbol.charAt(1)));
        }

        if (random.nextBoolean()) {
            formula.append(random.nextInt(1, 9));
        }

        return formula.toString();
    }

    /**
     * Generates a random unit of measurement.
     *
     * @return A random unit of measurement as a string
     */
    public String unit() {
        return randomFromLocaleList("scienceUnits", "science", "units");
    }

    /**
     * Generates a random unit symbol.
     *
     * @return A random unit symbol as a string
     */
    public String unitSymbol() {
        return randomFromLocaleList("scienceUnitSymbols", "science", "unit_symbols");
    }

    /**
     * Generates a random unit prefix (e.g., kilo, mega, giga).
     *
     * @return A random unit prefix as a string
     */
    public String unitPrefix() {
        return randomFromLocaleList("scienceUnitPrefixes", "science", "unit_prefixes");
    }

    /**
     * Generates a random unit prefix symbol (e.g., k, M, G).
     *
     * @return A random unit prefix symbol as a string
     */
    public String unitPrefixSymbol() {
        return randomFromLocaleList("scienceUnitPrefixSymbols", "science", "unit_prefix_symbols");
    }

    /**
     * Generates a random unit with a prefix.
     *
     * @return A random unit with prefix as a string
     */
    public String unitWithPrefix() {
        return String.format("%s%s",
            unitPrefix(),
            unit()
        );
    }

    /**
     * Generates a random unit symbol with a prefix symbol.
     *
     * @return A random unit symbol with prefix symbol as a string
     */
    public String unitSymbolWithPrefix() {
        return String.format("%s%s",
            unitPrefixSymbol(),
            unitSymbol()
        );
    }

    /**
     * Main method for testing the functionality of ScienceProvider.
     *
     * @param args Command line arguments (not used)
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        ScienceProvider scienceProvider = new ScienceProvider(new RandomService());
        System.out.println("Chemical Element: " + scienceProvider.chemicalElement());
        System.out.println("Chemical Symbol: " + scienceProvider.chemicalSymbol());
        System.out.println("Chemical Formula: " + scienceProvider.chemicalFormula());
        System.out.println("Unit: " + scienceProvider.unit());
        System.out.println("Unit Symbol: " + scienceProvider.unitSymbol());
        System.out.println("Unit Prefix: " + scienceProvider.unitPrefix());
        System.out.println("Unit Prefix Symbol: " + scienceProvider.unitPrefixSymbol());
        System.out.println("Unit with Prefix: " + scienceProvider.unitWithPrefix());
        System.out.println("Unit Symbol with Prefix: " + scienceProvider.unitSymbolWithPrefix());
    }
}
