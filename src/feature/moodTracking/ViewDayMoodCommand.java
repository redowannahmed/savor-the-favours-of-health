package feature.moodTracking;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import UI.InputProcessor;
import UI.iCommand;
import UI.TableRenderer;

public class ViewDayMoodCommand implements iCommand{
    private final MoodTrackerController controller;
    private final InputProcessor inputProcessor;
    private final TableRenderer tableRenderer;

    public ViewDayMoodCommand(MoodTrackerController controller, InputProcessor inputProcessor, TableRenderer tableRenderer) {
        this.controller = controller;
        this.inputProcessor = inputProcessor;
        this.tableRenderer = tableRenderer;
    }

    @Override
    public void execute() {
        String dateStr = inputProcessor.readLine("Enter date (YYYY-MM-DD): ");
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            inputProcessor.print("Invalid date format. Please use YYYY-MM-DD.");
            inputProcessor.pause();
            return;
        }
        List<MoodEntry> entries = controller.getMoodEntriesForDate(date);
        if (entries.isEmpty()) {
            inputProcessor.print("No mood entries found for " + date);
        } else {
            inputProcessor.print("Mood Entries for " + date + ":");
            MoodTrackingTableRenderer.renderMoodEntries(entries);
        }
        inputProcessor.pause();
    }
}
