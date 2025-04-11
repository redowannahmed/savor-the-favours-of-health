package feature.healthGoal;

import UI.InputProcessor;
import UI.iCommand;
import utils.ClearScreen;

public class AddGoalCommand implements iCommand{
    private final DailyHealthGoalsController controller;
    private final InputProcessor inputProcessor;
    
    public AddGoalCommand(DailyHealthGoalsController controller, InputProcessor inputProcessor) {
        this.controller = controller;
        this.inputProcessor = inputProcessor;
    }
    
    @Override
    public void execute() {
        ClearScreen.getInstance().clearScreen();
        String desc = inputProcessor.readLine("Enter goal description: ");
        try {
            controller.addGoal(desc);
            System.out.println();
            inputProcessor.print("Goal added.");
        } catch (IllegalArgumentException e) {
            inputProcessor.print(e.getMessage());
        }
        inputProcessor.pause();
    }
}
