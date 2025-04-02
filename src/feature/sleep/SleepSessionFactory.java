package feature.sleep;

import java.time.LocalDate;

public class SleepSessionFactory {
    public static SleepSession createSession(String sleepHoursInput) {
        double hours = Double.parseDouble(sleepHoursInput);
        return new SleepSession(LocalDate.now(), hours);
    }
}
