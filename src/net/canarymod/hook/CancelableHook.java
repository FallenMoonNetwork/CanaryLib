package net.canarymod.hook;

/**
 * A cancelable hook. The calling chain will be broken once this hook has been
 * set as cancelled and its current state will be returned to the system.
 * 
 * @author Chris
 * 
 */
public class CancelableHook extends Hook {
    protected boolean isCancelled = false;

    public void setCancelled() {
        isCancelled = true;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public Object[] getDataSet() {
        return new Object[] { Boolean.valueOf(isCancelled) };
    }

}
