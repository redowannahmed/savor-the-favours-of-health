package feature.moodTracking;

import java.time.LocalDate;
import java.util.List;

public class MoodTrackerController {
    private final iMoodService moodService;

    public MoodTrackerController(iMoodService moodService) {
        this.moodService = moodService;
    }

    public void logMoodEntry(int rating, Mood mood, String notes) {
        MoodEntry entry = new MoodEntry(LocalDate.now(), rating, mood, notes);
        moodService.logMoodEntry(entry);
    }

    public MoodSummary getMoodSummary() {
        LocalDate fourWeeksAgo = LocalDate.now().minusWeeks(4);
        List<MoodEntry> entries = moodService.getEntriesSince(fourWeeksAgo);
        return moodService.analyzeMoodEntries(entries);
    }
}
