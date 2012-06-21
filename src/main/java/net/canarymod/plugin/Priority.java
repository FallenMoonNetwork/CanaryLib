package net.canarymod.plugin;

/**
 * The Plugin Priority or "Execution order"
 * 
 * @author Chris
 * 
 */
public enum Priority {
    /**
     * For Plugins that monitor actions but do not interfere
     */
    PASSIVE,

    /**
     * Low level stuff like process player walking etc.
     */
    LOW,

    /**
     * Preferred Priority. This is the usual thing for blocking/altering actions
     */
    NORMAL,

    /**
     * Executed after critical.
     */
    HIGH,

    /**
     * Extremely critical. Will be executed as the very first Plugin.
     */
    CRITICAL;
}
