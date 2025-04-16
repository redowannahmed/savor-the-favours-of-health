package feature.moodTracking;

import UI.InputProcessor;
import UI.iCommand;
import colorUtils.ColorUtil;
import utils.ClearScreen;

public class LogMoodCommand implements iCommand{
    private final MoodTrackerController controller;
    private final InputProcessor inputProcessor;
    
    public LogMoodCommand(MoodTrackerController controller, InputProcessor inputProcessor) {
        this.controller = controller;
        this.inputProcessor = inputProcessor;
    }
    
    @Override
    public void execute() {
        ClearScreen.getInstance().clearScreen();
        int rating = inputProcessor.readInt(ColorUtil.applyNote("On a scale of 1 to 5, how would you rate today? "));
        ClearScreen.getInstance().clearScreen();
        if (rating >= 4) {
            inputProcessor.print(ColorUtil.applySuccess("Excellent!"));
        } else if (rating == 3) {
            inputProcessor.print(ColorUtil.applyHighlight("Not bad!"));
        } else {
            inputProcessor.print(ColorUtil.applyOption("I'm sorry to hear that!"));
        }
        System.out.println();
        inputProcessor.print("How would you describe your overall feelings for today?");
        System.out.println();
        inputProcessor.print(ColorUtil.applyOption("1. Happy"));
        inputProcessor.print(ColorUtil.applyOption("2. Sad"));
        inputProcessor.print(ColorUtil.applyOption("3. Angry"));
        System.out.println();
        int moodChoice = inputProcessor.readInt(ColorUtil.applyNote("Enter your choice: "));
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
                inputProcessor.print(ColorUtil.applyError("Invalid mood choice. Defaulting to Happy."));
                mood = new HappyMood();
                break;
        }
        System.out.println();
        String notes = inputProcessor.readLine(ColorUtil.applyNote("Any additional notes? (Leave blank if none): "));
        System.out.println();
        controller.logMoodEntry(rating, mood, notes);
        inputProcessor.print(ColorUtil.applySuccess("Mood logged successfully!"));
        inputProcessor.pause();
    }
}
