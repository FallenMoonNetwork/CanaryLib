package net.canarymod.api.scoreboard;

/** 
 * ScoreObjective wrapper
 *
 * @author Somners 
 */
public interface ScoreObjective {

    /**
     * Get the protocol name for this score objective. This name is for internal
     * use only. It can be thought of as the "key" or "identifier" of this
     * score objective.
     *
     * @return The protocol name.
     */
    public String getProtocolName();

    /**
     * Gets the ScoreObjectiveCriteria for this ScoreObjective.
     *
     * @return The score Objective Criteria.
     */
    public ScoreObjectiveCriteria getScoreObjectiveCriteria();

    /**
     * Gets the display name for this ScoreObjective. This is the name that will
     * be seen in game by players.
     *
     * @return The display name.
     */
    public String getDisplayName();

    /**
     * Sets and updates the display name to all clients.
     *
     * @param name
     *         The name you wish to set the display name to.
     */
    public void setDisplayName(String name);
}
