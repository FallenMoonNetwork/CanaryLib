package net.canarymod.api.packet;

/**
 * Called when a Packet that cannot be constructed is attempted to be constructed
 *
 * @author Jason (darkdiplomat)
 */
public class InvalidPacketConstructionException extends Exception {

    private static final long serialVersionUID = -7450608024867618509L;
    private final static String construct_fail = "Cannot construct Packet %d [%s] (%s). %s";

    public InvalidPacketConstructionException(int packetID, String packetHex, String name, String extra) {
        super(String.format(construct_fail, packetID, packetHex, name, extra));
    }
}
