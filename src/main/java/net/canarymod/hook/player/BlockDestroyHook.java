package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Called when a player destroy a block
 *
 * @author Jason (darkdiplomat)
 */
public final class BlockDestroyHook extends CancelableHook {

    private Player player;
    private Block block;

    public BlockDestroyHook(Player player, Block block) {
        this.player = player;
        this.block = block;
    }

    /**
     * Gets the {@link Player} clicking
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the {@link Block} the {@link Player} is left clicking
     *
     * @return block if player is clicking a block, null otherwise
     */
    public Block getBlock() {
        return block;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Block=%s]", getName(), player, block);
    }
}
