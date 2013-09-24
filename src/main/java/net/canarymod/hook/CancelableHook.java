package net.canarymod.hook;

/**
 * A cancelable hook. The calling chain will be broken once this hook has been
 * set as cancelled and its current state will be returned to the system.
 *
 * @author Chris (damagefilter)
 */
public abstract class CancelableHook extends Hook {
    protected boolean isCanceled = false;

    public void setCanceled() {
        isCanceled = true;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

}
