package net.canarymod.api.entity;

import net.canarymod.api.entity.living.LivingBase;

/**
 * Fireball Wrapper interface
 *
 * @author Jason (darkdiplomat)
 */
public interface Fireball extends Entity {

    /**
     * Gets the amount of ticks the Fireball has been alive<br>
     * If ticks reach 600, the Fireball is destroyed
     *
     * @return alive ticks
     */
    public int getTicksAlive();

    /**
     * Sets the amount of ticks the Fireball has been alive
     *
     * @param ticks
     *         the alive ticks
     */
    public void setTicksAlive(int ticks);

    /**
     * Gets the amount of ticks the Fireball has been in the air
     *
     * @return the in air ticks
     */
    public int getTicksInAir();

    /**
     * Gets the amount of ticks the Fireball has been in the air
     *
     * @param ticks
     *         the in air ticks
     */
    public void setTicksInAir(int ticks);

    /**
     * Gets the x-wise acceleration gain applied to the entity motion each tick
     *
     * @return the x-wise acceleration gain
     */
    public double getAccelerationX();

    /**
     * Sets the x-wise acceleration gain applied to the entity motion each tick
     *
     * @param accelX
     *         the x-wise acceleration gain
     */
    public void setAccelerationX(double accelX);

    /**
     * Gets the y-wise acceleration gain applied to the entity motion each tick
     *
     * @return the y-wise acceleration gain
     */
    public double getAccelerationY();

    /**
     * Sets the y-wise acceleration gain applied to the entity motion each tick
     *
     * @param accelY
     *         the y-wise acceleration gain
     */
    public void setAccelerationY(double accelY);

    /**
     * Gets the z-wise acceleration gain applied to the entity motion each tick
     *
     * @return the z-wise acceleration gain
     */
    public double getAccelerationZ();

    /**
     * Sets the z-wise acceleration gain applied to the entity motion each tick
     *
     * @param accelZ
     *         the z-wise acceleration gain
     */
    public void setAccelerationZ(double accelZ);

    /**
     * Gets the motion multiplier
     *
     * @return the motion factor
     */
    public float getMotionFactor();

    /**
     * Sets the motion multiplier
     *
     * @param factor
     *         the motion factor
     */
    public void setMotionFactor(float factor);

    /**
     * Get the entity that is the source of this fireball
     *
     * @return the {@link LivingBase} owner or {@code null} if no owner
     */
    public LivingBase getOwner();

}
