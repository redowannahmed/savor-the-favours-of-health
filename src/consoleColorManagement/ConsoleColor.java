package consoleColorManagement;

public class ConsoleColor {
    private final String ansiCode;

    ConsoleColor(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    String getAnsiCode() {
        return ansiCode;
    }
}
