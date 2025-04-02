package feature.aiQuery;

import UI.*;

public class AIHealthQueryUI extends AbstractFeatureUI{
    private final iAIHealthQuery aiService;

    public AIHealthQueryUI(iAIHealthQuery aiService) {
        this.aiService = aiService;
    }

    @Override
    public String getTitle() {
        return "AI Health Query";
    }

    @Override
    public void run() {
        boolean inFeature = true;
        while (inFeature) {
            clearScreen();
            System.out.println("=== " + getTitle() + " ===");
            System.out.println("1. Enter a health query");
            System.out.println("0. Return to Main Menu");
            
            String choice = inputProcessor.readLine("Choose an option: ");
            
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
                pause();
            }
        }
    }

    private void handleQuery() {
        ClearScreen.clearScreen();
        String userQuery = inputProcessor.readLine("Enter your health-related query: ");
        System.out.println("Fetching response from AI...");
        String aiResponse = aiService.getAIResponse(userQuery);
        System.out.println("\n\u001B[36m" + aiResponse + "\u001B[0m"); 
    }

    public static void main(String[] args) {
        iAIHealthQuery aiHealthQuery = new MistralAIHealthQueryService("ethHhWEx4lbnwSQYWjPO1S6ESSl1YvT1", "https://api.mistral.ai/v1/chat/completions");
        AIHealthQueryUI aiHealthQueryUI = new AIHealthQueryUI(aiHealthQuery);
        aiHealthQueryUI.run();
    }

}
