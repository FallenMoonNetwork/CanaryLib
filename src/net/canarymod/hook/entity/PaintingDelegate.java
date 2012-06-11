package net.canarymod.hook.entity;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

public class PaintingDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onPaintingDestroy((PaintingHook) hook);
    }

}
