package feature.healthGoal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyGoal {
    private int id;
    private String description;
    private LocalDate date;  // The date for which the goal is set (e.g., today).
    private GoalStatus status;  // Goal status (abstract type).

    public DailyGoal(int id, String description, LocalDate date, GoalStatus status) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
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

    // Serialize as "id | date | description | status"
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return id + " | " + date.format(formatter) + " | " + description + " | " + status.getStatusName();
    }

    // Deserialize from a line of text.
    public static DailyGoal deserialize(String line) {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length != 4) return null;
        try {
            int id = Integer.parseInt(parts[0]);
            LocalDate date = LocalDate.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE);
            String description = parts[2];
            String statusName = parts[3];
            GoalStatus status = GoalStatus.fromString(statusName);
            return new DailyGoal(id, description, date, status);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return serialize();
    }
}
