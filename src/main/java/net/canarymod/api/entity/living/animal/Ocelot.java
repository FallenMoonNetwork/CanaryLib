package net.canarymod.api.entity.living.animal;


import net.canarymod.api.entity.living.Ageable;


/**
 * Ocelot wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public interface Ocelot extends EntityAnimal, Tameable, Ageable {

    public enum SkinType {
        Tux, //

    }

    public SkinType getSkinType();

    public void setSkinType(SkinType type);
}
