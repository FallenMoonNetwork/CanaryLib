package net.canarymod.hook.world;


import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;


/**
 * Explosion hook. Contains information about an explosion.
 * @author Chris Ksoll
 *
 */
public final class IgnitionHook extends CancelableHook {

    private Block block;
    private int status;

    public IgnitionHook(Block block, int status) {
        this.block = block;
        this.status = status;
    }

    /**
     * Gets the block that is about to go up in flames
     * @return block
     */
    public Block getBlock() {
        return block;
    }

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
