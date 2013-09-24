package net.canarymod.api.scoreboard;

import java.util.List;

/**
 * ScoreObjectiveCriteria wrapper
 *
 * @author Somners 
 */
public interface ScoreObjectiveCriteria {

    /**
     * Gets the protocol name assigned to this {@link ScoreObjectiveCriteria}.
     *
     * @return The internal protocol name.
     */
    public String getProtocolName();

    /**
     * Used to get a score based on this criteria<br>
     * This method is used primarily for readOnly
     *
     * @param list
     *         List of parameters.
     *
     * @return The score.
     */
    public int getScore(List<?> list);

    /**
     * Is read only? If this is true you cannot add or remove to the score.
     * The score can only be set by {@link Score#setReadOnlyScore}
     *
     * @return true if read only, false otherwise.
     */
    public boolean isReadOnly();
}
