package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Block update hook. Contains information about a block updating.
 *
 * @author Jason (darkdiplomat)
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
     *
     * @return The {@link Block} updated.
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Gets the block id of the new {@link Block}
     *
     * @return {@link Block} ID value.
     */
    public int getNewBlockId() {
        return newBlockId;
    }

    @Override
    public final String toString() {
        return String.format("%s[Block=%s, New Block ID=%s]", getName(), block, newBlockId);
    }
}
