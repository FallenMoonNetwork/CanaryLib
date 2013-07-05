package net.canarymod.api.inventory;

import net.canarymod.api.entity.living.animal.EntityAnimal;

/**
 * @author darkdiplomat
 */
public interface AnimalInventory extends Inventory {

    public EntityAnimal getOwner();

}
