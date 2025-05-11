package com.datamirage.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * A utility class that provides lazy loading functionality with caching.
 * This class maintains a cache of loaded values and ensures that each value
 * is loaded only once, even if requested multiple times.
 */
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
     */
    @SuppressWarnings("unchecked")
    public static <T> T load(String key, Supplier<T> loader) {
        return (T) cache.computeIfAbsent(key, k -> loader.get());
    }
}