package net.canarymod.api.entity;

public interface Enderman extends EntityMob {

    /**
     * Get the block id for the block this enderman carries
     * 
     * @return
     */
    public int getCarriedBlock();

    /**
     * Set this creeper charged (as if hit by lightning)
     * 
     * @param charged
     */
    public void setCarriedBlock(int blockId);

    /**
     * Teleport the enderman to a random position nearby
     * 
     * @return
     */
    public boolean randomTeleport();

}
