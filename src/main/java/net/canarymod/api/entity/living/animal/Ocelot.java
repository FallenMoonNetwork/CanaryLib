package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.living.Ageable;

/**
 * Ocelot wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface Ocelot extends EntityAnimal, Tameable, Ageable {

    /**
     * SkinType enum
     *
     * @author Jason (darkdiplomat)
     */
    public enum SkinType {
        UNTAME, //
        TUXEDO, //
        GINGER, //
        SIAMESE; //
    }

    /**
     * Gets the SkinType of the Ocelot
     *
     * @return skintype
     */
    public SkinType getSkinType();

    /**
     * Sets the SkinType of the Ocelot
     *
     * @param type
     *         the SkinType to set
     */
    public void setSkinType(SkinType type);
}
