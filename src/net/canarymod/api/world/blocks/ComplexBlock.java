package net.canarymod.api.world.blocks;

/**
 * Mark a block as complex block
 * 
 * @author Chris
 * 
 */
public interface ComplexBlock {
    /**
     * Returns the IBlock representation for this complex block
     * 
     * @return
     */
    public Block getBlock();
}
