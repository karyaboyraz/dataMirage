package com.datamirage.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A utility class for generating batches of fake data.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * DataMirage dm = new DataMirage();
 * BatchGenerator batch = new BatchGenerator();
 *
 * // Generate 100 random names
 * List<String> names = batch.generate(100, () -> dm.name().fullName());
 *
 * // Generate 50 random addresses
 * List<String> addresses = batch.generate(50, () -> dm.address().streetAddress());
 * }
 * </pre>
 * </p>
 */
public class BatchGenerator {

    /**
     * Generates a list of items using the provided supplier.
     *
     * @param <T>       The type of items to generate
     * @param count     The number of items to generate
     * @param generator A supplier that generates individual items
     * @return A list containing the generated items
     * @throws IllegalArgumentException if count is less than 0
     */
    public <T> List<T> generate(int count, Supplier<T> generator) {
        if (count < 0) {
            throw new IllegalArgumentException("Count must be non-negative");
        }
        return IntStream.range(0, count)
                .mapToObj(i -> generator.get())
                .collect(Collectors.toList());
    }

    /**
     * Generates a list of unique items using the provided supplier.
     * If uniqueness cannot be achieved within a reasonable number of attempts,
     * the method will return as many unique items as possible.
     *
     * @param <T>       The type of items to generate
     * @param count     The number of unique items to generate
     * @param generator A supplier that generates individual items
     * @return A list containing unique generated items
     * @throws IllegalArgumentException if count is less than 0
     */
    public <T> List<T> generateUnique(int count, Supplier<T> generator) {
        if (count < 0) {
            throw new IllegalArgumentException("Count must be non-negative");
        }

        List<T> result = new ArrayList<>();
        int maxAttempts = count * 10; // Allow up to 10x attempts to find unique values
        int attempts = 0;

        while (result.size() < count && attempts < maxAttempts) {
            T item = generator.get();
            if (!result.contains(item)) {
                result.add(item);
            }
            attempts++;
        }

        return result;
    }

    /**
     * Generates a list of items and exports them directly to JSON.
     *
     * @param <T>       The type of items to generate
     * @param count     The number of items to generate
     * @param generator A supplier that generates individual items
     * @return A JSON string containing the generated items
     */
    public <T> String generateAsJson(int count, Supplier<T> generator) {
        List<T> items = generate(count, generator);
        return new DataExporter().toJson(items);
    }
}
