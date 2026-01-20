package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.DataLoader;
import com.datamirage.util.LazyLoader;
import com.datamirage.util.RandomService;

import java.util.List;

/**
 * An abstract base class for all data providers.
 * This class provides common functionality and checks for locale-specific data.
 */
public abstract class AbstractProvider {
    protected final RandomService random;
    protected final DataContext context;

    /**
     * Constructs a new AbstractProvider with the specified RandomService and DataContext.
     * This is the preferred constructor for locale-isolated data generation.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    protected AbstractProvider(RandomService random, DataContext context) {
        this.random = random;
        this.context = context;
    }

    /**
     * Constructs a new AbstractProvider with the specified RandomService.
     * This constructor is maintained for backward compatibility but does not support
     * instance-based locale isolation.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #AbstractProvider(RandomService, DataContext)} instead
     *             for proper locale isolation.
     */
    @Deprecated
    protected AbstractProvider(RandomService random) {
        this.random = random;
        this.context = null;
    }

    /**
     * Checks if the required field exists in the current locale's data.
     * If not, throws an exception indicating the data is missing for this locale.
     *
     * @param category The data category to check
     * @param field The field name to check
     * @throws IllegalStateException if the locale-specific data is missing
     */
    @SuppressWarnings("deprecation")
    protected void requireLocaleSpecificData(String category, String field) {
        if (context != null) {
            if (!context.hasLocaleSpecificField(category, field)) {
                throw new IllegalStateException(
                    "Missing locale-specific data for " + category + "." + field +
                    " in locale " + context.getLocale()
                );
            }
        } else {
            if (!DataLoader.hasLocaleSpecificField(category, field)) {
                throw new IllegalStateException(
                    "Missing locale-specific data for " + category + "." + field +
                    " in locale " + DataLoader.getCurrentLocale()
                );
            }
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
    @SuppressWarnings("deprecation")
    protected boolean hasLocaleSpecificData(String category, String field) {
        if (context != null) {
            return context.hasLocaleSpecificField(category, field);
        } else {
            return DataLoader.hasLocaleSpecificField(category, field);
        }
    }

    /**
     * Returns the DataContext associated with this provider.
     *
     * @return The DataContext, or null if not set
     */
    protected DataContext getContext() {
        return context;
    }

    /**
     * Loads a list of strings from locale-specific data with caching support.
     * This method abstracts the context/LazyLoader pattern used throughout providers.
     *
     * @param cacheKey The key to use for caching the loaded data
     * @param category The data category (e.g., "name", "address")
     * @param field The field within the category (e.g., "first_names", "cities")
     * @return A list of strings from the locale data
     */
    @SuppressWarnings("deprecation")
    protected List<String> getLocaleList(String cacheKey, String category, String field) {
        if (context != null) {
            return context.load(cacheKey, () -> context.getListData(category, field));
        }
        return LazyLoader.load(cacheKey, () -> DataLoader.getListData(category, field));
    }

    /**
     * Loads a list and returns a random element from it.
     * This is a convenience method combining getLocaleList() and random selection.
     *
     * @param cacheKey The key to use for caching the loaded data
     * @param category The data category (e.g., "name", "address")
     * @param field The field within the category
     * @return A randomly selected string from the locale data list
     */
    protected String randomFromLocaleList(String cacheKey, String category, String field) {
        return random.randomElement(getLocaleList(cacheKey, category, field));
    }
}
