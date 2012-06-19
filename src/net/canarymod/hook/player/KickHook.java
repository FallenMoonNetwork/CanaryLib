package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.Hook;

/**
 * Kick hook. Contains the player who was kicked and the player who kicked them
 * @author Chris Ksoll
 * @author Jason Jones
 */
public class KickHook extends Hook {
    private Player kicked;
    private Player mod;
    private String reason;
    
    public KickHook(Player kickedPlayer, Player kickingPlayer, String reason) {
        this.kicked = kickedPlayer;
        this.mod = kickingPlayer;
        this.reason = reason;
        this.type = Type.KICK;
    }
    
    /**
     * Get the {@link Player} being kicked
     * @return
     */
    public Player getKickedPlayer() {
        return kicked;
    }
    
    /**
     * Get the {@link Player} that has issued the kick
     * @return
     */
    public Player getKickingPlayer() {
        return mod;
    }
    
    public String getReason(){
        return reason;
    }
    
    /**
     * Return the set of Data in this order: KICKEDPLAYER KICKINGPLAYER REASON
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{kicked, mod, reason};
    }

}
