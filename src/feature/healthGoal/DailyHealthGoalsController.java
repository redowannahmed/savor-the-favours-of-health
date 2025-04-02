package feature.healthGoal;

import java.util.List;

public class DailyHealthGoalsController {
    private final iGoalService goalService;

    public DailyHealthGoalsController(iGoalService goalService) {
        this.goalService = goalService;
    }

    public void addGoal(String description) {
        goalService.addGoal(description);
    }

    public void changeGoalStatus(int goalId, GoalStatus newStatus) {
        goalService.changeGoalStatus(goalId, newStatus);
    }

    public List<DailyGoal> getTodayGoals() {
        return goalService.getTodayGoals();
    }
}
