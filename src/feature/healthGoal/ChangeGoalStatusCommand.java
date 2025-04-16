package feature.healthGoal;


import UI.InputProcessor;
import UI.iCommand;
import colorUtils.ColorUtil;
import utils.ClearScreen;

public class ChangeGoalStatusCommand implements iCommand{
    private final DailyHealthGoalsController controller;
    private final InputProcessor inputProcessor;
    
    public ChangeGoalStatusCommand(DailyHealthGoalsController controller, InputProcessor inputProcessor) {
        this.controller = controller;
        this.inputProcessor = inputProcessor;
    }
    
    @Override
    public void execute() {
        ClearScreen.getInstance().clearScreen();
        System.out.println();
        TableRenderer.renderGoalsTable(controller.getTodayGoals());
        System.out.println();
        int goalNumber = inputProcessor.readInt(ColorUtil.applyNote("Enter goal number to update (as shown in the list): "));
        System.out.println();
        int index = goalNumber - 1; 
        inputProcessor.print(ColorUtil.applyOption("Choose new status:"));
        inputProcessor.print(ColorUtil.applyOption("1. Pending"));
        inputProcessor.print(ColorUtil.applyOption("2. In Progress"));
        inputProcessor.print(ColorUtil.applyOption("3. Done"));
        System.out.println();

        String statusChoice = inputProcessor.readLine(ColorUtil.applyNote("Enter status choice: "));
        System.out.println();
        GoalStatus newStatus;
        switch (statusChoice) {
            case "1":
                newStatus = new PendingGoalStatus();
                break;
            case "2":
                newStatus = new InProgressGoalStatus();
                break;
            case "3":
                newStatus = new DoneGoalStatus();
                break;
            default:
                inputProcessor.print(ColorUtil.applyError("Invalid status choice."));
                inputProcessor.pause();
                return;
        }
        controller.changeGoalStatus(index, newStatus);
        ClearScreen.getInstance().clearScreen();
        inputProcessor.print(ColorUtil.applySuccess("Goal updated."));
        System.out.println();
        TableRenderer.renderGoalsTable(controller.getTodayGoals());
        inputProcessor.pause();
    }
}
