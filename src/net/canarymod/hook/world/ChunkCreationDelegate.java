package net.canarymod.hook.world;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link ChunkCreationHook}
 * @author Jason Jones
 *
 */
public class ChunkCreationDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        return this.li.onChunkCreation((ChunkCreationHook) hook);
    }

}
