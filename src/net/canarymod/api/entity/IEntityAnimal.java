package net.canarymod.api.entity;

public interface IEntityAnimal extends IEntityLiving, IAnimal {
    
    /**
     * Get the mobs attack strength
     * @return
     */
    public int getAttackStrenght();
    
    /**
     * Attack this entity!
     * TODO: Add DamageSource!!
     * @param entity
     */
    public void attackEntity(IEntityLiving entity);
    
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
