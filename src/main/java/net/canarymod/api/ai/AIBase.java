package net.canarymod.api.ai;

/**
 * This class executes AI tasks on a Living Entity based on the following Logic.
 * To use this, create a class implementing it and then add it to an entity via
 * { @link AIManager }.
 * @author Somners
 */
public interface AIBase {

    /**
     * Returns whether the AIBase should begin execution.
     * @return true if the manager should execute this, false otherwise.
     */
    public boolean shouldExecute();

    /**
     * Returns whether an in-progress AIBase should continue executing
     * @return true if the manager should continue executing this, false otherwise.
     */
    public boolean continueExecuting();

    /**
     * Returns whether the task requires multiple updates or not
     * @return true means this takes multiple ticks to execute, false otherwise.
     */
    public boolean isContinuous();

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting();

    /**
     * Resets the task
     */
    public void resetTask();

    /**
     * Updates the task.<br>
     * This is called on ticks following the tick where
     * AIBase.startExecuting() is called until AIBase.resetTask()
     * is called.
     */
    public void updateTask();
}
