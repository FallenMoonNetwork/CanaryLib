package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * RedstoneChange hook. Contains information about power flowing from one block to another
 *
 * @author Chris (damagefilter)
 */
public final class RedstoneChangeHook extends CancelableHook {

    private Block sourceBlock;
    private int oldLevel, newLevel;

    public RedstoneChangeHook(Block source, int oldLevel, int newLevel) {
        this.sourceBlock = source;
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    /**
     * Gets the {@link Block} the redstone is on
     *
     * @return
     */
    public Block getSourceBlock() {
        return sourceBlock;
    }

    /**
     * Get the power level for the redstone before the change
     *
     * @return
     */
    public int getOldLevel() {
        return oldLevel;
    }

    /**
     * get the powerlevel for redstone that it would be after the change
     *
     * @return
     */
    public int getNewLevel() {
        return newLevel;
    }

    @Override
    public final String toString() {
        return String.format("%s[Block=%s, New Level=%s, Old Level=%s]", getName(), sourceBlock, newLevel, oldLevel);
    }
}
