package net.canarymod.api;

import net.canarymod.api.entity.Player;

public interface PlayerManager {
    /**
     * Update a mounted moving player
     * @param player
     */
    public void updateMountedMovingPlayer(Player player);
    
    /**
     * Add the given player to this player manager
     * @param player
     */
    public void addPlayer(Player player);
    
    /**
     * Removre the given player from this player manager
     * @param player
     */
    public void removePlayer(Player player);
    
    /**
     * Mark a block at this position for updating for this playerManager
     * @param x
     * @param y
     * @param z
     */
    public void markBlockNeedsUpdate(int x, int y, int z);
}
