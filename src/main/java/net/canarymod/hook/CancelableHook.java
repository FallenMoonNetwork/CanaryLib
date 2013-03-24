package net.canarymod.hook;


/**
 * A cancelable hook. The calling chain will be broken once this hook has been
 * set as cancelled and its current state will be returned to the system.
 *
 * @author Chris
 *
 */
public abstract class CancelableHook extends Hook {
    protected boolean isCanceled = false;

    public void setCanceled() {
        isCanceled = true;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    @Override
    public Object[] getDataSet() {
        return new Object[] { Boolean.valueOf(isCanceled) };
    }

}
