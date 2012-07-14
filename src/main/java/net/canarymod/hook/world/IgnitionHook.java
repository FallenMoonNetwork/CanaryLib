package net.canarymod.hook.world;


import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Explosion hook. Contains information about an explosion.
 * @author Chris Ksoll
 *
 */
public final class IgnitionHook extends CancelableHook{
    
    private Block block;
    private int status;
    
    public IgnitionHook(Block block, int status){
        this.block = block;
        this.status = status;
        this.type = Type.IGNITE;
    }
    
    /**
     * Gets the block that is about to go up in flames
     * @return block
     */
    public Block getBlock(){
        return block;
    }
    
    /**
     * Return the set of Data in this order: BLOCK ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ block, isCanceled };
    }
    
    /**
     * Dispatches the hook to the given listener.
     * @param listener The listener to dispatch the hook to.
     */
    @Override
    public void dispatch(PluginListener listener) {
        listener.onIgnite(this);
    }

//    lock status depends on the light source: 1 = lava. 2 = lighter
//            * (flint + steel). 3 = spread (dynamic spreading of fire). 4 = fire
//            * destroying a block. 5 = lightning
            
    /**
     * Get the status of this block.<br>
     * <ul>
     *      <li><b>1</b> - Lava</li>
     *      <li><b>2</b> - Caused by flint&steel</li>
     *      <li><b>3</b> - natural firespread</li>
     *      <li><b>4</b> - fire destroying a block</li>
     *      <li><b>5</b> - Lightning strike</li>
     * </ul>
     * @return
     */
    public int getStatus() {
        return status;
    }

}
