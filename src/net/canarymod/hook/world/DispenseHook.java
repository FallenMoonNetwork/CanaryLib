package net.canarymod.hook.world;

import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Dispense hook. Contains information about a Dispenser dispensing an item.
 * @author Jason Jones
 *
 */
public class DispenseHook extends CancelableHook{
    
    private Block dispenser;
    private EntityItem itementity;
    
    public DispenseHook(Block dispenser, EntityItem itementity){
        this.dispenser = dispenser;
        this.itementity = itementity;
    }
    
    /**
     * gets the dispenser
     * @return
     */
    public Block getDispenser(){
        return dispenser;
    }
    
    /**
     * gets the item being dispensed
     * @return
     */
    public EntityItem getItem(){
        return itementity;
    }
    
    /**
     * Return the set of Data in this order: DISPENSER ITEMENTITY ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ dispenser, itementity, isCancelled };
    }
}
