package feature.moodTracking;

import UI.InputProcessor;
import UI.iCommand;
import colorUtils.ColorUtil;
import utils.ClearScreen;

public class ViewSummaryCommand implements iCommand{
    private final MoodTrackerController controller;
    private final InputProcessor inputProcessor;

    public ViewSummaryCommand(MoodTrackerController controller, InputProcessor inputProcessor) {
        this.controller = controller;
        this.inputProcessor = inputProcessor;
    }

    @Override
    public void execute() {
        ClearScreen.getInstance().clearScreen();
        MoodSummary summary = controller.getMoodSummary();
        inputProcessor.print(ColorUtil.applySubHeader("=== Mood Summary for Last 4 Weeks ==="));
        System.out.println();
        if (summary.getAverageRating() == 0) {
            inputProcessor.print(ColorUtil.applyImportantNote("No mood data available."));
        } else {
        inputProcessor.print(ColorUtil.applyNote("Average Rating: ") + 
                        ColorUtil.applyBoldBrightWhite(String.format("%.2f", summary.getAverageRating())));

        inputProcessor.print(ColorUtil.applyNote("Most frequent mood: ") + 
                        ColorUtil.applyBoldBrightWhite(summary.getMostFrequentMood()));
                        
            System.out.println();
            inputProcessor.print(ColorUtil.applyNote("Mood Counts:"));
            MoodTrackingTableRenderer.renderSummaryTable(summary.getMoodCounts());
        }
        inputProcessor.pause();
    }
}
