package feature.healthGoal;

import java.time.LocalDate;

public class Goal {
    private int id;  // Unique task identifier.
    private String description;
    private LocalDate date;  // The date the task was added (today’s list).
    private GoalStatus status;  // Task status (abstract type).

    // Constructors, getters, and setters.
    
    // Methods for serialization/deserialization to/from text (e.g., "id | date | description | status")
    public String serialize() {
        // Return a string representation (e.g., using a delimiter).
        return id + " | " + date.toString() + " | " + description + " | " + status.getStatusName();
    }

    public static Goal deserialize(String line) {
        // Parse the line and construct a Task.
        // Return null if parsing fails.
        return null; // Blueprint only.
    }
}
