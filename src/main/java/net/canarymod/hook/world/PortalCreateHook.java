package net.canarymod.hook.world;


import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Explosion hook. Contains information about an explosion.
 * @author Chris Ksoll
 *
 */
public final class PortalCreateHook extends CancelableHook{
    
    private Block[][] blocks;
    
    public PortalCreateHook(Block[][] blocks){
        this.blocks = blocks;

        this.type = Type.PORTAL_CREATE;
    }
    
    /**
     * Get the set of blocks that are used to create this portal
     * @return block
     */
    public Block[][] getBlockSet(){
        return blocks;
    }
    
    /**
     * Return the set of Data in this order: BLOCKS ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ blocks, isCanceled };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        listener.onPortalCreate(this);
    }

}
