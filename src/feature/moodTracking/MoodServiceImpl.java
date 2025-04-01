package feature.moodTracking;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoodServiceImpl implements iMoodService{
    private final iMoodRepository moodRepository;

    public MoodServiceImpl(iMoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void logMoodEntry(MoodEntry entry) {
        moodRepository.addEntry(entry);
    }

    @Override
    public List<MoodEntry> getEntriesSince(LocalDate startDate) {
        return moodRepository.getEntriesSince(startDate);
    }

    @Override
    public MoodSummary analyzeMoodEntries(List<MoodEntry> entries) {
        if (entries == null || entries.isEmpty()) {
            return new MoodSummary(new HashMap<>(), 0, "N/A");
        }
        int totalRating = 0;
        Map<String, Integer> counts = new HashMap<>();
        for (MoodEntry entry : entries) {
            totalRating += entry.getRating();
            String moodName = entry.getMood().getMoodName();
            counts.put(moodName, counts.getOrDefault(moodName, 0) + 1);
        }
        double avg = (double) totalRating / entries.size();
        String mostFrequent = "N/A";
        int maxCount = 0;
        for (Map.Entry<String, Integer> e : counts.entrySet()) {
            if (e.getValue() > maxCount) {
                maxCount = e.getValue();
                mostFrequent = e.getKey();
            }
        }
        return new MoodSummary(counts, avg, mostFrequent);
    }
}
