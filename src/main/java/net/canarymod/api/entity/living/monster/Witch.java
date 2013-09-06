package net.canarymod.api.entity.living.monster;

/**
 * Witch wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface Witch extends EntityMob {

    public boolean isAgressive();

    public void setAggressive(boolean aggressive);
}
