package controller;
import java.util.Map;

import UI.AbstractFeatureUI;
import UI.iFeatureUI;
import exceptions.FeatureInitializationException;
import factory.FeatureRegistry;
import factory.iFeatureFactory;


public class AppController extends AbstractFeatureUI{
    private final FeatureRegistry featureRegistry;
    private boolean isRunning = true;

    public AppController(FeatureRegistry featureRegistry) {
        this.featureRegistry = featureRegistry;
    }

    @Override
    public String getTitle() {
        return "Health Tracker Application";
    }

    @Override
    public void run() {
        while (isRunning) {
            displayMainMenu();
            String choice = inputProcessor.readLine("Choose an option: ");
            handleMainMenuChoice(choice);
        }
    }

    private void displayMainMenu() {
        clearScreen();
        inputProcessor.print("=== " + getTitle() + " ===");
        
        int index = 1;
        Map<String, iFeatureFactory> features = featureRegistry.getAvailableFeatures();
        for (String featureName : features.keySet()) {
            inputProcessor.print(index + ". " + featureName);
            index++;
        }
        inputProcessor.print("0. Exit Application");
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
                inputProcessor.print("Invalid choice. Please try again.");
                pause();
            }
        } catch (NumberFormatException e) {
            inputProcessor.print("Please enter a valid number.");
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
