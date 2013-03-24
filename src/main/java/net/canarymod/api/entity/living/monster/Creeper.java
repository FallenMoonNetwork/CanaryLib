package net.canarymod.api.entity.living.monster;


import net.canarymod.api.entity.Explosive;


/**
 * Creeper Wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface Creeper extends EntityMob, Explosive {

    /**
     * Set this creeper charged (as if hit by lightning)
     * 
     * @return {@code true} if charged; {@code false} if not
     */
    public boolean isCharged();

    /**
     * Set this creeper charged (as if hit by lightning)
     * 
     * @param charged
     */
    public void setCharged(boolean charged);

}
