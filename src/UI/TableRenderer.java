package UI;

import java.util.List;
import java.util.Map;

public class TableRenderer {
    // Existing method for rendering summary tables
    public void renderSummaryTable(Map<String, Integer> data) {
        System.out.println("+----------------------+-------+");
        System.out.println("| Mood                 | Days  |");
        System.out.println("+----------------------+-------+");
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            System.out.printf("| %-20s | %-5d |%n", entry.getKey(), entry.getValue());
        }
        System.out.println("+----------------------+-------+");
    }
    
    // New method for rendering a table of MoodEntries

}