package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

public class ItemDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case ITEM_DROP:
            return this.li.onItemDrop((ItemHook) hook);
        case ITEM_PICK_UP:
            return this.li.onItemPickup((ItemHook) hook);
        }
        return hook;
    }

}
