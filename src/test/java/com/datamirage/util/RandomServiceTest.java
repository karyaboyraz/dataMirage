package com.datamirage.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomServiceTest {
    private static final long TEST_SEED = 12345L;
    private RandomService randomService;

    @BeforeEach
    void setUp() {
        randomService = new RandomService(TEST_SEED);
    }

    @RepeatedTest(20)
    void nextInt_ShouldReturnNumberWithinBound() {
        int bound = 10;
        int result = randomService.nextInt(0, bound);
        assertTrue(result >= 0 && result <= bound);
    }

    @RepeatedTest(20)
    void randomElement_ShouldReturnElementFromList() {
        List<String> list = Arrays.asList("a", "b", "c");
        String result = randomService.randomElement(list);
        assertTrue(list.contains(result));
    }

    @RepeatedTest(20)
    void randomElement_ShouldThrowExceptionForNullList() {
        assertThrows(IllegalArgumentException.class, () -> randomService.randomElement((List<String>) null));
    }

    @RepeatedTest(20)
    void randomElement_ShouldThrowExceptionForEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> randomService.randomElement(Collections.emptyList()));
    }

    @RepeatedTest(20)
    void randomElement_ShouldReturnElementFromArray() {
        String[] array = {"a", "b", "c"};
        String result = randomService.randomElement(array);
        assertTrue(Arrays.asList(array).contains(result));
    }

    @RepeatedTest(20)
    void randomElement_ShouldReturnNullForNullArray() {
        assertNull(randomService.randomElement((String[]) null));
    }

    @RepeatedTest(20)
    void randomElement_ShouldReturnNullForEmptyArray() {
        assertNull(randomService.randomElement(new String[0]));
    }

    @RepeatedTest(20)
    void numerify_ShouldReplaceAllHashWithNumbers() {
        String pattern = "###-###-####";
        String result = randomService.numerify(pattern);
        assertTrue(result.matches("\\d{3}-\\d{3}-\\d{4}"));
    }

    @RepeatedTest(20)
    void letterify_ShouldReplaceAllQuestionMarksWithLetters() {
        String pattern = "???-???-????";
        String result = randomService.letterify(pattern);
        assertTrue(result.matches("[A-Z]{3}-[A-Z]{3}-[A-Z]{4}"));
    }

    @RepeatedTest(20)
    void bothify_ShouldReplaceBothHashAndQuestionMarks() {
        String pattern = "###-???-####";
        String result = randomService.bothify(pattern);
        assertTrue(result.matches("\\d{3}-[A-Z]{3}-\\d{4}"));
    }

    @RepeatedTest(20)
    void randomString_ShouldGenerateStringWithSpecifiedLength() {
        int length = 10;
        String result = randomService.randomString(length, true, true, true, false);
        assertEquals(length, result.length());
        assertTrue(result.matches("^[A-Za-z0-9]+$"));
    }

    @RepeatedTest(20)
    void randomString_ShouldThrowExceptionForInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> randomService.randomString(0, true, true, true, false));
    }

    @RepeatedTest(20)
    void randomString_ShouldThrowExceptionForNoCharacterTypes() {
        assertThrows(IllegalArgumentException.class, () -> randomService.randomString(10, false, false, false, false));
    }
} 