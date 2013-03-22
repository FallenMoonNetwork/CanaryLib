package net.canarymod.api.world.blocks;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.inventory.Item;

/**
 * Wraps the MobSpawnerLogic
 * 
 * @author Jason (darkdiplomat)
 * @author Willem (l4mRh4X0r)
 */
public interface MobSpawnerLogic{

    /**
     * Allows what to spawn to change on-the-fly.
     * <strong>NB:</strong> This only works if a complex entity has not been set.
     * 
     * @param spawn
     *            The name of the entity to spawn.
     * @see #setSpawnedEntity(BaseEntity)
     * @see #setSpawnedEntity(Item)
     */
    public void setSpawn(String spawn);

    /**
     * Returns the spawn used.
     * 
     * @return
     */
    public String getSpawn();

    /**
     * Allows delay of what to spawn to change on-the-fly.
     * Modification of this is near-useless as delays get randomized after
     * spawn.
     * 
     * @param delay
     */
    public void setDelay(int delay);

    /**
     * Returns the minimum delay of the spawner.
     * The delay between spawns is picked randomly between this and the max delay.
     * 
     * @return minDelay
     */
    public int getMinDelay();

    /**
     * Sets the minimum delay of the spawner.
     * The delay between spawns is picked randomly between this and the max delay.
     * Default is 200.
     * 
     * @param delay
     */
    public void setMinDelay(int delay);

    /**
     * Returns the maximum delay of the spawner.
     * The delay between spawns is picked randomly between this and the min delay.
     * 
     * @return maxDelay
     */
    public int getMaxDelay();

    /**
     * Sets the maximum delay of the spawner.
     * The delay between spawns is picked randomly between this and the min delay.
     * Default is 800.
     * 
     * @param delay
     */
    public void setMaxDelay(int delay);

    /**
     * Returns the amount of mobs this spawner attempts to spawn.
     * 
     * @return
     */
    public int getSpawnCount();

    /**
     * Sets the amount of mobs this spawner attempts to spawn.
     * Default is 4.
     * 
     * @param count
     */
    public void setSpawnCount(int count);

    /**
     * Returns the maximum number of entities this spawner allows nearby in order to continue spawning.
     * Any more entities and this spawner won't spawn mobs.
     * 
     * @return
     */
    public int getMaxNearbyEntities();

    /**
     * Sets the maximum number of entities this spawner allows nearby in order to continue spawning.
     * Any more entities and this spawner won't spawn mobs.
     * Default is 6.
     * 
     * @param entities
     */
    public void setMaxNearbyEntities(int entities);

    /**
     * If there are no players within this distance of the spawner, it won't spawn.
     * 
     * @return
     */
    public int getRequiredPlayerRange();

    /**
     * If there are no players within this distance of the spawner, it won't spawn.
     * Default is 16.
     * 
     * @param range
     */
    public void setRequiredPlayerRange(int range);

    /**
     * Returns the maximum distance that this spawner will spawn mobs at.
     * 
     * @return
     */
    public int getSpawnRange();

    /**
     * Sets the maximum distance that this spawner will spawn mobs at.
     * Default is 4.
     * 
     * @param range
     */
    public void setSpawnRange(int range);

    /**
     * Sets the entity spawned by this spawner.
     * 
     * @param entity
     *            The entity this spawner should spawn
     */
    public void setSpawnedEntity(Entity entity);

    /**
     * Sets the entity spawned by this spawner to an item.
     * 
     * @param item
     *            The item this spawner should spawn
     */
    public void setSpawnedEntity(Item item);

}
