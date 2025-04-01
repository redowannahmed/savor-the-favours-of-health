package feature.moodTracking.moodTrackUI;

import java.util.Scanner;

public class InputProcessor {
    private final Scanner scanner;

    public InputProcessor() {
        scanner = new Scanner(System.in);
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                String input = readLine(prompt);
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                print("Invalid number. Please try again.");
            }
        }
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void pause() {
        readLine("Press Enter to continue...");
    }
}
