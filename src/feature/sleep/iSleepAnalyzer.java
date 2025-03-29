package feature.sleep;
import java.util.List;

public interface iSleepAnalyzer {
    SleepAnalysisResult analyzeSleepTrend(List<SleepSession> sessions);
}
