package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;

/**
 * Ban hook. Contains information about an issued Ban
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public final class BanHook extends Hook {
    private Player banned;
    private Player moderator;
    private String ip;
    private String reason;
    private boolean ipban;
    private long bantime;

    public BanHook(Player banned, String ip, Player moderator, String reason, long bantime) {
        this.banned = banned;
        this.moderator = moderator;
        this.reason = reason;
        this.ip = ip;
        this.ipban = ip != null;
        this.bantime = bantime;
    }

    /**
     * Get the {@link Player} that has been banned.
     * This might be null if the banned player is offline/not currently on the server
     *
     * @return banned if not ipban, null otherwise
     */
    public Player getBannedPlayer() {
        return banned;
    }

    /**
     * Checks if this is an IP ban
     *
     * @return true if is IP ban
     */
    public boolean isIpBan() {
        return ipban;
    }

    /**
     * Gets the banned IP address
     *
     * @return ip if is ipban, null otherwise
     */
    public String getIp() {
        return ip;
    }

    /**
     * Get the {@link Player} that has issued the ban.
     * This may be null if the ban was issued from the console
     *
     * @return moderator
     */
    public Player getModerator() {
        return moderator;
    }

    /**
     * Gets the reason for the ban
     *
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Return the ban time as unix timestamp.
     * This is the amount of seconds this ban lasts
     *
     * @return the bantime, -1 if ban is a perma-ban
     */
    public long getBantime() {
        return bantime;
    }

    @Override
    public final String toString() {
        return String.format("%s[Banned=%s, Moderator=%s, isIPBan=%b, Reason=%s, BanTime=%s]", getName(), banned, moderator, ipban, reason, bantime);
    }
}
