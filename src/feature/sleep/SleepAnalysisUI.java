package feature.sleep;

import UI.AbstractFeatureUI;
import UI.iCommand;
import colorUtils.ColorUtil;
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
    
    public String getTitle() {
        return "Sleep Pattern Analyzer";
    }
    
    @Override
    public void run() {
        boolean exit = false;
        while (!exit) {
            clearScreen();
            printMenu();
            System.out.println();
            String choice = inputProcessor.readLine(ColorUtil.applyNote("Enter your choice: "));
            
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
        inputProcessor.print(ColorUtil.applySubHeader("=== " + getTitle() + " ==="));
        System.out.println();
        inputProcessor.print(ColorUtil.applyOption("1. Log your sleep hours"));
        inputProcessor.print(ColorUtil.applyOption("2. View sleep pattern and recommendation"));
        inputProcessor.print(ColorUtil.applyOption("0. Return to Main Menu"));
    }

}
