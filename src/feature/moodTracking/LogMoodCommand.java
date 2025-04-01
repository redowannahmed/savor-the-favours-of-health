package feature.moodTracking;

import UI.InputProcessor;
import UI.iCommand;

public class LogMoodCommand implements iCommand{
        private final MoodTrackerController controller;
    private final InputProcessor inputProcessor;
    
    public LogMoodCommand(MoodTrackerController controller, InputProcessor inputProcessor) {
        this.controller = controller;
        this.inputProcessor = inputProcessor;
    }
    
    @Override
    public void execute() {
        int rating = inputProcessor.readInt("On a scale of 1 to 5, how would you rate today? ");
        if (rating >= 4) {
            inputProcessor.print("Excellent!");
        } else if (rating == 3) {
            inputProcessor.print("Not bad!");
        } else {
            inputProcessor.print("I'm sorry to hear that!");
        }
        inputProcessor.print("How would you describe your overall feelings for today?");
        inputProcessor.print("1. Happy");
        inputProcessor.print("2. Sad");
        inputProcessor.print("3. Angry");
        int moodChoice = inputProcessor.readInt("Enter your choice: ");
        Mood mood;
        switch (moodChoice) {
            case 1:
                mood = new HappyMood();
                break;
            case 2:
                mood = new SadMood();
                break;
            case 3:
                mood = new AngryMood();
                break;
            default:
                inputProcessor.print("Invalid mood choice. Defaulting to Happy.");
                mood = new HappyMood();
                break;
        }
        String notes = inputProcessor.readLine("Any additional notes? (Leave blank if none): ");
        controller.logMoodEntry(rating, mood, notes);
        inputProcessor.print("Mood logged successfully!");
        inputProcessor.pause();
    }
}
