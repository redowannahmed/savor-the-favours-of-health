package feature.healthGoal;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utils.iDataReader;
import utils.iDataWriter;

public class TxtGoalRepository implements iGoalRepository{
    private final String filePath;
    private final iDataReader dataReader;
    private final iDataWriter dataWriter;

    public TxtGoalRepository(String filePath, iDataReader dataReader, iDataWriter dataWriter) {
        this.filePath = filePath;
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
    }

    @Override
    public List<DailyGoal> getGoalsForDate(LocalDate date) {
        List<DailyGoal> goals = new ArrayList<>();
        List<DailyGoal> allGoals = getAllGoals();
        for (DailyGoal goal : allGoals) {
            if (goal.getDate().equals(date)) {
                goals.add(goal);
            }
        }
        return goals;
    }

    @Override
    public void addGoal(DailyGoal goal) {
        List<DailyGoal> goals = getAllGoals();
        // Prevent duplicate goal (same description for the same date)
        for (DailyGoal g : goals) {
            if (g.getDate().equals(goal.getDate()) && g.getDescription().equalsIgnoreCase(goal.getDescription())) {
                System.out.println("A goal with that description already exists for today.");
                return;
            }
        }
        goals.add(goal);
        writeAllGoals(goals);
    }

    @Override
    public void updateGoal(DailyGoal goal) {
        List<DailyGoal> goals = getAllGoals();
        for (int i = 0; i < goals.size(); i++) {
            if (goals.get(i).getId() == goal.getId()) {
                goals.set(i, goal);
                break;
            }
        }
        writeAllGoals(goals);
    }

    @Override
    public List<DailyGoal> getAllGoals() {
        List<DailyGoal> goals = new ArrayList<>();
        try {
            String content = dataReader.readAll(filePath, String.class);
            if (content != null && !content.isEmpty()) {
                String[] lines = content.split("\\r?\\n");
                for (String line : lines) {
                    DailyGoal goal = DailyGoal.deserialize(line);
                    if (goal != null) {
                        goals.add(goal);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goals;
    }

    private void writeAllGoals(List<DailyGoal> goals) {
        StringBuilder sb = new StringBuilder();
        for (DailyGoal g : goals) {
            sb.append(g.serialize()).append(System.lineSeparator());
        }
        try {
            dataWriter.writeAll(filePath, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
