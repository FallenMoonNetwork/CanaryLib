package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.Hook;

/**
 * Ban hook. Contains information about an issued Ban
 * @author Chris Ksoll
 * @author Jason Jones
 *
 */
public class BanHook extends Hook {
    private Player banned;
    private Player moderator;
    private String ip;
    private boolean ipban;
    
    public BanHook(Player banned, String ip, Player moderator) {
        this.banned = banned;
        this.moderator = moderator;
        this.ip = ip;
        this.ipban = ip != null;
        this.type = Type.BAN;
    }
    
    /**
     * Get the player that has been banned
     * @return banned if not ipban, null otherwise
     */
    public Player getBannedPlayer() {
        return banned;
    }
    
    /**
     * checks if this is an IP ban
     * @return true if is IP ban
     */
    public boolean isIpBan(){
        return ipban;
    }
    
    /**
     * gets the banned IP address
     * @return ip if is ipban, null otherwise
     */
    public String getIp(){
        return ip;
    }
    
    /**
     * Get the player that has issued the ban
     * @return moderator
     */
    public Player getModerator() {
        return moderator;
    }
    
    /**
     * Return the set of Data in this order: BANNED IP MODERATOR
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{ banned, ip, moderator };
    }

}
