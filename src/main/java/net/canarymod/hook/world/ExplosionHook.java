package net.canarymod.hook.world;

import java.util.List;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Explosion hook. Contains information about an explosion.
 *
 * @author Jason (darkdiplomat)
 */
public final class ExplosionHook extends CancelableHook {

    private Block block;
    private Entity entity;
    private List<Block> blocksaffected;

    public ExplosionHook(Block block, Entity entity, List<Block> blocksaffected) {
        this.block = block;
        this.entity = entity;
        this.blocksaffected = blocksaffected;
    }

    /**
     * Gets the base affected {@link Block}
     *
     * @return block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Gets the {@link Entity} causing the explosion
     *
     * @return entity
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Gets the list of affected blocks
     *
     * @return blocksaffected
     */
    public List<Block> getAffectedBlocks() {
        return blocksaffected;
    }

    @Override
    public final String toString() {
        return String.format("%s[Block=%s, Entity=%s, Blocks Affected=%s]", getName(), block, entity, blocksaffected);
    }
}
