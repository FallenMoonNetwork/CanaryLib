package net.canarymod.hook.entity;

import net.canarymod.api.entity.Enderman;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;
import net.canarymod.plugin.PluginListener;

/**
 * Enderman hook. Contains information about an Enderman dropping or picking up a block
 * 
 * @author Jason (darkdiplomat)
 */
public final class EndermanHook extends CancelableHook{

    private Enderman enderman;
    private Block block;

    public EndermanHook(Enderman enderman, Block block, boolean dropping){
        this.enderman = enderman;
        this.block = block;
        this.type = dropping ? Type.ENDERMAN_DROP : Type.ENDERMAN_PICKUP;
    }

    /**
     * Gets the {@link Enderman}
     * 
     * @return
     */
    public Enderman getEnderman(){
        return enderman;
    }

    /**
     * Gets the {@link Block}
     * 
     * @return
     */
    public Block getBlock(){
        return block;
    }

    /**
     * Return the set of Data in this order: ENDERMAN BLOCK ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[] { enderman, block, isCanceled };
    }

    /**
     * Dispatches the hook to the given listener.
     * 
     * @param listener
     *            The listener to dispatch the hook to.
     */
    @SuppressWarnings("incomplete-switch")
    @Override
    public void dispatch(PluginListener listener){
        switch (this.type) {
            case ENDERMAN_DROP: {
                listener.onEndermanDrop(this);
                break;
            }
            case ENDERMAN_PICKUP: {
                listener.onEndermanPickUp(this);
            }
        }
    }
}
