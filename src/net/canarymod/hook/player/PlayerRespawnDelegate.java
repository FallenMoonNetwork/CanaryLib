package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link PlayerRespawnHook}
 * @author Jason Jones
 *
 */
public class PlayerRespawnDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onPlayerRespawn((PlayerRespawnHook) hook);
    }

}
