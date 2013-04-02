package net.canarymod.hook.world;


import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Piston Extend Hook<br>
 * Called when a Piston extends
 * 
 * @author Jason (darkdiplomat)
 */
public final class PistonExtendHook extends CancelableHook {

    private Block piston, moving;

    public PistonExtendHook(Block piston, Block moving) {
        this.piston = piston;
        this.moving = moving;
    }

    /**
     * Gets the piston {@link Block}
     * @return piston
     */
    public Block getPiston() {
        return piston;
    }

    /**
     * Gets the {@link Block} the piston is pushing/pulling
     * @return
     */
    public Block getMoving() {
        return moving;
    }
}
