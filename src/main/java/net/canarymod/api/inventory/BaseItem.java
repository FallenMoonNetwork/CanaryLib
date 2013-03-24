package net.canarymod.api.inventory;


/**
 * Base Item to retrieve Item statistics
 * 
 * @author Chris
 * 
 */
public interface BaseItem {

    /**
     * Get the maximum Stack size for this item
     * 
     * @return
     */
    public int getMaxStackSize();

    /**
     * Return the amount of damage this item can take before it breaks
     * 
     * @return
     */
    public int getMaxDamage();

    /**
     * Set the max amount of damage this item can take before it breaks
     * 
     * @param damage
     */
    public void setMaxDamage(int damage);

    /**
     * Check if this item is damageable
     * 
     * @return
     */
    public boolean isDamageable();
}
