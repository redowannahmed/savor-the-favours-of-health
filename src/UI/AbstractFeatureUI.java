package UI;

public abstract class AbstractFeatureUI implements iFeatureUI{
    protected final InputProcessor inputProcessor;
    protected final TableRenderer tableRenderer;
    
    public AbstractFeatureUI() {
        this.inputProcessor = new InputProcessor();
        this.tableRenderer = new TableRenderer();
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
