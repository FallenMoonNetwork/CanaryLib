package net.canarymod.hook.world;


import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;


/**
 * Ignition hook. Contains information about a {@link Block} being set on fire.
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public final class IgnitionHook extends CancelableHook {

    private Player player;
    private Block block;

    public IgnitionHook(Block block, Player player) {
        this.block = block;
        this.player = player;
    }

    /**
     * Gets the {@link Block} that is about to go up in flames
     * <p>
     * Block Statuses:
     * <ul>
     * <li><b>1</b> - Lava</li>
     * <li><b>2</b> - Flint&Steel</li>
     * <li><b>3</b> - Spread</li>
     * <li><b>4</b> - Burned Up</li>
     * <li><b>5</b> - Lightning Strike</li>
     * <li><b>6</b> - FireBall</li>
     * </ul>
     *
     * @return the {@link Block} catching fire
     * @see Block#getStatus()
     */
    public Block getBlock() {
        return block;
    }

    /**
     * The {@link Player} starting the fire if present
     *
     * @return the {@link Player} starting the fire or {@code null} if not started by a Player
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, block=%s]", getName(), player, block);
    }
}
