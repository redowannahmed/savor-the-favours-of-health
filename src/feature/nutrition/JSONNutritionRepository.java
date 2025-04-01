package feature.nutrition;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import utils.iDataReader;

public class JSONNutritionRepository implements iNutritionRepository{
    private final String filePath;
    private final iDataReader dataReader;
    private final Map<String, FoodItem> foodDatabase;

    public JSONNutritionRepository(String filePath, iDataReader dataReader) throws IOException {
        this.filePath = filePath;
        this.dataReader = dataReader;
        this.foodDatabase = loadFoodDatabase();
    }

    private Map<String, FoodItem> loadFoodDatabase() throws IOException {
        String content = dataReader.readAll(filePath, String.class);
        if (content == null || content.isEmpty()) {
            return new HashMap<>();
        }
        Type type = new TypeToken<Map<String, FoodItem>>() {}.getType();
        return new Gson().fromJson(content, type);
    }

    @Override
    public FoodItem getNutritionByName(String foodName) {
        if (foodName == null) return null;
        return foodDatabase.get(foodName.toLowerCase());
    }
}
