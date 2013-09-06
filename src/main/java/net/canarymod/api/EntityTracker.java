package net.canarymod.api;

import java.util.ArrayList;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.packet.Packet;
import net.canarymod.api.world.World;

/**
 * EntityTracker wrapper
 *
 * @author Chris (damagefilter)
 */
public interface EntityTracker {

    /**
     * Add an {@link Entity} to this entity tracker
     *
     * @param entity
     *         the {@link Entity} to be added
     */
    public void trackEntity(Entity entity);

    /**
     * Untrack the {@link Entity} given.
     *
     * @param entity
     *         the {@link Entity} to stop tracking
     */
    public void untrackEntity(Entity entity);

    /**
     * Untrack the player symmetrics for the given player.<br>
     * Call this after {@link EntityTracker#untrackEntity(Entity)} when you stop tracking a player!
     *
     * @param player
     *         the {@link Player} to stop tracking
     */
    public void untrackPlayerSymmetrics(Player player);

    /** Update all tracked entities inside this tracker. */
    public void updateTrackedEntities();

    /**
     * Get the dimension this entity tracker is in charge for
     *
     * @return the {@link World}
     */
    public World getAttachedDimension();

    /**
     * Send a {@link net.canarymod.api.packet.Packet} to a tracked {@link Player}
     *
     * @param player
     *         the {@link Player} to send the {@link net.canarymod.api.packet.Packet}
     * @param packet
     *         the {@link net.canarymod.api.packet.Packet} to be sent
     */
    public void sendPacketToTrackedPlayer(Player player, Packet packet);

    /**
     * Get an {@link ArrayList} of all tracked entities in this EntityTracker
     *
     * @return the {@link ArrayList} of tracked entities
     */
    public ArrayList<Entity> getTrackedEntities();
}
