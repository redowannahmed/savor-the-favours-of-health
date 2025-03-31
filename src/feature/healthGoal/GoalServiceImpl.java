package feature.healthGoal;


import java.time.LocalDate;
import java.util.List;

public class GoalServiceImpl implements iGoalService {
    private final iGoalRepository goalRepository;

    public GoalServiceImpl(iGoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public void addGoal(String description) {
        int newId = generateUniqueGoalId();
        DailyGoal goal = new DailyGoal(newId, description, LocalDate.now(), new PendingGoalStatus());
        goalRepository.addGoal(goal);
    }

    @Override
    public void changeGoalStatus(int goalId, GoalStatus newStatus) {
        List<DailyGoal> goals = goalRepository.getGoalsForDate(LocalDate.now());
        for (DailyGoal g : goals) {
            if (g.getId() == goalId) {
                g.setStatus(newStatus);
                goalRepository.updateGoal(g);
                break;
            }
        }
    }

    @Override
    public List<DailyGoal> getTodayGoals() {
        return goalRepository.getGoalsForDate(LocalDate.now());
    }

    private int generateUniqueGoalId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
}
