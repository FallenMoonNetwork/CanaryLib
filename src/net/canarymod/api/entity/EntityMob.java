package net.canarymod.api.entity;

import net.canarymod.api.DamageSource;

public interface EntityMob extends EntityLiving {

    /**
     * Get the mobs attack strength
     * 
     * @return
     */
    public int getAttackStrength();

    /**
     * Attack this entity!
     * 
     * @param entity
     */
    public void attackEntity(EntityLiving entity, DamageSource damageSource);

    /**
     * Set this Entities target entity. Depending on entity type this must not
     * necessarily be an attack target. Null to remove target
     * 
     * @param target
     */
    public void setTarget(EntityLiving target);

    /**
     * Get the current target of this entity
     * 
     * @return
     */
    public EntityLiving getTarget();

}
