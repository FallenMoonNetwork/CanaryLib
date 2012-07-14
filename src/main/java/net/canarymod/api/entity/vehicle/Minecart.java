package net.canarymod.api.entity.vehicle;

import net.canarymod.api.inventory.Container;
import net.canarymod.api.inventory.Item;

public interface Minecart extends Vehicle, Container<Item> {
    /**
     * Get this minecarts type.
     * 1 = normal, 2 = storage
     * The inventory methods will only work if the minecart type is a storage minecart (chest or furnace)
     * @return
     */
    public int getType();
}
