package net.canarymod.hook.world;

import net.canarymod.hook.Hook;
import net.canarymod.hook.HookDelegate;

/**
 * The delegate for {@link ChunkHook}
 * @author Jason Jones
 *
 */
public class ChunkDelegate extends HookDelegate{

    @Override
    public Hook callHook(Hook hook) {
        switch(hook.getType()){
        case CHUNK_CREATED:
            return this.li.onChunkCreated((ChunkHook)hook);
        case CHUNK_LOADED:
            return this.li.onChunkLoaded((ChunkHook)hook);
        case CHUNK_UNLOADED:
            return this.li.onChunkUnloaded((ChunkHook)hook);
        }
        return hook;
    }
}
