package com.datamirage.providers;

import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;

/**
 * An abstract base class for all data providers.
 * This class provides common functionality and checks for locale-specific data.
 */
public abstract class AbstractProvider {
    protected final RandomService random;

    /**
     * Constructs a new AbstractProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    protected AbstractProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Checks if the required field exists in the current locale's data.
     * If not, throws an exception indicating the data is missing for this locale.
     *
     * @param category The data category to check
     * @param field The field name to check
     * @throws IllegalStateException if the locale-specific data is missing
     */
    protected void requireLocaleSpecificData(String category, String field) {
        if (!DataLoader.hasLocaleSpecificField(category, field)) {
            throw new IllegalStateException(
                "Missing locale-specific data for " + category + "." + field + 
                " in locale " + DataLoader.getCurrentLocale()
            );
        }
    }

    /**
     * Checks if the required field exists in the current locale's data.
     * Returns a boolean indicating whether the data exists.
     *
     * @param category The data category to check
     * @param field The field name to check
     * @return true if the locale-specific data exists, false otherwise
     */
    protected boolean hasLocaleSpecificData(String category, String field) {
        return DataLoader.hasLocaleSpecificField(category, field);
    }
} 