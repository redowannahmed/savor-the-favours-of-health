import java.util.*;

import UI.iFeatureUI;
import controller.AppController;
import feature.aiQuery.iAIHealthQuery;
import feature.aiQuery.AIHealthQueryUI;
import feature.aiQuery.MistralAIHealthQueryService;

public class Main {
    public static void main(String[] args) {

        // 1. Instantiate the AI feature
        iAIHealthQuery aiService =
                new MistralAIHealthQueryService("ethHhWEx4lbnwSQYWjPO1S6ESSl1YvT1",
                        "https://api.mistral.ai/v1/chat/completions");
        iFeatureUI aiUI = new AIHealthQueryUI(aiService);
        
        // 4. Create the main menu with all features
        AppController app = new AppController(Arrays.asList(
            aiUI
            // ... add others ...
        ));

        // 5. Run!
        app.run();
    }
}