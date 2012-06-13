package net.canarymod.hook.world;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.blocks.Dispenser;
import net.canarymod.hook.CancelableHook;

/**
 * Dispense hook. Contains information about a Dispenser dispensing an entity.
 * @author Jason Jones
 *
 */
public class DispenseHook extends CancelableHook{
    
    private Dispenser dispenser;
    private Entity entity;
    
    public DispenseHook(Dispenser dispenser, Entity entity){
        this.dispenser = dispenser;
        this.entity = entity;
    }
    
    /**
     * gets the dispenser
     * @return dispenser
     */
    public Dispenser getDispenser(){
        return dispenser;
    }
    
    /**
     * gets the entity being dispensed
     * @return entity
     */
    public Entity getEntity(){
        return entity;
    }
    
    /**
     * Return the set of Data in this order: DISPENSER ENTITY ISCANCELLED
     */
    @Override
    public Object[] getDataSet(){
        return new Object[]{ dispenser, entity, isCancelled };
    }
}
