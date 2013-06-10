package net.canarymod.api;

import java.util.ArrayList;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.Block;

/**
 * PlayerManager wrapper
 * 
 * @author Chris (damagefilter)
 */
public interface PlayerManager {

    /**
     * Update a mounted moving {@link Player}
     * 
     * @param player
     *            the {@link Player} to update
     */
    public void updateMountedMovingPlayer(Player player);

    /**
     * Add the given {@link Player} to this PlayerManager
     * 
     * @param player
     *            the {@link Player} to be added
     */
    public void addPlayer(Player player);

    /**
     * Remove the given {@link Player} from this PlayerManager
     * 
     * @param player
     *            the {@link Player} to be removed
     */
    public void removePlayer(Player player);

    /**
     * Get a list of all {@link Player}s that are managed by this PlayerManager
     * 
     * @return an {@link ArrayList} of {@link Player}s
     */
    public ArrayList<Player> getManagedPlayers();

    /**
     * Marks a {@link Block} at this position for updating for this PlayerManager
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param z
     *            the z coordinate
     */
    public void markBlockNeedsUpdate(int x, int y, int z);

    /**
     * Get the maximum tracking distance for this PlayerManager
     * 
     * @return the maximum tracking distance
     */
    public int getMaxTrackingDistance();

    /**
     * Get the dimension this PlayerManager is in charge of
     * 
     * @return the {@link World} dimension
     */
    public World getAttachedDimension();
}
