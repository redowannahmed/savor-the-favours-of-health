package feature.healthGoal;

import java.util.List;

import UI.InputProcessor;
import UI.iCommand;
import utils.ClearScreen;

public class ViewGoalsCommand implements iCommand{
    private final DailyHealthGoalsController controller;
    private final InputProcessor inputProcessor;
    
    public ViewGoalsCommand(DailyHealthGoalsController controller, InputProcessor inputProcessor) {
        this.controller = controller;
        this.inputProcessor = inputProcessor;
    }
    
    @Override
    public void execute() {
        ClearScreen.getInstance().clearScreen();
        List<DailyGoal> goals = controller.getTodayGoals();
        if (goals.isEmpty()) {
            inputProcessor.print("No goals for today.");
        } else {
            TableRenderer.renderGoalsTable(goals);
        }
        inputProcessor.pause();
    }
}
