package net.canarymod.api.entity;

public interface Creeper extends EntityMob, Explosive {

    /**
     * Set this creeper charged (as if hit by lightning)
     * 
     * @return
     */
    public boolean isCharged();

    /**
     * Set this creeper charged (as if hit by lightning)
     * 
     * @param charged
     */
    public void setCharged(boolean charged);

}
