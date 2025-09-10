package fr.axopen.template-java.utils;

import fr.axopen.template-java.exception.ErrorEnum;
import fr.axopen.template-java.exception.FonctionalException;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {

    private FileHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static Path createPath(String root, String path) throws FonctionalException {
        // Get the file path and normalize it
        Path filePath = Paths.get(root, path).normalize().toAbsolutePath();

        // Get the root path and normalize it
        Path rootPath = Paths.get(root).toAbsolutePath();

        // Compare both absolute paths and check that the file path is inside the root path
        // It prevents from creating files outside the current working directory
        if (!filePath.startsWith(rootPath)) {
            throw new FonctionalException(ErrorEnum.PATH_TRANSVERSAL_DETECTED);
        }

        return filePath;
    }

    public static String readFileString(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw FonctionalException.format(e, ErrorEnum.ERROR_DURING_READ_FILE, path.toString());
        }
    }

    public static byte[] readFileBytes(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw FonctionalException.format(e, ErrorEnum.ERROR_DURING_READ_FILE, path.toString());
        }
    }

    public static InputStreamResource readFile(Path path) {
        try (InputStream inputStream = Files.newInputStream(path)) {
            return new InputStreamResource(inputStream);
        } catch (IOException e) {
            throw FonctionalException.format(e, ErrorEnum.ERROR_DURING_READ_FILE, path.toString());
        }
    }

}
