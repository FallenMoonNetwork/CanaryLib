package net.canarymod.api;

import net.canarymod.MathHelp;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.TextFormat;
import net.canarymod.config.Configuration;

/**
 * PlayerListEntry
 * <p/>
 * The information shown in the PlayerList ("Tab List")
 *
 * @author Jason (darkdiplomat)
 * @author Talmor
 */
public final class PlayerListEntry implements Cloneable {

    private Player player;
    private String name;
    private int ping;
    private boolean shown;

    /**
     * Constructs a new PlayerListEntry
     *
     * @param name
     *         the name to show in the PlayerList, max length of 16 characters
     * @param ping
     *         the ping to show in the PlayerList
     * @param shown
     *         whether to actually show on the list or not
     */
    public PlayerListEntry(String name, int ping, boolean shown) {
        this.setName(name);
        this.setPing(ping);
        this.shown = shown;
    }

    /**
     * Constructs a new PlayerListEntry
     *
     * @param player
     *         the {@link Player} this entry is for
     * @param shown
     *         whether to actually show on the list or not
     */
    public PlayerListEntry(Player player, boolean shown) {
        this.player = player;
        this.setName(player.getName());
        this.setPing(player.getPing());
        this.shown = shown;
    }

    /** Internal constructor for cloning */
    private PlayerListEntry(Player player, String name, int ping, boolean shown) {
        this.player = player;
        this.name = name;
        this.ping = ping;
        this.shown = shown;
    }

    /**
     * Gets whether this entry is to be shown
     *
     * @return {@code true} if shown; {@code false} otherwise
     */
    public final boolean isShown() {
        return shown;
    }

    /**
     * Sets whether this entry is to be shown
     *
     * @param shown
     *         {@code true} to show; {@code false} otherwise
     */
    public final void setShown(boolean shown) {
        this.shown = shown;
    }

    /**
     * Gets the name to be displayed in the list
     *
     * @return the name to be displayed
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets the name to be displayed in the list
     *
     * @param name
     *         the name to be set
     */
    public final void setName(String name) {
        if (Configuration.getServerConfig().isPlayerlistColorsEnabled()) {
            // Adjust length to account for formatting
            this.name = name.substring(0, Math.min(name.length(), 14)) + TextFormat.RESET;
        }
        else {
            // Formatting is disabled, remove it.
            name = TextFormat.removeFormatting(name);
            this.name = name.substring(0, Math.min(name.length(), 16));
        }
    }

    /**
     * Gets the ping to be displayed for the entry
     *
     * @return the ping
     */
    public final int getPing() {
        return ping;
    }

    /**
     * Sets the ping to be displayed for the entry. Should not be set higher than {@link Short#MAX_VALUE} or less than 0. (Will adjust as needed)
     *
     * @param ping
     *         the ping to display
     */
    public final void setPing(int ping) {
        this.ping = MathHelp.setInRange(ping, 0, 9999); // Adjust as needed
    }

    /**
     * Gets the {@link Player} this entry if for if set
     *
     * @return {@link Player} if player is set; {@code null} if player is not set
     */
    public final Player getPlayer() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public final PlayerListEntry clone() {
        return new PlayerListEntry(player, name, ping, shown);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PlayerListEntry) {
            PlayerListEntry other = (PlayerListEntry) obj;
            if (!other.getName().equals(this.name)) {
                return false;
            }
            if (other.getPing() != this.ping) {
                return false;
            }
            return other.isShown() == this.shown;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public final String toString() {
        return String.format("PlayerListEntry[Name=%s Shown=%b Ping=%d]", name, shown, ping);
    }
}
