package UI;

public abstract class AbstractFeatureUI implements iFeatureUI{
    protected final InputProcessor inputProcessor;
    
    public AbstractFeatureUI() {
        this.inputProcessor = new InputProcessor();
    }
    
    /**
     * Clears the console screen.
     */
    protected void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * Pauses the execution until the user presses Enter.
     */
    protected void pause() {
        inputProcessor.pause();
    }
}
