package feature.moodTracking;

import UI.InputProcessor;
import UI.iCommand;
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
        inputProcessor.print("=== Mood Summary for Last 4 Weeks ===");
        if (summary.getAverageRating() == 0) {
            inputProcessor.print("No mood data available.");
        } else {
            inputProcessor.print("Average Rating: " + String.format("%.2f", summary.getAverageRating()));
            inputProcessor.print("Most Frequent Mood: " + summary.getMostFrequentMood());
            inputProcessor.print("Mood Counts:");
            MoodTrackingTableRenderer.renderSummaryTable(summary.getMoodCounts());
        }
        inputProcessor.pause();
    }
}
