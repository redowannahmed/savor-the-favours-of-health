package feature.sleep;

import utils.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TxtSleepDataRepository implements iSleepRepository {

    private final String filePath;
    private final iDataReader dataReader;
    private final iDataWriter dataWriter;

    public TxtSleepDataRepository(String filePath, iDataReader dataReader, iDataWriter dataWriter) {
        this.filePath = filePath;
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
    }

    @Override
    public List<SleepSession> getAllSessions() {
        List<SleepSession> sessions = new ArrayList<>();
        try {
            String content = dataReader.readAll(filePath, String.class);
            if (content != null && !content.isEmpty()) {
                String[] lines = content.split("\\r?\\n");
                for (String line : lines) {
                    SleepSession session = SleepSession.deserialize(line);
                    if (session != null) {
                        sessions.add(session);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    @Override
    public void addSession(SleepSession session) {
    List<SleepSession> sessions = getAllSessions();
    
    // Check for an existing session with the same date.
    for (SleepSession s : sessions) {
        if (s.getDate().equals(session.getDate())) {
            System.out.println("Error: A sleep session for " + session.getDate() + " is already logged.");
            return;  
        }
    }
    
    sessions.add(session);
    
    // Build file content from sessions.
    StringBuilder sb = new StringBuilder();
    for (SleepSession s : sessions) {
            sb.append(s.serialize()).append(System.lineSeparator());
        }
        
        try {
            dataWriter.writeAll(filePath, sb.toString());
            System.out.println("Sleep session logged successfully for " + session.getDate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<SleepSession> getSessionsSince(LocalDate startDate) {
        List<SleepSession> allSessions = getAllSessions();
        List<SleepSession> filtered = new ArrayList<>();
        for (SleepSession session : allSessions) {
            if (session.getDate().isAfter(startDate) || session.getDate().isEqual(startDate)) {
                filtered.add(session);
            }
        }
        return filtered;
    }
}
