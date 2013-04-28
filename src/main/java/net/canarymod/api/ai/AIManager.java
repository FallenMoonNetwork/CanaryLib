package net.canarymod.api.ai;

/**
 * This class manages AI for Living Entities. Will do nothing for players.
 * @author Somners
 */
public interface AIManager {

    /**
     * Creates a new instance of the class extending AIBase to be added to the
     * Entity that owns this AIManager.
     * @param priority priority of this task. Higher numbers are higher priority.
     * @param ai The class to add.
     * @return True if it was successfully added, false otherwise.
     */
    public boolean addTask(int priority, Class<? extends AIBase> ai);

    /**
     * Remove the instance of the class extending AIBase from this entities list
     * of AI.
     * @param ai The Class type to remove
     * @return
     */
    public boolean removeTask(Class<? extends AIBase> ai);

    /**
     * Checks if the entity that owns this AIManager has an instance of the AIBase
     * of this class type.
     * @param ai Class to check.
     * @return true if it already has this ai, false otherwise.
     */
    public boolean hasTask(Class<? extends AIBase> ai);

    /**
     * Get a Task of the specific class type.
     * @param ai Class type to check
     * @return the task if it exits, null if it does not.
     */
    public AIBase getTask(Class<? extends AIBase> ai);

}
