package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * 
 * @author Jason Jones
 *
 */
public class EnchantDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onEnchant((EnchantHook) hook);
    }
    
}
