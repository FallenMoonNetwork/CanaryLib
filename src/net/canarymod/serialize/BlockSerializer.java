package net.canarymod.serialize;

import net.canarymod.CanaryDeserializeException;
import net.canarymod.api.world.blocks.Block;

public interface BlockSerializer extends Serializer {

    @Override
    public Block deserialize(String data) throws CanaryDeserializeException;

    @Override
    public String serialize(Object object);

}
