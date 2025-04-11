package utils;

public class ClearScreen {
    private static final ClearScreen instance = new ClearScreen();

    private ClearScreen() {}

    public static ClearScreen getInstance() {
        return instance;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
