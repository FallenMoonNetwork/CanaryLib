package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.Hook;

/**
 * Ban hook. Contains information about an issued Ban
 * @author Chris Ksoll
 *
 */
public class BanHook extends Hook {
    private Player bannedPlayer;
    private Player banningPlayer;
    
    public BanHook(Player bannedPlayer, Player banningPlayer) {
        this.bannedPlayer = bannedPlayer;
        this.banningPlayer = banningPlayer;
        this.type = Type.BAN;
    }
    
    /**
     * Get the player that has been banned
     * @return
     */
    public Player getBannedPlayer() {
        return bannedPlayer;
    }
    
    /**
     * Get the player that has issued the ban
     * @return
     */
    public Player getBanningPlayer() {
        return banningPlayer;
    }
    
    
    /**
     * Return the set of Data in this order: BANNEDPLAYER BANNINGPLAYER
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{bannedPlayer, banningPlayer};
    }

}
