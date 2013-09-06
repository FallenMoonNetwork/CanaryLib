package net.canarymod.hook;

import net.canarymod.Canary;

/**
 * A basic hook you can implement to create custom hooks. System Hooks also
 * extend this
 *
 * @author Chris Ksoll
 * @author Jason Jones
 */
public abstract class Hook {
    private boolean executed; // Prevent calling the same hook multiple times.

    /**
     * Get the name of this hook.
     *
     * @return simple class name
     */
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public int hashCode() {
        int hash = getClass().getSimpleName().length();

        return hash * getClass().getSimpleName().hashCode() + 2;
    }

    /**
     * Calls a Hook if not already executed
     *
     * @return this
     */
    public Hook call() {
        Canary.hooks().callHook(this);
        return this;
    }

    // Check for execution
    final boolean executed() {
        return executed;
    }

    // Set that it has executed to prevent subsequent calls
    final void hasExecuted() {
        executed = true;
    }
}
