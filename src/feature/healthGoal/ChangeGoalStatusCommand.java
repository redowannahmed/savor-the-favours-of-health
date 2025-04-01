package feature.healthGoal;

import UI.InputProcessor;
import UI.iCommand;

public class ChangeGoalStatusCommand implements iCommand{
    private final DailyHealthGoalsController controller;
    private final InputProcessor inputProcessor;
    
    public ChangeGoalStatusCommand(DailyHealthGoalsController controller, InputProcessor inputProcessor) {
        this.controller = controller;
        this.inputProcessor = inputProcessor;
    }
    
    @Override
    public void execute() {
        int goalNumber = inputProcessor.readInt("Enter goal number to update (as shown in the list): ");
        int index = goalNumber - 1; 
        inputProcessor.print("Choose new status:");
        inputProcessor.print("1. Pending");
        inputProcessor.print("2. In Progress");
        inputProcessor.print("3. Done");
        String statusChoice = inputProcessor.readLine("Enter status choice: ");
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
                inputProcessor.print("Invalid status choice.");
                inputProcessor.pause();
                return;
        }
        controller.changeGoalStatus(index, newStatus);
        inputProcessor.print("Goal updated.");
        inputProcessor.pause();
    }
}
