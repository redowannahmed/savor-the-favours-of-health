package feature.nutrition;

import java.io.IOException;

import UI.iFeatureUI;
import feature.healthGoal.InputHandler;
import utils.JsonDataReader;
import utils.iDataReader;

public class NutritionTrackerUI implements iFeatureUI{
    private final iNutritionService nutritionService;
    private final InputHandler inputHandler;

    public NutritionTrackerUI(iNutritionService nutritionService) {
        this.nutritionService = nutritionService;
        this.inputHandler = new InputHandler();
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
            System.out.println("=== Nutritional Intake Tracker ===");
            System.out.println("1. View Food Nutritional Information");
            System.out.println("0. Return to Main Menu");
            String choice = inputHandler.readLine("Enter your choice: ");
            switch (choice) {
                case "1":
                    handleEnterFood();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    inputHandler.pause();
                    break;
            }
        }
    }

    private void handleEnterFood() {
        String foodName = inputHandler.readLine("Enter the name of the food item: ");
        FoodItem item = nutritionService.getFoodNutrition(foodName);
        if (item == null) {
            System.out.println("Food item not found in the database.");
        } else {
            System.out.println("Food item found:");
            System.out.println("Name: " + item.getName());
            System.out.println("Calories: " + item.getCalories());
            System.out.println("Protein: " + item.getProtein());
            System.out.println("Carbs: " + item.getCarbs());
            System.out.println("Fats: " + item.getFats());
            if (item.getVitamins() != null && !item.getVitamins().isEmpty()) {
                System.out.println("Vitamins: " + item.getVitamins());
            }
        }
        inputHandler.pause();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) {
        iDataReader dataReader = new JsonDataReader();
        // Specify the JSON file path for nutritional data.
        String filePath = "nutrition.json";
        try {
            // Instantiate the JSONNutritionRepository using the JSONDataReader.
            iNutritionRepository nutritionRepository = new JSONNutritionRepository(filePath, dataReader);
            // Create the service layer for nutrition.
            iNutritionService nutritionService = new NutritionServiceImpl(nutritionRepository);
            // Instantiate the UI (which implements iFeatureUI).
            iFeatureUI nutritionUI = new NutritionTrackerUI(nutritionService);
            nutritionUI.run();
        } catch (IOException e) {
            System.out.println("Error loading nutrition data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
