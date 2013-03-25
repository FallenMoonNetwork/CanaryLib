package net.canarymod.api.entity.living.animal;


import net.canarymod.api.entity.living.Ageable;

/**
 * Sheep wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface Sheep extends EntityAnimal, Ageable {

    /**
     * Applies stuff to the sheep that would be applied when it actually eats
     * grass
     */
    public void eatGrass();

    /**
     * Get fleece color data (that is what you need to set a cloth's data to)
     * 
     * @return
     */
    public int getColor();

    /**
     * Set this sheep's fleece color
     */
    public void setColor(int color);

    /**
     * Check if this sheep is sheared
     * 
     * @return
     */
    public boolean isSheared();

    /**
     * Set this sheep sheared or not
     * 
     * @param sheared
     */
    public void setSheared(boolean sheared);
}
