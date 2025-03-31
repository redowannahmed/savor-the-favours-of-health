package feature.healthGoal;

public abstract class GoalStatus {
    public abstract String getStatusName();

    public static GoalStatus fromString(String statusName) {
        switch (statusName) {
            case "Pending":
                return new PendingGoalStatus();
            case "In Progress":
                return new InProgressGoalStatus();
            case "Done":
                return new DoneGoalStatus();
            default:
                return new PendingGoalStatus();
        }
    }
}