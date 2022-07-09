package com.example.itaminbackend.common.factory;

import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;

public class FileFactory {

    private static final ClassLoader CLASS_LOADER = FileFactory.class.getClassLoader();
    private static final String FILE_KEY = "files";

    public static MockMultipartFile getTestImage1() {
        return createImageFile("testImage1.png");
    }

    public static MockMultipartFile getTestImage2() {
        return createImageFile("testImage2.png");
    }

    public static MockMultipartFile getEmptyTestFile() { return createEmptyImageFile();}

    public static File getTestImage1File() {
        return createFile("testImage1.png");
    }

    public static File getTestImage2File() {
        return createFile("testImage2.png");
    }

    private static MockMultipartFile createImageFile(String fileName) {
        File file = createFile(fileName);

        return fileToMultipart(file);
    }

    public static MockMultipartFile fileToMultipart(File file) {
        try {
            return new MockMultipartFile(
                    FILE_KEY,
                    file.getName(),
                    ContentType.IMAGE_JPEG.getMimeType(),
                    Files.readAllBytes(file.toPath())
            );
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static MockMultipartFile createEmptyImageFile() {
        return new MockMultipartFile(
                "files",
                "",
                null,
                new byte[] {}
        );
    }

    private static File createFile(String fileName) {
        URL resource = CLASS_LOADER.getResource(fileName);
        Objects.requireNonNull(resource);

        return new File(resource.getFile());
    }
}
