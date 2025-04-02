package UI;

public abstract class AbstractFeatureUI extends ClearScreen implements iFeatureUI{
    protected final InputProcessor inputProcessor;
    
    public AbstractFeatureUI() {
        this.inputProcessor = new InputProcessor();
    }
    
    
    /**
     * Pauses the execution until the user presses Enter.
     */
    protected void pause() {
        inputProcessor.pause();
    }
}
