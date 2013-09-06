package net.canarymod.api.entity.living.monster;

/**
 * Skeleton wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface Skeleton extends EntityMob {

    /**
     * Gets whether this Skeleton is a WitherSkeleton
     *
     * @return {@code true} if WitherSkeleton; {@code false} if not
     */
    public boolean isWitherSkeleton();

    /**
     * Sets whether this Skeleton is a WitherSkeleton
     *
     * @param wither
     *         {@code true} if WitherSkeleton; {@code false} if not
     */
    public void setIsWitherSkeleton(boolean wither);
}
