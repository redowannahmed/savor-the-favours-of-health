package feature.sleep;

import java.time.LocalDate;

public interface iSleepAnalyzer {
    void recordSleepData(LocalDate date, int hoursSlept, String notes);

    SleepReport analyzeSleepData (LocalDate start, LocalDate end);
}
