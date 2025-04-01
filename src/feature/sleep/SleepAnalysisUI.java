package feature.sleep;

import UI.AbstractFeatureUI;
import UI.iCommand;
import utils.iDataReader;
import utils.iDataWriter;
import utils.txtDataReader;
import utils.txtDataWriter;
import java.util.HashMap;
import java.util.Map;

public class SleepAnalysisUI extends AbstractFeatureUI {
    private final iSleepRepository sleepRepository;
    private final iSleepAnalyzer sleepAnalyzer;
    private final Map<String, iCommand> commandRegistry;

    public SleepAnalysisUI(iSleepRepository sleepRepository, iSleepAnalyzer sleepAnalyzer) {
        this.sleepRepository = sleepRepository;
        this.sleepAnalyzer = sleepAnalyzer;
        this.commandRegistry = new HashMap<>();
        registerCommands();
    }
    
    private void registerCommands() {
        commandRegistry.put("1", new LogSleepSessionCommand(sleepRepository, inputProcessor));
        commandRegistry.put("2", new ViewSleepAnalysisCommand(sleepRepository, sleepAnalyzer, inputProcessor));
    }
    
    @Override
    public String getTitle() {
        return "Sleep Pattern Analyzer";
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
                    inputProcessor.print("Invalid option. Please try again.");
                    pause();
                }
            }
        }
    }
    
    private void printMenu() {
        inputProcessor.print("=== " + getTitle() + " ===");
        inputProcessor.print("1. Log your sleep hours");
        inputProcessor.print("2. View sleep pattern and recommendation");
        inputProcessor.print("0. Return to Main Menu");
    }

    public static void main(String[] args) {
        iDataReader dataReader = new txtDataReader();
        iDataWriter dataWriter = new txtDataWriter();
        iProgressBarRenderer progressRenderer = new DefaultProgressBarRenderer();
        iSleepRepository sleepRepository = new TxtSleepDataRepository("sleep.txt", dataReader, dataWriter);
        iSleepAnalyzer sleepAnalyzer = new SleepAnalyzer(progressRenderer);
        SleepAnalysisUI sleepUI = new SleepAnalysisUI(sleepRepository, sleepAnalyzer);
        sleepUI.run();
    }
}
