package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class SystemProviderTest {
    private SystemProvider systemProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        systemProvider = new SystemProvider(new RandomService());
    }

    @RepeatedTest(20)
    void fileName_ShouldReturnValidFileName() {
        String fileName = systemProvider.fileName();
        assertNotNull(fileName);
        assertFalse(fileName.isEmpty());
    }

    @RepeatedTest(20)
    void fileExtension_ShouldReturnValidExtension() {
        String extension = systemProvider.fileExtension();
        assertNotNull(extension);
        assertFalse(extension.isEmpty());
    }

    @RepeatedTest(20)
    void fileNameWithExtension_ShouldReturnValidFileName() {
        String fileName = systemProvider.fileNameWithExtension();
        assertNotNull(fileName);
        assertFalse(fileName.isEmpty());
        assertTrue(fileName.contains("."));
    }

    @RepeatedTest(20)
    void directoryPath_ShouldReturnValidPath() {
        String path = systemProvider.directoryPath();
        assertNotNull(path);
        assertFalse(path.isEmpty());
    }

    @RepeatedTest(20)
    void filePath_ShouldReturnValidPath() {
        String path = systemProvider.filePath();
        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertTrue(path.contains("/"));
    }

    @RepeatedTest(20)
    void mimeType_ShouldReturnValidMimeType() {
        String mimeType = systemProvider.mimeType();
        assertNotNull(mimeType);
        assertFalse(mimeType.isEmpty());
    }

    @RepeatedTest(20)
    void commonFileType_ShouldReturnValidType() {
        String type = systemProvider.commonFileType();
        assertNotNull(type);
        assertFalse(type.isEmpty());
    }

    @RepeatedTest(20)
    void commonFileExtension_ShouldReturnValidExtension() {
        String extension = systemProvider.commonFileExtension();
        assertNotNull(extension);
        assertFalse(extension.isEmpty());
    }

    @RepeatedTest(20)
    void commonFileName_ShouldReturnValidName() {
        String name = systemProvider.commonFileName();
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }

    @RepeatedTest(20)
    void commonFileNameWithExtension_ShouldReturnValidName() {
        String name = systemProvider.commonFileNameWithExtension();
        assertNotNull(name);
        assertFalse(name.isEmpty());
        assertTrue(name.contains("."));
    }
} 