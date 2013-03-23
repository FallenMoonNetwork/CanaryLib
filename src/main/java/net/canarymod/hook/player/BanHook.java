package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;

/**
 * Ban hook. Contains information about an issued Ban
 * @author Chris Ksoll
 * @author Jason Jones
 */
public final class BanHook extends Hook {
    private Player banned;
    private Player moderator;
    private String ip;
    private String reason;
    private boolean ipban;

    public BanHook(Player banned, String ip, Player moderator, String reason) {
        this.banned = banned;
        this.moderator = moderator;
        this.reason = reason;
        this.ip = ip;
        this.ipban = ip != null;
    }

    /**
     * Get the {@link Player} that has been banned
     * @return banned if not ipban, null otherwise
     */
    public Player getBannedPlayer() {
        return banned;
    }

    /**
     * Checks if this is an IP ban
     * @return true if is IP ban
     */
    public boolean isIpBan(){
        return ipban;
    }

    /**
     * Gets the banned IP address
     * @return ip if is ipban, null otherwise
     */
    public String getIp(){
        return ip;
    }

    /**
     * Get the {@link Player} that has issued the ban
     * @return moderator
     */
    public Player getModerator() {
        return moderator;
    }

    /**
     * Gets the reason for the ban
     * @return reason
     */
    public String getReason(){
        return reason;
    }

    /**
     * Return the set of Data in this order: BANNED IP MODERATOR REASON
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{ banned, ip, moderator, reason };
    }
}
