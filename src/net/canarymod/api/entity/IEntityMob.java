package net.canarymod.api.entity;

import net.canarymod.api.IDamageSource;

public interface IEntityMob extends IEntityLiving {
    
    /**
     * Get the mobs attack strength
     * @return
     */
    public int getAttackStrength();
    
    /**
     * Attack this entity!
     * @param entity
     */
    public void attackEntity(IEntityLiving entity, IDamageSource damageSource);
    
    /**
     * Set this Entities target entity. Depending on entity type this must
     * not necessarily be an attack target. Null to remove target
     * @param target
     */
    public void setTarget(IEntityLiving target);
    
    /**
     * Get the current target of this entity
     * @return
     */
    public IEntityLiving getTarget();
    
}
