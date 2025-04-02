package exceptions;

public class FeatureInitializationException extends Exception {
    public FeatureInitializationException(String message) {
        super(message);
    }

    public FeatureInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}