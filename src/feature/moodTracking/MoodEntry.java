package feature.moodTracking;

import java.time.LocalDate;

public class MoodEntry {
    private LocalDate date;
    private int rating; // 1 (worst) to 5 (best)
    private Mood mood;
    private String notes; // Optional additional notes

    public MoodEntry(LocalDate date, int rating, Mood mood, String notes) {
        this.date = date;
        this.rating = rating;
        this.mood = mood;
        this.notes = notes;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getRating() {
        return rating;
    }

    public Mood getMood() {
        return mood;
    }

    public String getNotes() {
        return notes;
    }
}
