package net.canarymod.api;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.packet.Packet;

/**
 * NetServerHandler wrapper
 *
 * @author Chris (damagefilter)
 */
public interface NetServerHandler {

    /**
     * Add a packet to this {@link Player}'s send queue.<br>
     * It will be sent when it's polled form the queue
     *
     * @param packet
     *         the {@link net.canarymod.api.packet.Packet} to be sent
     */
    public void sendPacket(Packet packet);

    /**
     * Handle chat for the {@link Player} attached to this NetServerHandler
     *
     * @param chatPacket
     *         the {@link Packet} for chat
     */
    public void handleChat(Packet chatPacket);

    /**
     * Make the attached {@link Player} handle a slash command
     *
     * @param command
     *         the String array of the command and arguments
     */
    public void handleCommand(String[] command);

    /**
     * Handle the respawn for the attached {@link Player}
     *
     * @param respawnPacket
     *         the {@link Packet} for respawn
     */
    public void handleRespawn(Packet respawnPacket);

    /**
     * Get the {@link Player} that is attached to this NetServerHandler
     *
     * @return the attached {@link Player}
     */
    public Player getUser();

    /**
     * Privately send a message to the attached {@link Player}
     *
     * @param messgage
     *         the message to be sent
     */
    public void sendMessage(String messgage);
}
