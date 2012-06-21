package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link InventoryHook}
 * @author Jason Jones
 *
 */
public class InventoryDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case CLOSE_INVENTORY:
            return this.li.onCloseInventory((InventoryHook)hook);
        case OPEN_INVENTORY:
            return this.li.onOpenInventory((InventoryHook) hook);
        }
        return hook;
    }
    
}
