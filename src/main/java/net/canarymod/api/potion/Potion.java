package net.canarymod.api.potion;

/**
 * A potion, can give various {@link PotionEffect}s
 *
 * @author Brian (WWOL)
 */
public interface Potion {

    /**
     * Gets the Minecraft protocol ID value for this potion.
     *
     * @return ID value.
     */
    public int getID();

    /**
     * Gets the Minecraft name of this potion.
     *
     * @return the name.
     */
    public String getName();

    /**
     * Gets the { @link PotionEffectType } type for this potion.
     *
     * @return { @link PotionEffectType }
     */
    public PotionEffectType getEffectType();

    /**
     * Returns whether or not this potion will have negative effects on the target.
     *
     * @return { @code true } - this potion will have negative effects on the target<br>
     *         { @code false } - this potion does not have negative effects on the target
     */
    public boolean isBad();

    /**
     * Gets the effectiveness level of the potion.
     *
     * @return the effectiveness.
     */
    public double getEffectiveness();

    /**
     * Returns whether or not this potion is in a usable state.
     *
     * @return { @code true } - this potion is usable<br>
     *         { @code false } - this potion is not usable for some reason
     */
    public boolean isUsable();

    /**
     * Returns whether or not this potion is an instant potion.
     *
     * @return { @code true } - this potion is instant<br>
     *         { @code false } - this potion is a splash potion
     */
    public boolean isInstant();
}
