package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.world.Dimension;

public interface Workbench{
    
    /**
     * Get this workbench's inventory
     * @return inventory
     */
    public Inventory getInventory();
    
    public Block getBlock();

    public int getX();
    
    public int getY();
    
    public int getZ();
    
    public Dimension getDimension();

}
