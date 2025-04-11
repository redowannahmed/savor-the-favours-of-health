package feature.sleep;

import UI.InputProcessor;
import UI.iCommand;
import utils.ClearScreen;

public class LogSleepSessionCommand implements iCommand{
    private final iSleepRepository sleepRepository;
    private final InputProcessor inputProcessor;
    
    public LogSleepSessionCommand(iSleepRepository sleepRepository, InputProcessor inputProcessor) {
        this.sleepRepository = sleepRepository;
        this.inputProcessor = inputProcessor;
    }
    
    @Override
    public void execute() {
        ClearScreen.getInstance().clearScreen();
        String sleepInput = inputProcessor.readLine("How many hours did you sleep? (e.g., 7.5, 8, etc.): ");
        try {
            SleepSession session = SleepSessionFactory.createSession(sleepInput);
            sleepRepository.addSession(session);
        } catch (NumberFormatException e) {
            inputProcessor.print("Invalid number format. Please enter a valid number.");
        }
        inputProcessor.pause();
    }
}
