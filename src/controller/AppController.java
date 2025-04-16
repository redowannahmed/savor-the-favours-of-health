package controller;
import java.util.Map;

import UI.AbstractFeatureUI;
import UI.iFeatureUI;
import colorUtils.ColorUtil;
import exceptions.FeatureInitializationException;
import factory.FeatureRegistry;
import factory.iFeatureFactory;


public class AppController extends AbstractFeatureUI{
    private final FeatureRegistry featureRegistry;
    private boolean isRunning = true;

    public AppController(FeatureRegistry featureRegistry) {
        this.featureRegistry = featureRegistry;
    }

    public String getTitle() {
        return "Savor the favours of health";
    }

    @Override
    public void run() {
        while (isRunning) {
            displayMainMenu();
            System.out.println();
            String choice = inputProcessor.readLine(ColorUtil.applyNote("Choose an option: "));
            handleMainMenuChoice(choice);
        }
    }

    private void displayMainMenu() {
        clearScreen();
        inputProcessor.print(ColorUtil.applyHeader("=== " + getTitle() + " ==="));
        System.out.println();

        int index = 1;
        Map<String, iFeatureFactory> features = featureRegistry.getAvailableFeatures();
        for (String featureName : features.keySet()) {
            String optionText = String.format("%2d. %s", index, featureName);
            inputProcessor.print(ColorUtil.applyOption(optionText));
            index++;
        }
        
        inputProcessor.print("\n" + ColorUtil.applyOption(" 0. Exit Application"));
        
    }

    private void handleMainMenuChoice(String choice) {
        try {
            Map<String, iFeatureFactory> features = featureRegistry.getAvailableFeatures();
            
            if (choice.equals("0")) {
                isRunning = false;
                return;
            }

            int choiceIndex = Integer.parseInt(choice) - 1;
            if (choiceIndex >= 0 && choiceIndex < features.size()) {
                String featureName = (String) features.keySet().toArray()[choiceIndex];
                launchFeature(featureName);
            } else {
                inputProcessor.print(ColorUtil.applyError("Invalid choice. Please try again."));
                pause();
            }
        } catch (NumberFormatException e) {
            inputProcessor.print(ColorUtil.applyCaution("Please enter a valid number."));
            pause();
        } catch (FeatureInitializationException e) {
            inputProcessor.print("Error: " + e.getMessage());
            pause();
        }
    }

    private void launchFeature(String featureName) throws FeatureInitializationException {
        iFeatureUI feature = featureRegistry.createFeature(featureName);
        feature.run();
    }
}
