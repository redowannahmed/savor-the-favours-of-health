package feature.nutrition;


import UI.AbstractFeatureUI;

import java.io.IOException;
import java.util.*;
import UI.iCommand;
import UI.iFeatureUI;
import utils.JsonDataReader;
import utils.iDataReader;

public class NutritionTrackerUI extends AbstractFeatureUI{
    private final iNutritionService nutritionService;
    private final Map<String, iCommand> commandRegistry;

    public NutritionTrackerUI(iNutritionService nutritionService) {
        this.nutritionService = nutritionService;
        this.commandRegistry = new HashMap<>();
        registerCommands();
    }
    
    private void registerCommands() {
        commandRegistry.put("1", new ViewFoodNutritionCommand(nutritionService, inputProcessor));
    }
    
    @Override
    public String getTitle() {
        return "Nutritional Intake Tracker";
    }
    
    @Override
    public void run() {
        boolean exit = false;
        while (!exit) {
            clearScreen();
            printMenu();
            String choice = inputProcessor.readLine("Enter your choice: ");
            if ("0".equals(choice)) {
                exit = true;
            } else {
                iCommand command = commandRegistry.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    inputProcessor.print("Invalid choice. Try again.");
                    pause();
                }
            }
        }
    }
    
    private void printMenu() {
        inputProcessor.print("=== " + getTitle() + " ===");
        inputProcessor.print("1. View Food Nutritional Information");
        inputProcessor.print("0. Return to Main Menu");
    }

    public static void main(String[] args) {
        iDataReader dataReader = new JsonDataReader();
        String filePath = "nutrition.json";
        try {
            iNutritionRepository nutritionRepository = new JSONNutritionRepository(filePath, dataReader);
            iNutritionService nutritionService = new NutritionServiceImpl(nutritionRepository);
            iFeatureUI nutritionUI = new NutritionTrackerUI(nutritionService);
            nutritionUI.run();
        } catch (IOException e) {
            System.out.println("Error loading nutrition data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
