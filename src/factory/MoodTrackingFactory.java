package factory;

import UI.iFeatureUI;
import exceptions.FeatureInitializationException;
import feature.moodTracking.MoodServiceImpl;
import feature.moodTracking.MoodTrackerController;
import feature.moodTracking.MoodTrackerUI;
import feature.moodTracking.TxtMoodRepository;
import feature.moodTracking.iMoodRepository;
import feature.moodTracking.iMoodService;
import utils.iDataReader;
import utils.iDataWriter;
import utils.txtDataReader;
import utils.txtDataWriter;

public class MoodTrackingFactory implements iFeatureFactory{
    @Override
    public iFeatureUI create() throws FeatureInitializationException 
    {
        try
        {
            iDataReader dataReader = new txtDataReader();
            iDataWriter dataWriter = new txtDataWriter();
            String filePath = "moodtracker.txt";
            iMoodRepository moodRepository = new TxtMoodRepository(filePath, dataReader, dataWriter);
            iMoodService moodService = new MoodServiceImpl(moodRepository);
            MoodTrackerController controller = new MoodTrackerController(moodService);
            return new MoodTrackerUI(controller);
        } catch (Exception e)
        {
            throw new FeatureInitializationException("Failed to initialize Mood tracking feature", e);
        }
    }
}
