package net.canarymod.api.scoreboard;

import java.util.List;

/** 
 * Scoreboard score
 *
 * @author Aaron (somners) 
 */
public interface Score {

    /**
     * Gets the name of this score.
     *
     * @return score name
     */
    public String getName();

    /**
     * Adds to the score
     *
     * @param toAdd
     *         amount to add
     */
    public void addToScore(int toAdd);

    /**
     * removes from the score
     *
     * @param toRemove
     *         amount to remove
     */
    public void removeFromScore(int toRemove);

    /**
     * Sets the score
     *
     * @param toSet
     *         value to set thes score to.
     */
    public void setScore(int toSet);

    /**
     * Gets the score
     *
     * @return the score.
     */
    public int getScore();

    /**
     * Gets the ScoreObjective for this score.
     *
     * @return the score objective
     */
    public ScoreObjective getScoreObjective();

    /**
     * Gets the scoreboard for this Score.
     *
     * @return The Scoreboard.
     */
    public Scoreboard getScoreboard();

    /**
     * This Method is used to set the score if it is a read-only score.<br>
     * This only needs to be done if you made a custom {@link ScoreObjectiveCriteria} to go with a {@link ScoreObjective}
     *
     * @param list
     *         The list of parameters needed to update this score.
     */
    public void setReadOnlyScore(List<?> list);

    /** Updates this Score to all clients connected to the server. */
    public void update();
}
