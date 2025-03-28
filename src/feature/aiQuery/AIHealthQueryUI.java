package feature.aiQuery;
import java.util.Scanner;

import UI.iFeatureUI;

public class AIHealthQueryUI implements iFeatureUI{
    private final iAIHealthQuery aiService;

    public AIHealthQueryUI (iAIHealthQuery aiService)
    {
        this.aiService = aiService;
    }

    @Override
    public String getTitle ()
    {
        return "AI health query";
    }

    @Override
    public void run()
    {
        Scanner sc = new Scanner(System.in);

        boolean inFeature = true;

        while (inFeature)
        {
            clearScreen();
            System.out.println("=== AI Health Query ===");
            System.out.println("1. Enter a health query");
            System.out.println("0. Return to Main Menu");
            System.out.print("Choose an option: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    handleQuery();
                    break;
                case "0":
                    inFeature = false; // exit this feature
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (inFeature) {
                System.out.println("Press Enter to continue...");
                sc.nextLine();
            }
        }
    }

    private void handleQuery() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your health-related query: ");
        String userQuery = sc.nextLine();

        System.out.println("Fetching response from AI...");
        String aiResponse = aiService.getAIResponse(userQuery);

        System.out.println("\n\u001B[36m" + aiResponse + "\u001B[0m"); 
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
