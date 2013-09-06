package net.canarymod.api.world;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Location;

/**
 * Village wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface Village {

    /**
     * Sets the Reputation for a Player
     *
     * @param player
     *         the Player to set rep for
     * @param rep
     *         the amount of reputation to set
     */
    public void setReputationForPlayer(Player player, int rep);

    /**
     * Gets the reputation for a Player
     *
     * @param player
     *         the Player to check rep for
     *
     * @return the reputation value
     */
    public int getReputationForPlayer(Player player);

    /**
     * Gets whether a Player's rep is too low (less than -15)
     *
     * @param player
     *         the Player to check rep for
     *
     * @return {@code true} if the Player's rep is below -15; {@code false} if not
     */
    public boolean isPlayerReputationTooLow(Player player);

    /**
     * Checks if the Village is in mating season
     *
     * @return {@code true} if it is mating season; {@code false} if not
     */
    public boolean isMatingSeason();

    /** Starts the Village's mating season */
    public void startMatingSeason();

    /** Ends the village's mating season */
    public void endMatingSeason();

    /**
     * Gets the center location of the Village
     *
     * @return center of the village
     */
    public Location getCenter();

    /**
     * Gets the radius the Village encompasses
     *
     * @return radius of the Village
     */
    public int getRadius();

    /**
     * Gets the number of Villagers in the Village
     *
     * @return villager count
     */
    public int getVillagerCount();

    /**
     * Gets the number of IronGolems in the Village
     *
     * @return IronGolem count
     */
    public int getIronGolemCount();

    /**
     * Gets if the Village has been raped and pillaged (ie: all doors destroyed)
     *
     * @return {@code true} if annihilated; {@code false} if not
     */
    public boolean isAnnihilated();

}
