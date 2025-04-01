package feature.moodTracking;

import java.time.LocalDate;
import java.util.List;

public interface iMoodService {
    void logMoodEntry(MoodEntry entry);
    MoodSummary analyzeMoodEntries(List<MoodEntry> entries);
    List<MoodEntry> getEntriesSince(LocalDate startDate);
    List<MoodEntry> getEntriesForDate(LocalDate date);
}
