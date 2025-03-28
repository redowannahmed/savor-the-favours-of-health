import java.util.*;

import UI.FeatureUI;
import controller.AppController;
import feature.aiQuery.AIHealthQueryService;
import feature.aiQuery.AIHealthQueryUI;
import feature.aiQuery.MistralAIHealthQueryService;

public class Main {
    public static void main(String[] args) {

        // 1. Instantiate the AI feature
        AIHealthQueryService aiService =
                new MistralAIHealthQueryService("ethHhWEx4lbnwSQYWjPO1S6ESSl1YvT1",
                        "https://api.mistral.ai/v1/chat/completions");
        FeatureUI aiUI = new AIHealthQueryUI(aiService);



        // 4. Create the main menu with all features
        AppController app = new AppController(Arrays.asList(
            aiUI
            // ... add others ...
        ));

        // 5. Run!
        app.run();
    }
}