package net.canarymod.serialize;

import net.canarymod.CanaryDeserializeException;
import net.canarymod.api.inventory.Item;

public interface ItemSerializer extends Serializer {

    @Override
    public Item deserialize(String data) throws CanaryDeserializeException;

    @Override
    public String serialize(Object item);

}
