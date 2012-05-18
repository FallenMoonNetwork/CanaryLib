package net.canarymod.api.entity;

public interface Fireball extends Entity {
    /**
     * Get the entity that is the source of this fireball
     * @return
     */
    public EntityLiving getShootingEntity();
}
