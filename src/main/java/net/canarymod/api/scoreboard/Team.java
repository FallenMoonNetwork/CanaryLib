package net.canarymod.api.scoreboard;

import java.util.List;

import net.canarymod.api.entity.living.humanoid.Player;

/** 
 * Scoreboard team wrapper
 *
 * @author Somners 
 */
public interface Team {

    /**
     * Gets the internal protocol name of this team.
     *
     * @return The team name.
     */
    public String getProtocolName();

    /**
     * Gets the display name of this team.
     *
     * @return The team name.
     */
    public String getDisplayName();

    /**
     * Sets the display name of this team.
     *
     * @param name
     *         name to set.
     */
    public void setDisplayName(String name);

    /**
     * Gets the prefix for this team.
     *
     * @return The prefix for this team.
     */
    public String getPrefix();

    /**
     * Sets the prefix for this team.
     *
     * @param prefix
     *         The prefix to set.
     */
    public void setPrefix(String prefix);

    /**
     * Gets the suffix for this team.
     *
     * @return This teams suffix.
     */
    public String getSuffix();

    /**
     * Sets the suffix for this team.
     *
     * @param suffix
     *         The suffix to set.
     */
    public void setSuffix(String suffix);

    /**
     * Get all players on this team.
     *
     * @return A list of the players on this team.
     */
    public List<Player> getPlayers();

    /**
     * Get all players names on this team.
     *
     * @return A list of the players names on this team.
     */
    public List<String> getPlayerNames();

    /**
     * Adds a player to this team.
     *
     * @param player
     *         The player to add.
     */
    public void addPlayer(Player player);

    /**
     * Checks if the given player is on this team.
     *
     * @param player
     *         Player to check.
     *
     * @return True if the player is on this team, false otherwise.
     */
    public boolean hasPlayer(Player player);

    /**
     * Removes the given player from this team.
     *
     * @param player
     *         The player to remove.
     */
    public void removePlayer(Player player);

    /**
     * Gets the scoreboard this team is associated with.
     *
     * @return The Scoreboard.
     */
    public Scoreboard getScoreboard();

    /**
     * Gets whether or not friendly fire is allowed on this team.
     *
     * @return true if friendly fire is allowed, false otherwise.
     */
    public boolean getAllowFriendlyFire();

    /**
     * Sets whether or not friendly fire is allowed on this team.
     *
     * @param bool
     *         value to set.
     */
    public void setAllowFriendlyFire(boolean bool);

    /**
     * Gets whether or not teamates can see their invisible teamates.
     *
     * @return True if they can see invsible teamates, false otherwise.
     */
    public boolean getSeeFriendlyInvisibles();

    /**
     * Sets whether or not teamates can see their invisible teamates.
     *
     * @param bool
     *         value to set.
     */
    public void setSeeFriendlyInvisibles(boolean bool);
}
