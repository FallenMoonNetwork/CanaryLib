package net.canarymod.api.entity;

import net.canarymod.api.inventory.Item;

/**
 * FireworkRocket wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface FireworkRocket extends Entity {

    /**
     * Gets the {@link Item} attached to the FireworkRocket
     *
     * @return the attached {@link Item} or {@code null} if there isn't one attached
     */
    public Item getItem();

    /**
     * Sets the {@link Item} attached to the FireworkRocket
     *
     * @param item
     *         the {@link Item} to attach
     */
    public void setItem(Item item);

    /**
     * Gets the time the FireworkRocket has been alive
     *
     * @return the been alive time
     */
    public int getLifeTime();

    /**
     * Sets the time the FireworkRocket has been alive
     *
     * @param life
     *         the time the rocket has been alive
     */
    public void setLifeTime(int life);

    /**
     * Gets the max time the FireworkRocket can be alive
     *
     * @return the max life time
     */
    public int getLifeTimeMax();

    /**
     * Sets the max time the FireworkRocket can be alive
     *
     * @param life_time
     *         the max life time
     */
    public void setLifeTimeMax(int life_time);
}
