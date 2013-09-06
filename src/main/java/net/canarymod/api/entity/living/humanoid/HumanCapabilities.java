package net.canarymod.api.entity.living.humanoid;

/**
 * HumanCapabilities wrapper interface
 *
 * @author Jason (darkdiplomat)
 */
public interface HumanCapabilities {

    /**
     * Checks if the {@link Human} is invulnerable
     *
     * @return {@code true} if invulnerable; {@code false} if not
     */
    public boolean isInvulnerable();

    /**
     * Sets if the {@link Human} is invulnerable
     *
     * @param invulnerable
     *         {@code true} if invulnerable; {@code false} if not
     */
    public void setInvulnerable(boolean invulnerable);

    /**
     * Checks if the {@link Human} is flying
     *
     * @return {@code true} if flying; {@code false} if not
     */
    public boolean isFlying();

    /**
     * Sets if the {@link Human} is flying
     *
     * @param flying
     *         {@code true} if flying; {@code false} if not
     */
    public void setFlying(boolean flying);

    /**
     * Checks if the {@link Human} may fly
     *
     * @return {@code true} if may fly; {@code false} if not
     */
    public boolean mayFly();

    /**
     * Sets if the {@link Human} may fly
     *
     * @param mayfly
     *         {@code true} if may fly; {@code false} if not
     */
    public void setMayFly(boolean mayfly);

    /**
     * Checks if the {@link Human} can instant build
     *
     * @return {@code true} if can instant build; {@code false} if not
     */
    public boolean instantBuild();

    /**
     * Sets if the {@link Human} can instant build
     *
     * @param instant
     *         {@code true} if can instant build; {@code false} if not
     */
    public void setInstantBuild(boolean instant);

    /**
     * Gets the flying speed of the {@link Human}
     *
     * @return flying speed
     */
    public float getFlySpeed();

    /**
     * Sets the walking speed of the {@link Human}
     *
     * @param speed
     *         the flying speed to set
     */
    public void setFlySpeed(float speed);

    /**
     * Gets the walking speed of the {@link Human}
     *
     * @return walking speed
     */
    public float getWalkSpeed();

    /**
     * Sets the walking speed of the {@link Human}
     *
     * @param speed
     *         the walking speed to set
     */
    public void setWalkSpeed(float speed);

}
