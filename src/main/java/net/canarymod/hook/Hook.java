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

    /**
     * Get the name of this hook.
     * 
     * @return
     */
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public int hashCode() {
        int hash = getClass().getSimpleName().length();

        return hash * getClass().getSimpleName().hashCode() + 2;
    }

    public Hook call() {
        Canary.hooks().callHook(this);
        return this;
    }
}
