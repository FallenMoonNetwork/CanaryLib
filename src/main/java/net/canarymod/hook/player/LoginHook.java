package net.canarymod.hook.player;

import net.canarymod.api.entity.Player;
import net.canarymod.hook.Hook;
import net.canarymod.plugin.PluginListener;

/**
 * Login hook. Do stuff with a player right after the player instance has been created
 * @author Chris Ksoll
 *
 */
public final class LoginHook extends Hook {
    private Player player;
    
    public LoginHook(Player player) {
        this.player = player;
        this.type = Type.LOGIN;
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
        listener.onLogin(this);
    }

}
