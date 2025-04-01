package feature.moodTracking;

import java.util.Map;

public class MoodSummary {
    private Map<String, Integer> moodCounts;
    private double averageRating;
    private String mostFrequentMood;

    public MoodSummary(Map<String, Integer> moodCounts, double averageRating, String mostFrequentMood) {
        this.moodCounts = moodCounts;
        this.averageRating = averageRating;
        this.mostFrequentMood = mostFrequentMood;
    }

    public Map<String, Integer> getMoodCounts() {
        return moodCounts;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getMostFrequentMood() {
        return mostFrequentMood;
    }
}
