package net.canarymod.api.entity.living.animal;


import net.canarymod.api.entity.living.Ageable;


public interface Wolf extends Tameable, Ageable {

    public void setCollarColor();

    public int getCollarColor();
}
