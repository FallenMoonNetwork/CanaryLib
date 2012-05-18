package net.canarymod.api.entity;

public interface EntityMob extends EntityLiving {

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
    
    /**
     * Check if this entity can spawn at its current specified position or not
     * 
     * @return true if the entity can
     */
    public boolean canSpawn();

}
