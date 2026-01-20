package com.datamirage.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataExporterTest {

    private DataExporter exporter;

    @BeforeEach
    void setUp() {
        exporter = new DataExporter();
    }

    @Test
    void toJson_WithVarargs_ShouldReturnValidJson() {
        String json = exporter.toJson("test1", "test2", "test3");
        assertNotNull(json);
        assertTrue(json.contains("test1"));
        assertTrue(json.contains("test2"));
        assertTrue(json.contains("test3"));
    }

    @Test
    void toJson_WithList_ShouldReturnValidJson() {
        List<String> data = Arrays.asList("item1", "item2", "item3");
        String json = exporter.toJson(data);
        assertNotNull(json);
        assertTrue(json.contains("item1"));
        assertTrue(json.contains("item2"));
        assertTrue(json.contains("item3"));
    }

    @Test
    void toJson_WithEmptyData_ShouldReturnEmptyArray() {
        String json = exporter.toJson();
        assertEquals("[ ]", json.trim());
    }

    @Test
    void toFile_WithVarargs_ShouldWriteToFile(@TempDir Path tempDir) throws IOException {
        File outputFile = tempDir.resolve("test.json").toFile();
        exporter.toFile(outputFile, "data1", "data2");

        assertTrue(outputFile.exists());
        String content = Files.readString(outputFile.toPath());
        assertTrue(content.contains("data1"));
        assertTrue(content.contains("data2"));
    }

    @Test
    void toFile_WithPath_ShouldWriteToFile(@TempDir Path tempDir) throws IOException {
        String outputPath = tempDir.resolve("test2.json").toString();
        exporter.toFile(outputPath, "item1", "item2");

        File outputFile = new File(outputPath);
        assertTrue(outputFile.exists());
        String content = Files.readString(outputFile.toPath());
        assertTrue(content.contains("item1"));
        assertTrue(content.contains("item2"));
    }

    @Test
    void toFile_WithList_ShouldWriteToFile(@TempDir Path tempDir) throws IOException {
        File outputFile = tempDir.resolve("test3.json").toFile();
        List<String> data = Arrays.asList("listItem1", "listItem2");
        exporter.toFile(outputFile, data);

        assertTrue(outputFile.exists());
        String content = Files.readString(outputFile.toPath());
        assertTrue(content.contains("listItem1"));
        assertTrue(content.contains("listItem2"));
    }
}
