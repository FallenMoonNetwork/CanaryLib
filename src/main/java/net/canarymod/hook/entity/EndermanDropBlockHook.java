package net.canarymod.hook.entity;

import net.canarymod.api.entity.living.monster.Enderman;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Enderman hook. Contains information about an Enderman dropping a block
 *
 * @author Jason (darkdiplomat)
 */
public class EndermanDropBlockHook extends CancelableHook{

    private Enderman enderman;
    private Block block;

    public EndermanDropBlockHook(Enderman enderman, Block block) {
        this.enderman = enderman;
        this.block = block;
    }

    /**
     * Gets the {@link Enderman}
     *
     * @return
     */
    public Enderman getEnderman() {
        return enderman;
    }

    /**
     * Gets the {@link Block}
     *
     * @return
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Return the set of Data in this order: ENDERMAN BLOCK ISCANCELLED
     */
    @Override
    public Object[] getDataSet() {
        return new Object[] { enderman, block, isCanceled };
    }
}
