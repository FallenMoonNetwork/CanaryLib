package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link TeleportHook}
 * @author Brian McCarthy
 *
 */
public class TeleportDelegate extends HookDelegate {

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case PORTAL_USE:
            return this.li.onPortalUse((TeleportHook)hook);
        case TELEPORT:
            return this.li.onTeleport((TeleportHook)hook);
        }
        return hook;
    }

}
