package net.canarymod.api.entity;

public interface EntityAnimal extends EntityLiving, Animal {

    /**
     * Get the mobs attack strength
     * 
     * @return
     */
    public int getAttackStrenght();

    /**
     * Attack this entity! TODO: Add DamageSource!!
     * 
     * @param entity
     */
    public void attackEntity(EntityLiving entity);

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
