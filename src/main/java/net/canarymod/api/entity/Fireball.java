package net.canarymod.api.entity;

import net.canarymod.api.entity.living.EntityLiving;

/**
 * Fireball Wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface Fireball extends Entity {

    /**
     * Get the entity that is the source of this fireball
     * 
     * @return owner
     */
    public EntityLiving getOwner();

}
