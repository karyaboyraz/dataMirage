package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.util.DataLoader;
import com.datamirage.util.LazyLoader;
import java.util.List;

/**
 * A provider class for generating system-related data.
 * This class provides methods to generate various system-related information such as
 * file names, extensions, paths, MIME types, and common file information.
 */
public class SystemProvider {
    private final RandomService random;
    private List<String> fileNames;
    private List<String> fileExtensions;
    private List<String> directoryPaths;
    private List<String> mimeTypes;
    private List<String> commonFileTypes;
    private List<String> commonFileExtensions;
    private List<String> commonFileNames;

    /**
     * Constructs a new SystemProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public SystemProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random file name.
     *
     * @return A random file name as a string
     */
    public String fileName() {
        fileNames = LazyLoader.load("systemFileNames", () -> DataLoader.getListData("system", "file_names"));
        return random.randomElement(fileNames);
    }

    /**
     * Generates a random file extension.
     *
     * @return A random file extension as a string
     */
    public String fileExtension() {
        fileExtensions = LazyLoader.load("systemFileExtensions", () -> DataLoader.getListData("system", "file_extensions"));
        return random.randomElement(fileExtensions);
    }

    /**
     * Generates a random file name with extension.
     *
     * @return A random file name with extension as a string
     */
    public String fileNameWithExtension() {
        return String.format("%s.%s",
            fileName(),
            fileExtension()
        );
    }

    /**
     * Generates a random directory path.
     *
     * @return A random directory path as a string
     */
    public String directoryPath() {
        directoryPaths = LazyLoader.load("systemDirectoryPaths", () -> DataLoader.getListData("system", "directory_paths"));
        return random.randomElement(directoryPaths);
    }

    /**
     * Generates a random file path.
     *
     * @return A random file path as a string
     */
    public String filePath() {
        return String.format("%s/%s",
            directoryPath(),
            fileNameWithExtension()
        );
    }

    /**
     * Generates a random MIME type.
     *
     * @return A random MIME type as a string
     */
    public String mimeType() {
        mimeTypes = LazyLoader.load("systemMimeTypes", () -> DataLoader.getListData("system", "mime_types"));
        return random.randomElement(mimeTypes);
    }

    /**
     * Generates a random common file type.
     *
     * @return A random common file type as a string
     */
    public String commonFileType() {
        commonFileTypes = LazyLoader.load("systemCommonFileTypes", () -> DataLoader.getListData("system", "common_file_types"));
        return random.randomElement(commonFileTypes);
    }

    /**
     * Generates a random common file extension.
     *
     * @return A random common file extension as a string
     */
    public String commonFileExtension() {
        commonFileExtensions = LazyLoader.load("systemCommonFileExtensions", () -> DataLoader.getListData("system", "common_file_extensions"));
        return random.randomElement(commonFileExtensions);
    }

    /**
     * Generates a random common file name.
     *
     * @return A random common file name as a string
     */
    public String commonFileName() {
        commonFileNames = LazyLoader.load("systemCommonFileNames", () -> DataLoader.getListData("system", "common_file_names"));
        return random.randomElement(commonFileNames);
    }

    /**
     * Generates a random common file name with extension.
     *
     * @return A random common file name with extension as a string
     */
    public String commonFileNameWithExtension() {
        return String.format("%s%s",
            commonFileName(),
            commonFileExtension()
        );
    }

    /**
     * Main method for testing the functionality of SystemProvider.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        SystemProvider systemProvider = new SystemProvider(new RandomService());
        System.out.println("File Name: " + systemProvider.fileName());
        System.out.println("File Extension: " + systemProvider.fileExtension());
        System.out.println("File Name with Extension: " + systemProvider.fileNameWithExtension());
        System.out.println("Directory Path: " + systemProvider.directoryPath());
        System.out.println("File Path: " + systemProvider.filePath());
        System.out.println("Mime Type: " + systemProvider.mimeType());
        System.out.println("Common File Type: " + systemProvider.commonFileType());
        System.out.println("Common File Extension: " + systemProvider.commonFileExtension());
        System.out.println("Common File Name: " + systemProvider.commonFileName());
        System.out.println("Common File Name with Extension: " + systemProvider.commonFileNameWithExtension());
    }
} 