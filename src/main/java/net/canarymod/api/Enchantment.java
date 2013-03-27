package net.canarymod.api;


import java.util.HashMap;
import java.util.Map;
import net.canarymod.api.entity.living.EntityLiving;


/**
 * Enchantment interface
 *
 * @author Chris
 *
 */
public interface Enchantment {

    /**
     * Type - used to identify enchantments
     *
     * @author Yariv
     *
     */
    public enum Type {
        Protection(0), FireProtection(1), FeatherFalling(2), BlastProtection(3), ProjectileProtection(4), Respiration(5), AquaAffinity(6), Sharpness(16), Smite(17), BaneOfArthropods(18), Knockback(19), FireAspect(20), Looting(21), Efficiency(32), SilkTouch(33), Unbreaking(34), Fortune(35), ArrowDamage(48), ArrowKnockback(49), ArrowFire(50), ArrowInfinite(51);

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
         * @return ID number of this enchantment.
         */
        public int getId() {
            return id;
        }

        /**
         * Gets the Type of enchantment from this Data Value.
         * @param type Enchantment.Type to get the Data Value from.
         * @return The ID number for this Enchantment.Type
         */
        public static Type fromId(final int type) {
            return map.get(type);
        }
    }

    /**
     * Get a new Enchantment
     *
     * @param type The Enchantment.Type to get an Enchantment instance of.
     * @return Returns a new instance of the given Enchantment.Type
     */
    public Enchantment getEnchantment(Type type);

    /**
     * Get this Enchantment's Type
     *
     * @return The EnchantMent.Type of this Enchantment.
     */
    public Type getType();

    /**
     * Get the level of this enchantment
     *
     * @return A value of 1 - 5. Varies by Enchantment type.
     */
    public int getLevel();

    /**
     * Set this enchantments type
     *
     * @param type Enchantment.Type to set for this Enchantment.
     */
    public void setType(Type type);

    /**
     * Set this enchantments type
     *
     * @param type Enchantment ID value to set for this Enchantment
     */
    public void setType(int type);

    /**
     * Calculates the damage done by this enchantment to the given IEntityLiving
     *
     * @param entity Entity to calculate damage done to.
     * @return Damage value that would be done to this entity.
     */
    public int getDamageDone(EntityLiving entity);

    /**
     * Calculate the damage that will be dealt to armor with this enchantment on
     * it.
     *
     * @param damageSource Damagesource for this attack.
     * @return Damage value done to the armour.
     */
    public int getDamageModifier(Object damageSource);

    /**
     * Check if this enchantment can stack with another one
     *
     * @param other Enchantment to check.
     * @return true - this is a stackable enchantment<br>false - this is not a
     * stackable enchantment
     */
    public boolean canStack(Enchantment other);

    /**
     * Check if this enchantment is valid
     *
     * @return true - this is a valid enchantment<br>false - this is not a
     * valid enchantment
     */
    public boolean isValid();

    /**
     * Get weight of this enchantment
     *
     * @return The weight of the enchantment (priority level)
     */
    public int getWeight();

    /**
     * Get the maximum level this enchantment can have
     *
     * @return the maximum level this enchantment can have (will be integer 1-5)
     */
    public int getMaxEnchantmentLevel();

    /**
     * Get the smallest valid level this enchantment can have
     *
     * @return the minimum level this enchantment can have (will be integer 1-5)
     */
    public int getMinEnchantmentLevel();
}
