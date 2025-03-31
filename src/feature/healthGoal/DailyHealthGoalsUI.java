package feature.healthGoal;

import java.util.List;

import UI.iFeatureUI;
import utils.iDataReader;
import utils.iDataWriter;
import utils.txtDataReader;
import utils.txtDataWriter;

public class DailyHealthGoalsUI implements iFeatureUI{
    private final DailyHealthGoalsController controller;
    private final InputHandler inputHandler;
    private final TableRenderer tableRenderer;

    public DailyHealthGoalsUI(DailyHealthGoalsController controller) {
        this.controller = controller;
        this.inputHandler = new InputHandler();
        this.tableRenderer = new TableRenderer();
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
            System.out.println("=== Daily Health Goals ===");
            System.out.println("1. Add a Goal");
            System.out.println("2. Change Goal Status");
            System.out.println("3. View Today's Goals");
            System.out.println("0. Return to Main Menu");
            String choice = inputHandler.readLine("Enter your choice: ");

            switch (choice) {
                case "1":
                    handleAddGoal();
                    break;
                case "2":
                    handleChangeGoalStatus();
                    break;
                case "3":
                    handleViewGoals();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    inputHandler.pause();
                    break;
            }
        }
    }

    private void handleAddGoal() {
        String desc = inputHandler.readLine("Enter goal description: ");
        try {
            controller.addGoal(desc);
            System.out.println("Goal added.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        inputHandler.pause();
    }

    private void handleChangeGoalStatus() {
        int goalNumber = inputHandler.readInt("Enter goal number to update (as shown in the list): ");
        // Adjust for 0-based index.
        int index = goalNumber - 1;
        System.out.println("Choose new status:");
        System.out.println("1. Pending");
        System.out.println("2. In Progress");
        System.out.println("3. Done");
        String statusChoice = inputHandler.readLine("Enter status choice: ");
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
                System.out.println("Invalid status choice.");
                inputHandler.pause();
                return;
        }
        controller.changeGoalStatus(index, newStatus);
        System.out.println("Goal updated.");
        inputHandler.pause();
    }

    private void handleViewGoals() {
        List<DailyGoal> goals = controller.getTodayGoals();
        if (goals.isEmpty()) {
            System.out.println("No goals for today.");
        } else {
            // Display table with first column as goal number (1-based).
            System.out.println("+------+--------------------------------+--------------+");
            System.out.println("| No.  | Description                    | Status       |");
            System.out.println("+------+--------------------------------+--------------+");
            for (int i = 0; i < goals.size(); i++) {
                DailyGoal goal = goals.get(i);
                System.out.printf("| %-4d | %-30s | %-12s |%n", i + 1, goal.getDescription(), goal.getStatus().getStatusName());
            }
            System.out.println("+------+--------------------------------+--------------+");
        }
        inputHandler.pause();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        iDataReader dataReader = new txtDataReader();
        iDataWriter dataWriter = new txtDataWriter();
        String filePath = "healthGoals.txt";
        iGoalRepository goalRepository =
            new TxtGoalRepository(filePath, dataReader, dataWriter);
        iGoalService goalService = new GoalServiceImpl(goalRepository);
        DailyHealthGoalsController controller = new DailyHealthGoalsController(goalService);
        iFeatureUI dailyHealthGoalsUI = new DailyHealthGoalsUI(controller);
        dailyHealthGoalsUI.run();
    }
}
