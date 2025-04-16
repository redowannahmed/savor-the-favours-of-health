package feature.moodTracking;

import java.util.List;
import java.util.Map;

import colorUtils.ColorUtil;

public final class MoodTrackingTableRenderer {
    public static final void renderMoodEntries(List<MoodEntry> entries) {
        String topBorder = "┌────────────┬────────┬──────────────┬──────────────────────┐";
        String divider = "├────────────┼────────┼──────────────┼──────────────────────┤";
        String bottomBorder = "└────────────┴────────┴──────────────┴──────────────────────┘";
        
        System.out.println(ColorUtil.applyBorder(topBorder));
        
        String header = String.format("│ %-10s │ %-6s │ %-12s │ %-20s │",
                                    "Date",
                                    "Rating",
                                    "Mood",
                                    "Notes");
        System.out.println(ColorUtil.applyHeader(header));
        System.out.println(ColorUtil.applyBorder(divider));
        
        for (MoodEntry entry : entries) {
            String row = String.format("│ %-10s │ %-6d │ %-12s │ %-20s │",
                                    entry.getDate(),
                                    entry.getRating(),
                                    entry.getMood().getMoodName(),
                                    entry.getNotes() == null ? "" : entry.getNotes());
            
            row = row.replace(entry.getMood().getMoodName(), ColorUtil.applyHighlight(entry.getMood().getMoodName()))
                .replace(entry.getNotes() == null ? "" : entry.getNotes(), 
                        ColorUtil.applyOption(entry.getNotes() == null ? "" : entry.getNotes()));
            
            System.out.println(row);
        }
        
        System.out.println(ColorUtil.applyBorder(bottomBorder));
    }

    public static final void renderSummaryTable(Map<String, Integer> data) {

        final int MOOD_WIDTH = 20;
        final int DAYS_WIDTH = 5;
        
        String topBorder = "┌──────────────────────┬───────┐";
        String divider = "├──────────────────────┼───────┤";
        String bottomBorder = "└──────────────────────┴───────┘";
        
        System.out.println(ColorUtil.applyBorder(topBorder));
        
        String header = String.format("│ %-" + MOOD_WIDTH + "s │ %-" + DAYS_WIDTH + "s │",
                                    "Mood", "Days");
        System.out.println(ColorUtil.applyHeader(header));
        System.out.println(ColorUtil.applyBorder(divider));
        
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String mood = entry.getKey();
            String days = String.valueOf(entry.getValue());
            
            int moodVisibleLength = mood.length();
            int daysVisibleLength = days.length();
            

            String coloredMood = ColorUtil.applyOption(mood);
            String coloredDays = ColorUtil.applyOption(days);
            
            String row = String.format("│ %-" + (MOOD_WIDTH + (coloredMood.length()-moodVisibleLength)) + "s │ %-" + (DAYS_WIDTH + (coloredDays.length()-daysVisibleLength)) + "s │",
                                     coloredMood, coloredDays);
            
            System.out.println(row);
        }
        
        System.out.println(ColorUtil.applyBorder(bottomBorder));
    }

}
