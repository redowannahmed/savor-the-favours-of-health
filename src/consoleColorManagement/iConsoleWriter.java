package consoleColorManagement;

public interface iConsoleWriter {
    void write(String message, ConsoleColor color);
    void writeLine(String message, ConsoleColor color);
    void reset();
}
