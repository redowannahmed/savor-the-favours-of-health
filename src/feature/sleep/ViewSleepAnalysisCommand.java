package feature.sleep;

import java.time.LocalDate;
import java.util.List;

import UI.ClearScreen;
import UI.InputProcessor;
import UI.iCommand;

public class ViewSleepAnalysisCommand implements iCommand{
    private final iSleepRepository sleepRepository;
    private final iSleepAnalyzer sleepAnalyzer;
    private final InputProcessor inputProcessor;
    
    public ViewSleepAnalysisCommand(iSleepRepository sleepRepository, iSleepAnalyzer sleepAnalyzer, InputProcessor inputProcessor) {
        this.sleepRepository = sleepRepository;
        this.sleepAnalyzer = sleepAnalyzer;
        this.inputProcessor = inputProcessor;
    }
    
    @Override
    public void execute() {
        ClearScreen.clearScreen();
        LocalDate fourWeeksAgo = LocalDate.now().minusWeeks(4);
        List<SleepSession> sessions = sleepRepository.getSessionsSince(fourWeeksAgo);
        if (sessions.isEmpty()) {
            inputProcessor.print("No sleep data available for the past 4 weeks.");
        } else {
            SleepAnalysisResult result = sleepAnalyzer.analyzeSleepTrend(sessions);
            inputProcessor.print(String.format("Average sleep duration: %.2f hours", result.getAvgSleepDuration()));
            inputProcessor.print("Progress: " + result.getProgressBar());
            inputProcessor.print("Recommendation: " + result.getRecommendation());
        }
        inputProcessor.pause();
    }
}
