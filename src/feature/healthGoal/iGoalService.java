package feature.healthGoal;

import java.util.List;

public interface iGoalService {
    void addGoal(String description);
    void changeGoalStatus(int goalId, GoalStatus newStatus);
    List<DailyGoal> getTodayGoals();
}