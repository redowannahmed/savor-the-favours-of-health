package feature.sleep;

import java.time.LocalDate;
import java.util.List;

public interface iSleepRepository {
    List<SleepSession> getAllSessions();
    void addSession(SleepSession session);
    List<SleepSession> getSessionsSince(LocalDate startDate);
}
