package consoleColorManagement;

public class AnsiConsoleWriter implements iConsoleWriter{
    @Override
    public void write(String message, ConsoleColor color) {
        System.out.print(color.getAnsiCode() + message);
    }

    @Override
    public void writeLine(String message, ConsoleColor color) {
        System.out.println(color.getAnsiCode() + message + ConsoleColors.RESET.getAnsiCode());
    }

    @Override
    public void reset() {
        System.out.print(ConsoleColors.RESET.getAnsiCode());
    }
}
