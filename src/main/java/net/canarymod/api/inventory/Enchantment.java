package net.canarymod.api.inventory;

import java.util.HashMap;
import java.util.Map;
import net.canarymod.api.DamageSource;
import net.canarymod.api.entity.living.EntityLiving;

/**
 * Enchantment interface
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public interface Enchantment {

    /**
     * Type - used to identify enchantments
     * 
     * @author Yariv
     * @author Jason (darkdiplomat)
     */
    public enum Type {
        Protection(0), //
        FireProtection(1), //
        FeatherFalling(2), //
        BlastProtection(3), //
        ProjectileProtection(4), //
        Respiration(5), //
        AquaAffinity(6), //
        Thorns(7), //
        Sharpness(16), //
        Smite(17), //
        BaneOfArthropods(18), //
        Knockback(19), //
        FireAspect(20), //
        Looting(21), //
        Efficiency(32), //
        SilkTouch(33), //
        Unbreaking(34), //
        Fortune(35), //
        ArrowDamage(48), //
        ArrowKnockback(49), //
        ArrowFire(50), //
        ArrowInfinite(51);

        private int id;
        private static Map<Integer, Type> map;

        private Type(int id) {
            this.id = id;
            add(id, this);
        }

        private static void add(int type, Type name) {
            if (map == null) {
                map = new HashMap<Integer, Type>();
            }

            map.put(type, name);
        }

        /**
         * Gets the Data Value of this Enchantment.
         * 
         * @return ID number of this enchantment.
         */
        public int getId() {
            return id;
        }

        /**
         * Gets the Type of enchantment from this Data Value.
         * 
         * @param type
         *            Enchantment.Type to get the Data Value from.
         * @return The ID number for this Enchantment.Type
         */
        public static Type fromId(final int type) {
            return map.get(type);
        }
    }

    /**
     * Get weight of this enchantment
     * 
     * @return The weight of the enchantment (priority level)
     */
    public int getWeight();

    /**
     * Get the smallest valid level this enchantment can have
     * 
     * @return the minimum level this enchantment can have (will be integer 1-5)
     */
    public int getMinEnchantmentLevel();

    /**
     * Get the maximum level this enchantment can have
     * 
     * @return the maximum level this enchantment can have (will be integer 1-5)
     */
    public int getMaxEnchantmentLevel();

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     * 
     * @param lvl
     *            the Level to test
     * @return the enchantability
     */
    public int getMinEnchantability();

    /**
     * Returns the maximum value of enchantability needed on the enchantment level passed.
     * 
     * @param lvl
     *            the Level to test
     * @return the enchantability
     */
    public int getMaxEnchantability();

    /**
     * Calculates the damage protection of the enchantment based on level and damage source passed.
     * 
     * @param lvl
     *            the level to test
     * @param source
     *            the damage source
     * @return damage modifier
     */
    public int getDamageModifier(DamageSource source);

    /**
     * Calculates the damage done by this enchantment to the given EntityLiving
     * 
     * @param entity
     *            the {@link EntityLiving} to calculate damage done to.
     * @return Damage value that would be done to this entity.
     */
    public int getDamageModifier(EntityLiving entity);

    /**
     * Check if this enchantment can stack with another one
     * 
     * @param other
     *            Enchantment to check.
     * @return true - this is a stackable enchantment<br>
     *         false - this is not a
     *         stackable enchantment
     */
    public boolean canStack(Enchantment other);

    /**
     * Checks if the Enchantment can be applied to the {@link Item}
     * 
     * @param item
     *            the {@link Item} to test
     * @return {@code true} if can be applied; {@code false} if not
     */
    public boolean canEnchantItem(Item item);

    /**
     * Get this Enchantment's Type
     * 
     * @return The Enchantment.Type of this Enchantment.
     */
    public Type getType();

    /**
     * Get the level of this enchantment
     * 
     * @return A value of 1 - 5. Varies by Enchantment type.
     */
    public short getLevel();

    /**
     * Sets the level of this enchantment
     * 
     * @param level
     *            the level to set
     */
    public void setLevel(short level);

    /**
     * Checks if the level of the Enchantment is within the min and max range
     * 
     * @return {@code true} if valid; {@code false} if not
     */
    public boolean isValid();
}
