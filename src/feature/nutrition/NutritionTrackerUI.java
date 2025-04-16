package feature.nutrition;


import UI.AbstractFeatureUI;

import java.util.*;
import UI.iCommand;
import colorUtils.ColorUtil;

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
    
    public String getTitle() {
        return "Nutritional Intake Tracker";
    }
    
    @Override
    public void run() {
        boolean exit = false;
        while (!exit) {
            clearScreen();
            printMenu();
            String choice = inputProcessor.readLine(ColorUtil.applyNote("Enter your choice: "));
            if ("0".equals(choice)) {
                exit = true;
            } else {
                iCommand command = commandRegistry.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    inputProcessor.print(ColorUtil.applyError("Invalid choice. Try again."));
                    pause();
                }
            }
        }
    }
    
    private void printMenu() {
        inputProcessor.print(ColorUtil.applySubHeader("=== " + getTitle() + " ==="));
        System.out.println();
        inputProcessor.print("1. View Food Nutritional Information");
        inputProcessor.print("0. Return to Main Menu");
        System.out.println();
    }
}
