package feature.moodTracking.moodTrackUI;

import java.util.HashMap;
import java.util.Map;
import UI.iFeatureUI;
import feature.moodTracking.MoodServiceImpl;
import feature.moodTracking.MoodTrackerController;
import feature.moodTracking.TxtMoodRepository;
import feature.moodTracking.iMoodRepository;
import feature.moodTracking.iMoodService;
import utils.iDataReader;
import utils.iDataWriter;
import utils.txtDataReader;
import utils.txtDataWriter;

public class MoodTrackerUI implements iFeatureUI{
    private final Map<String, Command> commandRegistry;
    private final InputProcessor inputProcessor;
    private final TableRenderer tableRenderer;
    private final MoodTrackerController controller;

    public MoodTrackerUI(MoodTrackerController controller) {
        this.controller = controller;
        this.inputProcessor = new InputProcessor();
        this.tableRenderer = new TableRenderer();
        this.commandRegistry = new HashMap<>();
        registerCommands();
    }
    
    private void registerCommands() {
        commandRegistry.put("1", new LogMoodCommand(controller, inputProcessor));
        commandRegistry.put("2", new ViewSummaryCommand(controller, tableRenderer, inputProcessor));
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
            if (choice.equals("0")) {
                exit = true;
            } else {
                Command command = commandRegistry.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    inputProcessor.print("Invalid choice. Try again.");
                    inputProcessor.pause();
                }
            }
        }
    }
    
    private void printMenu() {
        inputProcessor.print("=== Mood Tracker ===");
        inputProcessor.print("1. Log your mood for today");
        inputProcessor.print("2. View mood summary for the last 4 weeks");
        inputProcessor.print("0. Return to Main Menu");
    }
    
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
