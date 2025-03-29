package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonDataWriter implements iDataWriter{
        private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    @Override
    public <T> void writeAll(String filePath, T data) throws IOException {
        Path path = Path.of(filePath);
        
        if (path.getParent() !=null)
        {
            Files.createDirectories(path.getParent());
        }

        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8))
        {
            GSON.toJson(data, writer);
        }
    }
}
