package feature.healthGoal;

import java.time.LocalDate;
import java.util.List;

public interface iGoalRepository {
    List<DailyGoal> getGoalsForDate(LocalDate date);
    void addGoal(DailyGoal goal);
    void updateTodayGoalAtIndex(int index, DailyGoal updatedGoal);
    List<DailyGoal> getAllGoals();
}
