package feature.healthGoal;

import java.util.HashMap;
import java.util.Map;

import UI.iCommand;
import colorUtils.ColorUtil;
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
    
    public String getTitle() {
        return "Daily Health Goals";
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
        inputProcessor.print(ColorUtil.applySubHeader("=== " + getTitle() + " ==="));
        inputProcessor.print("\n" + ColorUtil.applyOption("1. Add a Goal"));
        inputProcessor.print(ColorUtil.applyOption("2. Change Goal Status"));
        inputProcessor.print(ColorUtil.applyOption("3. View Today's Goals"));
        inputProcessor.print(ColorUtil.applyOption("0. Return to Main Menu") + "\n");
    }
}
