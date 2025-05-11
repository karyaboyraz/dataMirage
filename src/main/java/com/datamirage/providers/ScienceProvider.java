package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.util.DataLoader;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating science-related data.
 * This class provides methods to generate various scientific information such as
 * chemical elements, symbols, formulas, units, and unit prefixes.
 */
public class ScienceProvider {
    private final RandomService random;
    private List<String> elements;
    private List<String> symbols;
    private List<String> units;
    private List<String> unitSymbols;
    private List<String> unitPrefixes;
    private List<String> unitPrefixSymbols;

    /**
     * Constructs a new ScienceProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public ScienceProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random chemical element name.
     *
     * @return A random chemical element name as a string
     */
    public String chemicalElement() {
        elements = LazyLoader.load("scienceElements", () -> DataLoader.getListData("science", "elements"));
        return random.randomElement(elements);
    }

    /**
     * Generates a random chemical element symbol.
     *
     * @return A random chemical element symbol as a string
     */
    public String chemicalSymbol() {
        symbols = LazyLoader.load("scienceSymbols", () -> DataLoader.getListData("science", "symbols"));
        return random.randomElement(symbols);
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
        units = LazyLoader.load("scienceUnits", () -> DataLoader.getListData("science", "units"));
        return random.randomElement(units);
    }

    /**
     * Generates a random unit symbol.
     *
     * @return A random unit symbol as a string
     */
    public String unitSymbol() {
        unitSymbols = LazyLoader.load("scienceUnitSymbols", () -> DataLoader.getListData("science", "unit_symbols"));
        return random.randomElement(unitSymbols);
    }

    /**
     * Generates a random unit prefix (e.g., kilo, mega, giga).
     *
     * @return A random unit prefix as a string
     */
    public String unitPrefix() {
        unitPrefixes = LazyLoader.load("scienceUnitPrefixes", () -> DataLoader.getListData("science", "unit_prefixes"));
        return random.randomElement(unitPrefixes);
    }

    /**
     * Generates a random unit prefix symbol (e.g., k, M, G).
     *
     * @return A random unit prefix symbol as a string
     */
    public String unitPrefixSymbol() {
        unitPrefixSymbols = LazyLoader.load("scienceUnitPrefixSymbols", () -> DataLoader.getListData("science", "unit_prefix_symbols"));
        return random.randomElement(unitPrefixSymbols);
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