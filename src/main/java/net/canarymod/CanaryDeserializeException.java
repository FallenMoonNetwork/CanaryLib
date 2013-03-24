package net.canarymod;


/**
 * Exception that will be thrown if the serialized String
 * of a CanarySerializable object does not have the expected length
 * 
 * @author Chris Ksoll
 */
public class CanaryDeserializeException extends RuntimeException {

    private static final long serialVersionUID = 4068917113777742144L;
    String vendor;

    /**
     * Constructs a new {@code CanaryDeserializeException}
     * 
     * @param message
     *            the message of the exception that occurred
     * @param vendor
     *            the vendor
     */
    public CanaryDeserializeException(String message, String vendor) {
        super(message);
        this.vendor = vendor;
    }

    /**
     * Return the vendor of the serializer that threw this exception.
     * 
     * @return
     */
    public String getVendor() {
        return vendor;
    }
}
