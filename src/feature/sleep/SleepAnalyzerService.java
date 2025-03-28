package feature.sleep;

import java.time.LocalDate;

public class SleepAnalyzerService implements iSleepAnalyzer{

    SleepReport report = new SleepReport();

    SleepAnalyzerService (SleepReport report)
    {
        this.report = report;
    }

    @Override
    public void recordSleepData(LocalDate date, int hoursSlept, String notes) {

    }

    @Override
    public SleepReport analyzeSleepData(LocalDate start, LocalDate end)
    {
        return null;
    }

    
}
