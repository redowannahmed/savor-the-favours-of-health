package feature.moodTracking;

import java.time.LocalDate;
import java.util.List;

public interface iMoodRepository {
    List<MoodEntry> getAllEntries();
    void addEntry(MoodEntry entry);
    List<MoodEntry> getEntriesSince(LocalDate startDate);
}
