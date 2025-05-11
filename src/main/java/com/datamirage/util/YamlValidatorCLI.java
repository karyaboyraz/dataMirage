package com.datamirage.util;

import com.datamirage.locale.DataMirageLocale;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Command-line interface for the YamlValidator utility.
 * This class provides a simple way to validate YAML files across locales from the command line.
 */
public class YamlValidatorCLI {

    /**
     * Main method for running the YAML validator from the command line.
     * Usage: java -cp datamirage.jar com.datamirage.util.YamlValidatorCLI [command] [args]
     * Commands:
     * - validate-locale [locale_code]: Validates all YAML files in the specified locale
     * - validate-file [file_name] [locale_code]: Validates a specific file in the specified locale
     * - list-locales: Lists all supported locales
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }

        String command = args[0].toLowerCase();
        YamlValidator validator = new YamlValidator();

        switch (command) {
            case "validate-locale":
                if (args.length < 2) {
                    System.out.println("Error: Missing locale code argument.");
                    printHelp();
                    return;
                }
                validateLocale(validator, args[1]);
                break;
                
            case "validate-file":
                if (args.length < 3) {
                    System.out.println("Error: Missing file name or locale code argument.");
                    printHelp();
                    return;
                }
                validateFile(validator, args[1], args[2]);
                break;
                
            case "list-locales":
                listLocales();
                break;
                
            case "help":
                printHelp();
                break;
                
            default:
                System.out.println("Error: Unknown command '" + command + "'");
                printHelp();
                break;
        }
    }
    
    /**
     * Validates all YAML files in the specified locale.
     * 
     * @param validator The YamlValidator instance
     * @param localeCode The locale code to validate
     */
    private static void validateLocale(YamlValidator validator, String localeCode) {
        Optional<DataMirageLocale> localeOpt = getLocaleByCode(localeCode);
        
        if (localeOpt.isEmpty()) {
            System.out.println("Error: Invalid locale code '" + localeCode + "'");
            System.out.println("Run 'list-locales' to see available locales.");
            return;
        }
        
        DataMirageLocale locale = localeOpt.get();
        System.out.println("Validating all YAML files in locale: " + locale.getCode());
        
        List<YamlValidator.ValidationResult> results = validator.validateLocale(locale);
        
        // Print results
        for (YamlValidator.ValidationResult result : results) {
            System.out.println("\n" + result.toString());
        }
        
        // Print summary
        long validCount = results.stream().filter(YamlValidator.ValidationResult::isValid).count();
        System.out.println("\n--- Summary ---");
        System.out.println("Valid files: " + validCount + " out of " + results.size());
        
        if (validCount < results.size()) {
            System.out.println("Invalid files:");
            results.stream()
                .filter(result -> !result.isValid())
                .forEach(result -> System.out.println("- " + result.getFileName() + ".yaml"));
        }
    }
    
    /**
     * Validates a specific YAML file in the specified locale.
     * 
     * @param validator The YamlValidator instance
     * @param fileName The name of the file to validate (without extension)
     * @param localeCode The locale code to validate
     */
    private static void validateFile(YamlValidator validator, String fileName, String localeCode) {
        Optional<DataMirageLocale> localeOpt = getLocaleByCode(localeCode);
        
        if (localeOpt.isEmpty()) {
            System.out.println("Error: Invalid locale code '" + localeCode + "'");
            System.out.println("Run 'list-locales' to see available locales.");
            return;
        }
        
        DataMirageLocale locale = localeOpt.get();
        System.out.println("Validating file '" + fileName + ".yaml' in locale: " + locale.getCode());
        
        YamlValidator.ValidationResult result = validator.validateFile(fileName, locale);
        System.out.println(result.toString());
    }
    
    /**
     * Lists all supported locales.
     */
    private static void listLocales() {
        System.out.println("Supported locales:");
        
        for (DataMirageLocale locale : DataMirageLocale.values()) {
            System.out.println("- " + locale.getCode() + ": " + locale.name());
        }
    }
    
    /**
     * Prints help information.
     */
    private static void printHelp() {
        System.out.println("YAML Validator CLI");
        System.out.println("Usage: java -cp datamirage.jar com.datamirage.util.YamlValidatorCLI [command] [args]");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("  validate-locale [locale_code]");
        System.out.println("    Validates all YAML files in the specified locale against TR_TR");
        System.out.println();
        System.out.println("  validate-file [file_name] [locale_code]");
        System.out.println("    Validates a specific file in the specified locale against TR_TR");
        System.out.println();
        System.out.println("  list-locales");
        System.out.println("    Lists all supported locales");
        System.out.println();
        System.out.println("  help");
        System.out.println("    Shows this help information");
    }
    
    /**
     * Gets a locale by its code.
     * 
     * @param code The locale code
     * @return An Optional containing the locale if found, or empty if not found
     */
    private static Optional<DataMirageLocale> getLocaleByCode(String code) {
        return Arrays.stream(DataMirageLocale.values())
            .filter(locale -> locale.getCode().equalsIgnoreCase(code))
            .findFirst();
    }
} 