package feature.healthGoal;

public class InProgressGoalStatus extends GoalStatus{
    @Override
    public String getStatusName() {
        return "in progress";
    }
}
