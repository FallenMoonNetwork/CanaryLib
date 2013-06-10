package net.canarymod.api.entity.living.animal;

import net.canarymod.api.DyeColor;
import net.canarymod.api.entity.living.Ageable;

/**
 * Wolf wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface Wolf extends Tameable, Ageable {

    /**
     * Sets the Collar color
     * 
     * @param color
     */
    public void setCollarColor(DyeColor color);

    /**
     * Gets the Collar color
     * 
     * @return collar color
     */
    public DyeColor getCollarColor();
}
