package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.Hook;
import net.canarymod.plugin.PluginListener;

/**
 * Chat hook. Contains player, prefix, message and receivers information
 * @author Chris Ksoll
 *
 */
public final class LevelUpHook extends Hook {
    private Player player;

    
    public LevelUpHook(Player player) {
        this.player = player;
        this.type = Type.LEVEL_UP;
    }
    
    /**
     * Get the {@link Player} instance
     * @return
     */
    public Player getPlayer() {
        return player;
    }
    
   
    
    /**
     * Return the set of Data in this order: PLAYER
     */
    @Override
    public Object[] getDataSet() {
        return new Object[]{player};
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        listener.onLevelUp(this);
    }

}
