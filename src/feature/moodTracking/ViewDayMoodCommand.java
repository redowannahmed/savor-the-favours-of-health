package feature.moodTracking;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import UI.InputProcessor;
import utils.ClearScreen;
import UI.iCommand;
import colorUtils.ColorUtil;

public class ViewDayMoodCommand implements iCommand{
    private final MoodTrackerController controller;
    private final InputProcessor inputProcessor;

    public ViewDayMoodCommand(MoodTrackerController controller, InputProcessor inputProcessor) {
        this.controller = controller;
        this.inputProcessor = inputProcessor;
    }

    @Override
    public void execute() {
        ClearScreen.getInstance().clearScreen();
        String dateStr = inputProcessor.readLine(ColorUtil.applyNote("Enter date (YYYY-MM-DD): "));
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            inputProcessor.print(ColorUtil.applyError("Invalid date format. Please use YYYY-MM-DD."));
            inputProcessor.pause();
            return;
        }
        System.out.println();
        List<MoodEntry> entries = controller.getMoodEntriesForDate(date);
        if (entries.isEmpty()) {
            inputProcessor.print(ColorUtil.applyCaution("No mood entries found for " + date));
        } else {
            inputProcessor.print(ColorUtil.applySuccess("Mood Entries for " + date + ":"));
            System.out.println();
            MoodTrackingTableRenderer.renderMoodEntries(entries);
        }
        inputProcessor.pause();
    }
}
