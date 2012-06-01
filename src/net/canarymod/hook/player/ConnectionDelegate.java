package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link ConnectionHook}
 * @author Jason Jones
 *
 */
public class ConnectionDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case PLAYER_CONNECT:
            return this.li.onPlayerConnect((ConnectionHook) hook);
        case PLAYER_DISCONNECT:
            return this.li.onPlayerDisconnect((ConnectionHook) hook);
        }
        return hook;
    }
    
}
