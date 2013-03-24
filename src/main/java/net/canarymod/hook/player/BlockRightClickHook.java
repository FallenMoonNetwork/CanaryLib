package net.canarymod.hook.player;


import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;


/**
 * Is called when a player right-clicks a block, eg. uses it.
 * @author chris
 *
 */
public class BlockRightClickHook extends CancelableHook {
    private Block block;
    private Player player;

    /**
     * Construct a new BlockRightClickHook
     * @param block
     * @param player
     */
    public BlockRightClickHook(Block block, Player player) {
        this.block = block;
        this.player = player;
    }

    /**
     * Get the player who clicked
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the block that was clicked.
     * @return
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Return the dataset: BLOCK, PLAYER, ISCANCELED
     */
    @Override
    public Object[] getDataSet() {
        return new Object[] { block, player, isCanceled};
    }
}
