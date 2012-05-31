package net.canarymod.hook.entity;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link AttackHook}
 * @author Jason Jones
 *
 */
public class AttackDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onAttack((AttackHook) hook);
    }

}
