package net.canarymod.api.entity.living.animal;


import net.canarymod.api.entity.living.EntityLiving;


public interface Tameable extends EntityAnimal {

    /**
     * If the entity is tamed, this returns its owner
     * 
     * @return
     */
    public EntityLiving getOwner();

    /**
     * Set the owner of this entity
     * 
     * @param entity
     */
    public void setOwner(EntityLiving entity);

    /**
     * Check if that animal is tamed
     * 
     * @return
     */
    public boolean isTamed();

    /**
     * Set this entity tamed or not
     * 
     * @param tamed
     */
    public void setTamed(boolean tamed);

    /**
     * Check if this animal is currently sitting
     * 
     * @return
     */
    public boolean isSitting();

    /**
     * Set this entity sitting or not
     * 
     * @param sitting
     */
    public void setSitting(boolean sitting);
}
