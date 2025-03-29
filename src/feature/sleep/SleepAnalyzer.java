package feature.sleep;

import java.util.List;

public class SleepAnalyzer implements iSleepAnalyzer {
    private static final double TARGET_SLEEP_HOURS = 8.0;
    private static final int BAR_LENGTH = 20;
    
    private final iProgressBarRenderer progressBarRenderer;

    // Inject the progress bar renderer via the constructor.
    public SleepAnalyzer(iProgressBarRenderer progressBarRenderer) {
        this.progressBarRenderer = progressBarRenderer;
    }

    @Override
    public SleepAnalysisResult analyzeSleepTrend(List<SleepSession> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            return new SleepAnalysisResult(0, "", "No sleep data available.");
        }
        double totalHours = 0;
        for (SleepSession session : sessions) {
            totalHours += session.getSleepHours();
        }
        double avg = totalHours / sessions.size();
        String progressBar = progressBarRenderer.renderProgressBar(avg, TARGET_SLEEP_HOURS, BAR_LENGTH);
        String recommendation = (avg < TARGET_SLEEP_HOURS)
                ? "Try to get at least " + String.format("%.1f", TARGET_SLEEP_HOURS - avg) + " more hours of sleep."
                : "Great! You're meeting your sleep goal.";
        return new SleepAnalysisResult(avg, progressBar, recommendation);
    }

}
