package UI;

import utils.ClearScreen;

public abstract class AbstractFeatureUI implements iFeatureUI{
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

    public void clearScreen ()
    {
        ClearScreen.getInstance().clearScreen();
    }
}
