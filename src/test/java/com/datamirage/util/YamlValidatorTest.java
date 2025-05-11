package com.datamirage.util;

import com.datamirage.locale.DataMirageLocale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YamlValidatorTest {

    private YamlValidator validator;

    @BeforeEach
    void setUp() {
        validator = new YamlValidator();
    }

    @Test
    void validateFile_ShouldDetectMissingFile() {
        // Test validation of a non-existent file
        YamlValidator.ValidationResult result = validator.validateFile("nonexistent", DataMirageLocale.EN_US);
        
        assertFalse(result.fileExists());
        assertFalse(result.isValid());
    }

    @Test
    void validateFile_WithExistingFile() {
        // This test assumes that address.yaml exists in both TR_TR and EN_US locales
        String filePath = "src/main/resources/data/en_US/address.yaml";
        Assumptions.assumeTrue(Files.exists(Paths.get(filePath)), 
                "This test requires address.yaml in EN_US locale");
        
        YamlValidator.ValidationResult result = validator.validateFile("address", DataMirageLocale.EN_US);
        
        assertTrue(result.fileExists());
        System.out.println(result);
    }

    @Test
    void validateLocale_ShouldValidateAllFiles() {
        // This test validates all files in a locale
        String localePath = "src/main/resources/data/en_US";
        Assumptions.assumeTrue(Files.exists(Paths.get(localePath)), 
                "This test requires the EN_US locale directory");
        
        List<YamlValidator.ValidationResult> results = validator.validateLocale(DataMirageLocale.EN_US);
        
        assertFalse(results.isEmpty(), "Should return validation results for files");
        
        // Print results for manual inspection
        results.forEach(result -> System.out.println(result.toString()));
        
        // Print summary
        long validCount = results.stream().filter(YamlValidator.ValidationResult::isValid).count();
        System.out.println("\nSummary: " + validCount + " out of " + results.size() + " files are valid");
    }
    
    @Test
    void validateFile_ShouldVerifyKeyOrder() {
        // This test focuses on key order checking
        // Assumptions.assumeTrue(/* file exists and expected to have ordered keys */);
        
        String fileName = "address"; // Or any file known to have consistent key order
        YamlValidator.ValidationResult result = validator.validateFile(fileName, DataMirageLocale.EN_US);
        
        if (result.fileExists()) {
            System.out.println("Key order check for " + fileName + ": " + 
                               (result.areKeysInSameOrder() ? "Passed" : "Failed"));
            System.out.println(result);
        }
    }
} 