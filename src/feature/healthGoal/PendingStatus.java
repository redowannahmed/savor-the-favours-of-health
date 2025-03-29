package feature.healthGoal;

public class PendingStatus extends GoalStatus {
    @Override
    public String getStatusName() {
        return "Pending";
    }
}
