package net.canarymod.api.entity.living;

import net.canarymod.api.world.Village;

/**
 * IronGolem wrapper interface
 *
 * @author Jason (darkdiplomat)
 */
public interface IronGolem extends Golem {

    /**
     * Gets the Village the IronGolem is attached to
     *
     * @return the Village the IronGolem is attached to
     */
    public Village getVillage();

    /**
     * Sets the Village the IronGolem is attached to
     *
     * @param village
     *         the Village to attach the IronGolem to
     */
    public void setVillage(Village village);

    /**
     * Checks if the IronGolem was created by a Player
     *
     * @return {@code true} if was Player created; {@code false} if not
     */
    public boolean isPlayerCreated();

    /**
     * Sets if the IronGolem was created by a Player
     *
     * @param created
     *         {@code true} if was Player created; {@code false} if not
     */
    public void setPlayerCreated(boolean created);

    /**
     * Checks if the IronGolem is holding a rose
     *
     * @return {@code true} if holding a rose; {@code false} if not
     */
    public boolean isHoldingRose();

    /**
     * Sets whether the IronGolem is holding a rose
     *
     * @param holding
     *         {@code true} if holding a rose; {@code false} if not
     */
    public void setHoldingRose(boolean holding);

    /**
     * Gets the ticks that the IronGolem should hold a rose.<br>
     * When this reaches 0, the IronGolem stops holding a rose.
     *
     * @return the holding rose ticks
     */
    public int getHoldRoseTicks();

    /**
     * Sets the ticks that the IronGolem should hold a rose.
     *
     * @param ticks
     *         the amount of holding rose ticks
     */
    public void setHoldRoseTicks(int ticks);
}
