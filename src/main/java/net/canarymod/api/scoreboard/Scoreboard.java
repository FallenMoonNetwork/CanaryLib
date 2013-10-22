package net.canarymod.api.scoreboard;

import java.util.List;
import net.canarymod.api.entity.living.humanoid.Player;

/**
 * Scoreboard wrapper interface
 *
 * @author Aaron (somners)
 */
public interface Scoreboard {

    /**
     * Gets a list of Scoreoard Objectives associated with this scoreboard.
     *
     * @return List of ScoreObjective instances.
     */
    public List<ScoreObjective> getScoreObjectives();

    /**
     * Creates and Adds a new ScoreObjective to this Scoreboard.
     *
     * @param name
     *         Name of Objective to add.
     */
    public void addScoreObjective(String name);

    /**
     * Creates and Adds a scoreboard objective.
     *
     * @param name
     *         The protocol name of the objective to add.
     * @param criteria
     *         The custom ScoreObjective Criteria<br>
     *         This can be null to use a dummy non-read-only criteria.<br>
     *         <b>NOTE: </b> This is only needed if you wish to make it a read-only
     *         score, if this is not the case use {@link #addScoreObjective(String)}
     *
     * @see #addScoreObjective(String)
     */
    public void addScoreObjective(String name, ScoreObjectiveCriteria criteria);

    /**
     * Removes a ScoreObjective from this Scoreboard.
     *
     * @param objective
     *         The Objective to remove.
     */
    public void removeScoreObjective(ScoreObjective objective);

    /**
     * Removes a ScoreObjective from this Scoreboard.
     *
     * @param name
     *         The name of the Objective to remove.
     */
    public void removeScoreObjective(String name);

    /**
     * Get a scoreboard objective.
     *
     * @param name
     *         The protocol name of the objective to get.
     *
     * @return The objective or null if it does not exist.
     */
    public ScoreObjective getScoreObjective(String name);

    /**
     * Get a List of Team instances associated with this Scoreboard.
     *
     * @return the List of Teams.
     */
    public List<Team> getTeams();

    /**
     * Adds a new Team to this Scoreboard.
     *
     * @param team
     *         the {@link Team} to add
     */
    public void addTeam(Team team);

    /**
     * Removes a team from this scoreboard.
     *
     * @param team
     *         the team to remove.
     */
    public void removeTeam(Team team);

    /**
     * Removes a team from this scoreboard.
     *
     * @param name
     *         name of the team to remove.
     */
    public void removeTeam(String name);

    /**
     * Gets the score for the given player and given score objective.
     *
     * @param player
     *         name oft he player to get a score for.
     * @param scoreObjective
     *         the objective to get a score for.
     *
     * @return {@link Score}
     */
    public Score getScore(Player player, ScoreObjective scoreObjective);

    /**
     * Gets the score for the given player and given score objective.
     *
     * @param name
     *         name of the score to get
     * @param scoreObjective
     *         the objective to get a score for.
     *
     * @return {@link Score}
     */
    public Score getScore(String name, ScoreObjective scoreObjective);

    /**
     * Gets a list of scores for the given score objective.
     *
     * @param scoreObjective
     *         score objective to get scores for.
     *
     * @return list of {@link Score}
     */
    public List<Score> getScores(ScoreObjective scoreObjective);

    /**
     * Gets a list of all scores for all objectives.
     *
     * @return list of {@link Score}
     */
    public List<Score> getAllScores();

    /**
     * Sets the {@link ScoreObjective}'s position for all players on the server.
     *
     * @param type
     *         the position type.
     * @param objective
     *         the objective to set.
     */
    public void setScoreObjectivePostion(ScorePosition type, ScoreObjective objective);

    /**
     * Sets the {@link ScoreObjective}'s position for the given player.
     *
     * @param type
     *         the position type.
     * @param objective
     *         the objective to set.
     * @param player
     *         the player to set the scoreboard for.
     */
    public void setScoreObjectivePostion(ScorePosition type, ScoreObjective objective, Player player);

}
