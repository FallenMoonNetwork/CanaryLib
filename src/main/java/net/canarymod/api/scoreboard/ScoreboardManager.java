package net.canarymod.api.scoreboard;

import net.canarymod.api.world.World;

/** @author Somners */
public interface ScoreboardManager {

    /**
     * Get the Scoreboard for this world.
     *
     * @param worldName
     *         Name of the world to get the scoreboard for.
     *
     * @return the Scoreboard.
     */
    public Scoreboard getScoreboard(String worldName);

    /**
     * Get the Scoreboard for this world.
     *
     * @param world
     *         The world to get the scoreboard for.
     *
     * @return the Scoreboard.
     */
    public Scoreboard getScoreboard(World world);

    /**
     * Gets a registered {@link ScoreObjectiveCriteria}
     *
     * @param name
     *         the protocol name of the criteria to get.
     *
     * @return The {@link ScoreObjectiveCriteria} or null if it is not registered.
     */
    public ScoreObjectiveCriteria getScoreCriteria(String name);

    /**
     * Registers the {@link ScoreObjectiveCriteria} if it is not already registered.
     *
     * @param name
     *         the protocol name of the criteria to register.
     * @param criteria
     *         the class to register.
     */
    public void registerScoreCriteria(String name, Class<? extends ScoreObjectiveCriteria> criteria);
}
