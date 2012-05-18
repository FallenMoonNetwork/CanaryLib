package net.canarymod.api.entity;

public interface Explosive {

    /**
     * Sets whether the explosion can damage the world: blocks
     * 
     * @param canDamage
     */
    public void setCanDamageWorld(boolean canDamage);

    /**
     * Gets whether the explosion can damage the world
     * 
     * @return
     */
    public boolean canDamageWorld();

    /**
     * Sets whether the explosion can damage entities
     * 
     * @param canDamage
     */
    public void setCanDamageEntities(boolean canDamage);

    /**
     * Gets whether the explosion can damage entities
     * 
     * @return
     */
    public boolean canDamageEntities();
}
