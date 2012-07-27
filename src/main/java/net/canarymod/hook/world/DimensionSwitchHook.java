package net.canarymod.hook.world;


import net.canarymod.api.entity.Player;
import net.canarymod.api.world.World;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Dimension switch hook. Contains information about a player going to switch into a different dimension.
 * @author Chris Ksoll
 *
 */
public final class DimensionSwitchHook extends CancelableHook{
    
    private World world;
    private Player player;
    
    public DimensionSwitchHook(Player player, World world){
        this.world = world;
        this.player = player;
        this.type = Type.DIMENSION_SWITCH;
    }
    
    /**
     * Get the player that is about to switch into a different dimension
     * @return block
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     * Get the target world
     * @return
     */
    public World getWorld() {
        return world;
    }
    
    /**
     * Override the target world
     * @param world
     */
    public void setWorld(World world) {
        this.world = world;
    }
    /**
     * Return the set of Data in this order: PLAYER WORLD ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ player, world, isCanceled };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        listener.onDimensionSwitch(this);
    }
}
