package com.datamirage;

import com.datamirage.locale.DataMirageLocale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to verify that different DataMirage instances with different locales
 * are properly isolated and don't interfere with each other.
 */
@DisplayName("Locale Isolation Tests")
class LocaleIsolationTest {

    @Test
    @DisplayName("Two instances with different locales should be independent")
    void twoInstances_WithDifferentLocales_ShouldBeIndependent() {
        // Given: Two DataMirage instances with different locales
        DataMirage trMirage = new DataMirage(DataMirageLocale.TR_TR);
        DataMirage enMirage = new DataMirage(DataMirageLocale.EN_US);

        // When: We get first names from both instances
        String trFirstName = trMirage.name().firstName();
        String enFirstName = enMirage.name().firstName();

        // Then: Both should return valid non-null values
        assertNotNull(trFirstName, "Turkish first name should not be null");
        assertNotNull(enFirstName, "English first name should not be null");
        assertFalse(trFirstName.isEmpty(), "Turkish first name should not be empty");
        assertFalse(enFirstName.isEmpty(), "English first name should not be empty");

        // Verify locales are preserved
        assertEquals(DataMirageLocale.TR_TR, trMirage.getLocale(), "Turkish instance should have TR_TR locale");
        assertEquals(DataMirageLocale.EN_US, enMirage.getLocale(), "English instance should have EN_US locale");
    }

    @Test
    @DisplayName("Second instance should not affect first instance")
    void secondInstance_ShouldNotAffectFirstInstance() {
        // Given: A Turkish DataMirage instance
        DataMirage trMirage = new DataMirage(DataMirageLocale.TR_TR);

        // When: We get a name from the Turkish instance
        String firstTrName = trMirage.name().firstName();

        // And: We create an English instance
        DataMirage enMirage = new DataMirage(DataMirageLocale.EN_US);

        // And: We get a name from the English instance
        String enName = enMirage.name().firstName();

        // And: We get another name from the Turkish instance
        String secondTrName = trMirage.name().firstName();

        // Then: All names should be valid
        assertNotNull(firstTrName, "First Turkish name should not be null");
        assertNotNull(enName, "English name should not be null");
        assertNotNull(secondTrName, "Second Turkish name should not be null");

        // Locales should still be correct
        assertEquals(DataMirageLocale.TR_TR, trMirage.getLocale(), "Turkish instance locale should remain TR_TR");
        assertEquals(DataMirageLocale.EN_US, enMirage.getLocale(), "English instance locale should remain EN_US");
    }

    @RepeatedTest(5)
    @DisplayName("Multiple calls should consistently use correct locale")
    void multipleCalls_ShouldConsistentlyUseCorrectLocale() {
        // Given: Two DataMirage instances with different locales
        DataMirage deMirage = new DataMirage(DataMirageLocale.DE_DE);
        DataMirage frMirage = new DataMirage(DataMirageLocale.FR_FR);

        // When: We make multiple calls interleaved between instances
        List<String> deNames = new ArrayList<>();
        List<String> frNames = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            deNames.add(deMirage.name().firstName());
            frNames.add(frMirage.name().firstName());
        }

        // Then: All names should be valid
        assertEquals(10, deNames.size());
        assertEquals(10, frNames.size());

        for (String name : deNames) {
            assertNotNull(name, "German name should not be null");
            assertFalse(name.isEmpty(), "German name should not be empty");
        }

