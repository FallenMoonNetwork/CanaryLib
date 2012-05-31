package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link RightClickHook}
 * @author Jason Jones
 *
 */
public class RightClickDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case BLOCK_RIGHTCLICKED:
            return this.li.onBlockRightClicked((RightClickHook) hook);
        case BLOCK_PLACE:
            return this.li.onBlockPlace((RightClickHook) hook);
        case ITEM_USE:
            return this.li.onItemUse((RightClickHook)hook);
        }
        return hook;
    }

}
