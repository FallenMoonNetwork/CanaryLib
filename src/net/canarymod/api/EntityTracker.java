package net.canarymod.api;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.Player;

public interface EntityTracker {
    /**
     * Add an entity to this entity tracker
     * @param entity
     */
    public void trackEntity(Entity entity);
    
    /**
     * Untrack the entity given.
     * @param entity
     */
    public void untrackEntity(Entity entity);
    
    /**
     * Untrack the player symmetrics for the given player.
     * (Call this after {@link EntityTracker#untrackEntity(Entity)} when you're untracking a player!)
     * @param player
     */
    public void untrackPlayerSymmetrics(Player player);
}
