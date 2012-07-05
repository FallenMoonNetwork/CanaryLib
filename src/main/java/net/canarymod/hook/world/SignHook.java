package net.canarymod.hook.world;

import net.canarymod.api.entity.Player;
import net.canarymod.api.world.blocks.Sign;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.Hook;
import net.canarymod.plugin.PluginListener;

/**
 * Sign hook. Contains infomation about a sign either being changed by or shown to a player
 * @author Jason Jones
 */
public final class SignHook extends CancelableHook{
    
    private Sign sign;
    private Player player;
    
    public SignHook(Player player, Sign sign, boolean change){
        this.player = player;
        this.sign = sign;
        this.type = change ? Hook.Type.SIGN_CHANGE : Hook.Type.SIGN_SHOW;
    }
    
    /**
     * Gets the {@link Player}
     * @return player
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Gets the {@link Sign}
     * @return sign
     */
    public Sign getSign(){
        return sign;
    }
    
    /**
     * Return the set of Data in this order: PLAYER SIGN ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ player, sign, isCanceled() };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        switch (this.type) {
            case SIGN_CHANGE: {
                listener.onSignChange(this);
                break;
            }
            case SIGN_SHOW: {
                listener.onSignShow(this);
                break;
            }
        }
    }

}
