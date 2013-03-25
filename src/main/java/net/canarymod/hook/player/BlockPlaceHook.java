package net.canarymod.hook.player;


import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;


/**
 * Is called when a player right-clicks a block, eg. uses it.
 * @author chris
 *
 */
public class BlockPlaceHook extends CancelableHook {
    private Block block;
    private Player player;

    /**
     * Construct a new BlockPlaceHook
     * @param block block that will be placed
     * @param player placing player
     */
    public BlockPlaceHook(Block block, Player player) {
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
     * Get the block that will be placed
     * @return
     */
    public Block getBlock() {
        return block;
    }
}
