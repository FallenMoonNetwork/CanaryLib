package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.living.Ageable;

/**
 * Chicken Wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface Chicken extends EntityAnimal, Ageable {

    /**
     * Gets the ticks until laying the next Egg
     *
     * @return tick until next egg
     */
    public int getTimeUntilNextEgg();

    /**
     * Time in ticks (~20/Second) until the chicken lays the next egg.
     * Set 0 to make it lay an egg more or less instantly
     *
     * @param timeTicks
     *         the ticks until the next egg
     */
    public void setTimeUntilNextEgg(int timeTicks);
}
