package feature.healthGoal;


import java.time.LocalDate;
import java.util.List;

public class GoalServiceImpl implements iGoalService {
    private final iGoalRepository goalRepository;

    public GoalServiceImpl(iGoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public void addGoal(String description) throws IllegalArgumentException {
        // Validate that the description is not blank.
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Goal description cannot be blank.");
        }
        DailyGoal goal = new DailyGoal(description.trim(), LocalDate.now(), new PendingGoalStatus());
        goalRepository.addGoal(goal);
    }

    @Override
    public void changeGoalStatus(int goalNumber, GoalStatus newStatus) {
        List<DailyGoal> todayGoals = goalRepository.getGoalsForDate(LocalDate.now());
        if (goalNumber < 0 || goalNumber >= todayGoals.size()) {
            System.out.println("Invalid goal number.");
            return;
        }
        DailyGoal goal = todayGoals.get(goalNumber);
        // Create a new DailyGoal with updated status (preserving description and date).
        DailyGoal updatedGoal = new DailyGoal(goal.getDescription(), goal.getDate(), newStatus);
        goalRepository.updateTodayGoalAtIndex(goalNumber, updatedGoal);
    }

    @Override
    public List<DailyGoal> getTodayGoals() {
        return goalRepository.getGoalsForDate(LocalDate.now());
    }
}