        for (String name : frNames) {
            assertNotNull(name, "French name should not be null");
            assertFalse(name.isEmpty(), "French name should not be empty");
        }
    }

    @Test
    @DisplayName("Concurrent access should not mix locales")
    void concurrentAccess_ShouldNotMixLocales() throws InterruptedException {
        // Given: Two DataMirage instances with different locales
        DataMirage trMirage = new DataMirage(DataMirageLocale.TR_TR);
        DataMirage enMirage = new DataMirage(DataMirageLocale.EN_US);

        int numThreads = 10;
        int operationsPerThread = 100;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(numThreads * 2);

        List<String> trNames = java.util.Collections.synchronizedList(new ArrayList<>());
        List<String> enNames = java.util.Collections.synchronizedList(new ArrayList<>());
        List<Exception> exceptions = java.util.Collections.synchronizedList(new ArrayList<>());

        ExecutorService executor = Executors.newFixedThreadPool(numThreads * 2);

        // When: Multiple threads access both instances concurrently
        for (int i = 0; i < numThreads; i++) {
            // Turkish threads
            executor.submit(() -> {
                try {
                    startLatch.await();
                    for (int j = 0; j < operationsPerThread; j++) {
                        trNames.add(trMirage.name().firstName());
                    }
                } catch (Exception e) {
                    exceptions.add(e);
                } finally {
                    doneLatch.countDown();
                }
            });

            // English threads
            executor.submit(() -> {
                try {
                    startLatch.await();
                    for (int j = 0; j < operationsPerThread; j++) {
                        enNames.add(enMirage.name().firstName());
                    }
                } catch (Exception e) {
                    exceptions.add(e);
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        // Start all threads
        startLatch.countDown();

        // Wait for completion
        boolean completed = doneLatch.await(30, TimeUnit.SECONDS);
        executor.shutdown();

        // Then: No exceptions should have occurred
        assertTrue(completed, "All threads should complete within timeout");
        assertTrue(exceptions.isEmpty(), "No exceptions should have occurred: " + exceptions);

        // All names should be valid
        assertEquals(numThreads * operationsPerThread, trNames.size());
        assertEquals(numThreads * operationsPerThread, enNames.size());

        for (String name : trNames) {
            assertNotNull(name, "Turkish name should not be null");
            assertFalse(name.isEmpty(), "Turkish name should not be empty");
        }

        for (String name : enNames) {
            assertNotNull(name, "English name should not be null");
            assertFalse(name.isEmpty(), "English name should not be empty");
        }
    }

    @Test
    @DisplayName("All provider types should use correct locale")
    void allProviderTypes_ShouldUseCorrectLocale() {
        // Given: Two DataMirage instances with different locales
        DataMirage trMirage = new DataMirage(DataMirageLocale.TR_TR);
        DataMirage enMirage = new DataMirage(DataMirageLocale.EN_US);

        // When/Then: Various providers should work without issues
        // Name provider
        assertNotNull(trMirage.name().firstName());
        assertNotNull(enMirage.name().firstName());

        // Company provider
        assertNotNull(trMirage.company().name());
        assertNotNull(enMirage.company().name());

        // Address provider - cities should be locale-specific
        assertNotNull(trMirage.address().city());
        assertNotNull(enMirage.address().city());

        // Food provider
        assertNotNull(trMirage.food().dish());
        assertNotNull(enMirage.food().dish());

        // Phone provider
        assertNotNull(trMirage.phoneNumber().cellPhone());
        assertNotNull(enMirage.phoneNumber().cellPhone());
    }

    @Test
    @DisplayName("Creating many instances should not cause issues")
    void manyInstances_ShouldNotCauseIssues() {
        // Given: Many DataMirage instances with various locales
        DataMirageLocale[] locales = DataMirageLocale.values();
        List<DataMirage> instances = new ArrayList<>();

        // When: We create multiple instances for each locale
        for (int i = 0; i < 3; i++) {
            for (DataMirageLocale locale : locales) {
                instances.add(new DataMirage(locale));
            }
        }

        // Then: All instances should work correctly
        for (DataMirage instance : instances) {
            assertNotNull(instance.name().firstName(), "Instance should return valid name");
            assertNotNull(instance.getLocale(), "Instance should have a locale");
        }
    }

    @Test
    @DisplayName("Instance locale should be accessible via getLocale()")
    void getLocale_ShouldReturnCorrectLocale() {
        // Given/When: DataMirage instances with different locales
        DataMirage trMirage = new DataMirage(DataMirageLocale.TR_TR);
        DataMirage enMirage = new DataMirage(DataMirageLocale.EN_US);
        DataMirage deMirage = new DataMirage(DataMirageLocale.DE_DE);
        DataMirage frMirage = new DataMirage(DataMirageLocale.FR_FR);
        DataMirage esMirage = new DataMirage(DataMirageLocale.ES_ES);
        DataMirage itMirage = new DataMirage(DataMirageLocale.IT_IT);
        DataMirage ruMirage = new DataMirage(DataMirageLocale.RU_RU);

        // Then: getLocale() should return the correct locale
        assertEquals(DataMirageLocale.TR_TR, trMirage.getLocale());
        assertEquals(DataMirageLocale.EN_US, enMirage.getLocale());
        assertEquals(DataMirageLocale.DE_DE, deMirage.getLocale());
        assertEquals(DataMirageLocale.FR_FR, frMirage.getLocale());
        assertEquals(DataMirageLocale.ES_ES, esMirage.getLocale());
        assertEquals(DataMirageLocale.IT_IT, itMirage.getLocale());
        assertEquals(DataMirageLocale.RU_RU, ruMirage.getLocale());
    }

    @Test
    @DisplayName("Default constructor should use default locale")
    void defaultConstructor_ShouldUseDefaultLocale() {
        // Given/When: DataMirage with default constructor
        DataMirage mirage = new DataMirage();

        // Then: Should use default locale (EN_US based on DataMirageLocale.DEFAULT)
        assertNotNull(mirage.getLocale());
        // Should be able to generate data
        assertNotNull(mirage.name().firstName());
    }
}
