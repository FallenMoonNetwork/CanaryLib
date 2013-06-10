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

    /**
     * Gets if the Creeper is preparing to take everything out with it
     * 
     * @return {@code true} if agro; {@code false} if not
     */
    public boolean isAgro();

    /**
     * Sets the Creeper agro state
     * 
     * @param agro
     *            {@code true} if agro; {@code false} if not
     */
    public void setAgro(boolean agro);

}
