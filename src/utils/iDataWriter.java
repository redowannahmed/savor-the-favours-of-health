package utils;

import java.io.IOException;

public interface iDataWriter {
   <T> void writeAll(String filePath, T data) throws IOException;
}
