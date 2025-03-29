package feature.sleep;

import java.time.LocalDate;

public class SleepSessionFactory {

    // Parse a time string that could be in 24-hour or 12-hour format.
    public static SleepSession createSession(String sleepHoursInput) {
        double hours = Double.parseDouble(sleepHoursInput);
        return new SleepSession(LocalDate.now(), hours);
    }
}
