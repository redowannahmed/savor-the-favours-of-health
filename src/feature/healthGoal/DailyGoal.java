package feature.healthGoal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyGoal {
    private String description;
    private LocalDate date;  
    private GoalStatus status;

    public DailyGoal(String description, LocalDate date, GoalStatus status) {
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public GoalStatus getStatus() {
        return status;
    }

    public void setStatus(GoalStatus status) {
        this.status = status;
    }

    // Serialize as "date | description | status"
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return date.format(formatter) + " | " + description + " | " + status.getStatusName();
    }

    public static DailyGoal deserialize(String line) {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length != 3) return null;
        try {
            LocalDate date = LocalDate.parse(parts[0], DateTimeFormatter.ISO_LOCAL_DATE);
            String description = parts[1];
            String statusName = parts[2];
            GoalStatus status = GoalStatus.fromString(statusName);
            return new DailyGoal(description, date, status);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return serialize();
    }
}
