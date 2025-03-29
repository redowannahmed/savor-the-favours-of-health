package feature.sleep;

public class SleepAnalysisResult {
    private double avgSleepDuration;
    private String progressBar;
    private String recommendation;

    public SleepAnalysisResult(double avgSleepDuration, String progressBar, String recommendation) {
        this.avgSleepDuration = avgSleepDuration;
        this.progressBar = progressBar;
        this.recommendation = recommendation;
    }

    public double getAvgSleepDuration() {
        return avgSleepDuration;
    }

    public String getProgressBar() {
        return progressBar;
    }

    public String getRecommendation() {
        return recommendation;
    }
}
