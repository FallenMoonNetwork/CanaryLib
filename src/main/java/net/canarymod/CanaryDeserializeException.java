package net.canarymod;

/**
 * Exception that will be thrown if the serialized String 
 * of a CanarySerializable object does not have the expected lenght
 * @author Chris Ksoll
 *
 */
public class CanaryDeserializeException extends RuntimeException {

    private static final long serialVersionUID = 4068917113777742144L;

    public CanaryDeserializeException(String message) {
        super(message);
    }
}
