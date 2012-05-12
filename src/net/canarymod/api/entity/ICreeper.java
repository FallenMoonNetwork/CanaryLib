package net.canarymod.api.entity;

public interface ICreeper extends IEntityMob, IExplosive {
    
    /**
     * Set this creeper charged (as if hit by lightning)
     * @return
     */
    public boolean isCharged();
    
    /**
     * Set this creeper charged (as if hit by lightning)
     * @param charged
     */
    public void setCharged(boolean charged);
    
}
