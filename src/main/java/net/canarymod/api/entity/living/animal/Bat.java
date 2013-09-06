package net.canarymod.api.entity.living.animal;

/**
 * Bat wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface Bat extends EntityAnimal {

    /**
     * Tells if this Bat is hanging or not
     *
     * @return {@code true} if hanging; {@code false} if not
     */
    public boolean isHanging();

    /**
     * Sets wheter or not this Bat is hanging
     *
     * @param hanging
     *         {@code true} for hanging; {@code false} for not
     */
    public void setHanging(boolean hanging);

}
