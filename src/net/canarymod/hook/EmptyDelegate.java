package net.canarymod.hook;

import net.canarymod.Logman;

/**
 * For internal use only
 */
final class EmptyDelegate extends HookDelegate{

    @Override
    public final Hook callHook(Hook hook) {
        Logman.logWarning("Hook: "+hook.getName()+" called with EmptyDelegate.");
        return hook;
    }
    
}
