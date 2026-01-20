package com.datamirage.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.datamirage.locale.DataMirageLocale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Instance-based context for managing locale and data caching.
 * Each DataMirage instance has its own DataContext, ensuring locale isolation.
 *
 * <p>This class replaces the global state in DataLoader with instance-level state,
 * allowing multiple DataMirage instances with different locales to coexist without
 * interfering with each other.</p>
 */
public class DataContext {
    private static final Logger logger = LoggerFactory.getLogger(DataContext.class);
    private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    private static final String DATA_PATH = "data/";
    private static final String COMMON_PATH = "data/common/";

    // Global YAML cache - shared across all instances (parse results are locale-independent for same file)
    private static final Map<String, Map<String, Object>> yamlCache = new ConcurrentHashMap<>();

    private final DataMirageLocale locale;
    // Instance-level lazy cache - keyed by category+field, stores loaded lists
    private final Map<String, Object> lazyCache = new ConcurrentHashMap<>();
    // Instance-level merged data cache - keyed by category
    private final Map<String, Map<String, Object>> mergedDataCache = new ConcurrentHashMap<>();
    // Instance-level locale-specific data cache - keyed by category
    private final Map<String, Map<String, Object>> localeSpecificCache = new ConcurrentHashMap<>();

    /**
     * Constructs a new DataContext with the specified locale.
     *
     * @param locale The locale to use for data loading
     */
    public DataContext(DataMirageLocale locale) {
        this.locale = locale;
    }

    /**
     * Returns the locale associated with this context.
     *
     * @return The locale
     */
    public DataMirageLocale getLocale() {
        return locale;
    }

    /**
     * Loads a value lazily and caches it for future use within this context.
     * This is the instance-based replacement for LazyLoader.load().
     *
     * @param <T> The type of the value to load
     * @param key The key under which to cache the loaded value
     * @param loader A supplier that provides the value if it's not already cached
     * @return The loaded value, either from cache or newly loaded
     */
    @SuppressWarnings("unchecked")
    public <T> T load(String key, Supplier<T> loader) {
        return (T) lazyCache.computeIfAbsent(key, k -> loader.get());
    }

    /**
     * Retrieves a list of strings from the specified category and field.
     * The field can be specified using dot notation for nested fields.
     * First tries to get the field from locale-specific data, if not found tries common data.
     *
     * @param category The category of data to load
     * @param field The field to retrieve, using dot notation for nested fields
     * @return An unmodifiable list of strings from the specified field
     * @throws RuntimeException if the field is not found in both locale-specific and common data
     */
    @SuppressWarnings("unchecked")
    public List<String> getListData(String category, String field) {
        Map<String, Object> data = loadMergedYamlData(category);
        Object result = getFieldValue(data, field);
        if (result instanceof List) {
            return Collections.unmodifiableList((List<String>) result);
        }
        throw new RuntimeException("Field " + field + " not found in data for " + category);
    }

    /**
     * Checks if the specified field exists directly in the locale-specific data file.
     *
     * @param category The category of data to check
     * @param field The field to check, using dot notation for nested fields
     * @return true if the field exists in locale-specific data, false otherwise
     */
    public boolean hasLocaleSpecificField(String category, String field) {
        Map<String, Object> localeData = loadLocaleSpecificYamlData(category);
        if (localeData == null) {
            return false;
        }
        Object result = getFieldValue(localeData, field);
        return result instanceof List && !((List<?>) result).isEmpty();
    }

    /**
     * Loads merged YAML data for the specified category.
     * Common data is loaded first, then locale-specific data overrides conflicting keys.
     *
     * @param category The category of data to load
     * @return A map containing the merged YAML data
     */
    private Map<String, Object> loadMergedYamlData(String category) {
        return mergedDataCache.computeIfAbsent(category, cat -> {
            Map<String, Object> mergedData = new LinkedHashMap<>();

            // Load common data first
            Map<String, Object> commonData = loadYamlFile(COMMON_PATH + cat + ".yaml");
            if (commonData != null) {
                deepMerge(mergedData, commonData);
            }

            // Load locale-specific data, which should override common data if keys conflict
            Map<String, Object> localeData = loadYamlFile(DATA_PATH + locale.getCode() + "/" + cat + ".yaml");
            if (localeData != null) {
                deepMerge(mergedData, localeData);
            }

            if (mergedData.isEmpty()) {
                throw new RuntimeException("Data file not found: " + cat);
            }
            return mergedData;
        });
    }

    /**
     * Loads YAML data ONLY from the locale-specific directory.
     *
     * @param category The category of data to load
     * @return A map containing the locale-specific YAML data, or null if file doesn't exist
     */
    private Map<String, Object> loadLocaleSpecificYamlData(String category) {
        return localeSpecificCache.computeIfAbsent(category, cat ->
            loadYamlFile(DATA_PATH + locale.getCode() + "/" + cat + ".yaml")
        );
    }

    /**
     * Loads a YAML file from the classpath.
     * Uses a global cache for parsed YAML content to avoid re-parsing the same file.
     *
     * @param path The path to the YAML file
     * @return A map containing the parsed YAML data, or null if file doesn't exist
     */
    private Map<String, Object> loadYamlFile(String path) {
        return yamlCache.computeIfAbsent(path, p -> {
            InputStream is = DataContext.class.getClassLoader().getResourceAsStream(p);
            if (is != null) {
                try {
                    logger.trace("Loading YAML file from {}", p);
                    return objectMapper.readValue(is, new TypeReference<>() {});
                } catch (IOException e) {
                    logger.warn("Error loading data from {}: {}", p, e.getMessage());
                }
            }
            return null;
        });
    }

    /**
     * Gets the value of a field from a map using dot notation.
     *
     * @param data The map containing the data
     * @param field The field to retrieve, using dot notation for nested fields
     * @return The value of the field, or null if not found
     */
    @SuppressWarnings("unchecked")
    private Object getFieldValue(Map<String, Object> data, String field) {
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
     * Deep merges the source map into the target map.
     * For nested maps, recursively merges. For other values, overwrites.
     *
     * @param target The target map to merge into
     * @param source The source map to merge from
     */
    @SuppressWarnings("unchecked")
    private void deepMerge(Map<String, Object> target, Map<String, Object> source) {
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            String key = entry.getKey();
            Object newValue = entry.getValue();
            if (target.containsKey(key)) {
                Object existingValue = target.get(key);
                if (existingValue instanceof Map && newValue instanceof Map) {
                    deepMerge((Map<String, Object>) existingValue, (Map<String, Object>) newValue);
                } else {
                    target.put(key, newValue);
                }
            } else {
                target.put(key, newValue);
            }
        }
    }
}
