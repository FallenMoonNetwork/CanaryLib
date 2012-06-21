package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link ExperienceHook}
 * @author Jason Jones
 *
 */
public class ExperienceDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onExpChange((ExperienceHook) hook);
    }

}
