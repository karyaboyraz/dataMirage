package com.datamirage.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class BatchGeneratorTest {

    private BatchGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new BatchGenerator();
    }

    @Test
    void generate_ShouldReturnCorrectNumberOfItems() {
        AtomicInteger counter = new AtomicInteger(0);
        List<Integer> items = generator.generate(10, counter::incrementAndGet);

        assertEquals(10, items.size());
        assertEquals(1, items.get(0));
        assertEquals(10, items.get(9));
    }

    @Test
    void generate_WithZeroCount_ShouldReturnEmptyList() {
        List<String> items = generator.generate(0, () -> "test");
        assertTrue(items.isEmpty());
    }

    @Test
    void generate_WithNegativeCount_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                generator.generate(-1, () -> "test")
        );
    }

    @Test
    void generateUnique_ShouldReturnUniqueItems() {
        AtomicInteger counter = new AtomicInteger(0);
        List<Integer> items = generator.generateUnique(5, counter::incrementAndGet);

        assertEquals(5, items.size());
        assertEquals(5, items.stream().distinct().count());
    }

    @Test
    void generateUnique_WithDuplicateGenerator_ShouldStopAfterMaxAttempts() {
        // Generator that always returns the same value
        List<String> items = generator.generateUnique(5, () -> "same");

        // Should only have 1 unique item
        assertEquals(1, items.size());
    }

    @Test
    void generateUnique_WithZeroCount_ShouldReturnEmptyList() {
        List<String> items = generator.generateUnique(0, () -> "test");
        assertTrue(items.isEmpty());
    }

    @Test
    void generateUnique_WithNegativeCount_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                generator.generateUnique(-1, () -> "test")
        );
    }

    @Test
    void generateAsJson_ShouldReturnValidJson() {
        AtomicInteger counter = new AtomicInteger(0);
        String json = generator.generateAsJson(3, () -> "item" + counter.incrementAndGet());

        assertNotNull(json);
        assertTrue(json.contains("item1"));
        assertTrue(json.contains("item2"));
        assertTrue(json.contains("item3"));
    }

    @Test
    void generate_ShouldCallSupplierCorrectNumberOfTimes() {
        AtomicInteger callCount = new AtomicInteger(0);
        generator.generate(5, () -> {
            callCount.incrementAndGet();
            return "test";
        });

        assertEquals(5, callCount.get());
    }
}
