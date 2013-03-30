package net.canarymod.hook.player;


import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;


/**
 * Is called when a player right-clicks a block, eg. uses it.
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdoplomat)
 */
public class BlockRightClickHook extends CancelableHook {
    private Block clicked, placed;
    private Player player;

    /**
     * Construct a new BlockRightClickHook
     * @param block
     * @param player
     */
    public BlockRightClickHook(Player player, Block clicked, Block placed) {
        this.clicked = clicked;
        this.placed = placed;
        this.player = player;
    }

    /**
     * Get the {@link Player} who clicked the {@link Block}
     * 
     * @return the {@link Player} clicking
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the {@link Block} that was clicked.
     * 
     * @return the {@link Block} that was clicked
     */
    public Block getBlockClicked() {
        return clicked;
    }

    /**
     * Gets the {@link Block} that was placed, or Air if no block was placed
     * 
     * @return the {@link Block} that was placed
     */
    public Block getBlockPlaced() {
        return placed;
    }
}
