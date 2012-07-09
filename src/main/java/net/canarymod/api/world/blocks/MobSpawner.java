package net.canarymod.api.world.blocks;

public interface MobSpawner extends ComplexBlock{
    
    /**
     * Get the animal/mob type set for the spawner
     * @return The spawned type
     */
    public String getSpawnType();
    
    /**
     * Set the animal/mob type
     * @param spawn
     */
    public void setSpawnType(String spawn);
    
    /**
     * Get the ticks between two spawns
     * @return
     */
    public int getDelay();
    
    /**
     * Set the ticks between two spawns
     * @param delay
     */
    public void setDelay(int delay);
}
