package net.canarymod.api.entity.living.monster;


import net.canarymod.api.entity.living.EntityLiving;


/**
 * A Mob base-entity that all mobs inherit from.
 * Declares some methods useful for mobs
 * @author Chris Ksoll
 *
 */
public interface EntityMob extends EntityLiving {

    /**
     * Helper-enum for easy spawning mobs from a world
     * @author Chris Ksoll
     *
     */
    public enum MobType {
        ZOMBIE, SKELETON, SPIDER, CREEPER, ENDERMAN, SILVERFISH, GIANTZOMBIE, SLIME, PIGZOMBIE, LAVASLIME, GHAST, BLAZE;
    }
    
}
