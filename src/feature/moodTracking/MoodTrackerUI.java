package feature.moodTracking;

import java.util.HashMap;
import java.util.Map;
import UI.*;
import colorUtils.ColorUtil;


public class MoodTrackerUI extends AbstractFeatureUI{
    private final Map<String, iCommand> commandRegistry;
    private final MoodTrackerController controller;

    public MoodTrackerUI(MoodTrackerController controller) {
        super();
        this.controller = controller;
        this.commandRegistry = new HashMap<>();
        registerCommands();
    }
    
    private void registerCommands() {
        commandRegistry.put("1", new LogMoodCommand(controller, inputProcessor));
        commandRegistry.put("2", new ViewSummaryCommand(controller, inputProcessor));
        commandRegistry.put("3", new ViewDayMoodCommand(controller, inputProcessor));
    }
    
    public String getTitle() {
        return "Mood Tracker";
    }
    
    @Override
    public void run() {
        boolean exit = false;
        while (!exit) {
            clearScreen();
            printMenu();
            String choice = inputProcessor.readLine(ColorUtil.applyNote("Enter your choice: "));
            if ("0".equals(choice)) {
                exit = true;
            } else {
                iCommand command = commandRegistry.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    inputProcessor.print(ColorUtil.applyError("Invalid choice. Try again."));
                    pause();
                }
            }
        }
    }
    
    private void printMenu() {
        inputProcessor.print(ColorUtil.applySubHeader("=== Mood Tracker ==="));
        System.out.println();
        inputProcessor.print(ColorUtil.applyOption("1. Log your mood for today"));
        inputProcessor.print(ColorUtil.applyOption("2. View mood summary for the last 4 weeks"));
        inputProcessor.print(ColorUtil.applyOption("3. View mood entries for a specific day"));
        inputProcessor.print(ColorUtil.applyOption("0. Return to Main Menu"));

        System.out.println();
    }

}
