package feature.sleep;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SleepSession {
    private LocalDate date;
    private double sleepHours;

    public SleepSession(LocalDate date, double sleepHours) {
        this.date = date;
        this.sleepHours = sleepHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getSleepHours() {
        return sleepHours;
    }

    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return date.format(formatter) + " | " + sleepHours;
    }

    public static SleepSession deserialize(String line) {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length != 2) return null;
        try {
            LocalDate date = LocalDate.parse(parts[0], DateTimeFormatter.ISO_LOCAL_DATE);
            double sleepHours = Double.parseDouble(parts[1]);
            return new SleepSession(date, sleepHours);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return serialize();
    }
}
