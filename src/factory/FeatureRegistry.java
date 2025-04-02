package factory;

import java.util.LinkedHashMap;
import java.util.Map;

import UI.iFeatureUI;
import exceptions.FeatureInitializationException;

public class FeatureRegistry {
    private final Map<String, iFeatureFactory> featureFactories = new LinkedHashMap<>();

    public void registerFeature(String name, iFeatureFactory factory) {
        featureFactories.put(name, factory);
    }

    public iFeatureUI createFeature(String name) throws FeatureInitializationException {
        if (!featureFactories.containsKey(name)) {
            throw new FeatureInitializationException("Feature not found: " + name);
        }
        return featureFactories.get(name).create();
    }

    public Map<String, iFeatureFactory> getAvailableFeatures() {
        return new LinkedHashMap<>(featureFactories);
    }
}
