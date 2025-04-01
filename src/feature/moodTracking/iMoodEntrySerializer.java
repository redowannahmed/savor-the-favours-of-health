package feature.moodTracking;

public interface iMoodEntrySerializer {
    String serialize(MoodEntry entry);
    MoodEntry deserialize(String line);
}
