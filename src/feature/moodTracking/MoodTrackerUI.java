package feature.moodTracking;

import java.util.HashMap;
import java.util.Map;
import UI.*;
import utils.iDataReader;
import utils.iDataWriter;
import utils.txtDataReader;
import utils.txtDataWriter;


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
        // The shared inputProcessor and tableRenderer come from AbstractFeatureUI.
        commandRegistry.put("1", new LogMoodCommand(controller, inputProcessor));
        commandRegistry.put("2", new ViewSummaryCommand(controller, tableRenderer, inputProcessor));
        commandRegistry.put("3", new ViewDayMoodCommand(controller, inputProcessor, tableRenderer));
    }
    
    @Override
    public String getTitle() {
        return "Mood Tracker";
    }
    
    @Override
    public void run() {
        boolean exit = false;
        while (!exit) {
            clearScreen();
            printMenu();
            String choice = inputProcessor.readLine("Enter your choice: ");
            if ("0".equals(choice)) {
                exit = true;
            } else {
                iCommand command = commandRegistry.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    inputProcessor.print("Invalid choice. Try again.");
                    pause();
                }
            }
        }
    }
    
    private void printMenu() {
        inputProcessor.print("=== Mood Tracker ===");
        inputProcessor.print("1. Log your mood for today");
        inputProcessor.print("2. View mood summary for the last 4 weeks");
        inputProcessor.print("3. View mood entries for a specific day");
        inputProcessor.print("0. Return to Main Menu");
    }

    public static void main(String[] args) {
        iDataReader dataReader = new txtDataReader();
        iDataWriter dataWriter = new txtDataWriter();
        String filePath = "moodtracker.txt";
        iMoodRepository moodRepository = new TxtMoodRepository(filePath, dataReader, dataWriter);
        iMoodService moodService = new MoodServiceImpl(moodRepository);
        MoodTrackerController controller = new MoodTrackerController(moodService);
        iFeatureUI moodUI = new MoodTrackerUI(controller);
        moodUI.run();
    }

}
