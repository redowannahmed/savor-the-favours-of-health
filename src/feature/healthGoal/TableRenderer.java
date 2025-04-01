package feature.healthGoal;

import java.util.List;

public class TableRenderer {
    public static final void renderGoalsTable(List<DailyGoal> goals) {
        System.out.println("+------+--------------------------------+--------------+");
        System.out.println("| No.  | Description                    | Status       |");
        System.out.println("+------+--------------------------------+--------------+");
        for (int i = 0; i < goals.size(); i++) {
            DailyGoal goal = goals.get(i);
            System.out.printf("| %-4d | %-30s | %-12s |%n", i + 1, goal.getDescription(), goal.getStatus().getStatusName());
        }
        System.out.println("+------+--------------------------------+--------------+");
    }
}