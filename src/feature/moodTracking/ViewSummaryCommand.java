package feature.moodTracking;

import UI.InputProcessor;
import UI.iCommand;
import UI.TableRenderer;

public class ViewSummaryCommand implements iCommand{
    private final MoodTrackerController controller;
    private final TableRenderer tableRenderer;
    private final InputProcessor inputProcessor;

    public ViewSummaryCommand(MoodTrackerController controller, TableRenderer tableRenderer, InputProcessor inputProcessor) {
        this.controller = controller;
        this.tableRenderer = tableRenderer;
        this.inputProcessor = inputProcessor;
    }

    @Override
    public void execute() {
        MoodSummary summary = controller.getMoodSummary();
        inputProcessor.print("=== Mood Summary for Last 4 Weeks ===");
        if (summary.getAverageRating() == 0) {
            inputProcessor.print("No mood data available.");
        } else {
            inputProcessor.print("Average Rating: " + String.format("%.2f", summary.getAverageRating()));
            inputProcessor.print("Most Frequent Mood: " + summary.getMostFrequentMood());
            inputProcessor.print("Mood Counts:");
            tableRenderer.renderSummaryTable(summary.getMoodCounts());
        }
        inputProcessor.pause();
    }
}
