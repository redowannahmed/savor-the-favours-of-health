package factory;

import java.io.IOException;

import UI.iFeatureUI;
import exceptions.FeatureInitializationException;
import feature.nutrition.JSONNutritionRepository;
import feature.nutrition.NutritionServiceImpl;
import feature.nutrition.NutritionTrackerUI;
import feature.nutrition.iNutritionRepository;
import feature.nutrition.iNutritionService;
import utils.JsonDataReader;
import utils.iDataReader;

public class NutritionTrackerFactory implements iFeatureFactory {
    @Override
    public iFeatureUI create() throws FeatureInitializationException {
        try {
            iDataReader dataReader = new JsonDataReader();
            String filePath = "nutrition.json";
            try {
                iNutritionRepository nutritionRepository = new JSONNutritionRepository(filePath, dataReader);
                iNutritionService nutritionService = new NutritionServiceImpl(nutritionRepository);
                return new NutritionTrackerUI(nutritionService);
            } catch (IOException e) {
                throw new FeatureInitializationException("Failed to initialize nutrition repository", e);
            }
        } catch (Exception e) {
            throw new FeatureInitializationException("Failed to initialize nutrition tracker feature", e);
        }
    }
}
