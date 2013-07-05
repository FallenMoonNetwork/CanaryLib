package net.canarymod.api.entity.living.monster;

import net.canarymod.api.entity.living.humanoid.Player;

/**
 * PigZombie wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface PigZombie extends Zombie {

    /**
     * basic check to see if this PigZombie is angry
     * 
     * @return true if angry
     */
    public boolean isAngry();

    /**
     * gets this PigZombie's anger level
     * 
     * @return level
     */
    public int getAngerLevel();

    /**
     * sets this PigZombie's anger level
     * 
     * @param level
     */
    public void setAngerLevel(int level);

    /**
     * Argo on the specified {@link Player}
     * 
     * @param player
     *            the {@link Player} to become angry at
     */
    public void becomeAngryAt(Player player);

    /**
     * Get mad!
     * 
     * @param entity
     */
    public void dontMakeLemonade(Player player);
}
