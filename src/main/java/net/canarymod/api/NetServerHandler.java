package net.canarymod.api;


import net.canarymod.api.entity.living.humanoid.Player;


public interface NetServerHandler {
    
    /**
     * Add a packet to this players send queue.<br>
     * It will be sent when it's polled form the queue
     * @param packet
     */
    public void sendPacket(Packet packet);
    
    /**
     * Handle chat for the player attached to this serverhandler
     * @param chatPacket
     */
    public void handleChat(Packet chatPacket);
    
    /**
     * Make the attached player handle a slash command
     * @param command
     */
    public void handleCommand(String[] command);
    
    /**
     * Handle the respawn for the attached player
     * @param respawnPacket
     */
    public void handleRespawn(Packet respawnPacket);
    
    /**
     * Get the player that is attached to this NetServerHandler
     * @return
     */
    public Player getUser();
    
    /**
     * Privately send a message to the attached player
     * @param messgage
     */
    public void sendMessage(String messgage);
}
