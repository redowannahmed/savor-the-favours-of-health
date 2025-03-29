package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class txtDataWriter implements iDataWriter {
    @Override
    public <T> void writeAll(String filePath, T data) throws IOException {
        Path path = Path.of(filePath);
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }
        // Write the data's string representation to the file.
        Files.writeString(path, data.toString(), StandardCharsets.UTF_8);
    }
}