package net.canarymod.api.entity;

public interface Sheep extends EntityAnimal {
    /**
     * Applies stuff to the sheep that would be applied when it actually eats
     * grass
     */
    public void eatGrass();

    /**
     * Get fleece color data (that is what you need to set a cloths data to)
     * 
     * @return
     */
    public int getColor();

    /**
     * Set this sheeps fleece color
     */
    public void setColor();

    /**
     * Check if this sheep is sheared
     * 
     * @return
     */
    public boolean isSheared();

    /**
     * Set this sheep sheard or not
     * 
     * @param sheared
     */
    public void setSheared(boolean sheared);
}
