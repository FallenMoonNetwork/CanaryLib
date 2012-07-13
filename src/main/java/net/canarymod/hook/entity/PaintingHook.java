package net.canarymod.hook.entity;

import net.canarymod.api.entity.Painting;
import net.canarymod.api.entity.Player;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.Hook;
import net.canarymod.plugin.PluginListener;

/**
 * Painting hook. Contains information about a player destroying a painting.
 * @author Jason Jones
 */
public final class PaintingHook extends CancelableHook{
    
    private Painting painting;
    private Player player;
    
    public PaintingHook(Painting painting, Player player){
        this.painting = painting;
        this.player = player;
        this.type = Hook.Type.PAINTING_DESTROY;
    }
    
    /**
     * Gets the {@link Painting}
     * @return painting
     */
    public Painting getPainting(){
        return painting;
    }
    
    /**
     * Gets the {@link Player}
     * @return player
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Return the set of Data in this order: PAINTING PLAYER ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ painting, player, isCanceled };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        listener.onPaintingDestroy(this);
    }

}
