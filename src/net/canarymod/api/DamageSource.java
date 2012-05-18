package net.canarymod.api;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.Player;

public interface DamageSource {
    /**
     * Can this damage be dealt against an entity in creative mode?
     * 
     * @return true if yes, false otherwise
     */
    public boolean validInCreativeMode();

    /**
     * Check if this is fire damage
     * 
     * @return
     */
    public boolean isFireDamage();

    /**
     * Check if this is projectile damage
     * 
     * @return
     */
    public boolean isProjectile();

    /**
     * Return the type of this damage
     * 
     * @return
     */
    public String getDamagetype();

    /**
     * Set a custom death message to this damage type. put "%d" (without ") as
     * placeholder for a player name. This message will not be localized!
     * 
     * @param deathmessage
     */
    public void setCustomDeathMessage(String deathmessage);

    /**
     * Get deathmessage for this damage type
     * 
     * @param player
     * @return
     */
    public String getDeathMessage(Player player);

    /**
     * How much hunger will be added by indulging this damage type
     * 
     * @return
     */
    public float getHungerDamage();

    /**
     * Set the amount of hunger this damage costs
     * 
     * @param hunger
     */
    public void setHungerDamage(float hunger);

    /**
     * Get the entity that caused the damage. This may return null if damage
     * wasn't caused by an entity!
     * 
     * @return
     */
    public Entity getDamageDealer();

    /**
     * Check if this damage is unblockable (penetrates armor and can't be held
     * off by blocking it)
     * 
     * @return
     */
    public boolean isUnblockable();

    /**
     * Set this damagetype as unblockable or not
     * 
     * @param blockable
     */
    public void setUnblockable(boolean blockable);
}
