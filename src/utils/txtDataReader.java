package utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class txtDataReader implements iDataReader {
    @Override
    public <T> T readAll(String filePath, Type typeOfT) throws IOException {
        Path path = Path.of(filePath);
        if (!Files.exists(path) || Files.size(path) == 0) {
            return null;
        }
        String content = Files.readString(path, StandardCharsets.UTF_8);
        if (typeOfT.equals(String.class)) {
            return (T) content;
        }
        throw new UnsupportedOperationException("TxtDataReader supports only String type reading.");
    }
}
