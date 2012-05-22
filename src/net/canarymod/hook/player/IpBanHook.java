package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.Hook;

/**
 * Ip Ban hook. Contains information about an issued IP Ban
 * @author Chris Ksoll
 *
 */
public class IpBanHook extends Hook {
    private String bannedIp;
    private Player banningPlayer;
    
    public IpBanHook(String bannedIp, Player banningPlayer) {
        this.bannedIp = bannedIp;
        this.banningPlayer = banningPlayer;
        this.type = Type.IPBAN;
    }
    
    /**
     * Get the IP that has been banned
     * @return
     */
    public String getBannedIp() {
        return bannedIp;
    }
    
    /**
     * Get the player that has issued the ban
     * @return
     */
    public Player getBanningPlayer() {
        return banningPlayer;
    }
    
    
    /**
     * Return the set of Data in this order: BANNEDIPR BANNINGPLAYER
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{bannedIp, banningPlayer};
    }

}
