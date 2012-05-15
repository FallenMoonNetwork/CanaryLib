package net.canarymod.api.entity;

import net.canarymod.api.inventory.IItem;

public interface IEntityItem extends IEntity{
    
    /**
     * Set items age.
     * @param age
     */
    public void setAge(int age);
    
    /**
     * Get items age
     * @return
     */
    public int getAge();
    
    /**
     * Get the Item attached to this EntityItem.
     * (That's the thing you can put in player inventories)
     * @return
     */
    public IItem getItem();
}
