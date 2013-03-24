package net.canarymod.api;


/**
 * Packet interface.
 * This interface is not automatically applied to packets,
 * for performance reason packets should be wrapped on demand only!
 * @author Chris Ksoll
 *
 */
public interface Packet {

    /**
     * Get the packet size
     * @return
     */
    public int getPacketSize();
    
    /**
     * Returns the ID for this packet.<br>
     * For example the Id of packet3Chat is 3
     * @return
     */
    public int getPacketId();
}
