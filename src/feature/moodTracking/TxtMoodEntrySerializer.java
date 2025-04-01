package feature.moodTracking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TxtMoodEntrySerializer implements iMoodEntrySerializer{
        private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public String serialize(MoodEntry entry) {
        // Format: date | rating | moodName | notes
        String notes = (entry.getNotes() == null) ? "" : entry.getNotes();
        return entry.getDate().format(formatter) + " | " + entry.getRating() + " | " +
               entry.getMood().getMoodName() + " | " + notes;
    }

    @Override
    public MoodEntry deserialize(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            return null;
        }
        try {
            LocalDate date = LocalDate.parse(parts[0], formatter);
            int rating = Integer.parseInt(parts[1]);
            String moodName = parts[2];
            Mood mood;
            switch (moodName.toLowerCase()) {
                case "happy":
                    mood = new HappyMood();
                    break;
                case "sad":
                    mood = new SadMood();
                    break;
                case "angry":
                    mood = new AngryMood();
                    break;
                default:
                    // Default fallback, you could throw an exception or choose a default mood.
                    mood = new HappyMood();
                    break;
            }
            String notes = (parts.length >= 4) ? parts[3] : "";
            return new MoodEntry(date, rating, mood, notes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
