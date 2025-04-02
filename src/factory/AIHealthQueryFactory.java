package factory;

import UI.iFeatureUI;
import exceptions.FeatureInitializationException;
import feature.aiQuery.AIHealthQueryUI;
import feature.aiQuery.MistralAIHealthQueryService;
import feature.aiQuery.iAIHealthQuery;

public class AIHealthQueryFactory implements iFeatureFactory{
    @Override
     public iFeatureUI create() throws FeatureInitializationException 
     {
        try
        {
            iAIHealthQuery aiHealthQuery = new MistralAIHealthQueryService("ethHhWEx4lbnwSQYWjPO1S6ESSl1YvT1", "https://api.mistral.ai/v1/chat/completions");
            // AIHealthQueryUI aiHealthQueryUI = new AIHealthQueryUI(aiHealthQuery);
            return new AIHealthQueryUI(aiHealthQuery);
        }
        catch (Exception e)
        {
            throw new FeatureInitializationException("Failed to initialize ai health query feature", e);
        }

     }
}
