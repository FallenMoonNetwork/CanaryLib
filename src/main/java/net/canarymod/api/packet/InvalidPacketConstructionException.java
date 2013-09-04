package net.canarymod.api.packet;

/**
 * Called when a Packet that cannot be constructed is attempted to be constructed
 *
 * @author Jason (darkdiplomat)
 */
public class InvalidPacketConstructionException extends Exception {
    private final static String construct_fail = "Cannot construct Packet %d [%s] (%s). %s";

    public InvalidPacketConstructionException(int packetID, String packetHex, String name, String extra) {
        super(String.format(construct_fail, packetID, packetHex, name, extra));
    }
}
