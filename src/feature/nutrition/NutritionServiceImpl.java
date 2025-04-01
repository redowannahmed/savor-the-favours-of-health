package feature.nutrition;

public class NutritionServiceImpl implements iNutritionService{
    private final iNutritionRepository nutritionRepository;

    public NutritionServiceImpl(iNutritionRepository nutritionRepository) {
        this.nutritionRepository = nutritionRepository;
    }

    @Override
    public FoodItem getFoodNutrition(String foodName) {
        return nutritionRepository.getNutritionByName(foodName);
    }
}
