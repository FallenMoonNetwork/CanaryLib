package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.inventory.Item;

/**
 * EntityAnimal wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface EntityAnimal extends EntityLiving {

    /**
     * Checks if the {@link Item} is the item the initiates breeding
     *
     * @param item
     *         the {@link Item} to check
     *
     * @return {@code true} if breeding item; {@code false} if not
     */
    public boolean isBreedingItem(Item item);
}
