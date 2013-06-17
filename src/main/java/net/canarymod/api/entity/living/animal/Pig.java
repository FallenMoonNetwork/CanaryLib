package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.living.Ageable;

/**
 * Pig wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface Pig extends EntityAnimal, Ageable {

    /**
     * Gets whether the Pig has a saddle or not
     * 
     * @return {@code true} if saddled; {@code false} if not
     */
    public boolean isSaddled();

    /**
     * Sets whether the Pig has a saddle or not
     * 
     * @param saddle
     *            {@code true} for saddled; {@code false} for not saddled
     */
    public void setSaddled(boolean saddle);
}
