package feature.nutrition;

import java.util.Map;

public class FoodItem {
    private String name;
    private double calories;
    private double protein;
    private double carbs;
    private double fats;
    // Optional: vitamins or micronutrients.
    private Map<String, Double> vitamins;

    // Default constructor required for Gson.
    public FoodItem() {}

    public FoodItem(String name, double calories, double protein, double carbs, double fats, Map<String, Double> vitamins) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.vitamins = vitamins;
    }

    public String getName() {
        return name;
    }
    public double getCalories() {
        return calories;
    }
    public double getProtein() {
        return protein;
    }
    public double getCarbs() {
        return carbs;
    }
    public double getFats() {
        return fats;
    }
    public Map<String, Double> getVitamins() {
        return vitamins;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCalories(double calories) {
        this.calories = calories;
    }
    public void setProtein(double protein) {
        this.protein = protein;
    }
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }
    public void setFats(double fats) {
        this.fats = fats;
    }
    public void setVitamins(Map<String, Double> vitamins) {
        this.vitamins = vitamins;
    }
}
