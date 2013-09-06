package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Piston Retract hook<br>
 * Called when a piston retracts
 *
 * @author Jason (darkdiplomat)
 */
public final class PistonRetractHook extends CancelableHook {

    private Block piston, moving;

    public PistonRetractHook(Block piston, Block moving) {
        this.piston = piston;
        this.moving = moving;
    }

    /**
     * Gets the piston {@link Block}
     *
     * @return piston
     */
    public Block getPiston() {
        return piston;
    }

    /**
     * Gets the {@link Block} the piston is pushing/pulling
     *
     * @return the {@link Block}
     */
    public Block getMoving() {
        return moving;
    }

    @Override
    public final String toString() {
        return String.format("%s[Piston=%s, Moving=%s]", getName(), piston, moving);
    }
}
