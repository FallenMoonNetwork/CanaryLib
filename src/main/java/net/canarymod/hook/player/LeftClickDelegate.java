package net.canarymod.hook.player;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link LeftClickDelegate}
 * @author Jason Jones
 *
 */
public class LeftClickDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case ARM_SWING:
            return this.li.onArmSwing((LeftClickHook)hook);
        case BLOCK_LEFTCLICKED:
            return this.li.onBlockLeftClicked((LeftClickHook)hook);
        case BLOCK_BREAK:
            return this.li.onBlockBreak((LeftClickHook) hook);
        }
        return hook;
    }
}
