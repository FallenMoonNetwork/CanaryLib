package net.canarymod.hook.entity;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * 
 * @author Jason Jones
 *
 */
public class DamageDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onDamage((DamageHook) hook);
    }

}
