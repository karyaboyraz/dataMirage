package com.datamirage.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class for exporting generated data to JSON format.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * DataMirage dm = new DataMirage();
 * DataExporter exporter = new DataExporter();
 *
 * // Export to JSON string
 * String json = exporter.toJson(dm.name().fullName(), dm.address().streetAddress());
 *
 * // Export to file
 * exporter.toFile("data.json", dm.name().fullName(), dm.address().streetAddress());
 * }
 * </pre>
 * </p>
 */
public class DataExporter {
    private final ObjectMapper mapper;

    /**
     * Constructs a new DataExporter with default settings.
     */
    public DataExporter() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Constructs a new DataExporter with a custom ObjectMapper.
     *
     * @param mapper The ObjectMapper to use for JSON serialization
     */
    public DataExporter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Converts the given data objects to a JSON string.
     *
     * @param data The objects to convert to JSON
     * @return A JSON string representation of the data
     * @throws DataExportException if the conversion fails
     */
    public String toJson(Object... data) {
        try {
            return mapper.writeValueAsString(Arrays.asList(data));
        } catch (JsonProcessingException e) {
            throw new DataExportException("Failed to convert data to JSON", e);
        }
    }

    /**
     * Converts a list of objects to a JSON string.
     *
     * @param data The list of objects to convert to JSON
     * @return A JSON string representation of the data
     * @throws DataExportException if the conversion fails
     */
    public String toJson(List<?> data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new DataExportException("Failed to convert data to JSON", e);
        }
    }

    /**
     * Writes the given data objects to a JSON file.
     *
     * @param path The path to the output file
     * @param data The objects to write to the file
     * @throws DataExportException if the write operation fails
     */
    public void toFile(String path, Object... data) {
        toFile(new File(path), data);
    }

    /**
     * Writes the given data objects to a JSON file.
     *
     * @param file The output file
     * @param data The objects to write to the file
     * @throws DataExportException if the write operation fails
     */
    public void toFile(File file, Object... data) {
        try {
            mapper.writeValue(file, Arrays.asList(data));
        } catch (IOException e) {
            throw new DataExportException("Failed to write data to file: " + file.getPath(), e);
        }
    }

    /**
     * Writes a list of objects to a JSON file.
     *
     * @param path The path to the output file
     * @param data The list of objects to write to the file
     * @throws DataExportException if the write operation fails
     */
    public void toFile(String path, List<?> data) {
        toFile(new File(path), data);
    }

    /**
     * Writes a list of objects to a JSON file.
     *
     * @param file The output file
     * @param data The list of objects to write to the file
     * @throws DataExportException if the write operation fails
     */
    public void toFile(File file, List<?> data) {
        try {
            mapper.writeValue(file, data);
        } catch (IOException e) {
            throw new DataExportException("Failed to write data to file: " + file.getPath(), e);
        }
    }

    /**
     * Exception thrown when data export operations fail.
     */
    public static class DataExportException extends RuntimeException {
        public DataExportException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
