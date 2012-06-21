package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link PlayerMoveHook}
 * @author Jason Jones
 *
 */
public class PlayerMoveDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onPlayerMove((PlayerMoveHook) hook);
    }

}
