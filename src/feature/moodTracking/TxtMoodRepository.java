package feature.moodTracking;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utils.iDataReader;
import utils.iDataWriter;

public class TxtMoodRepository implements iMoodRepository{
    private final String filePath;
    private final iDataReader dataReader;
    private final iDataWriter dataWriter;
    private final iMoodEntrySerializer serializer;

    public TxtMoodRepository(String filePath, iDataReader dataReader, iDataWriter dataWriter) {
        this.filePath = filePath;
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
        this.serializer = new TxtMoodEntrySerializer(); // You can inject this if needed.
    }

    @Override
    public List<MoodEntry> getAllEntries() {
        List<MoodEntry> entries = new ArrayList<>();
        try {
            String content = dataReader.readAll(filePath, String.class);
            if (content != null && !content.isEmpty()) {
                String[] lines = content.split("\\r?\\n");
                for (String line : lines) {
                    MoodEntry entry = serializer.deserialize(line);
                    if (entry != null) {
                        entries.add(entry);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    @Override
    public void addEntry(MoodEntry entry) {
        List<MoodEntry> entries = getAllEntries();
        entries.add(entry);
        writeAllEntries(entries);
    }

    @Override
    public List<MoodEntry> getEntriesSince(LocalDate startDate) {
        List<MoodEntry> all = getAllEntries();
        List<MoodEntry> result = new ArrayList<>();
        for (MoodEntry entry : all) {
            if (!entry.getDate().isBefore(startDate)) {
                result.add(entry);
            }
        }
        return result;
    }

    private void writeAllEntries(List<MoodEntry> entries) {
        StringBuilder sb = new StringBuilder();
        for (MoodEntry entry : entries) {
            sb.append(serializer.serialize(entry)).append(System.lineSeparator());
        }
        try {
            dataWriter.writeAll(filePath, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
