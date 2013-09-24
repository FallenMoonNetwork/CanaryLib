package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Flow hook. Contains information about a liquid flowing from one block to another
 *
 * @author Jason (darkdiplomat)
 */
public final class FlowHook extends CancelableHook {

    private Block from, to;

    public FlowHook(Block from, Block to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the {@link Block} flowing from
     *
     * @return the {@link Block}
     */
    public Block getBlockFrom() {
        return from;
    }

    /**
     * Gets the {@link Block} flowing to
     *
     * @return the {@link Block}
     */
    public Block getBlockTo() {
        return to;
    }

    @Override
    public final String toString() {
        return String.format("%s[Block To=%s, Block From=%s]", getName(), to, from);
    }
}
