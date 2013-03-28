package net.canarymod.api;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.nbt.BaseTag;

/**
 * This represents an Entity to spawn from a Mob Spawner.
 * @author Somners
 */
public interface MobSpawnerEntry {

    /**
     * Sets the chance that this entry will spawn compared to other entries.
     *
     * @param i The chance that this entry will spawn.
     */
    public void setWeight(int i);

    /**
     * Gets the chance that this entry will spawn compared to other entries.
     *
     * @return The chance that this entry will spawn.
     */
    public int getWeight();

    /**
     * Get an instance of the Entity representing this Entry.
     *
     * @return An instance of the Entity this Entry will spawn.
     */
    public Entity getEntity();

    /**
     * Set an instance of the Entity we wish to spawn with this Entry.
     *
     * @param entity The entity to spawn.
     */
    public void setEntity(Entity entity);

    /**
     * Checks if this is a valid Mob Spawner Entry. Basicaly makes sure the entity
     * set is not null;
     *
     * @return true - This Entry is Valid<br>false - This Entry is not valid.
     */
    public boolean isValid();

    /**
     * Get the 'SpawnPotentials' NBT Tag for this Entry.<br>
     * NOTE: This should probably only be used by Canary.
     *
     * @return A 'SpawnPotentials' NBT Tag entry. Null if not a valid entry.
     */
    BaseTag getSpawnPotentialsTag();
}
