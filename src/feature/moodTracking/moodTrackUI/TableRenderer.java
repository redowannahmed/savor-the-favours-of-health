package feature.moodTracking.moodTrackUI;

import java.util.Map;

public class TableRenderer {
        public void renderSummaryTable(Map<String, Integer> moodCounts) {
        System.out.println("+----------------------+-------+");
        System.out.println("| Mood                 | Days  |");
        System.out.println("+----------------------+-------+");
        for (Map.Entry<String, Integer> entry : moodCounts.entrySet()) {
            System.out.printf("| %-20s | %-5d |%n", entry.getKey(), entry.getValue());
        }
        System.out.println("+----------------------+-------+");
    }
}
