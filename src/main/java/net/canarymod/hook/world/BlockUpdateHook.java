package net.canarymod.hook.world;


import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;


/**
 * Block update hook. Contains information about a block updating.
 * @author Jason Jones
 *
 */
public final class BlockUpdateHook extends CancelableHook {

    private Block block;
    private int newBlockId;

    public BlockUpdateHook(Block block, int newBlockId) {
        this.block = block;
        this.newBlockId = newBlockId;
    }

    /**
     * Gets {@link Block} updating
     * @return
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Gets the block id of the new {@link Block}
     * @return
     */
    public int getNewBlockId() {
        return newBlockId;
    }

    /**
     * Return the set of Data in this order: BLOCK NEWBLOCKID ISCANCELLED
     */
    @Override
    public Object[] getDataSet() {
        return new Object[] { block, newBlockId, isCanceled };
    }
}
