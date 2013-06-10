package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.Entity;

/**
 * Bat wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface Bat extends Entity {

    /**
     * Tells if this Bat is hanging or not
     * 
     * @return {@code true} if hanging; {@code false} if not
     */
    public boolean isHanging();

    /**
     * Sets wheter or not this Bat is hanging
     * 
     * @param hanging
     *            {@code true} for hanging; {@code false} for not
     */
    public void setHanging(boolean hanging);

}
