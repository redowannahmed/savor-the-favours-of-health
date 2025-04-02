package factory;

import UI.iFeatureUI;
import exceptions.FeatureInitializationException;
import feature.sleep.DefaultProgressBarRenderer;
import feature.sleep.SleepAnalysisUI;
import feature.sleep.SleepAnalyzer;
import feature.sleep.TxtSleepDataRepository;
import feature.sleep.iProgressBarRenderer;
import feature.sleep.iSleepAnalyzer;
import feature.sleep.iSleepRepository;
import utils.iDataReader;
import utils.iDataWriter;
import utils.txtDataReader;
import utils.txtDataWriter;

public class SleepDataAnalysisFactory implements iFeatureFactory{
    @Override
    public iFeatureUI create() throws FeatureInitializationException
    {
        try 
        {
            iDataReader dataReader = new txtDataReader();
            iDataWriter dataWriter = new txtDataWriter();
            iProgressBarRenderer progressRenderer = new DefaultProgressBarRenderer();
            iSleepRepository sleepRepository = new TxtSleepDataRepository("sleep.txt", dataReader, dataWriter);
            iSleepAnalyzer sleepAnalyzer = new SleepAnalyzer(progressRenderer);
            return new SleepAnalysisUI(sleepRepository, sleepAnalyzer);
        } catch (Exception e)
        {
            throw new FeatureInitializationException("failed to initialize Sleep data analysis feature", e);
        }
    }
}
