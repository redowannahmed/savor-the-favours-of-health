package feature.healthGoal;

import java.util.HashMap;
import java.util.Map;

import UI.iCommand;
import UI.iFeatureUI;
import utils.iDataReader;
import utils.iDataWriter;
import utils.txtDataReader;
import utils.txtDataWriter;
import UI.AbstractFeatureUI;

public class DailyHealthGoalsUI extends AbstractFeatureUI{
    private final DailyHealthGoalsController controller;
    private final Map<String, iCommand> commandRegistry;

    public DailyHealthGoalsUI(DailyHealthGoalsController controller) {
        this.controller = controller;
        this.commandRegistry = new HashMap<>();
        registerCommands();
    }
    
    private void registerCommands() {
        commandRegistry.put("1", new AddGoalCommand(controller, inputProcessor));
        commandRegistry.put("2", new ChangeGoalStatusCommand(controller, inputProcessor));
        commandRegistry.put("3", new ViewGoalsCommand(controller, inputProcessor));
    }
    
    @Override
    public String getTitle() {
        return "Daily Health Goals";
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
        inputProcessor.print("=== " + getTitle() + " ===");
        inputProcessor.print("1. Add a Goal");
        inputProcessor.print("2. Change Goal Status");
        inputProcessor.print("3. View Today's Goals");
        inputProcessor.print("0. Return to Main Menu");
    }

    public static void main(String[] args) {
        // Initialization code (for demonstration purposes)
        iDataReader dataReader = new txtDataReader();
        iDataWriter dataWriter = new txtDataWriter();
        String filePath = "healthGoals.txt";
        iGoalRepository goalRepository = new TxtGoalRepository(filePath, dataReader, dataWriter);
        iGoalService goalService = new GoalServiceImpl(goalRepository);
        DailyHealthGoalsController controller = new DailyHealthGoalsController(goalService);
        iFeatureUI dailyHealthGoalsUI = new DailyHealthGoalsUI(controller);
        dailyHealthGoalsUI.run();
    }
}
