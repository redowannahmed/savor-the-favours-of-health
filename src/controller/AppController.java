package controller;
import java.util.List;
import java.util.Scanner;

import UI.FeatureUI;

public class AppController {
    private final List<FeatureUI> features;

    public AppController(List<FeatureUI> features) {
        this.features = features;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            clearScreen();
            System.out.println("===== MAIN MENU =====");
            for (int i = 0; i < features.size(); i++) {
                System.out.println((i + 1) + ". " + features.get(i).getTitle());
            }
            System.out.println("0. Exit Application");
            System.out.print("Choose an option: ");

            String choice = sc.nextLine();
            int option;
            try {
                option = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                option = -1;
            }

            if (option == 0) {
                running = false;
            } else if (option > 0 && option <= features.size()) {
                features.get(option - 1).run();
            } else {
                System.out.println("Invalid option, try again.");
                System.out.println("Press Enter to continue...");
                sc.nextLine();
            }
        }

        System.out.println("Exiting application...");
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
