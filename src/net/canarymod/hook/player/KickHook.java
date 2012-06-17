package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.Hook;

/**
 * Kick hook. Contains the player who was kicked and the player who kicked them
 * @author Chris Ksoll
 *
 */
public class KickHook extends Hook {
    private Player kicked;
    private Player mod;
    
    public KickHook(Player kickedPlayer, Player kickingPlayer) {
        this.kicked = kickedPlayer;
        this.mod = kickingPlayer;
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
    
    
    /**
     * Return the set of Data in this order: KICKEDPLAYER KICKINGPLAYER
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{kicked, mod};
    }

}
