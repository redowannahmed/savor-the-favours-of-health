package feature.sleep;

import java.time.LocalDate;
import java.util.List;

import UI.InputProcessor;
import UI.iCommand;
import colorUtils.ColorUtil;
import utils.ClearScreen;

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
        ClearScreen.getInstance().clearScreen();
        LocalDate fourWeeksAgo = LocalDate.now().minusWeeks(4);
        List<SleepSession> sessions = sleepRepository.getSessionsSince(fourWeeksAgo);
        if (sessions.isEmpty()) {
            inputProcessor.print(ColorUtil.applyError("No sleep data available for the past 4 weeks."));
        } else {
            SleepAnalysisResult result = sleepAnalyzer.analyzeSleepTrend(sessions);
            inputProcessor.print("Progress: " + result.getProgressBar());
            inputProcessor.print(String.format("Average sleep duration: %.2f hours", result.getAvgSleepDuration()));
            inputProcessor.print("Recommendation: " + result.getRecommendation());
            System.out.println();
        }
        inputProcessor.pause();
    }
}

