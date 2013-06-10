package net.canarymod.api.entity;

/**
 * EnderCrystal wrapper<br>
 * the Crystals that heal the EnderDragon
 * 
 * @author Jason (darkdiplomat)
 */
public interface EnderCrystal extends Entity, Explosive {

    /**
     * Gets the health of the EnderCrystal
     * 
     * @return health
     */
    public int getHealth();

    /**
     * Gets the health of the EnderCrystal
     * 
     * @param health
     */
    public void setHealth(int health);

    /**
     * Gets whether this EnderCrystal should detonate with only one strike (default is 1 hit kills)
     * 
     * @return {@true} if it can only take one hit; {@code false} if it can take more
     */
    public boolean isOneHitDetonate();

    /**
     * Sets whether this EnderCrystal should detonate with only one strike (default is 1 hit kills)
     */
    public void setOneHitDetonate(boolean oneHit);

}
