import controller.AppController;
import factory.AIHealthQueryFactory;
import factory.DailyHealthGoalsFactory;
import factory.FeatureRegistry;
import factory.MoodTrackingFactory;
import factory.NutritionTrackerFactory;
import factory.SleepDataAnalysisFactory;

public class Main {
    public static void main(String[] args) {
        FeatureRegistry registry = new FeatureRegistry();
        
        registry.registerFeature("Daily Health Goals", new DailyHealthGoalsFactory());
        registry.registerFeature("Log sleep information", new SleepDataAnalysisFactory());
        registry.registerFeature("AI health query", new AIHealthQueryFactory());
        registry.registerFeature("Daily Mood Logger", new MoodTrackingFactory());
        registry.registerFeature("Nutrition tracker", new NutritionTrackerFactory());

        AppController app = new AppController(registry);
        app.run();
    }
}
