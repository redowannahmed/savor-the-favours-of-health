package feature.aiQuery;

import UI.*;
import colorUtils.ColorUtil;

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
            System.out.println(ColorUtil.applyHeader("=== " + getTitle() + " ==="));
            System.out.println();

            System.out.println(ColorUtil.applyOption("1. Enter a health query"));
            System.out.println(ColorUtil.applyOption("0. Return to Main Menu"));
            System.out.println();

            String choice = inputProcessor.readLine(ColorUtil.applyNote("Choose an option: "));
            
            switch (choice) {
                case "1":
                    handleQuery();
                    break;
                case "0":
                    inFeature = false; // exit this feature
                    break;
                default:
                    System.out.println(ColorUtil.applyError("Invalid choice. Please try again."));
            }
            
            if (inFeature) {
                pause();
            }
        }
    }

    private void handleQuery() {
        clearScreen();
        String userQuery = inputProcessor.readLine(ColorUtil.applyOption("Enter your health-related query: "));
        System.out.println();
        System.out.println(ColorUtil.applyHighlight("Fetching response from AI..."));
        System.out.println();
        
        String aiResponse = aiService.getAIResponse(userQuery);
        System.out.println(ColorUtil.applyNote(aiResponse)); 
    }
}
