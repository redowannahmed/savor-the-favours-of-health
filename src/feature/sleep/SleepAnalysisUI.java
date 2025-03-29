package feature.sleep;

import UI.iFeatureUI;
import utils.iDataReader;
import utils.iDataWriter;
import utils.txtDataReader;
import utils.txtDataWriter;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

public class SleepAnalysisUI implements iFeatureUI {
    private final iSleepRepository sleepRepository;
    private final iSleepAnalyzer sleepAnalyzer;
    private final Scanner scanner = new Scanner(System.in);

    public SleepAnalysisUI(iSleepRepository sleepRepository, iSleepAnalyzer sleepAnalyzer) {
        this.sleepRepository = sleepRepository;
        this.sleepAnalyzer = sleepAnalyzer;
    }

    @Override
    public String getTitle() {
        return "Sleep Pattern Analyzer";
    }

    @Override
    public void run() {
        boolean exit = false;
        while (!exit) {
            clearScreen();
            System.out.println("=== Sleep Pattern Analyzer ===");
            System.out.println("1. Log your sleep hours");
            System.out.println("2. View sleep pattern and recommendation");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    logSleepSession();
                    break;
                case "2":
                    viewSleepAnalysis();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    pause();
            }
            if (!exit) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private void logSleepSession() {
        System.out.print("How many hours did you sleep? (e.g., 7.5, 8, etc.): ");
        String sleepInput = scanner.nextLine();
        try {
            SleepSession session = SleepSessionFactory.createSession(sleepInput);
            sleepRepository.addSession(session);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid number.");
        }
    }

    private void viewSleepAnalysis() {
        // Get sessions for the past 4 weeks.
        LocalDate fourWeeksAgo = LocalDate.now().minusWeeks(4);
        List<SleepSession> sessions = sleepRepository.getSessionsSince(fourWeeksAgo);
        SleepAnalysisResult result = sleepAnalyzer.analyzeSleepTrend(sessions);
        if (sessions.isEmpty()) {
            System.out.println("No sleep data available for the past 4 weeks.");
        } else {
            System.out.printf("Average sleep duration: %.2f hours%n", result.getAvgSleepDuration());
            System.out.println("Progress: " + result.getProgressBar());
            System.out.println("Recommendation: " + result.getRecommendation());
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pause() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        iDataReader dataReader = new txtDataReader();
        iDataWriter dataWriter = new txtDataWriter();
        iProgressBarRenderer progressRenderer = new DefaultProgressBarRenderer();
        // Specify file path for sleep data
        iSleepRepository sleepRepository = new TxtSleepDataRepository("sleep.txt", dataReader, dataWriter);
        iSleepAnalyzer sleepAnalyzer = new SleepAnalyzer(progressRenderer);
        SleepAnalysisUI sleepUI = new SleepAnalysisUI(sleepRepository, sleepAnalyzer);
        sleepUI.run();
    }
}
