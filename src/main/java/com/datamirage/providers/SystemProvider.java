package com.datamirage.providers;

import com.datamirage.util.DataContext;
import com.datamirage.util.RandomService;

/**
 * A provider class for generating system-related data.
 * This class provides methods to generate various system-related information such as
 * file names, extensions, paths, MIME types, and common file information.
 */
public class SystemProvider extends AbstractProvider {

    /**
     * Constructs a new SystemProvider with the specified RandomService and DataContext.
     *
     * @param random The RandomService instance to use for generating random values
     * @param context The DataContext instance for locale-specific data loading
     */
    public SystemProvider(RandomService random, DataContext context) {
        super(random, context);
    }

    /**
     * Constructs a new SystemProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     * @deprecated Use {@link #SystemProvider(RandomService, DataContext)} instead
     */
    @Deprecated
    public SystemProvider(RandomService random) {
        super(random);
    }

    /**
     * Generates a random file name.
     *
     * @return A random file name as a string
     */
    public String fileName() {
        return randomFromLocaleList("systemFileNames", "system", "file_names");
    }

    /**
     * Generates a random file extension.
     *
     * @return A random file extension as a string
     */
    public String fileExtension() {
        return randomFromLocaleList("systemFileExtensions", "system", "file_extensions");
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
        return randomFromLocaleList("systemDirectoryPaths", "system", "directory_paths");
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
        return randomFromLocaleList("systemMimeTypes", "system", "mime_types");
    }

    /**
     * Generates a random common file type.
     *
     * @return A random common file type as a string
     */
    public String commonFileType() {
        return randomFromLocaleList("systemCommonFileTypes", "system", "common_file_types");
    }

    /**
     * Generates a random common file extension.
     *
     * @return A random common file extension as a string
     */
    public String commonFileExtension() {
        return randomFromLocaleList("systemCommonFileExtensions", "system", "common_file_extensions");
    }

    /**
     * Generates a random common file name.
     *
     * @return A random common file name as a string
     */
    public String commonFileName() {
        return randomFromLocaleList("systemCommonFileNames", "system", "common_file_names");
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
    @SuppressWarnings("deprecation")
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
