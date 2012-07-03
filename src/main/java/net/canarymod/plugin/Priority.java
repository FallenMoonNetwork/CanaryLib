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
    PASSIVE(new PriorityNode("PASSIVE", 100)),

    /**
     * Low level stuff like process player walking etc.
     */
    LOW(new PriorityNode("LOW", 300)),

    /**
     * Preferred Priority. This is the usual thing for blocking/altering actions
     */
    NORMAL(new PriorityNode("NORMAL", 500)),

    /**
     * Executed after critical.
     */
    HIGH(new PriorityNode("HIGH", 700)),

    /**
     * Extremely critical. Will be executed as the very first Plugin.
     */
    CRITICAL(new PriorityNode("CRITICAL", 900));

    final PriorityNode priorityNode;

    Priority(PriorityNode priorityNode) {
        this.priorityNode = priorityNode;
    }

    public PriorityNode getPriorityNode() {
        return this.priorityNode;
    }
}
