package com.datamirage.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * A utility class that provides lazy loading functionality with caching.
 * This class maintains a cache of loaded values and ensures that each value
 * is loaded only once, even if requested multiple times.
 *
 * @deprecated Use {@link DataContext#load(String, java.util.function.Supplier)} instead
 *             for instance-based caching that supports locale isolation.
 */
@Deprecated
public class LazyLoader {

    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    /**
     * Loads a value lazily and caches it for future use.
     * If the value is already in the cache, it is returned immediately.
     * Otherwise, the provided loader is used to load the value, which is then cached.
     *
     * @param <T> The type of the value to load
     * @param key The key under which to cache the loaded value
     * @param loader A supplier that provides the value if it's not already cached
     * @return The loaded value, either from cache or newly loaded
     * @deprecated Use {@link DataContext#load(String, java.util.function.Supplier)} instead.
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public static <T> T load(String key, Supplier<T> loader) {
        return (T) cache.computeIfAbsent(key, k -> loader.get());
    }

    /**
     * Clears the entire cache.
     * This is called when the global locale changes to ensure fresh data is loaded.
     *
     * @deprecated This method exists only for backward compatibility with
     *             {@link DataLoader#setLocale(com.datamirage.locale.DataMirageLocale)}.
     *             Use instance-based {@link DataContext} instead.
     */
    @Deprecated
    public static void clearCache() {
        cache.clear();
    }
}