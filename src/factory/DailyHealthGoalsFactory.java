package factory;

import UI.iFeatureUI;
import exceptions.FeatureInitializationException;
import feature.healthGoal.DailyHealthGoalsController;
import feature.healthGoal.DailyHealthGoalsUI;
import feature.healthGoal.GoalServiceImpl;
import feature.healthGoal.TxtGoalRepository;
import feature.healthGoal.iGoalRepository;
import feature.healthGoal.iGoalService;
import utils.iDataReader;
import utils.iDataWriter;
import utils.txtDataReader;
import utils.txtDataWriter;

public class DailyHealthGoalsFactory implements iFeatureFactory{
        @Override
    public iFeatureUI create() throws FeatureInitializationException {
        try {
            iDataReader dataReader = new txtDataReader();
            iDataWriter dataWriter = new txtDataWriter();
            String filePath = "healthGoals.txt";
            iGoalRepository goalRepository = new TxtGoalRepository(filePath, dataReader, dataWriter);
            iGoalService goalService = new GoalServiceImpl(goalRepository);
            DailyHealthGoalsController controller = new DailyHealthGoalsController(goalService);
            return new DailyHealthGoalsUI(controller);
        } catch (Exception e) {
            throw new FeatureInitializationException("Failed to initialize Daily Goals feature", e);
        }
    }
}
