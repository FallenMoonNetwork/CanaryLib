package net.canarymod.api.world.blocks;

/**
 * Mark a block as complex block
 * @author Chris
 *
 */
public interface IComplexBlock {
    /**
     * Returns the IBlock representation for this complex block
     * @return
     */
    public IBlock getBlock();
}
