package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HelpersProviderTest {
    private HelpersProvider helpersProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        helpersProvider = new HelpersProvider(new RandomService());
    }

    @RepeatedTest(20)
    void randomElement_ShouldReturnValidElementFromList() {
        List<String> list = Arrays.asList("a", "b", "c");
        String result = helpersProvider.randomElement(list);
        assertNotNull(result);
        assertTrue(list.contains(result));
    }

    @RepeatedTest(20)
    void randomElement_ShouldReturnValidElementFromArray() {
        String[] array = {"a", "b", "c"};
        String result = helpersProvider.randomElement(array);
        assertNotNull(result);
        assertTrue(Arrays.asList(array).contains(result));
    }

    @RepeatedTest(20)
    void randomKey_ShouldReturnValidKey() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        String result = helpersProvider.randomKey(map);
        assertNotNull(result);
        assertTrue(map.containsKey(result));
    }

    @RepeatedTest(20)
    void randomValue_ShouldReturnValidValue() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        Integer result = helpersProvider.randomValue(map);
        assertNotNull(result);
        assertTrue(map.containsValue(result));
    }

    @RepeatedTest(20)
    void randomize_ShouldReturnValidString() {
        String input = "###???";
        String result = helpersProvider.randomize(input);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.matches("^\\d{3}[A-Z]{3}$"));
    }

    @RepeatedTest(20)
    void numerify_ShouldReturnValidNumericString() {
        String input = "###-###-####";
        String result = helpersProvider.numerify(input);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.matches("^\\d{3}-\\d{3}-\\d{4}$"));
    }

    @RepeatedTest(20)
    void letterify_ShouldReturnValidLetterString() {
        String input = "???-???-????";
        String result = helpersProvider.letterify(input);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.matches("^[A-Z]{3}-[A-Z]{3}-[A-Z]{4}$"));
    }

    @RepeatedTest(20)
    void bothify_ShouldReturnValidString() {
        String input = "##??-??##-????";
        String result = helpersProvider.bothify(input);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.matches("^\\d{2}[A-Z]{2}-[A-Z]{2}\\d{2}-[A-Z]{4}$"));
    }
} 