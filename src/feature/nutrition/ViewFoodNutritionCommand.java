package feature.nutrition;

import UI.InputProcessor;
import UI.iCommand;
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
        String foodName = inputProcessor.readLine("Enter the name of the food item: ");
        FoodItem item = nutritionService.getFoodNutrition(foodName);
        if (item == null) {
            inputProcessor.print("Food item not found in the database.");
        } else {
            inputProcessor.print("Food item found:");
            inputProcessor.print("Name: " + item.getName());
            inputProcessor.print("Calories: " + item.getCalories());
            inputProcessor.print("Protein: " + item.getProtein());
            inputProcessor.print("Carbs: " + item.getCarbs());
            inputProcessor.print("Fats: " + item.getFats());
            if (item.getVitamins() != null && !item.getVitamins().isEmpty()) {
                inputProcessor.print("Vitamins: " + item.getVitamins());
            }
        }
        inputProcessor.pause();
    }
}
