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

        public int getId() {
            return id;
        }

        public static Type fromId(final int type) {
            return map.get(type);
        }
    }

    /**
     * Get a new Enchantment
     * 
     * @param typle
     * @return
     */
    public Enchantment getEnchantment(Type typle);

    /**
     * Get this enchantments Type
     * 
     * @return
     */
    public Type getType();

    /**
     * Get the level of this enchantment
     * 
     * @return
     */
    public int getLevel();

    /**
     * Set this enchantments type
     * 
     * @param type
     */
    public void setType(Type type);

    /**
     * Set this enchantments type
     * 
     * @param type
     */
    public void setType(int type);

    /**
     * Calculates the damage done by this enchantment to the given IEntityLiving
     * 
     * @param entity
     * @return
     */
    public int getDamageDone(EntityLiving entity);

    /**
     * Calculate the damage that will be dealt to armor with this enchantment on
     * it
     * 
     * @param damageSource
     * @return
     */
    public int getDamageModifier(Object damageSource);

    /**
     * Check if this enchantment can stack with another one
     * 
     * @param other
     * @return
     */
    public boolean canStack(Enchantment other);

    /**
     * Check if this enchantment is valid
     * 
     * @return
     */
    public boolean isValid();

    /**
     * Get weight of this enchantment
     * 
     * @return
     */
    public int getWeight();

    /**
     * Get the maximum level this enchantment can have
     * 
     * @return
     */
    public int getMaxEnchantmentLevel();

    /**
     * Get the smallest valid level this enchantment can have
     * 
     * @return
     */
    public int getMinEnchantmentLevel();
}
