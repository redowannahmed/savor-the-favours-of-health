package utils;

import java.lang.reflect.Type;
import java.io.IOException;

public interface iDataReader {
    <T> T readAll(String filePath, Type typeOfT) throws IOException;
}
