package com.datamirage.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.datamirage.locale.DataMirageLocale;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedHashMap;

/**
 * A utility class for loading and caching YAML data files.
 * This class provides functionality to load data from YAML files based on the current locale
 * and cache the loaded data for better performance.
 */
public final class DataLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    private static final Map<String, Map<String, Object>> dataCache = new ConcurrentHashMap<>();
    private static final Map<String, Map<String, Object>> localeSpecificCache = new ConcurrentHashMap<>();
    private static DataMirageLocale currentLocale = DataMirageLocale.TR_TR;
    private static final String DATA_PATH = "data/";
    private static final String COMMON_PATH = "data/common/";

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws IllegalStateException if an attempt is made to instantiate this class
     */
    private DataLoader() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Sets the current locale for data loading.
     * This method also clears the data cache to ensure fresh data is loaded for the new locale.
     *
     * @param locale The new locale to set
     */
    public static void setLocale(DataMirageLocale locale) {
        currentLocale = locale;
        dataCache.clear();
        localeSpecificCache.clear();
    }

    /**
     * Returns the current locale being used for data loading.
     *
     * @return The current locale
     */
    public static DataMirageLocale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * Retrieves a list of strings from the specified category and field in the YAML data.
     * The field can be specified using dot notation for nested fields.
     * First tries to get the field from locale-specific data, if not found tries common data.
     *
     * @param category The category of data to load
     * @param field The field to retrieve, using dot notation for nested fields
     * @return An unmodifiable list of strings from the specified field
     * @throws RuntimeException if the field is not found in both locale-specific and common data
     */
    @SuppressWarnings("unchecked")
    public static List<String> getListData(String category, String field) {
        Map<String, Object> data = loadYamlData(category);
        Object result = getFieldValue(data, field);
        if (result instanceof List) {
            return Collections.unmodifiableList((List<String>) result);
        }
        throw new RuntimeException("Field " + field + " not found in data for " + category);
    }
    
    /**
     * Checks if the specified field exists directly in the locale-specific data file, 
     * without falling back to common data.
     *
     * @param category The category of data to check
     * @param field The field to check, using dot notation for nested fields
     * @return true if the field exists in locale-specific data, false otherwise
     */
    public static boolean hasLocaleSpecificField(String category, String field) {
        Map<String, Object> localeData = loadLocaleSpecificYamlData(category);
        if (localeData == null) {
            return false;
        }
        Object result = getFieldValue(localeData, field);
        return result instanceof List && !((List<?>) result).isEmpty();
    }

    /**
     * Gets the value of a field from a map using dot notation.
     *
     * @param data The map containing the data
     * @param field The field to retrieve, using dot notation for nested fields
     * @return The value of the field, or null if not found
     */
    @SuppressWarnings("unchecked")
    private static Object getFieldValue(Map<String, Object> data, String field) {
        String[] fieldParts = field.split("\\.");
        Object current = data;

        for (String part : fieldParts) {
            if (current instanceof Map) {
                current = ((Map<String, Object>) current).get(part);
                if (current == null) {
                    return null;
                }
            } else {
                return null;
            }
        }

        return current;
    }

    /**
     * Loads YAML data for the specified category from the appropriate locale directory.
     * First tries to load from the locale-specific directory, then falls back to common directory.
     * The data is cached to improve performance on subsequent requests.
     *
     * @param category The category of data to load
     * @return A map containing the loaded YAML data
     * @throws RuntimeException if the data file cannot be found or loaded
     */
    private static Map<String, Object> loadYamlData(String category) {
        String cacheKey = currentLocale.getCode() + "/" + category;
        return dataCache.computeIfAbsent(cacheKey, key -> {
            Map<String, Object> mergedData = new LinkedHashMap<>();

            // Load common data first
            String commonPath = COMMON_PATH + category + ".yaml";
            InputStream commonIs = DataLoader.class.getClassLoader().getResourceAsStream(commonPath);
            if (commonIs != null) {
                try {
                    Map<String, Object> commonData = objectMapper.readValue(commonIs, new TypeReference<>() {});
                    deepMerge(mergedData, commonData);
                } catch (IOException e) {
                    System.out.println("Error loading common data from " + commonPath + ": " + e.getMessage());
                }
            }

            // Load locale-specific data, which should override common data if keys conflict
            String localePath = DATA_PATH + currentLocale.getCode() + "/" + category + ".yaml";
            InputStream localeIs = DataLoader.class.getClassLoader().getResourceAsStream(localePath);
            if (localeIs != null) {
                try {
                    Map<String, Object> localeData = objectMapper.readValue(localeIs, new TypeReference<>() {});
                    deepMerge(mergedData, localeData);
                } catch (IOException e) {
                    System.out.println("Error loading locale data from " + localePath + ": " + e.getMessage());
                }
            }

            if (mergedData.isEmpty()) {
                System.out.println("Resource not found in both: " + localePath + " and " + commonPath);
                throw new RuntimeException("Data file not found: " + category);
            }
            return mergedData;
        });
    }
    
    /**
     * Loads YAML data ONLY from the locale-specific directory, without merging common data.
     *
     * @param category The category of data to load
     * @return A map containing the loaded locale-specific YAML data, or null if file doesn't exist
     */
    private static Map<String, Object> loadLocaleSpecificYamlData(String category) {
        String cacheKey = currentLocale.getCode() + "/" + category;
        return localeSpecificCache.computeIfAbsent(cacheKey, key -> {
            // Load only locale-specific data
            String localePath = DATA_PATH + currentLocale.getCode() + "/" + category + ".yaml";
            InputStream localeIs = DataLoader.class.getClassLoader().getResourceAsStream(localePath);
            if (localeIs != null) {
                try {
                    return objectMapper.readValue(localeIs, new TypeReference<>() {});
                } catch (IOException e) {
                    System.out.println("Error loading locale data from " + localePath + ": " + e.getMessage());
                }
            }
            return null;
        });
    }

    private static void deepMerge(Map<String, Object> original, Map<String, Object> newData) {
        for (Map.Entry<String, Object> entry : newData.entrySet()) {
            String key = entry.getKey();
            Object newValue = entry.getValue();
            if (original.containsKey(key)) {
                Object existingValue = original.get(key);
                if (existingValue instanceof Map && newValue instanceof Map) {
                    deepMerge((Map<String, Object>) existingValue, (Map<String, Object>) newValue);
                } else {
                    original.put(key, newValue);
                }
            } else {
                original.put(key, newValue);
            }
        }
    }
} 