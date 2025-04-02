package factory;

import UI.iFeatureUI;
import exceptions.FeatureInitializationException;

public interface iFeatureFactory {
    iFeatureUI create() throws FeatureInitializationException;
}
