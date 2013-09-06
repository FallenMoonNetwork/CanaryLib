package net.canarymod.hook.world;

import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * TNT Activate Hook
 * <p/>
 * Called when a TNT block is activated
 *
 * @author Jason (darkdiplomat)
 */
public final class TNTActivateHook extends CancelableHook {
    private Block tnt;
    private LivingBase activator;
    private ActivationCause cause;

    public TNTActivateHook(Block tnt, LivingBase activator, ActivationCause cause) {
        this.tnt = tnt;
        this.activator = activator;
        this.cause = cause;
    }

    /**
     * Gets the {@link Block} of TNT being activated
     *
     * @return the {@link Block} of TNT
     */
    public Block getTNT() {
        return tnt;
    }

    /**
     * Gets the {@link LivingBase} that is causing the TNT to activate (if applicable)
     *
     * @return the {@link LivingBase} if present; {@code null} otherwise
     */
    public LivingBase getActivator() {
        return activator;
    }

    /**
     * Gets the {@link ActivationCause} of the TNT Activation
     *
     * @return the {@link ActivationCause}
     */
    public ActivationCause getCause() {
        return cause;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s[TNT=%s, Activator=%s, Cause=%s]", getName(), tnt, activator, cause);
    }

    /**
     * TNT Activation causes
     *
     * @author Jason (darkdiplomat)
     */
    public enum ActivationCause {
        ENTITY,
        FIRE,
        EXPLOSION,
        REDSTONE,
        UNKNOWN;
    }
}
