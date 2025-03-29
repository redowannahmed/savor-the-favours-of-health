package utils;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class JsonDataReader implements iDataReader{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public <T> T readAll (String filePath, Type typeOfT) throws IOException {
        Path path = Path.of(filePath);

        if (!Files.exists(path) || Files.size(path) == 0)
        {
            return null;
        }

        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8))
        {
            return GSON.fromJson(reader, typeOfT);
        } catch (JsonSyntaxException e)
        {
            throw new IOException("Error parsing json file: " + e.getMessage());
        }
    }
}
