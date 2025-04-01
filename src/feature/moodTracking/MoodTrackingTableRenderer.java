package feature.moodTracking;

import java.util.List;

public final class MoodTrackingTableRenderer {
        public static final void renderMoodEntries(List<MoodEntry> entries) {
        System.out.println("+------------+--------+--------------+----------------------+");
        System.out.println("| Date       | Rating | Mood         | Notes                |");
        System.out.println("+------------+--------+--------------+----------------------+");
        for (MoodEntry entry : entries) {
            System.out.printf("| %-10s | %-6d | %-12s | %-20s |%n",
                    entry.getDate(), entry.getRating(), entry.getMood().getMoodName(),
                    entry.getNotes() == null ? "" : entry.getNotes());
        }
        System.out.println("+------------+--------+--------------+----------------------+");
    }
}
