package feature.healthGoal;

import java.util.List;

public interface iGoalService {
    void addGoal(String description) throws IllegalArgumentException;
        void changeGoalStatus(int goalNumber, GoalStatus newStatus);
        List<DailyGoal> getTodayGoals();
}