package net.canarymod.hook.world;


import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;


/**
 * Block update hook. Contains information about a block updating.
 * @author Jason Jones
 *
 */
public final class LeafDecayHook extends CancelableHook {

    private Block block;

    public LeafDecayHook(Block block) {
        this.block = block;
    }

    /**
     * Get the leaf block that is about to decay
     * @return
     */
    public Block getBlock() {
        return block;
    }
}
