package feature.healthGoal;

import java.util.List;
import colorUtils.*;

public class TableRenderer {
    public static final void renderGoalsTable(List<DailyGoal> goals) {
        // Border templates (without color)
        String topBorder    = "┌──────┬────────────────────────────────┬──────────────┐";
        String divider      = "├──────┼────────────────────────────────┼──────────────┤";
        String bottomBorder = "└──────┴────────────────────────────────┴──────────────┘";
        
        // Print colored borders
        System.out.println(ColorUtil.applyBorder(topBorder));
        
        // Header - apply color after formatting
        String header = String.format("│ %-4s │ %-30s │ %-12s │", 
                                    "No.", 
                                    "Description", 
                                    "Status");
        System.out.println(ColorUtil.applyHeader(header));
        
        System.out.println(ColorUtil.applyBorder(divider));
        
        // Table Content
        for (int i = 0; i < goals.size(); i++) {
            DailyGoal goal = goals.get(i);
            String statusText = goal.getStatus().getStatusName();
            
            // Format first without colors
            String row = String.format("│ %-4d │ %-30s │ %-12s │", 
                                     i + 1, 
                                     goal.getDescription(),
                                     statusText);
            
            // Then apply colors by replacing plain text with colored versions
            row = row.replace(goal.getDescription(), ColorUtil.applyOption(goal.getDescription()))
                   .replace(statusText, getStatusColor(statusText));
            
            System.out.println(row);
        }
        
        System.out.println(ColorUtil.applyBorder(bottomBorder));
    }
    
    private static String getStatusColor(String status) {
        switch (status.toLowerCase()) {
            case "done":
                return ColorUtil.applySuccess(status);
            case "in progress":
                return ColorUtil.applyHighlight(status); 
            case "pending":
                return ColorUtil.applyBanner(status); 
            default:
                return ColorUtil.applyProgress(status); 
        }
    }
}