package feature.nutrition;

import UI.InputProcessor;
import UI.iCommand;
import colorUtils.ColorUtil;
import utils.ClearScreen;

public class ViewFoodNutritionCommand implements iCommand{
    private final iNutritionService nutritionService;
    private final InputProcessor inputProcessor;
    
    public ViewFoodNutritionCommand(iNutritionService nutritionService, InputProcessor inputProcessor) {
        this.nutritionService = nutritionService;
        this.inputProcessor = inputProcessor;
    }
    
    @Override
    public void execute() {
        ClearScreen.getInstance().clearScreen();
        String foodName = inputProcessor.readLine(ColorUtil.applyNote("Enter the name of the food item: "));
        System.out.println();
        FoodItem item = nutritionService.getFoodNutrition(foodName);
        if (item == null) {
            inputProcessor.print(ColorUtil.applyCaution("Food item not found in the database."));
        } else {
            inputProcessor.print(ColorUtil.applySuccess("Food item found:"));
            System.out.println();
            inputProcessor.print(ColorUtil.applyOption("Name: ") + item.getName());
            inputProcessor.print(ColorUtil.applyOption("Calories: ") + item.getCalories());
            inputProcessor.print(ColorUtil.applyOption("Protein: ") + item.getProtein());
            inputProcessor.print(ColorUtil.applyOption("Carbs: ") + item.getCarbs());
            inputProcessor.print(ColorUtil.applyOption("Fats: ") + item.getFats());
            if (item.getVitamins() != null && !item.getVitamins().isEmpty()) {
                inputProcessor.print(ColorUtil.applyOption("Vitamins: ") + item.getVitamins());
                System.out.println();
            }
        }
        inputProcessor.pause();
    }
}
