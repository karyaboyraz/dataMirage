package com.datamirage.util;

import com.datamirage.locale.DataMirageLocale;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A utility class for validating YAML data files against a reference locale.
 * This class compares YAML files across different locales to ensure they have
 * the same structure and keys.
 */
public class YamlValidator {
    private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    private static final String DATA_PATH = "src/main/resources/data/";
    private static final DataMirageLocale REFERENCE_LOCALE = DataMirageLocale.TR_TR;

    /**
     * Validates all YAML files in a target locale against the reference locale (TR_TR).
     * 
     * @param targetLocale The locale to validate
     * @return A list of validation results for each file
     */
    public List<ValidationResult> validateLocale(DataMirageLocale targetLocale) {
        List<ValidationResult> results = new ArrayList<>();
        List<String> referenceFiles = getYamlFiles(REFERENCE_LOCALE);
        
        for (String fileName : referenceFiles) {
            ValidationResult result = validateFile(fileName, targetLocale);
            results.add(result);
        }
        
        return results;
    }
    
    /**
     * Validates a specific YAML file in a target locale against the reference locale.
     * 
     * @param fileName The name of the file to validate (without path or extension)
     * @param targetLocale The locale to validate
     * @return A validation result for the file
     */
    public ValidationResult validateFile(String fileName, DataMirageLocale targetLocale) {
        ValidationResult result = new ValidationResult(fileName, targetLocale);
        
        try {
            Map<String, Object> referenceData = loadYamlData(fileName, REFERENCE_LOCALE);
            Map<String, Object> targetData = loadYamlData(fileName, targetLocale);
            
            if (targetData == null) {
                result.setFileExists(false);
                return result;
            }
            
            result.setFileExists(true);
            assert referenceData != null;
            validateKeys(referenceData, targetData, "", result);
            
        } catch (IOException e) {
            result.addError("Error loading YAML file: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * Recursively compares keys between reference and target data.
     * 
     * @param referenceData The reference data map
     * @param targetData The target data map
     * @param path The current path in the YAML structure
     * @param result The validation result to update
     */
    @SuppressWarnings("unchecked")
    private void validateKeys(Map<String, Object> referenceData, Map<String, Object> targetData, 
                             String path, ValidationResult result) {
        // Check for missing keys in target
        Set<String> referenceKeys = referenceData.keySet();
        Set<String> targetKeys = targetData.keySet();
        
        // Find missing keys (in reference but not in target)
        for (String key : referenceKeys) {
            String currentPath = path.isEmpty() ? key : path + "." + key;
            
            if (!targetKeys.contains(key)) {
                result.addMissingKey(currentPath);
            } else {
                // If both have the key, check if values are both maps for deeper validation
                Object refValue = referenceData.get(key);
                Object targetValue = targetData.get(key);
                
                if (refValue instanceof Map && targetValue instanceof Map) {
                    validateKeys(
                        (Map<String, Object>) refValue, 
                        (Map<String, Object>) targetValue, 
                        currentPath, 
                        result
                    );
                }
            }
        }
        
        // Find extra keys (in target but not in reference)
        for (String key : targetKeys) {
            String currentPath = path.isEmpty() ? key : path + "." + key;
            
            if (!referenceKeys.contains(key)) {
                result.addExtraKey(currentPath);
            }
        }
        
        // Check if keys appear in the same order
        List<String> refKeyList = new ArrayList<>(referenceKeys);
        List<String> targetKeyList = new ArrayList<>(targetKeys);
        
        // Filter to only include common keys for order comparison
        List<String> commonRefKeys = refKeyList.stream()
            .filter(targetKeyList::contains)
            .toList();
        
        List<String> commonTargetKeys = targetKeyList.stream()
            .filter(refKeyList::contains)
            .toList();
        
        // Check if common keys are in the same order
        boolean sameOrder = true;
        for (int i = 0; i < commonRefKeys.size(); i++) {
            if (!commonRefKeys.get(i).equals(commonTargetKeys.get(i))) {
                sameOrder = false;
                break;
            }
        }
        
        if (!sameOrder && !commonRefKeys.isEmpty()) {
            result.setKeysInSameOrder(false);
            result.addError("Keys at " + (path.isEmpty() ? "root" : path) + " are not in the same order");
        }
    }
    
    /**
     * Loads a YAML file from the specified locale.
     * 
     * @param fileName The name of the file to load (without extension)
     * @param locale The locale to load from
     * @return A map containing the YAML data, or null if the file doesn't exist
     * @throws IOException If an error occurs while reading the file
     */
    private Map<String, Object> loadYamlData(String fileName, DataMirageLocale locale) throws IOException {
        String filePath = DATA_PATH + locale.getCode() + "/" + fileName + ".yaml";
        Path path = Paths.get(filePath);
        
        if (!Files.exists(path)) {
            // Try to find the file in the classpath
            ClassLoader classLoader = getClass().getClassLoader();
            URL resourceURL = classLoader.getResource("data/" + locale.getCode() + "/" + fileName + ".yaml");
            
            if (resourceURL != null) {
                try (InputStream is = resourceURL.openStream()) {
                    return objectMapper.readValue(is, new TypeReference<LinkedHashMap<String, Object>>() {});
                }
            }
            
            return null;
        }
        
        try (InputStream is = Files.newInputStream(path)) {
            return objectMapper.readValue(is, new TypeReference<LinkedHashMap<String, Object>>() {});
        }
    }
    
    /**
     * Gets a list of YAML file names in a locale directory.
     * 
     * @param locale The locale to list files from
     * @return A list of file names without extensions
     */
    private List<String> getYamlFiles(DataMirageLocale locale) {
        String dirPath = DATA_PATH + locale.getCode();
        Path path = Paths.get(dirPath);
        
        try (Stream<Path> stream = Files.list(path)) {
            return stream
                .filter(file -> !Files.isDirectory(file))
                .map(file -> file.getFileName().toString())
                .filter(name -> name.endsWith(".yaml"))
                .map(name -> name.substring(0, name.length() - 5)) // Remove .yaml extension
                .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error listing files in directory: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * A class to hold validation results for a YAML file.
     */
    public static class ValidationResult {
        private final String fileName;
        private final DataMirageLocale locale;
        private boolean fileExists;
        private boolean keysInSameOrder = true;
        private final List<String> missingKeys = new ArrayList<>();
        private final List<String> extraKeys = new ArrayList<>();
        private final List<String> errors = new ArrayList<>();
        
        public ValidationResult(String fileName, DataMirageLocale locale) {
            this.fileName = fileName;
            this.locale = locale;
        }
        
        public void setFileExists(boolean fileExists) {
            this.fileExists = fileExists;
        }
        
        public void setKeysInSameOrder(boolean keysInSameOrder) {
            this.keysInSameOrder = keysInSameOrder;
        }
        
        public void addMissingKey(String key) {
            missingKeys.add(key);
        }
        
        public void addExtraKey(String key) {
            extraKeys.add(key);
        }
        
        public void addError(String error) {
            errors.add(error);
        }
        
        public String getFileName() {
            return fileName;
        }
        
        public DataMirageLocale getLocale() {
            return locale;
        }
        
        public boolean fileExists() {
            return fileExists;
        }
        
        public boolean areKeysInSameOrder() {
            return keysInSameOrder;
        }
        
        public List<String> getMissingKeys() {
            return missingKeys;
        }
        
        public List<String> getExtraKeys() {
            return extraKeys;
        }
        
        public List<String> getErrors() {
            return errors;
        }
        
        public boolean isValid() {
            return fileExists && keysInSameOrder && missingKeys.isEmpty() && 
                  extraKeys.isEmpty() && errors.isEmpty();
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Validation result for ").append(fileName).append(" in locale ").append(locale.getCode()).append(":\n");
            
            if (!fileExists) {
                sb.append("- File does not exist\n");
                return sb.toString();
            }
            
            if (isValid()) {
                sb.append("- Valid: All keys match the reference file\n");
                return sb.toString();
            }
            
            if (!missingKeys.isEmpty()) {
                sb.append("- Missing keys: ").append(String.join(", ", missingKeys)).append("\n");
            }
            
            if (!extraKeys.isEmpty()) {
                sb.append("- Extra keys: ").append(String.join(", ", extraKeys)).append("\n");
            }
            
            if (!keysInSameOrder) {
                sb.append("- Keys are not in the same order as the reference file\n");
            }
            
            if (!errors.isEmpty()) {
                sb.append("- Errors: ").append(String.join(", ", errors)).append("\n");
            }
            
            return sb.toString();
        }
    }
} 